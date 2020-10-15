package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaceA extends Remote{
    public String fetchId(String x) throws RemoteException;
}
