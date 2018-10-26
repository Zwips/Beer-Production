package logic.mes;

public class OptimalMachineSpecifications {


    float getOptimalSpeed(int productType){
        switch (productType) {
            case 0:
                return 599;

            case 1:
                return 300;

            case 2:
                return 150;

            case 3:
                return 200;

            case 4:
                return 100;

            case 5:
                return 125;

            default:
                return 100;
        }
    }

    float maxSpeed(int productType) {
        switch (productType) {
            case 0:
                return 600;

            case 1:
                return 300;

            case 2:
                return 150;

            case 3:
                return 200;

            case 4:
                return 100;

            case 5:
                return 125;

            default:
                return 100;
        }
    }
}
