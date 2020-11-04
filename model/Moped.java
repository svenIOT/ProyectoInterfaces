package model;

public class Moped extends Vehicle {

	private String mat_ciclo;

	// Constructor con todos los campos
	public Moped(String num_bastidor, String marca, String modelo, String combustible, String precio,
			String concesionario, String nombreCliente, String nombreVentas, String mat_ciclo) {
		super(num_bastidor, marca, modelo, combustible, precio, concesionario, nombreCliente, nombreVentas);
		this.mat_ciclo = mat_ciclo;
	}

	// Constructor sin códigos FK
	public Moped(String num_bastidor, String marca, String modelo, String combustible, String precio,
			String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio);
		this.mat_ciclo = mat_coche;
	}

	public String getMat_ciclo() {
		return mat_ciclo;
	}

}
