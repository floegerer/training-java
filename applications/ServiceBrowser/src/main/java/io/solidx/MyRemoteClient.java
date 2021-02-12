package io.solidx;
import java.rmi.*;

public class MyRemoteClient {

    public static void main(String[] args) {
        
        new MyRemoteClient().go();

    }

    public void go() { 

        System.out.println("------");

        try {

            MyRemote service = (MyRemote) Naming.lookup("//localhost:45000/RemoteHello");

            String s = service.sayHello();

            System.out.println(s);

        } catch (Exception e) { e.printStackTrace(); }
    }
    
}
