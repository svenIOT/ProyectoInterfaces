package model;

public class Car extends Vehicle {

	private String mat_coche;

	// Constructor con todos los campos
	public Car(String num_bastidor, String marca, String modelo, String combustible, String precio, String concesionario, String nombreCliente, String nombreVentas,  String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio, concesionario, nombreCliente, nombreVentas);
		this.mat_coche = mat_coche;
	}

	// Constructor sin códigos FK
	public Car(String num_bastidor, String marca, String modelo, String combustible, String precio, String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio);
		this.mat_coche = mat_coche;
	}

	

	public String getMat_coche() {
		return mat_coche;
	}

}
