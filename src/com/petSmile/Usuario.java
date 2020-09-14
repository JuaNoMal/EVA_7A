package com.petSmile;

public class Usuario {
	
	private int id;
	private String nombreUsuario;
	private String password;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, String nombreUsuario, String password) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", password=" + password + "]";
	}
	
	

}
