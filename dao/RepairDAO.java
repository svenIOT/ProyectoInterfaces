package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.Repair;

public class RepairDAO extends AbstractDAO {

	public RepairDAO() {
		super();
	}

	/**
	 * Devuelve una lista con todas las reparaciones
	 * 
	 * @return List de reparación
	 */
	public List<Repair> getRepairs() {
		var repairs = new ArrayList<Repair>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_REPAIRS);
			while (rs.next()) {
				repairs.add(
						new Repair(rs.getInt("cod_reparacion"), rs.getInt("cod_mecanico"), rs.getString("num_bastidor"),
								rs.getString("piezas"), rs.getString("fecha_entrada"), rs.getString("fecha_salida")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return repairs;
	}

	/**
	 * Inserta la reparación en la BBDD
	 * 
	 * @param repair
	 */
	public void addRepair(Repair repair) {

	}

	/**
	 * Elimina la reparación con el código pasado por parámetro
	 * 
	 * @param repairId (Código de reparación)
	 */
	public void finishRepair(int repairId) {
		try {
			con.setAutoCommit(false);
			stm = con.createStatement();
			stm.executeUpdate("DELETE FROM taller.reparacion WHERE cod_reparacion=" + repairId);
			con.commit();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
		}

	}

}
