package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.Car;
import model.Moped;
import model.Motorcycle;
import model.Vehicle;

public class VehicleDAO extends AbstractDAO {

	public VehicleDAO() {
		super();
	}

	/**
	 * Comprueba el tipo de vehiculo y lo añade a la BBDD
	 * 
	 * @param v           objeto vehiculo
	 * @param vehicleType tipo de vehículo que viene de un combobox
	 * @param enrollment  matrícula del vehículo en cuestión, viene de un txtField
	 */
	public void addVehicle(Vehicle v, String vehicleType, String enrollment) {
		String vehicleTypeInsert;
		// Asignar tipo de vehiculo para insertarlo en su tabla
		if (vehicleType.equalsIgnoreCase("coche")) {
			vehicleTypeInsert = "INSERT INTO taller.coche (mat_coche, num_bastidor) VALUES ('" + enrollment + "', '"
					+ v.getNum_bastidor() + "')";
		} else if (vehicleType.equalsIgnoreCase("motocicleta")) {
			vehicleTypeInsert = "INSERT INTO taller.motocicleta (mat_moto, num_bastidor) VALUES ('" + enrollment
					+ "', '" + v.getNum_bastidor() + "')";
		} else {
			vehicleTypeInsert = "INSERT INTO taller.ciclomotor (mat_ciclo, num_bastidor) VALUES ('" + enrollment
					+ "', '" + v.getNum_bastidor() + "' )";
		}

		try {
			stm = con.createStatement();
			stm.executeUpdate(
					"INSERT INTO taller.vehiculo (num_bastidor, cod_ventas, cod_cliente, cod_conce, marca, modelo, combustible, precio) VALUES ("
							+ "'" + v.getNum_bastidor() + "', " + v.getCod_ventas() + ", " + v.getCod_cliente() + ", "
							+ v.getCod_conce() + ", '" + v.getMarca() + "', '" + v.getModelo() + "', '"
							+ v.getCombustible() + "', " + v.getPrecio() + ")");
			stm.executeUpdate(vehicleTypeInsert);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Busca un vehículo según el número de bastidor
	 * 
	 * @param frameNumber String
	 * @return Objeto vehículo
	 */
	public Vehicle searchVehicle(String frameNumber) {
		Vehicle vehiculo = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT vehiculo.num_bastidor, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM vehiculo WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				vehiculo = new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return vehiculo;
	}

	/**
	 * Devuelve el coche con el número de bastidor indicado
	 * 
	 * @param frameNumber
	 * @return Objeto coche
	 */
	public Car searchCar(String frameNumber) {
		Car car = null;
		String licenseNumber = "", employeeSurnames = "", concesionaireName = "", brand = "", carModel = "", fuel = "",
				price = "", clientSurnames = "";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT coche.mat_coche, persona.apellidos, concesionario.nombre, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.concesionario ON "
							+ "vehiculo.cod_conce = concesionario.cod_conce INNER JOIN taller.coche ON vehiculo.num_bastidor = coche.num_bastidor INNER JOIN taller.ventas ON vehiculo.cod_ventas = ventas.cod_ventas "
							+ "INNER JOIN taller.empleado ON ventas.cod_empleado = empleado.cod_empleado INNER JOIN taller.persona ON empleado.dni = persona.dni WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				licenseNumber = rs.getString(1);
				employeeSurnames = rs.getString(2);
				concesionaireName = rs.getString(3);
				brand = rs.getString(4);
				carModel = rs.getString(5);
				fuel = rs.getString(6);
				price = rs.getString(7);

			}

			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT persona.apellidos FROM taller.persona INNER JOIN taller.cliente ON persona.dni = cliente.dni INNER JOIN taller.vehiculo ON cliente.cod_cliente = vehiculo.cod_cliente WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				clientSurnames = rs.getString(1);
			}
			car = new Car(frameNumber, brand, carModel, fuel, price, concesionaireName, clientSurnames,
					employeeSurnames, licenseNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return car;
	}

	/**
	 * Devuelve la moto con el número de bastidor indicado
	 * 
	 * @param fameNumber
	 * @return Objeto moto
	 */
	public Motorcycle searchMotorcycle(String frameNumber) {
		Motorcycle motorcycle = null;
		String licenseNumber = "", employeeSurnames = "", concesionaireName = "", brand = "", motorcycleModel = "", fuel = "",
				price = "", clientSurnames = "";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT motocicleta.mat_moto, persona.apellidos, concesionario.nombre, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.concesionario ON "
							+ "vehiculo.cod_conce = concesionario.cod_conce INNER JOIN taller.motocicleta ON vehiculo.num_bastidor = motocicleta.num_bastidor INNER JOIN taller.ventas ON vehiculo.cod_ventas = ventas.cod_ventas "
							+ "INNER JOIN taller.empleado ON ventas.cod_empleado = empleado.cod_empleado INNER JOIN taller.persona ON empleado.dni = persona.dni WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				licenseNumber = rs.getString(1);
				employeeSurnames = rs.getString(2);
				concesionaireName = rs.getString(3);
				brand = rs.getString(4);
				motorcycleModel = rs.getString(5);
				fuel = rs.getString(6);
				price = rs.getString(7);

			}

			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT persona.apellidos FROM taller.persona INNER JOIN taller.cliente ON persona.dni = cliente.dni INNER JOIN taller.vehiculo ON cliente.cod_cliente = vehiculo.cod_cliente WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				clientSurnames = rs.getString(1);
			}
			motorcycle = new Motorcycle(frameNumber, brand, motorcycleModel, fuel, price, concesionaireName, clientSurnames,
					employeeSurnames, licenseNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return motorcycle;
	}

