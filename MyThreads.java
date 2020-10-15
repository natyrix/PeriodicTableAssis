package sample;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyThreads extends Model {
    public static void main(String [] args) throws RemoteException {
        Registry registry= LocateRegistry.createRegistry(2312);
        myclass cl1 = new myclass();
        registry.rebind("test1",cl1);
        System.out.println("Server is ready");
    }
}
