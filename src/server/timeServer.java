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
	final private static String serverUrl = "//localhost:8808/lyzDemo-SERVER";
    public static void main(String[] args) {   
        try{   
            LocateRegistry.createRegistry(8808);   
            RmiSampleImpl server= new RmiSampleImpl();   
            Naming.rebind( serverUrl, server);   
            ClockThreadPrint clockThread = new ClockThreadPrint();  
            clockThread.start();  
            System.out.println("start server to serve client!"); 
        }catch (MalformedURLException me){   
            System.out.println("Malformed URL: " + me.toString());   
        }catch(RemoteException re){   
            System.out.println("Remote Exception: "+re.toString());   
        }   
    }   
    public static class ClockThreadPrint extends Thread {  
        @Override  
        public void run() {  
            super.run();  
            while (true) {  
                System.out.println("I am server,my currentTime:"+System.currentTimeMillis());  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                } 
            }  
        }  
    }
  
} 
