package model;

import java.sql.Blob;

public class Policeman extends User{
	
	private int id_policia;
	private Blob foto_policia;
	private String dni;
	private String nombre_policia;
	private String apellido_policia;
	private String rango;
	private int id_user;
	
	public int getId_policia() {
		return id_policia;
	}
	public void setId_policia(int id_policia) {
		this.id_policia = id_policia;
	}
	public Blob getFoto_policia() {
		return foto_policia;
	}
	public void setFoto_policia(Blob foto_policia) {
		this.foto_policia = foto_policia;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre_policia() {
		return nombre_policia;
	}
	public void setNombre_policia(String nombre_policia) {
		this.nombre_policia = nombre_policia;
	}
	public String getApellido_policia() {
		return apellido_policia;
	}
	public void setApellido_policia(String apellido_policia) {
		this.apellido_policia = apellido_policia;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
}
