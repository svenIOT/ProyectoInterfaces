package model;

public class Motorcycle extends Vehicle {

	private String mat_moto;

	public Motorcycle(String num_bastidor, String marca, String modelo, String combustible, String precio, int cod_ventas,
			int cod_cliente, int cod_conce, String vehicleType, String mat_coche) {
		super(num_bastidor, marca, modelo, combustible, precio, cod_ventas, cod_cliente, cod_conce, vehicleType);
		this.mat_moto = mat_coche;
	}

	public String getMat_moto() {
		return mat_moto;
	}

}
