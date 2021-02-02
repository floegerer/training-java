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
            Naming.rebind("//127.0.0.1:45000/test", service);

        } catch (Exception e) { e.printStackTrace(); }

    }
}