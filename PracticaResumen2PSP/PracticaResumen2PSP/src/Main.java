import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		FIFO miFIFO = new FIFO(cargarProcesos());
		//miFIFO.runFIFO();
		
		Integer quantum = 2;
		RoundRobin miRR = new RoundRobin(cargarProcesos(), quantum);
		miRR.runRoundRobin();
		
		SRT miSRT = new SRT(cargarProcesos());
		miSRT.runSRT();
		
		SJF miSJF = new SJF(cargarProcesos());
		//miSJF.runSJF();
	}
	
	private static ArrayList<Proceso> cargarProcesos() {
		ArrayList<Proceso> procesos = new ArrayList<Proceso>();
		procesos.add(new Proceso('A',0,5));
		procesos.add(new Proceso('B',2,4));
		procesos.add(new Proceso('C',3,3));
		procesos.add(new Proceso('D',5,2));
		procesos.add(new Proceso('E',6,3));
		
		return procesos;
		
	}

}
