/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.machineConnection.admin;

/** Represents a admin in the system.
 * @author HCHB
 *
 */

import communication.machineConnection.IAdmin;


public class Admin implements IAdmin {

    private String identifier="Admin.";

    @Override
    public CurrentProductType getCurrentProductType(String prefix) {

        return new CurrentProductType(prefix+identifier);
    }

    @Override
    public DefectiveProducts getDefectiveProducts(String prefix) {

        return new DefectiveProducts(prefix+identifier);
    }

    @Override
    public ProducedProducts getProducedProducts(String prefix) {
        return new ProducedProducts(prefix+identifier);
    }

    @Override
    public StopReasonID getStopReasonId(String prefix) {
        return new StopReasonID(prefix+identifier);
    }

    @Override
    public StopReasonValue getStopReasonValue(String prefix) {
        return new StopReasonValue(prefix+identifier);
    }
}