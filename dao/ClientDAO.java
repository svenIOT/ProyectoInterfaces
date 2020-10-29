package dao;

import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientDAO extends AbstractDAO {

	public ClientDAO() {
		super();
	}

	/**
	 * Añade un cliente a la BBDD
	 * @param e Objeto cliente
	 */
	protected void addClient(Client e) {
		try {
			stm = con.createStatement();
			stm.executeUpdate("INSERT INTO taller.cliente (cod_cliente, dni) VALUES (0, " + e.getDni() + ")");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena la tabla de contenido inicial (Todos los clientes)
	 * 
	 * @return Lista de clientes
	 */
	protected List<Client> fillTable() {
		var clients = new ArrayList<Client>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT cliente.cod_cliente, cliente.dni, persona.nombre, persona.apellidos, persona.telefono FROM persona INNER JOIN cliente on persona.dni = cliente.dni");
			while (rs.next()) {
				clients.add(
						new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clients;
	}

	/**
	 * Busca si hay un cliente con el dni y si existe devuelve un objeto cliente con
	 * sus datos
	 * 
	 * @param dni String
	 * @return Objeto cliente
	 */
	protected Client searchClient(String dni) {
		Client client = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(
					"SELECT cliente.cod_cliente, cliente.dni, persona.nombre, persona.apellidos, persona.telefono FROM persona INNER JOIN cliente on persona.dni = cliente.dni WHERE cliente.dni='"
							+ dni + "'");
			if (rs.next()) {
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return client;
	}

}
