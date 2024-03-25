package model;

import java.sql.Blob;

public class News {
	private int id_new;
	private Blob imageNews;
	private String titulo;
	private String descripcion_noticia;
	private int ID_administrador;
	
	public News() {
		
	}
	
	public News(int id_new, Blob imageNews, String titulo, String descripcion_noticia, int iD_administrador) {
		super();
		this.id_new = id_new;
		this.imageNews = imageNews;
		this.titulo = titulo;
		this.descripcion_noticia = descripcion_noticia;
		ID_administrador = iD_administrador;
	}

	public int getId_new() {
		return id_new;
	}

	public void setId_new(int id_new) {
		this.id_new = id_new;
	}

	public Blob getImageNews() {
		return imageNews;
	}

	public void setImageNews(Blob imageNews) {
		this.imageNews = imageNews;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion_noticia() {
		return descripcion_noticia;
	}

	public void setDescripcion_noticia(String descripcion_noticia) {
		this.descripcion_noticia = descripcion_noticia;
	}

	public int getID_administrador() {
		return ID_administrador;
	}

	public void setID_administrador(int iD_administrador) {
		ID_administrador = iD_administrador;
	}
	
	
}
