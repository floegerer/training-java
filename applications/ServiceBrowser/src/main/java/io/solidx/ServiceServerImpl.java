package io.solidx;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
    
    HashMap serviceList;

    public ServiceServerImpl() throws RemoteException  {
        setUpServices();
    }

    private void setUpServices() {
        serviceList = new HashMap();
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Visual music service", new MiniMusicService());    
        serviceList.put("Day of the week service", new DayOfTheWeekService());  
    }

    public Object [] getServiceList() {

        System.out.println("in remote");
        return serviceList.keySet().toArray();

    }

    public Service getService(Object serviceKey) throws RemoteException {

        Service theService = (Service) serviceList.get(serviceKey);
        return theService;

    }

    public static void main(String[] args) {
        
        try {
            Naming.rebind("//localhost:45000/ServiceServer", new ServiceServerImpl());
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println("Remote service is running...");  
        
    }
}
