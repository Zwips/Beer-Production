package regressionTest;

import communication.CommunicationFacade;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.SetPreparedStatement;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.SimpleCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import regressionTest.errorfunctions.PilsnerErrorFunction;
import regressionTest.optimizers.PilsnerOptimizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestExp {

    private WeightedObservedPoints dataPol;
    private WeightedObservedPoints dataExp;
    private double variableA;
    private double variableB;
    private double variableC;
    private double randomOffset;

    public TestExp(){
        this.variableA = 4;
        this.variableB = 0.02;
        this.variableC = 3;
        this.randomOffset = 0;

        dataExp = new WeightedObservedPoints();
        dataPol = new WeightedObservedPoints();

        System.out.println("a = " + variableA + "\t b = " + variableB + "\t c = " + variableC + "\t random offset = " + randomOffset +"\n");
    }

    void initializeExponentialData(){
        System.out.println("Exponential");
        for (int i = 100; i < 3600; i++) {
            double x = i/6.0;
            double value = variableA*Math.exp(variableB*x)+variableC+(Math.random())*randomOffset;
            this.dataExp.add(x ,value);
            System.out.println("value: " + value + "\t x: " + x);
        }
        System.out.println("a = " + variableA + "\t b = " + variableB + "\t c = " + variableC + "\t random offset = " + randomOffset);

    }

    void initializePolynomialData(){
        System.out.println("\nPolynomial");
        for (int i = 0; i < 2000; i++) {
            double x = i/200.0;
            double value = variableA*x+variableB*x*x+variableC+(Math.random()-0.5)*randomOffset;
            this.dataPol.add(x ,value);
            System.out.println("value: " + value + "\t x: " + x);
        }
        System.out.println("a = " + variableA + "\t b = " + variableB + "\t c = " + variableC + "\t random offset = " + randomOffset);

    }

    void fitPolynomail(){
        System.out.println("f(x) = c+a*x+b*x^2");

        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2);
        double[] results = fitter.fit(this.dataPol.toList());

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }

    ExpFittingResult fitExponential(double[] guess){
        System.out.println("f(x) = c+a*exp(b*x)");
        ExponentialFunction function = new ExponentialFunction();

        System.out.println("guess: c = "+guess[0]+" , a = "+guess[1]+" , b = "+guess[2]);
        SimpleCurveFitter fitter = SimpleCurveFitter.create(function, guess);
        double[] results = fitter.fit(this.dataExp.toList());

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }






        double sumY = 0;
        for (WeightedObservedPoint point : this.dataExp.toList()) {
            sumY += point.getY();
        }
        double averageY = sumY/this.dataExp.toList().size();

        double sumResiduals = 0;
        double sumTotal = 0;

        for (WeightedObservedPoint point : this.dataExp.toList()) {
            sumTotal += Math.pow((point.getY()-averageY),2);
            sumResiduals += Math.pow((point.getY()-function.value(point.getX(), results)),2);
            if (sumTotal<0 || sumResiduals<0){
                System.out.println("below 0");
            }
        }

        double squareR = 1.0-sumResiduals/sumTotal;

        System.out.println("R-square: "+squareR);

        return new ExpFittingResult(results, squareR);
    }

    void fitExponentialMuliple(double [] guess){
        ExpFittingResult result1 = this.fitExponential(guess);
        //guess = result1.getParameters();
        ExpFittingResult result2 = this.fitExponential(guess);
        ExpFittingResult result3 = this.fitExponential(guess);

    }


    public static void main(String[] args) {
        TestExp test = new TestExp();

        //test.initializePolynomialData();
        //test.fitPolynomail();

        test.initializeExponentialData();
        double[] guess = {0.0, 0.27, 0.01};
        test.fitExponential(guess);


        PilsnerOptimizer pilsnerOptimizer = new PilsnerOptimizer();
        WeightedObservedPoints weightedData = new WeightedObservedPoints();
        double[] guess1 = {0, 2.05, 0.04};

        List<Defectives> data = test.getDefectiveData("Ale");

        double sumY = 0;
        for (Defectives defectives : data) {
            weightedData.add(defectives.speed, defectives.defective);
            System.out.println("defective: "+defectives.defective+"\t speed: "+defectives.speed);
            sumY += defectives.defective;
        }



        double averageY = sumY/data.size();

        double sumResiduals = 0;
        double sumTotal = 0;
        PilsnerErrorFunction function = new PilsnerErrorFunction();

        for (WeightedObservedPoint point : weightedData.toList()) {
            sumTotal += Math.pow((point.getY()-averageY),2);
            sumResiduals += Math.pow((point.getY()-function.value(point.getX(), guess1)),2);

        }

        double squareR = 1.0-sumResiduals/sumTotal;

        System.out.println("R-square: "+squareR);













        //test.dataExp = weightedData;
        //test.fitExponential(guess1);

        //weightedData = test.mockPilsnerData();
        double bestSpeed = pilsnerOptimizer.profitOptimalSpeed(guess1, weightedData, 2.85, 4.75, 600);

        System.out.println("best speed: " + bestSpeed);




    }

    WeightedObservedPoints mockPilsnerData(){
        System.out.println("Exponential");
        double variableA = 0.27;
        double variableB = 0.01;
        double variableC = 0.0;
        double randomOffset = 1;

        WeightedObservedPoints points = new WeightedObservedPoints();
        for (int i = 0; i < 600; i++) {
            double x = i/1.0;
            double value = variableA*Math.exp(variableB*x)+variableC+(Math.random())*randomOffset;
            points.add(x ,value);
            System.out.println("value: " + value + "\t x: " + x);
        }
        System.out.println("a = " + variableA + "\t b = " + variableB + "\t c = " + variableC + "\t random offset = " + randomOffset);

        return points;
    }

    private List<Defectives> getDefectiveData(String type){
        ResultSet results = null;

        Connection connection = new DatabaseConnector().openConnection();

        try{
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM defectives WHERE product = ?");

            pStatement.setString(1,type);

            results = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        }

        List<Defectives> defectiveList = new ArrayList<>();
        try {
            results.isBeforeFirst();

            while(results.next()){
                int defectives = results.getInt("numberofdefective");
                double batchSize = results.getDouble("productsinbatch");
                double speed = results.getDouble("machinespeed");
                defectiveList.add(new Defectives(defectives, (int) batchSize, speed));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return defectiveList;
    }

    //<editor-fold desc="custom fitter template">
    /*LeastSquaresProblem problem = new LeastSquaresProblem() {
            @Override
            public RealVector getStart() {

                RealVector realVector = new ArrayRealVector();
            }

            @Override
            public int getObservationSize() {
                return 0;
            }

            @Override
            public int getParameterSize() {
                return 3;
            }

            @Override
            public Evaluation evaluate(RealVector point) {

                Evaluation evaluation = new AbstractEvaluation() {
                    @Override
                    public RealMatrix getJacobian() {
                        return null;
                    }

                    @Override
                    public RealVector getResiduals() {
                        return null;
                    }

                    @Override
                    public RealVector getPoint() {
                        return null;
                    }
                };

            }

            @Override
            public Incrementor getEvaluationCounter() {
                return null;
            }

            @Override
            public Incrementor getIterationCounter() {
                return null;
            }

            @Override
            public ConvergenceChecker<Evaluation> getConvergenceChecker() {

                ConvergenceChecker<Evaluation> evaluationConvergenceChecker = new ConvergenceChecker<Evaluation>() {
                    @Override
                    public boolean converged(int iteration, Evaluation previous, Evaluation current) {
                        return false;
                    }
                };

            }
        };*/
    //</editor-fold>

    private class Defectives{
        private double defective;
        private double batchSize;
        private double speed;

        public Defectives(int defective, int batchSize, double speed) {
            this.defective = defective;
            this.batchSize = batchSize;
            this.speed = speed;
        }

        public double getDefective() {
            return defective;
        }

        public double getBatchSize() {
            return batchSize;
        }

        public double getSpeed() {
            return speed;
        }
    }


    private class ExpFittingResult{

        private ExpFittingResult(double[] result, double squareR){

        }

    }

}
