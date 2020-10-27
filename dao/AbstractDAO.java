package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import common.Constants;

public abstract class AbstractDAO {

	protected Connection con;
	protected Statement stm;
	protected ResultSet rs;

	public AbstractDAO() {
		connectDB();
	}

	/**
	 * Conecta con la base de datos
	 */
	private void connectDB() {
		try {
			Class.forName(Constants.CONTROLLER);
			try {
				this.con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cierra el resultSet, statment y conexión
	 */
	protected void closeConexion() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
