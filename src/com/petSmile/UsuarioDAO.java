package com.petSmile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petSmile.Usuario;
import com.petSmile.Conexion;

public class UsuarioDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	public boolean guardar(Usuario usuario) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO usuario (id, nombreUsuario, password) VALUES(?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setString(2, usuario.getNombreUsuario());
			statement.setString(3, usuario.getPassword());

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

	// editar usuario
	public boolean editar(Usuario usuario) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE usuario SET nombreUsuario=?, password=? WHERE id=?";
			statement = connection.prepareStatement(sql);

			statement.setString(2, usuario.getNombreUsuario());
			statement.setString(3, usuario.getPassword());

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

	// eliminar usuario
	public boolean eliminar(int idUsuario) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM usuario WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idUsuario);

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

	// obtener lista de usuarios
	public List<Usuario> obtenerUsuarios() throws SQLException {
		ResultSet resultSet = null;
		List<Usuario> listaUsuarios= new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM usuario";
			statement=connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Usuario u=new Usuario();
				u.setId(resultSet.getInt(1));
				u.setNombreUsuario(resultSet.getString(2));
				u.setPassword(resultSet.getString(3));
				listaUsuarios.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;
	}

	// obtener producto
	public Usuario obtenerUsuario(int idUsuario) throws SQLException {
		ResultSet resultSet = null;
		Usuario p=new Usuario();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM usuario WHERE id =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {				
				p.setId(resultSet.getInt(1));
				p.setNombreUsuario(resultSet.getString(2));
				p.setPassword(resultSet.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}
	
	public Usuario usuarioLogin(String nombreUsuario, String password) throws SQLException {
		ResultSet resultSet = null;
		Usuario p=new Usuario();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM usuario WHERE nombreUsuario =? and password=?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, nombreUsuario);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {		
				p.setId(resultSet.getInt(1));
				p.setNombreUsuario(resultSet.getString(2));
				p.setPassword(resultSet.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}


	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}
