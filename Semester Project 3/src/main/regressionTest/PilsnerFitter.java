/*
package regressionTest;

import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.IntegerSequence;

import java.util.Collection;

public class PilsnerFitter extends AbstractCurveFitter {


    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> points) {

        LeastSquaresProblem problem = new LeastSquaresProblem() {
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
            public IntegerSequence.Incrementor getEvaluationCounter() {
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
        };

    }
}
*/
