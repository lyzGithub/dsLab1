package common;
/*
 * create by liyize 2016.11.28
 */
import java.rmi.RemoteException;   
import java.rmi.server.UnicastRemoteObject;   




public class RmiSampleImpl extends UnicastRemoteObject implements RmiSample {   
  /**  
   *   implementation for interface
   */  
  private static final long serialVersionUID = 2742977636753958461L;   
  final static  private String secCodeServer = "liyize";
  public RmiSampleImpl() throws RemoteException {   
      super();   
  }   

 
  public double getTime(String secCode) throws RemoteException{
	  if(secCode.equals(secCodeServer)){
		  return System.currentTimeMillis(); 
	  }
	  return -1;
  }
 

}   