	/**
	 * Devuelve el ciclomotor con el número de bastidor indicado
	 * 
	 * @param frameNumber
	 * @return Objeto ciclomotor
	 */
	public Moped searchMoped(String frameNumber) {
		Moped moped = null;
		String licenseNumber = "", employeeSurnames = "", concesionaireName = "", brand = "", mopedModel = "", fuel = "",
				price = "", clientSurnames = "";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT ciclomotor.mat_ciclo, persona.apellidos, concesionario.nombre, vehiculo.marca, vehiculo.modelo, vehiculo.combustible, vehiculo.precio FROM taller.vehiculo INNER JOIN taller.concesionario ON "
							+ "vehiculo.cod_conce = concesionario.cod_conce INNER JOIN taller.ciclomotor ON vehiculo.num_bastidor = ciclomotor.num_bastidor INNER JOIN taller.ventas ON vehiculo.cod_ventas = ventas.cod_ventas "
							+ "INNER JOIN taller.empleado ON ventas.cod_empleado = empleado.cod_empleado INNER JOIN taller.persona ON empleado.dni = persona.dni WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				licenseNumber = rs.getString(1);
				employeeSurnames = rs.getString(2);
				concesionaireName = rs.getString(3);
				brand = rs.getString(4);
				mopedModel = rs.getString(5);
				fuel = rs.getString(6);
				price = rs.getString(7);

			}

			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT persona.apellidos FROM taller.persona INNER JOIN taller.cliente ON persona.dni = cliente.dni INNER JOIN taller.vehiculo ON cliente.cod_cliente = vehiculo.cod_cliente WHERE vehiculo.num_bastidor='"
							+ frameNumber + "'");
			if (rs.next()) {
				clientSurnames = rs.getString(1);
			}
			moped = new Moped(frameNumber, brand, mopedModel, fuel, price, concesionaireName, clientSurnames,
					employeeSurnames, licenseNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return moped;
	}
	
	/**
	 * Devuelve una lista con todos los coches
	 * 
	 * @return Lista de coches
	 */
	public List<Car> getCars() {
		var cars = new ArrayList<Car>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_CARS);
			while (rs.next()) {
				cars.add(new Car(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(2)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cars;
	}

	/**
	 * Devuelve una lista con todas las motocicletas
	 * 
	 * @return Lista de motocicletas
	 */
	public List<Motorcycle> getMotorcycles() {
		var motorcycles = new ArrayList<Motorcycle>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_MOTORCICLES);
			while (rs.next()) {
				motorcycles.add(new Motorcycle(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(2)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return motorcycles;

	}

	/**
	 * Devuelve una lista con todos los ciclomotores
	 * 
	 * @return Lista de ciclomtores
	 */
	public List<Moped> getMopeds() {
		var moped = new ArrayList<Moped>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_MOPEDS);
			while (rs.next()) {
				moped.add(new Moped(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(2)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return moped;
	}

}