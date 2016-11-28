package common;   
/*
 * create by liyize 2016.11.28
 */
import java.rmi.Remote;   
import java.rmi.RemoteException;   
  
/*
 * define the process interface
 */
public interface RmiSample extends Remote {   
   
    public double getTime(String secCode) throws RemoteException;
}  