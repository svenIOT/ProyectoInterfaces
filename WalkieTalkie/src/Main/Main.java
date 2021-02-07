package Main;

import java.io.IOException;
import java.util.Scanner;

import ClienteServidor.Cliente;
import ClienteServidor.Servidor;

/**
 * Main para gestionar el funcionamiento
 * del WalkieTalkie
 * @author andresprog
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner miScanner = new Scanner(System.in);
		Cliente Client = null;
		Servidor Server = null;
		int frecuencia = 6000;
		String opcionElegida = "";
		String mensaje = "";
		boolean escribirServer = false;
		boolean escribirClient = false;
		boolean conexion = false;
		
		while(true) {
			//Se imprime el menú princiapl
			System.out.println("--------------------------------");
			System.out.println("----------WalkieTalkie----------");
			System.out.println("--------------------------------");
			System.out.println("");
			System.out.println("Bienvenido, elija la opción a la que quiere acceder:");
			System.out.println("");
			System.out.println("1. Enviar un mensaje.");
			System.out.println("2. Esperar un mensaje.");
			System.out.println("3. Salir.");
			System.out.println("");
			opcionElegida = miScanner.nextLine();
			
			switch(opcionElegida) {
				case "1":
					//Se crea el servidor
					Server = new Servidor(frecuencia);
					conexion = true;
					escribirServer = true;
					escribirClient = false;
					while(conexion) {
						while(escribirServer) {
							System.out.println("Introduzca el mensaje:");
							mensaje = miScanner.nextLine();
							Server.flujoSalidaServidor(mensaje);
							
							if(mensaje.equalsIgnoreCase("cambio") || mensaje.equalsIgnoreCase("Cambio")) {
								escribirServer = false;
								escribirClient = true;
								System.out.println("Cambio. Esperando mensaje.");
							}
							
							if(mensaje.equalsIgnoreCase("cambio y corto") || mensaje.equalsIgnoreCase("Cambio y corto")) {
								escribirServer = false;
								escribirClient = false;
								conexion = false;
								System.out.println("Conexión cerrada.");
							}
						}
						
						while(!escribirServer) {
							mensaje = Server.flujoEntradaServidor();
							System.out.println("Mensaje recibido: " + mensaje);
							
							if(mensaje.equalsIgnoreCase("Cambio") || mensaje.equalsIgnoreCase("cambio")) {
								System.out.println("Cambio");
								escribirServer = true;
							}
							
							if(mensaje.equalsIgnoreCase("Cambio y corto") || mensaje.equalsIgnoreCase("cambio y corto")) {
								conexion = false;
								escribirServer = false;
								escribirClient = false;
								System.out.println("Conexión cerrada.");
							}
						}
						
					}
					Server.cerrarConexionServidor();
					break;
				case "2":
					Client = new Cliente(frecuencia);
					conexion = true;
					escribirClient = false;
					escribirServer = true;
					while(conexion) {
						
						while(escribirClient) {
							System.out.println("Introduzca el mensaje:");
							mensaje = miScanner.nextLine();
							Client.flujoSalidaCliente(mensaje);
							
							if(mensaje.equalsIgnoreCase("cambio") || mensaje.equalsIgnoreCase("Cambio")) {
								escribirClient = false;
								escribirServer = true;
								System.out.println("Cambio. Esperando mensaje.");
								mensaje = "";
							}
							
							if(mensaje.equalsIgnoreCase("cambio y corto") || mensaje.equalsIgnoreCase("Cambio y corto")) {
								escribirClient = false;
								escribirServer = false;
								conexion = false;
								System.out.println("Conexión cerrada.");
							}
						}
						
						while(!escribirClient) {
							mensaje = Client.flujoEntradaCliente();
							System.out.println("Mensaje recibido: " + mensaje);
							
							if(mensaje.equalsIgnoreCase("Cambio") || mensaje.equalsIgnoreCase("cambio")) {
								System.out.println("Cambio.");
								escribirClient = true;
								escribirServer = false;
							} else if(mensaje.equalsIgnoreCase("Cambio y corto") || mensaje.equalsIgnoreCase("cambio y corto")) {
								escribirClient = false;
								escribirServer = false;
								conexion = false;
								System.out.println("Conexión cerrada.");
							}
						}
						
					}
					Client.cerrarConexionCliente();
					break;
				case "3":
					System.out.println("El programa ha sido finalizado.");	
					System.exit(0);
			}
		}
	}
	

}
