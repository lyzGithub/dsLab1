package server;

public class ClockThreadPrint extends Thread {  
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