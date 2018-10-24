/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineConnection.status;

import machineConnection.IStatus;

/**
 *
 * @author HCHB
 */
public class Status implements IStatus {
    
        private String identifier ="Status.";


        @Override
        public BatchAmountCurrent getBatchAmountCurrent(String prefix) {
                return new BatchAmountCurrent(prefix + identifier);
        }

        @Override
        public BatchIDCurrent getBatchIdCurrent(String prefix) {
                return new BatchIDCurrent(prefix + identifier);
        }

        @Override
        public CurrentState getCurrentState(String prefix) {
                return new CurrentState(prefix + identifier);
        }

        @Override
        public Humidity getHumidity(String prefix) {
                return new Humidity(prefix + identifier);
        }

        @Override
        public MachineSpeedCurrent getMachineSpeedCurrent(String prefix) {
                return new MachineSpeedCurrent(prefix + identifier);
        }

        @Override
        public MachineSpeedNormalized getMachineSpeedNormalized(String prefix) {
                return new MachineSpeedNormalized(prefix + identifier);
        }

        @Override
        public Temperature getTemperature(String prefix) {
                return new Temperature(prefix + identifier);
        }

        @Override
        public Vibration getVibration(String prefix) {
                return new Vibration(prefix + identifier);
        }
}
