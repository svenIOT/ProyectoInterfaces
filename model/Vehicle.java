package model;

public class Vehicle {

	private String num_bastidor, marca, modelo, combustible, precio;
	private int cod_ventas, cod_cliente, cod_conce;

	// Constructor con todos los campos
	public Vehicle(String num_bastidor, String marca, String modelo, String combustible, String precio, int cod_ventas,
			int cod_cliente, int cod_conce) {
		this.num_bastidor = num_bastidor;
		this.marca = marca;
		this.modelo = modelo;
		this.combustible = combustible;
		this.precio = precio;
		this.cod_ventas = cod_ventas;
		this.cod_cliente = cod_cliente;
		this.cod_conce = cod_conce;
	}
	
	// Constructor sin códigos FK
	public Vehicle(String num_bastidor, String marca, String modelo, String combustible, String precio) {
		this.num_bastidor = num_bastidor;
		this.marca = marca;
		this.modelo = modelo;
		this.combustible = combustible;
		this.precio = precio;
	}

	
	// Getters

	public String getNum_bastidor() {
		return num_bastidor;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCombustible() {
		return combustible;
	}

	public String getPrecio() {
		return precio;
	}

	public int getCod_ventas() {
		return cod_ventas;
	}

	public int getCod_cliente() {
		return cod_cliente;
	}

	public int getCod_conce() {
		return cod_conce;
	}
	
	

}
