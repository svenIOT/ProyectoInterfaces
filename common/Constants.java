package common;

public class Constants {
	// BBDD------------
	public static final String CONTROLLER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/taller?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	// ----------------

	// CONSULTAS-------
	// UserDAO
	public static final String SELECT_MECHANICALS_ALL_DATA = "SELECT * FROM taller.persona INNER JOIN taller.empleado ON persona.dni = empleado.dni INNER JOIN taller.mecanico ON empleado.cod_empleado = mecanico.cod_empleado;";
	// ClientDAO
	public static final String SELECT_CLIENTS_ALL_DATA = "SELECT cliente.cod_cliente, cliente.dni, persona.nombre, persona.apellidos, persona.telefono FROM persona INNER JOIN cliente on persona.dni = cliente.dni";
	// VehicleDAO
	public static final String SELECT_VEHICLES = "SELECT * FROM taller.vehiculo";
	public static final String SELECT_CARS_AlL_DATA = "SELECT vehiculo.num_bastidor, coche.mat_coche, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.coche ON vehiculo.num_bastidor = coche.num_bastidor;";
	public static final String SELECT_MOTORCICLES_ALL_DATA = "SELECT vehiculo.num_bastidor, motocicleta.mat_moto, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.motocicleta ON vehiculo.num_bastidor = motocicleta.num_bastidor;";
	public static final String SELECT_MOPEDS_ALL_DATA = "SELECT vehiculo.num_bastidor, ciclomotor.mat_ciclo, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.ciclomotor ON vehiculo.num_bastidor = ciclomotor.num_bastidor;";
	public static final String SELECT_COUNT_VEHICLES_UNSOLD = "SElECT count (*) from taller.vehiculo where cod_cliente = null";
	// SellingPropositionDAO
	public static final String SELECT_PROPOSITION = "SELECT * FROM taller.propuesta;";
	// RepairDAO
	public static final String SELECT_REPAIRS = "SELECT * FROM taller.reparacion;";
	// ----------------

}
