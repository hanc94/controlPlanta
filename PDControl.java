
//Clase que implementa un control PD
public class PDControl extends Control {
	
	//Constructor, recibe como argumentos los coeficientes de la parte
	//no recursiva del controlador
	public PDControl(double b0i,double b1i){
		super(b0i,b1i);	
		
	}
	
	//Implementa la ecuación en diferencias del control PD
	//u[n]=b0*e[n]+b1*e[n-1]
	public double compute(double en){
		
		double un;
		
		un=en*b0+b1*en1;
		
		en1=en;	
		
		return un;
	}

}
