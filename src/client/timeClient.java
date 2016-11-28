package client;
/*
 * create by liyize 2016.11.28
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.RmiSample;

public class timeClient {   
  
    /**  
     * @param args  
     */  
	private static String secCode = null;
	private static double delayTime = 0;
	private static double cerrentTimeFromServer = 0;
	final private static String ipAddress = "114.212.86.211";
	private static String url = null;
    public static void main(String[] args) {   
    	
    	secCode = args[0];
    	//secCode = "liyize";
    	url = "//"+ipAddress+":8808/lyzDemo-SERVER"; 
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
    	             
    	            RmiSample RmiObject = (RmiSample) Naming.lookup(url);  
    	            cerrentTimeFromServer = RmiObject.getTime(secCode);
    	            if(cerrentTimeFromServer == -1){
    	            	System.out.println("secCode is wrong!");
    	            	System.exit(-1);
    	            }
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
    	              
    	            RmiSample RmiObject = (RmiSample) Naming.lookup(url);   
    	            double Time1 = System.currentTimeMillis();
    	            double returnTime = RmiObject.getTime(secCode);
    	            if(returnTime == -1){
    	            	System.out.println("secCode is wrong!");
    	            	System.exit(-1);
    	            }
    	            double Time4 = System.currentTimeMillis();
    	            double a = (Time1-returnTime);
    	            double b = (returnTime - Time4);
    	            delayTime = (a>b)?a:b;
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
