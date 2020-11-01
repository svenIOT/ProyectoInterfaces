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
	 * Devuelve una lista con todos los coches
	 * 
	 * @return
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
	 * @return
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
	 * @return
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
