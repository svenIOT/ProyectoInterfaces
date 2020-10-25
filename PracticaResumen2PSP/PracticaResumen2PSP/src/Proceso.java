
/*
 * Clase para crear un proceso
 * Contiene los atributos que deberá tener un proceso así como
 * los getters y setters.
 * @author Andres
 */
public class Proceso {

	protected char PID;
	protected int tiempoLlegada;
	protected int tiempoRafaga;
	protected int tiempoFinal;
	protected int tiempoRafagaInicial;
	
	/*
	 * Constructor
	 * @param miPID
	 * @param miTiempoLlegada
	 * aram miTiempoRafaga
	 */
	public Proceso(char miPID, int miTiempoLlegada, int miTiempoRafaga) {
		this.PID = miPID;
		this.tiempoLlegada = miTiempoLlegada;
		this.tiempoRafaga = miTiempoRafaga;
		tiempoRafagaInicial = this.tiempoRafaga;
		tiempoFinal = 0;
	}
	
	/*
	 * Getter
	 * @return PID
	 */
	public char getPID() {
		return PID;
	}

	/*
	 * Setter
	 */
	public void setPID(char pID) {
		PID = pID;
	}

	/*
	 * Getter
	 * @return tiempoLlegada
	 */
	public int getTiempoLlegada() {
		return tiempoLlegada;
	}

	/*
	 * Setter
	 */
	public void setTiempoLlegada(int tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	/*
	 * Getter
	 * @return tiempoRafaga
	 */
	public int getTiempoRafaga() {
		return tiempoRafaga;
	}

	/*
	 * Setter
	 */
	public void setTiempoRafaga(int tiempoRafaga) {
		this.tiempoRafaga = tiempoRafaga;
	}
	
	/*
	 * Setter
	 */
	public void setTiempoRafagaInicial(int tiempoRafagaInicial) {
		this.tiempoRafagaInicial = tiempoRafagaInicial;
	}
	
	/*
	 * Getter
	 * @return tiempoRafagaInicial
	 */
	public int getTiempoRafagaInicial() {
		return tiempoRafagaInicial;
	}
	
	/*
	 * Setter
	 */
	public void setTiempoFinal(int tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}
	
	
	/*
	 * Método para calcular el índice de penalización de cada proceso.
	 */
	public Float calcIndicePenalizacion() {
		float resta = (tiempoFinal - tiempoLlegada);
		float promedio = resta/tiempoRafagaInicial;
		return promedio;
		
	}
	
	/*
	 * Método que devuelve el String para mostrar los índices de penalización de los procesos
	 */
	public String toStringIndicePenal() {
		return "Índice de penalización del proceso " + PID + ": " + calcIndicePenalizacion();
	}
	
	/*
	 * Método para devolver si un proceso ha terminado o si sigue en curso.
	 */
	public String toString() {
		if (tiempoRafaga!=0) { return "[Proceso " + PID + " - Ráfaga restante: " + tiempoRafaga + "]" + "\n"; } 
		else { return "[Proceso " + PID + " Duración restante: " + tiempoRafaga + "] -- TERMINADO" + "\n"; }
	}
	
	
}
