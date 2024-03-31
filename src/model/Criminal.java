package model;

import java.sql.Blob;

public class Criminal {
	
	private int id_criminal;
    private Blob foto_criminal;
    private String dni;
    private String nombre_criminal;
    private String apellido_criminal;
    private String descripcion_criminal;
    private int id_administrador;
    private int id_policia;
	
    public int getId_criminal() {
		return id_criminal;
	}
	public void setId_criminal(int id_criminal) {
		this.id_criminal = id_criminal;
	}
	public Blob getFoto_criminal() {
		return foto_criminal;
	}
	public void setFoto_criminal(Blob foto_criminal) {
		this.foto_criminal = foto_criminal;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre_criminal() {
		return nombre_criminal;
	}
	public void setNombre_criminal(String nombre_criminal) {
		this.nombre_criminal = nombre_criminal;
	}
	public String getApellido_criminal() {
		return apellido_criminal;
	}
	public void setApellido_criminal(String apellido_criminal) {
		this.apellido_criminal = apellido_criminal;
	}
	public String getDescripcion_criminal() {
		return descripcion_criminal;
	}
	public void setDescripcion_criminal(String descripcion_criminal) {
		this.descripcion_criminal = descripcion_criminal;
	}
	public int getId_administrador() {
		return id_administrador;
	}
	public void setId_administrador(int id_administrador) {
		this.id_administrador = id_administrador;
	}
	public int getId_policia() {
		return id_policia;
	}
	public void setId_policia(int id_policia) {
		this.id_policia = id_policia;
	}
}
