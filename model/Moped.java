package model;

public class Moped extends Vehicle {

	private String mat_ciclo;

	public Moped(String num_bastidor, String marca, String modelo, String combustible, String precio, int cod_ventas,
			int cod_cliente, int cod_conce, String mat_ciclo) {
		super(num_bastidor, marca, modelo, combustible, precio, cod_ventas, cod_cliente, cod_conce);
		this.mat_ciclo = mat_ciclo;
	}

	public String getMat_ciclo() {
		return mat_ciclo;
	}

}
