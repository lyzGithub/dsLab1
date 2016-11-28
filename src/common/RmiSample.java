package common;   
  
import java.rmi.Remote;   
import java.rmi.RemoteException;   
  
public interface RmiSample extends Remote {   
    public int sum(int a, int b) throws RemoteException;   
    public double getTime() throws RemoteException;
    public double comDelay(double clientTime) throws RemoteException;
}  