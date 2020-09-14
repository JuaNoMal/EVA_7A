package com.petSmile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petSmile.Conexion;
import com.petSmile.Dueno;

public class DuenoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// guardar Dueño
		public boolean guardar(Dueno dueno) throws SQLException {
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();

			try {
				connection.setAutoCommit(false);
				sql = "INSERT INTO dueno (rut, nombre, apellido, direccion, correo, telefono, nombreMascota) VALUES(?,?,?,?,?,?,?)";
				statement = connection.prepareStatement(sql);

				statement.setInt(1, dueno.getRut());
				statement.setString(2, dueno.getNombre());
				statement.setString(3, dueno.getApellido());
				statement.setString(4, dueno.getDireccion());
				statement.setString(5, dueno.getCorreo());
				statement.setInt(6, dueno.getTelefono());
				statement.setString(7, dueno.getNombreMascota());

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

		// editar Dueño
		public boolean editar(Dueno dueno) throws SQLException {
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			try {
				connection.setAutoCommit(false);
				sql = "UPDATE dueno SET rut=?, nombre=?, apellido=?, direccion=?, correo=?, telefono=?, nombreMascota=? WHERE rut=?";
				statement = connection.prepareStatement(sql);

				statement.setInt(1, dueno.getRut());
				statement.setString(2, dueno.getNombre());
				statement.setString(3, dueno.getApellido());
				statement.setString(4, dueno.getDireccion());
				statement.setString(5, dueno.getCorreo());
				statement.setInt(6, dueno.getTelefono());
				statement.setString(7, dueno.getNombreMascota());

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

		// eliminar Dueño
		public boolean eliminar(int rut) throws SQLException {
			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();
			try {
				connection.setAutoCommit(false);
				sql = "DELETE FROM dueno WHERE rut=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, rut);

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

		// obtener lista de dueños
		public List<Dueno> obtenerDuenos() throws SQLException {
			ResultSet resultSet = null;
			List<Dueno> listaDuenos= new ArrayList<>();

			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();

			try {
				sql = "SELECT * FROM dueno";
				statement=connection.prepareStatement(sql);
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
					Dueno d=new Dueno();
					d.setRut(resultSet.getInt(1));
					d.setNombre(resultSet.getString(2));
					d.setApellido(resultSet.getString(3));
					d.setDireccion(resultSet.getString(4));
					d.setCorreo(resultSet.getString(5));
					d.setTelefono(resultSet.getInt(6));
					d.setNombreMascota(resultSet.getString(7));
					listaDuenos.add(d);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return listaDuenos;
		}

		// obtener Dueño
		public Dueno obtenerDueno(int rut) throws SQLException {
			ResultSet resultSet = null;
			Dueno d=new Dueno();

			String sql = null;
			estadoOperacion = false;
			connection = obtenerConexion();

			try {
				sql = "SELECT * FROM dueno WHERE rut =?";
				statement=connection.prepareStatement(sql);
				statement.setInt(1, rut);
				
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {				
					d.setRut(resultSet.getInt(1));
					d.setNombre(resultSet.getString(2));
					d.setApellido(resultSet.getString(3));
					d.setDireccion(resultSet.getString(4));
					d.setCorreo(resultSet.getString(5));
					d.setTelefono(resultSet.getInt(6));
					d.setNombreMascota(resultSet.getString(7));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return d;
		}

		// obtener conexion pool
		private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
		}

}
