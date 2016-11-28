package server;
/*
 * create by liyize 2016.11.27
 * 
 */

import java.net.MalformedURLException;   
import java.rmi.Naming;   
import java.rmi.RemoteException;   
import java.rmi.registry.LocateRegistry;


import common.RmiSampleImpl;   
  

 
public class timeServer {   
  
    /**  
     * @param args  
     * here is for begin server.
     */  
    public static void main(String[] args) {   
        try{   
            LocateRegistry.createRegistry(8808);   
            RmiSampleImpl server= new RmiSampleImpl();   
            Naming.rebind("//localhost:8808/lyzDemo-SERVER" , server);   
            ClockThreadPrint clockThread = new ClockThreadPrint();  
            clockThread.start();  
            System.out.println("start server to serve client!"); 
        }catch (MalformedURLException me){   
            System.out.println("Malformed URL: " + me.toString());   
        }catch(RemoteException re){   
            System.out.println("Remote Exception: "+re.toString());   
        }   
    }   
     
  
} 
