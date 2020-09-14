package com.petSmile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petSmile.Conexion;
import com.petSmile.Agenda;

public class AgendaDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// guardar agenda
	@SuppressWarnings("null")
	public boolean guardar(Agenda agenda) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO agenda (id, idMascota, rutDueno, nombreMascota, hora, fecha) VALUES(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, (Integer) null);
			statement.setInt(2, agenda.getIdMascota());
			statement.setInt(3, agenda.getRutDueno());
			statement.setString(4, agenda.getNombreMascota());
			statement.setDate(5, agenda.getHora());
			statement.setDate(6, agenda.getFecha());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// editar agenda
	public boolean editar(Agenda agenda) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE productos SET idMascota=?, rutDueno=?, nombreMascota=?, hora=?, fecha=? WHERE id=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, agenda.getIdMascota());
			statement.setInt(2, agenda.getRutDueno());
			statement.setString(3, agenda.getNombreMascota());
			statement.setDate(4, agenda.getHora());
			statement.setDate(5, agenda.getFecha());;
			statement.setInt(6, agenda.getId());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// eliminar agenda
	public boolean eliminar(int idAgenda) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM agenda WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAgenda);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// obtener lista de agenda
	public List<Agenda> obtenerAgendas() throws SQLException {
		ResultSet resultSet = null;
		List<Agenda> listaAgendas= new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM productos";
			statement=connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Agenda a=new Agenda();
				a.setId(resultSet.getInt(1));
				a.setIdMascota(resultSet.getInt(2));
				a.setRutDueno(resultSet.getInt(3));
				a.setNombreMascota(resultSet.getString(4));
				a.setHora(resultSet.getDate(5));
				a.setFecha(resultSet.getDate(6));
				listaAgendas.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAgendas;
	}

	// obtener agenda
	public Agenda obtenerAgenda(int idAgenda) throws SQLException {
		ResultSet resultSet = null;
		Agenda a=new Agenda();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM agenda WHERE id =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, idAgenda);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {				
				a.setId(resultSet.getInt(1));
				a.setIdMascota(resultSet.getInt(2));
				a.setRutDueno(resultSet.getInt(3));
				a.setNombreMascota(resultSet.getString(4));
				a.setHora(resultSet.getDate(5));
				a.setFecha(resultSet.getDate(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
