package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import common.RmiSample;

public class timeClient {   
  
    /**  
     * @param args  
     */  
	private static double delayTime = 0;
	private static double cerrentTimeFromServer = 0;
    public static void main(String[] args) {   
    	ClockThreadGetTime getClockThread = new ClockThreadGetTime();  
    	getClockThread.start();  
    	ClockThreadGetDelay getDelay = new ClockThreadGetDelay();
        getDelay.start();
        System.out.println("begin update time within server for every second!");    
        
    }   
    
    /*
     * the class thread get time from server
     */
    private static class  ClockThreadGetTime extends Thread{
    	@Override  
    	public void run() {  
    		super.run();  
    		while (true) {  
    			try {   
    	            String url = "//114.212.86.211:8808/lyzDemo-SERVER";   
    	            RmiSample RmiObject = (RmiSample) Naming.lookup(url);  
    	            cerrentTimeFromServer = RmiObject.getTime();
    	            System.out.println("I am client, time from server:"+(cerrentTimeFromServer+delayTime));   
    	        } catch (RemoteException rex) {   
    	            System.out.println("Error in lookup: " + rex.toString());   
    	        } catch (java.net.MalformedURLException me) {   
    	            System.out.println("Malformed URL: " + me.toString());   
    	        } catch (java.rmi.NotBoundException ne) {   
    	            System.out.println("NotBound: " + ne.toString());   
    	        }
    			
    			try {  
    				Thread.sleep(1000);  
    			} catch (InterruptedException e) {  
    				e.printStackTrace();  
    			} 
    		}  
    	}  
    	
    }
    
    /*
     * this class get delayTime 
     */
    private static class  ClockThreadGetDelay extends Thread{
    	@Override  
    	public void run() {  
    		super.run();  
    		while (true) {  
    			try {   
    	            String url = "//114.212.86.211:8808/lyzDemo-SERVER";   
    	            RmiSample RmiObject = (RmiSample) Naming.lookup(url);   
    	            double Time1 = System.currentTimeMillis();
    	            double returnTime = RmiObject.comDelay(Time1);
    	            double Time4 = System.currentTimeMillis();
    	            delayTime = (Time4-Time1)/2;
    	            System.out.println("Update delay time, delay time:"+delayTime);
    	            
    	        } catch (RemoteException rex) {   
    	            System.out.println("Error in lookup: " + rex.toString());   
    	        } catch (java.net.MalformedURLException me) {   
    	            System.out.println("Malformed URL: " + me.toString());   
    	        } catch (java.rmi.NotBoundException ne) {   
    	            System.out.println("NotBound: " + ne.toString());   
    	        }
    			
    			try {  
    				Thread.sleep(5000);  //every 5 second to update the delay time.
    			} catch (InterruptedException e) {  
    				e.printStackTrace();  
    			} 
    		}  
    	}  
    	
    }
} 
