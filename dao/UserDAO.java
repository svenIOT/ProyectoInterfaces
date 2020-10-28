package dao;

import model.Employee;

public class UserDAO extends AbstractDAO {
	
	

	public UserDAO() {
		super();
	}

	/**
	 * Comprueba si el empleado existe en la BBDD
	 * @param e
	 * @return
	 */
	public boolean login(Employee e) {
		boolean isLogin = false;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM empleado WHERE usuario='" + e.getUsername()
					+ "' AND contrasena='" + e.getPassword() + "'");
			isLogin = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isLogin;
	}

	/**
	 * Comprueba si el empleado pertenece al departamento de ventas
	 * @param e
	 * @return
	 */
	public boolean isSalesEmployee(Employee e) {
		var isSales = false;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM empleado INNER JOIN VENTAS ON empleado.cod_empleado = ventas.cod_empleado WHERE username='" + e.getUsername()
					+ "' AND contrasena='" + e.getPassword() + "'");
			isSales = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isSales;
	}

}
