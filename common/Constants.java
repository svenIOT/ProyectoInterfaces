package common;

public class Constants {
	// BBDD------------
	public static final String CONTROLLER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/taller?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
	// ----------------

	// CONSULTAS-------
	//ClientDAO
	public static final String SELECT_CLIENTS = "SELECT cliente.cod_cliente, cliente.dni, persona.nombre, persona.apellidos, persona.telefono FROM persona INNER JOIN cliente on persona.dni = cliente.dni";
	//VehicleDAO
	public static final String SELECT_VEHICLES = "SELECT * FROM taller.vehiculo";
	public static final String SELECT_CARS = "SELECT vehiculo.num_bastidor, coche.mat_coche, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.coche ON vehiculo.num_bastidor = coche.num_bastidor;";
	public static final String SELECT_MOTORCICLES = "SELECT vehiculo.num_bastidor, motocicleta.mat_moto, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.motocicleta ON vehiculo.num_bastidor = motocicleta.num_bastidor;";
	public static final String SELECT_MOPEDS = "SELECT vehiculo.num_bastidor, ciclomotor.mat_ciclo, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.ciclomotor ON vehiculo.num_bastidor = ciclomotor.num_bastidor;";
	//SellingPropositionDAO
	public static final String SELECT_PROPOSITION = "SELECT * FROM taller.propuesta";
	// ----------------

}
