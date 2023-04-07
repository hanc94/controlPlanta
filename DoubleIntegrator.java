import java.util.concurrent.Semaphore;
import java.util.TimerTask;
import java.util.Timer;


public class DoubleIntegrator extends TimerTask {
	
	
   
    
    private double x1;
    private double u;
	
	private Semaphore mutex_u,mutex_y;
	
	private Timer timer;
	
	
	private double x1n1,x2n1,h;
	
	
	
	public DoubleIntegrator(){
		
		x1n1=0.0;
		x2n1=0.0;
		
		Timer timer = new Timer();
		
		
		mutex_u= new Semaphore(1);
		
		mutex_y=new Semaphore(1);
		
		
		h=1e-3;
		
		
		timer.scheduleAtFixedRate(this,0,(long)(h*1000));
		
		
		
	}
	
	public double getOutput(){
		double yo;
		
		yo=0.0;
		
		try {
			mutex_y.acquire();
			yo=x1;	
			
			mutex_y.release();	
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
		return yo;
		
		
		
	}
	
	private void setOutput(double yi){
		
		try {
			mutex_y.acquire();
			x1=yi;	
			
			mutex_y.release();	
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
			
		
	}
	
	public void setControl(double ui){
			
		try {
			mutex_u.acquire();
			u=ui;				
			mutex_u.release();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
			
		
		
	}
	
	private double getControl(){
		
		double uo;
		
		uo=0.0;
		
		try {
			mutex_u.acquire();
			uo=u;				
			mutex_u.release();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return uo;
			
		
		
	}
	
	
	
	public void run(){
		
		
		double un,x1n,x2n;
		
		
		un=getControl();		
		
		
		//Euler method
		x1n=x1n1+h*x2n1;
		x2n=x2n1+h*un;
		
		
		x1n1=x1n;
		x2n1=x2n;
		
		setOutput(x1n);
		
		
				
	}

}
