package io.solidx;

import java.rmi.*;

public interface ServiceServer extends Remote {

    Object[] getServiceList() throws RemoteException;
    Service getService(Object serviceKey) throws RemoteException;

    public static void main(String[] args) {
        
    }

}


