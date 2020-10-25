import java.util.ArrayList;
import java.util.Collections;

/*
 * Clase para gestionar los Algoritmos.
 * @author Andres
 */
public abstract class AbstractAlgoritmos {
	
	protected ArrayList<Proceso> listaProcesos;
	protected ArrayList<String> listaPromedio;
	protected ArrayList<Float> listaPromedioAlgoritmo;
	
	/*
	 * Método que devuelve un proceso creado cuando sea necesario
	 * comparar para ver qué proceso tiene menos tiempo de ráfaga.
	 * @param i
	 */
	public Proceso crearProceso(int i) {
		char PID = (char) listaProcesos.get(i).getPID();
		Integer tiempoLlegada = listaProcesos.get(i).getTiempoLlegada();
		Integer tiempoRafaga = listaProcesos.get(i).getTiempoRafaga();
		Proceso miProceso = listaProcesos.get(i);
		return miProceso;
	}
	
	
	/*
	 * Método para listar los promedios de los procesos.
	 * Imprime por pantalla los índices de penalización de los procesos.
	 */
	public void listarPromedios() {
		Float promedio = null;
		System.out.println("------------------------------");
		System.out.println("Promedio de los procesos: ");
		//Ordenar procesos
		Collections.sort(listaPromedio);
		for(int i=0;i<listaPromedio.size();i++) {
			System.out.println(listaPromedio.get(i).toString());
		}
	}

}
