package model;

import java.sql.Blob;

public class Arsenal {
	private int id_arsenal;
	private Blob foto_arsenal;
	private String nombre_arsenal;
	private String descripcion_arsenal;
	private String tipo;
	private int id_administrador;
	
	public int getId_arsenal() {
		return id_arsenal;
	}
	public void setId_arsenal(int id_arsenal) {
		this.id_arsenal = id_arsenal;
	}
	public Blob getFoto_arsenal() {
		return foto_arsenal;
	}
	public void setFoto_arsenal(Blob foto_arsenal) {
		this.foto_arsenal = foto_arsenal;
	}
	public String getNombre_arsenal() {
		return nombre_arsenal;
	}
	public void setNombre_arsenal(String nombre_arsenal) {
		this.nombre_arsenal = nombre_arsenal;
	}
	public String getDescripcion_arsenal() {
		return descripcion_arsenal;
	}
	public void setDescripcion_arsenal(String descripcion_arsenal) {
		this.descripcion_arsenal = descripcion_arsenal;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId_administrador() {
		return id_administrador;
	}
	public void setId_administrador(int id_administrador) {
		this.id_administrador = id_administrador;
	}
}
