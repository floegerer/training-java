package io.solidx;

import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote  {

    public String sayHello() { 

        return "Server says, Hey";

    }

    public MyRemoteImpl() throws RemoteException {} 

    public static void main(String[] args) {
        try {

            MyRemote service = new MyRemoteImpl();
            Naming.rebind("//localhost:45000/RemoteHello", service);

        } catch (Exception e) { e.printStackTrace(); }

    }
}