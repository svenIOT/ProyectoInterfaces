import java.util.ArrayList;

/*
 * Clase que resolverá los procesos mediante el algoritmo RoundRobin
 * @author Andres
 */
public class RoundRobin extends AbstractAlgoritmos {
	Integer quantum;
	
	/*
	 * Constructor
	 * @param miListaProcesos
	 * @param miQuantum
	 */
	public RoundRobin(ArrayList<Proceso> miListaProcesos, Integer miQuantum) {
		listaProcesos = miListaProcesos;
		quantum = miQuantum;
		listaPromedio = new ArrayList<String>();
		listaPromedioAlgoritmo = new ArrayList<Float>();
	}
	
	/*
	 * Método que ejecuta el algoritmo RoundRobin
	 */
	public void runRoundRobin() {
		Integer instante = 0;
		Proceso miProceso;
		/*
		 * Variables booleanas para controlar cuando terminan los procesos
		 * y cuando toca cambiar el turno, es decir, pasar al siguiente proceso.
		 */
		boolean terminado = false;
		boolean turno = false;
		/*
		 * Bucle que se ejecuta mientras todos los procesos no hayan terminado
		 */
		while(terminado==false) {
			turno = false;
			while (turno==false) {
				if(!listaProcesos.isEmpty()) {
					if (listaProcesos.get(0).getTiempoLlegada()<=instante) {
						for (int i=0; i<quantum;i++) {
							if(listaProcesos.get(0).getTiempoRafaga()!=0) {
								listaProcesos.get(0).setTiempoRafaga(listaProcesos.get(0).getTiempoRafaga()-1);
								System.out.print("Ciclo: " + instante + " -- " + listaProcesos.get(0).toString());
								if (listaProcesos.get(0).getTiempoRafaga()==0) {
									listaProcesos.get(0).setTiempoFinal(instante+1);
								}
								instante++;
								
								if (i==1) {
									if (listaProcesos.get(0).getTiempoRafaga()!=listaProcesos.get(0).getTiempoRafagaInicial()) {
										miProceso = crearProceso(0);
										listaProcesos.remove(0);
										listaProcesos.add(miProceso);
										turno = true;
									}
								}
							}
						}
					}
				} else {
					turno = true;
				}
			   /*
			    * Los procesos que terminan, se eliminan de la lista y se añaden
			    * en las listas de los promedios para calcular los índices de
				* penalización de los procesos y del algoritmo.
				*/
				if (!listaProcesos.isEmpty()) {
					for (int i=0; i<listaProcesos.size(); i++) {
						if (listaProcesos.get(i).getTiempoRafaga()==0) {
							String indicePenal;
							Float promedio;
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
			 * Cuando la lista está vacia se termina el bucle y se imprimen los índices de penalización.
			 */
			if(listaProcesos.isEmpty()) {
				terminado = true;
				System.out.println("\n" + "Se han ejecutado todos los procesos con el algoritmo RoundRobin -- QQ = " + quantum + "\n");
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
}
	
	

