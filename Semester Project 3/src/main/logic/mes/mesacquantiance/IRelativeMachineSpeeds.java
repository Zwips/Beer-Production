package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;
import java.util.List;

public interface IRelativeMachineSpeeds {
    void addMachine(IMesMachine machine);

    void removeMachine(String machineName);

    List<String> getMostEffectiveMachines(ProductTypeEnum type);

    Float getMostEffectiveProduct(String machineName, ProductTypeEnum type);

    void update(String machineName, ProductTypeEnum type, float speed);
}
