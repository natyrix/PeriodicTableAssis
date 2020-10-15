package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class myclass extends UnicastRemoteObject implements interfaceA {
    public myclass() throws RemoteException{
    }
    @Override
    public String fetchId(String xx) throws RemoteException {
        System.out.println(xx);
        String y = null;
        int a=0,b=0,c=0;
        char[] x = xx.toCharArray();
        for(int i=0;i<x.length ;i++ ){
            if(x[i]=='=' || x[i]==',' ){
                if(x[i]=='='){
                    a=i;
                    c++;
                }
                else if(x[i]==','){
                    b=i;
                    c++;
                }
            }
            else if(c==2) {
                break;
            }
        }
        if(a!=0 && b!=0) {
            y = xx.substring(a + 1, b);

            System.out.println(y);

            return y;
        }
        return null;
    }
}
