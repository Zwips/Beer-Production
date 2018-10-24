/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquantiance;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.StatusException;
import com.prosysopc.ua.client.UaClient;

/**
 *
 * @author HCHB
 */
public interface IMachineConnection {

    float readCurrentProductID(UaClient client) throws ServiceException, StatusException;
    int readNumberOfDefectiveProducts(UaClient client) throws ServiceException, StatusException;
    int  readNumberOfProducedProducts(UaClient client) throws ServiceException, StatusException;
    int readStopReasonID(UaClient client, String prefix) throws ServiceException, StatusException;
    int readStopReasonValue(UaClient client, String prefix) throws ServiceException, StatusException;

}
