import java.util.concurrent.Semaphore;
import java.util.TimerTask;
import java.util.Timer;


public class Plant extends TimerTask {
	
	
    private double h;
    private double K;
    private double tau;
    private double Td;
    
    private double y;
    private double u;
	
	private Semaphore mutex_u,mutex_y;
	
	private Timer timer;
	
	private int Nd;
	
	private int xins;
	
	private double yn1;
	
	private double buffer[];
	
	public Plant(double hi,double Ki,double taui,double Tdi){
		
		h=hi;
		K=Ki;
		tau=taui;
		Td=Tdi;
		
		Nd=(int)(Td/h);
		
		buffer= new double[Nd];
		
		xins=0;
		yn1=0.0;
		
		Timer timer = new Timer();
		
		
		mutex_u= new Semaphore(1);
		
		mutex_y=new Semaphore(1);
		
		
		
		timer.scheduleAtFixedRate(this,0,(long)(h*1000));
		
		
		
	}
	
	public double getOutput(){
		double yo;
		
		yo=0.0;
		
		try {
			mutex_y.acquire();
			yo=y;	
			
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
			y=yi;	
			
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
	
	private int mod(int x,int n){
		int r = x % n;
		if (r < 0)
		{
		    r += n;
		}
		return r;
	}
	
	
	public void run(){
		
		
		double un,yn;
		
		
		un=getControl();
		
		buffer[xins]=un;
		
		//Euler method
		yn=yn1+h*(K/tau*buffer[mod((xins+Nd-1),Nd)]-(1.0/tau)*yn1);
		yn1=yn;
		
		setOutput(yn);
		
		
		xins=mod((xins-1),Nd);
		
		
	
		
	}

}
