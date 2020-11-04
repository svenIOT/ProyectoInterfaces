package model;

public class Motorcycle extends Vehicle {

	private String mat_moto;

	// Constructor con todos los campos
	public Motorcycle(String num_bastidor, String marca, String modelo, String combustible, String precio,
			String concesionario, String nombreCliente, String nombreVentas, String mat_moto) {
		super(num_bastidor, marca, modelo, combustible, precio, concesionario, nombreCliente, nombreVentas);
		this.mat_moto = mat_moto;
	}

	// Constructor sin códigos FK
	public Motorcycle(String num_bastidor, String marca, String modelo, String combustible, String precio,
			String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio);
		this.mat_moto = mat_coche;
	}

	public String getMat_moto() {
		return mat_moto;
	}

}
