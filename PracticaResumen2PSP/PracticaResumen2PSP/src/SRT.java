import java.util.ArrayList;

/*
 * Clase que resuelve los procesos haciendo uso del algoritmo SRT
 */
public class SRT extends AbstractAlgoritmos {

	ArrayList<Proceso> miNewListaProcesos;
	Integer instante;
	
	/*
	 * Constructor
	 * @param miListaProcesos
	 */
	public SRT(ArrayList<Proceso> miListaProcesos) {
		listaProcesos = miListaProcesos;
		listaPromedio = new ArrayList<String>();
		listaPromedioAlgoritmo = new ArrayList<Float>();
		miNewListaProcesos = new ArrayList<Proceso>();
		instante = 0;
	}
	
	/*
	 * Método que ejecuta el algoritmo SRT
	 */
	public void runSRT() {
		/*
		 * Es necesario usar una variable booleana para controlar cuando se terminan
		 * de ejecutar los procesos.
		 */
		Boolean terminado = false;
		/*
		 * Mientras haya procesos pendientes de ejecutar no termina el bucle.
		 */
		while (terminado == false) {
			if(!listaProcesos.isEmpty()) {
				Proceso procesoCorto;
				/*
				 * Se añaden a una nueva lista los procesos en orden de tiempo de ráfaga
				 * y se eliminan de la lista de Procesos para evitar repeticiones.
				 */
				procesoCorto = getProcesoCorto();
				miNewListaProcesos.add(procesoCorto);
				for (int i=0; i<listaProcesos.size(); i++) {
					if(listaProcesos.get(i).getPID()==procesoCorto.getPID()) {
						listaProcesos.remove(i);
					}
				}
			}
			/*
			 * Se resuelven los procesos de la nueva lista.
			 */
			for(int i=0;i<miNewListaProcesos.size();i++) {
				while(miNewListaProcesos.get(i).getTiempoRafaga()!=0) {
					miNewListaProcesos.get(i).setTiempoRafaga(miNewListaProcesos.get(i).getTiempoRafaga()-1);
					System.out.print("Ciclo: " + instante + " -- " + miNewListaProcesos.get(i).toString());
					if(miNewListaProcesos.get(i).getTiempoRafaga()==0) {
						miNewListaProcesos.get(i).setTiempoFinal(instante+1);
					}
					instante++;
				}
			}
		
			/*
			 * Cuando los procesos se terinan de ejecutar se añaden a una
			 * nueva lista de promedios para calcular el índice de penalización
			 * de los procesos y del algoritmo
			 */
			for(int i=0;i<miNewListaProcesos.size();i++) {
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
			
			/*
			 * Cuando se terminan de ejecutar todos los procesos, se muestra por pantalla los
			 * índices de penalización de los procesos y del algoritmo.
			 */
			if(listaProcesos.isEmpty() && miNewListaProcesos.isEmpty()) {
				terminado = true;
				System.out.println("\n" + "Se han ejecutado todos los procesos con el algoritmo SRT" + "\n");
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
	 * Getter que duevuelve el proceso con menor tiempo de ráfaga
	 * de la lista de procesos.
	 * @return procesoCorto
	 */
	public Proceso getProcesoCorto() {
		Proceso procesoCorto = null;
		Proceso proceso1 = null;
		Proceso proceso2 = null;
		for(int i=0; i<listaProcesos.size();i++) {
			int i2 = i+1;
			if(listaProcesos.get(i).getTiempoLlegada()<=instante) {
				proceso1 = crearProceso(i);
			}
			if(i2<listaProcesos.size()) {
				if(listaProcesos.get(i2).getTiempoLlegada()<=instante) {
					proceso2 = crearProceso(i2);
					
				}
			}
			if(proceso1!=null && proceso2!=null) {
				if (procesoCorto == null) {
					if(proceso1.getTiempoRafaga()<proceso2.getTiempoRafaga()) {
						procesoCorto = proceso1;
					} else {
						procesoCorto = proceso2;
					}
				
				} else {
					if(proceso1.getTiempoRafaga()<procesoCorto.getTiempoRafaga()) {
						procesoCorto = proceso1;
					}
				}
			} else {
				procesoCorto = proceso1;
			}
		
		}	
		return procesoCorto;
	}
}
