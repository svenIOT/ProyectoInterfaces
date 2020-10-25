import java.util.ArrayList;

/*
 * Clase que resolverá los procesos mediante el algoritmo SJF
 * @author Andres
 */
public class SJF extends AbstractAlgoritmos{
	ArrayList<Proceso> miNewListaProcesos;
	Integer instante;
	
	/*
	 * Constructor
	 * @param miListaProcesos
	 */
	public SJF(ArrayList<Proceso> miListaProcesos) {
		listaProcesos = miListaProcesos;
		listaPromedio = new ArrayList<String>();
		listaPromedioAlgoritmo = new ArrayList<Float>();
		miNewListaProcesos = new ArrayList<Proceso>();
		instante = 0;
	}
	
	/*
	 * Método que ejecuta el algoritmo SJF
	 */
	public void runSJF() {
		/*
		 * Variables booleanas para controlar cuando terminan los procesos
		 * y cuándo toca ejecturar otro proceso, es decir, cambiar el turno.
		 */
		Boolean terminado = false;
		Boolean turno = false;
		Proceso procesoCorto = null;
		/*
		 * Bucle que se ejecuta mientras haya procesos pendientes de ejecutar
		 */
		while (terminado == false) {
			if(!listaProcesos.isEmpty()) {
				/*
				 * En este caso es necesario ver que proceso es el que tiene
				 * menor tiempo de ráfaga.
				 */
				procesoCorto = getProcesoCorto();
				miNewListaProcesos.add(procesoCorto);
				/*
				 * Conforme se añaden los procesos a la nueva lista se eliminan
				 * de la principal para evitar que se repitan.
				 */
				for (int i=0;i<listaProcesos.size();i++) {
					if (listaProcesos.get(i).getPID()==procesoCorto.getPID()) {
						listaProcesos.remove(i);
					}
				}
			}
			turno = false;
			/*
			 * Se ejecutan los procesos que se han añadido en la nueva lista 
			 */
			for(int i=0; i<miNewListaProcesos.size(); i++) {
				while(turno==false) {
					if(miNewListaProcesos.get(i).getTiempoRafaga()!=0) {
						if(procesoCorto!=null) {
							if(miNewListaProcesos.get(i).getTiempoRafaga()<=procesoCorto.getTiempoRafaga()) {
								miNewListaProcesos.get(i).setTiempoRafaga(miNewListaProcesos.get(i).getTiempoRafaga()-1);
								System.out.print("Ciclo: " + instante + " -- " + miNewListaProcesos.get(i).toString());
								instante++;
								if(miNewListaProcesos.get(i).getTiempoRafaga()==0) {
									miNewListaProcesos.get(i).setTiempoFinal(instante);
								}
							} else {
								/*
								 * Se eliminan de la nueva lista y se cambia el turno.
								 */
								listaProcesos.add(miNewListaProcesos.get(i));
								miNewListaProcesos.remove(miNewListaProcesos.get(i));
								turno = true;
							}
						} else {
							miNewListaProcesos.get(i).setTiempoRafaga(miNewListaProcesos.get(i).getTiempoRafaga()-1);
							System.out.print("Ciclo: " + instante + " -- " + miNewListaProcesos.get(i).toString());
							if(miNewListaProcesos.get(i).getTiempoRafaga()==0) {
								miNewListaProcesos.get(i).setTiempoFinal(instante);
							}
							instante++;
						}
						if(!miNewListaProcesos.isEmpty()) {
							if(miNewListaProcesos.get(i).getTiempoRafaga()==0) {
								turno = true;
							}
						}
							if(miNewListaProcesos.get(i).getTiempoRafaga()==0) {
								String indicePenal;
								Float promedio;
								indicePenal = miNewListaProcesos.get(i).toStringIndicePenal();
								promedio = miNewListaProcesos.get(i).calcIndicePenalizacion();
								listaPromedio.add(indicePenal);
								listaPromedioAlgoritmo.add(promedio);
								miNewListaProcesos.remove(i);
							
						}
					}
				}
			}

			/*
			 * Una vez las dos listas están vacías se muestran por pantalla
			 * los índices de penalización de los procesos y del algoritmo
			 */
			if(listaProcesos.isEmpty() && miNewListaProcesos.isEmpty()) {
				terminado = true;
				System.out.println("\n" + "Se han ejecutado todos los procesos con el algoritmo SJF" + "\n");
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
	
	/*
	 * Getter que devuelve el proceso con menor tiempo de ráfaga de la lista 
	 * de procesos.
	 * @return procesoCorto
	 */
	public Proceso getProcesoCorto() {
		Proceso procesoCorto = null;
		Proceso procesoA = null;
		Proceso procesoB = null;
		for(int i=0; i<listaProcesos.size();i++) {
			int i2 = i+1;
			if(listaProcesos.get(i).getTiempoLlegada()<=instante) {
				procesoA = crearProceso(i);
			}
			if(i2<listaProcesos.size()) {
				if(listaProcesos.get(i2).getTiempoLlegada()<=instante) {
					procesoB = crearProceso(i2);
					
				}
			}
			if(procesoA!=null && procesoB!=null) {
				if (procesoCorto == null) {
					if(procesoA.getTiempoRafaga()<procesoB.getTiempoRafaga()) {
						procesoCorto = procesoA;
					} else {
						procesoCorto = procesoB;
					}
				
				} else {
					if(procesoA.getTiempoRafaga()<procesoCorto.getTiempoRafaga()) {
						procesoCorto = procesoA;
					}
				}
			} else {
				procesoCorto = procesoA;
			}
		
		}	
		return procesoCorto;
	}
}
