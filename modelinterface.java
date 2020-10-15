package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface modelinterface extends Remote{
    public String getName(String idd) throws RemoteException;
    public String [] getInfo(String idd) throws RemoteException;
    public String [] getinfoGroup(String x) throws RemoteException;
}
