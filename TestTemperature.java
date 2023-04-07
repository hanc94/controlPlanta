//Importa clases que se usarán de Java
import java.util.*;

//Clase que implementa el método run el algoritmo de control
class ControlTask extends TimerTask {
	
	//Variables que intervienen en el control
	double control,reference,error;
	
	//Objeto tipo TempPlant (Planta de Temperatura)
	TempPlant tp;
	
	//Objeto tipo PIControl (Controlador PI)
	PIControl pi;

	//Constructor
	public ControlTask(){
		
		//Crea la planta de temperatura
		tp=new TempPlant();
		
		//Crea el controlador PI
		pi=new PIControl(1.868,-1.81);
		
		//Referencia a la que se desea llevar la planta
		reference=2.23;		
	}

	//Método que implementa el controlador
	public void run() {
		
		//Se calcula la señal de error
		error=reference-tp.getOutput();
		
		//Se calcula el controlador PI
		control=pi.compute(error);
		
		//Se envía señal de control a la planta
		tp.setControl(control);
		
		//Se imprime la salida de la planta
		System.out.println("Control Task temperature is:"+tp.getOutput());
	}
}

//Clase que implementa el método main, punto de entrada al programa
class TestTemperature {
	//Método main
	public static void main(String[] args) {
		
		//Objeto tipo Timer para ejecutar la tarea de control periódicamente
		Timer timer = new Timer();
		
		//Se cera el objeto para la tarea de control
		ControlTask Task = new ControlTask();
		
		//Se inicia el temporizador de manera periódica
		timer.scheduleAtFixedRate(Task,0,1000);
		
		
	}
}