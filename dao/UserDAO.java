package dao;

import model.Employee;

public class UserDAO extends AbstractDAO {

	public UserDAO() {
		super();
	}

	/**
	 * Comprueba si el empleado existe en la BBDD
	 * 
	 * @param e Empleado
	 * @return boolean
	 */
	public boolean login(Employee e) {
		boolean isLogin = false;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM empleado WHERE usuario='" + e.getUsername() + "' AND contrasena='"
					+ e.getPassword() + "'");
			isLogin = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isLogin;
	}

	/**
	 * Comprueba si el empleado pertenece al departamento de ventas
	 * 
	 * @param e Empleado
	 * @return boolean
	 */
	public boolean isSalesEmployee(Employee e) {
		var isSales = false;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT * FROM taller.empleado INNER JOIN taller.ventas ON empleado.cod_empleado = ventas.cod_empleado WHERE usuario='"
							+ e.getUsername() + "' AND contrasena='" + e.getPassword() + "'");
			isSales = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isSales;
	}

	/**
	 * Comprueba si el empleado es un empleado jefe del departamento de mecánica
	 * 
	 * @param e Empleado
	 * @return boolean
	 */
	public boolean isBossMechanical(Employee e) {
		var isBoss = false;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT * FROM taller.empleado INNER JOIN taller.mecanico ON empleado.cod_empleado = mecanico.cod_empleado WHERE usuario='"
							+ e.getUsername() + "' AND contrasena='" + e.getPassword() + "'");
			// Comprobar si el código de jefe y el de mecánico es igual (es jefe)
			if(rs.next() && rs.getInt("cod_mecanico") == rs.getInt("cod_mecanico_jefe")) {
				isBoss = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isBoss;
	}

}
