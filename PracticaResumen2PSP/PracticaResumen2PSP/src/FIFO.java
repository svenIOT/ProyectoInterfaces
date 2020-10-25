import java.util.ArrayList;

/*
 * Clase que resolverá los procesos mediante el algoritmo FIFO
 * @author Andres
 */
public class FIFO extends AbstractAlgoritmos{
	
	/*
	 * Constructor
	 * @param miListaProcesos
	 */
	public FIFO(ArrayList<Proceso> miListaProcesos) {
		listaProcesos = miListaProcesos;
		listaPromedio = new ArrayList<String>();
		listaPromedioAlgoritmo = new ArrayList<Float>();
	}
	
	/*
	 * Método para ejecutar el algoritmo FIFO.
	 */
	public void runFIFO() {
		Integer instante = 0;
		
		/*
		 * Creo un bucle que ejecuta los procesos y los resuelve imprimiendo
		 * por pantalla el instante en el que se ejecuta.
		 */
		while(!listaProcesos.isEmpty()) {
			for(int i=0; i<listaProcesos.size();i++) {
				Proceso miProceso = listaProcesos.get(i);
				
				if(miProceso.getTiempoLlegada()<=instante) {
					while(miProceso.getTiempoRafaga()!=0) {
						miProceso.setTiempoRafaga(miProceso.getTiempoRafaga() - 1);
						System.out.print("Ciclo: " + instante + " -- " + miProceso.toString());
						
						if(miProceso.getTiempoRafaga()==0) {
							miProceso.setTiempoFinal(instante+1);
						}
						instante++;
					}
					
				}
			}
			
			/*
			 * Los procesos que terminan se eliminan de la lista para que el
			 * bucle no sea infinito.
			 */
			if(!listaProcesos.isEmpty()) {
				String indicePenal;
				Float promedio;
				for(int i=0; i<listaProcesos.size(); i++) {
					if(listaProcesos.get(i).getTiempoRafaga()==0) {
						indicePenal = listaProcesos.get(i).toStringIndicePenal();
						promedio = listaProcesos.get(i).calcIndicePenalizacion();
						listaPromedio.add(indicePenal);
						listaPromedioAlgoritmo.add(promedio);
						listaProcesos.remove(i);
					}
				}
			}
		}
		/*
		 * Cuando todos los procesos se hayan ejectuado, se muestran los promedios.
		 */
		if(listaProcesos.isEmpty()) {
			System.out.println("\n" + "Se han ejecutado todos los procesos con el algoritmo FIFO" + "\n");
			listarPromedios();
			Float media = 0.0f;;
			for (int i=0; i < listaPromedioAlgoritmo.size(); i++) {
				 media = media + listaPromedioAlgoritmo.get(i);
				}
			media = media/listaPromedioAlgoritmo.size();
			System.out.println("------------------------------");
			System.out.println("El índice de penalización medio del algoritmo es: " + media);
		}
	}
	
	
	

	
}
