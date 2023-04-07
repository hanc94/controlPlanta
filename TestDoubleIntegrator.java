import java.util.*;

class DIControlTask extends TimerTask {
	int count = 1;
	double control,reference,error;
	DoubleIntegrator di;
	PDControl pd;

	public DIControlTask(){
		di=new DoubleIntegrator();
		pd=new PDControl(0.3483 , -0.3099);
		reference=2.23;		
	}

	public void run() {
		error=reference-di.getOutput();
		control=pd.compute(error);
		di.setControl(control);
		System.out.println("Control Task, double integrator output is: "+di.getOutput()+" and control signal is: "+control);
	}
}
class TestDoubleIntegrator {
	public static void main(String[] args) {
		Timer timer = new Timer();
		DIControlTask Task = new DIControlTask();
		timer.scheduleAtFixedRate(Task,0,1000);
	}
}