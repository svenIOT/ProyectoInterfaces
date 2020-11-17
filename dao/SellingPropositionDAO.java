package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.SellingProposition;

public class SellingPropositionDAO extends AbstractDAO {

	public SellingPropositionDAO() {
		super();
	}

	/**
	 * Devuelve todas las proposiciones de venta
	 * 
	 * @return Lista de propuestas
	 */
	public List<SellingProposition> getSellingProposition() {
		var sellingProposition = new ArrayList<SellingProposition>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_PROPOSITION);
			while (rs.next()) {
				sellingProposition.add(new SellingProposition(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getString(5)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return sellingProposition;
	}

	/**
	 * Inserta en la BBDD la propuesta de venta que se le pasa como parámetro
	 * 
	 * @param sp propuesta de venta
	 */
	public void addSellingProposition(SellingProposition sp) {
		try {
			stm = con.createStatement();
			stm.executeQuery(
					"INSERT INTO taller.propuesta (cod_propuesta, cod_cliente, cod_ventas, num_bastidor, fecha_validez) VALUES ("
							+ sp.getCod_propuesta() + ", " + sp.getCod_cliente() + ", " + sp.getCod_ventas() + ", '"
							+ sp.getNum_bastidor() + "', '" + sp.getFecha_validez() + "')");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public String getDni_cliente(Integer cod_cliente) {
		String dni="";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT cliente.dni from taller.cliente where cliente.cod_cliente = " + cod_cliente+";");
			if(rs.next()) {
				dni = rs.getString(1);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return dni;
	}
	
	
	public SellingProposition searchProposition(String dni) {
		SellingProposition proposition = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * from taller.propuesta inner join taller.cliente where cliente.dni ='" + dni + "'"
					+ "AND cliente.cod_cliente = propuesta.cod_cliente;");
			if (rs.next()) {
				proposition = new SellingProposition(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return proposition;
	}

}
