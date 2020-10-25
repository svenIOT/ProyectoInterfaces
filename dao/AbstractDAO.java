package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import common.Constants;

public abstract class AbstractDAO {

	protected Connection con;
	
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
	
}
