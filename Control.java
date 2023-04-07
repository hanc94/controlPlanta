//Clase abstracta a partir de la cual se derivan las clases
//PIControl y PDControl
public abstract class Control {
	
	//Coeficientes de la parte no recursiva del controlador
	protected double b0,b1;
	//Miembro para almacenar e[n-1]
	protected double en1;
	

	//Constructor
	public Control(double b0i,double b1i){
		b0=b0i;
		b1=b1i;	
		//Condiciones iniciales en cero
		en1=0.0;
		
			
	}
	
	//Método abstracto que debe ser implementado en las clases hijas.
	public abstract double compute(double en);

}
