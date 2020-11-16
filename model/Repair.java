package model;

import java.sql.Date;

public class Repair {
	
	private int cod_reparacion, cod_mecanico;
	String num_bastidor, piezas;
	Date fecha_entrada, fecha_salida;
	
	public Repair(int cod_reparacion, int cod_mecanico, String num_bastidor, String piezas, Date fecha_entrada,
			Date fecha_salida) {
		this.cod_reparacion = cod_reparacion;
		this.cod_mecanico = cod_mecanico;
		this.num_bastidor = num_bastidor;
		this.piezas = piezas;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
	}
	
	
	public int getCod_reparacion() {
		return cod_reparacion;
	}
	public int getCod_mecanico() {
		return cod_mecanico;
	}
	public String getNum_bastidor() {
		return num_bastidor;
	}
	public String getPiezas() {
		return piezas;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}

}
