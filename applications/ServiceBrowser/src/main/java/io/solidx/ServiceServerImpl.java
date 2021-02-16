package io.solidx;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {

    public static void main(String[] args) throws RemoteException {
        ServiceServerImpl MyService = new ServiceServerImpl();
    }

    HashMap serviceList;

    public ServiceServerImpl() throws RemoteException  {
        setUpServices();
    }

    private void setUpServices() {
        serviceList = new HashMap();
        // serviceList.put("Dice Rolling Service", new DiceService());
        // serviceList.put("Day of the week service", new DayOfTheWeekService());
        // serviceList.put("Visual music service", new MiniMusicService());
    }

    @Override
    public Object [] getServiceList() throws RemoteException {

        // TODO Auto-generated method stub
        return null;

    }

    @Override
    public Service getService(Object serviceKey) throws RemoteException {

        // TODO Auto-generated method stub
        return null;

    }
}
