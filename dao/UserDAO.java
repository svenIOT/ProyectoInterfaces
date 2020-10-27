package dao;

import model.Employee;

public class UserDAO extends AbstractDAO {
	
	

	public UserDAO() {
		super();
	}

	public boolean login(Employee e) {
		boolean isLogin = false;
		try {
			con.createStatement();
			stm.executeQuery("SELECT * FROM empleado WHERE usuario='" + e.getUsername()
					+ "' AND contrasena='" + e.getPassword() + "'");
			isLogin = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isLogin;
	}

	public boolean isSalesEmployee(Employee e) {
		var isSales = false;
		try {
			con.createStatement();
			stm.executeQuery("SELECT * FROM empleado INNER JOIN VENTAS ON empleado.cod_empleado = ventas.cod_empleado WHERE username='" + e.getUsername()
					+ "' AND contrasena='" + e.getPassword() + "'");
			isSales = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isSales;
	}

}
