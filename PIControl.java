//Clase que implementa un control PI
public class PIControl extends Control {
	
	//Miembro donde se almacena u[n-1], el control PI es recursivo
	private double un1;
	
	//Constructor
	public PIControl(double b0i,double b1i){
		//Se invoca el constructor de la clase madre o super clase
		super(b0i,b1i);		
		//Condiciones iniciales en cero
		un1=0.0;
	}
	
	//Implementa la ecuación en diferencias del control PI
	//u[n]=b0*e[n]+b1*e[n-1]+u[n-1]
	public double compute(double en){
		
		double un;
		
		un=en*b0+b1*en1+un1;
		
		en1=en;
		un1=un;
		
		return un;
	}

}
