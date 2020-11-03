package model;

public class Car extends Vehicle {

	private String mat_coche;
	private String concesionario, nombreCliente, nombreVentas;

	// Constructor con todos los campos
	public Car(String num_bastidor, String marca, String modelo, String combustible, String precio, String mat_coche,
			String concesionario, String nombreCliente, String nombreVentas) {
		super(num_bastidor, marca, modelo, combustible, precio);
		this.mat_coche = mat_coche;
		this.concesionario = concesionario;
		this.nombreCliente = nombreCliente;
		this.nombreVentas = nombreVentas;
	}

	// Constructor sin códigos FK
	public Car(String num_bastidor, String marca, String modelo, String combustible, String precio, String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio);
		this.mat_coche = mat_coche;
	}

	public String getMat_coche() {
		return mat_coche;
	}

	public String getConcesionario() {
		return concesionario;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public String getNombreVentas() {
		return nombreVentas;
	}

}
