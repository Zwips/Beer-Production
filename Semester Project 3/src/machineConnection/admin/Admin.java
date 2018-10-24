/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineConnection.admin;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;

/**
 *
 * @author HCHB
 */
public class Admin {

    private String Identifier="Admin.";

    public float readCurrentProductID(UaClient client, String prefix) throws ServiceException, StatusException {
        CurrentProductType prod = new CurrentProductType();
        return prod.readCurrentProductID(client, prefix);
    }

    public int readNumberOfDefectiveProducts(UaClient client, String prefix) throws ServiceException, StatusException {
        DefectiveProducts prod = new DefectiveProducts();
        return prod.readNumberOfDefectiveProducts(client, prefix);
    }

    public int  readNumberOfProducedProducts(UaClient client, String prefix) throws ServiceException, StatusException {
        ProducedProducts prod = new ProducedProducts();
        return prod.readNumberOfProducedProducts(client, prefix);
    }

    public int readStopReasonID(UaClient client, String prefix) throws ServiceException, StatusException {
        StopReasonID prod = new StopReasonID();
        return prod.readStopReasonID(client, prefix);
    }

    public int readStopReasonValue(UaClient client, String prefix) throws ServiceException, StatusException {
        StopReasonValue prod = new StopReasonValue();
        return prod.readStopReasonValue(client, prefix);
    }

}