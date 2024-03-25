package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.News;
import model.User;

public class Controller implements InterfaceController{
	
	private Connection conn;
	private PreparedStatement stmt;
	private final String SHOW_NEWS = "SELECT * FROM NOTICIA";
	final String SELECT_USER = "SELECT * FROM users WHERE username=? AND password=?";
	
	public boolean logIn(String u, String p) {
		ResultSet rs = null;
		User us = null;

		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = conn.prepareStatement(SELECT_USER);

			// Cargamos los parámetros
			stmt.setString(1, u);
			stmt.setString(2, p);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				us = new User();
				us.setUsername(u);
				us.setPassword(p);
				return true;
			} else {
				us = null;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return false;
	}
	
	@Override
	public ArrayList<News> showNews() {
		ResultSet rs = null;
		News n = null;
		ArrayList<News> ns = new ArrayList<News>();
		
		this.openConnection();
		try {
			stmt = conn.prepareStatement(SHOW_NEWS);

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				n = new News();
				n.setId_new(rs.getInt("ID_noticia"));
				n.setImageNews(rs.getBlob("foto_noticia"));
				n.setTitulo(rs.getString("titulo"));
				n.setDescripcion_noticia(rs.getString("descripcion_noticia"));
				n.setID_administrador(rs.getInt("ID_administrador"));
				ns.add(n);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ns;
	}
	
	private void openConnection(){
		 try {
		  String url ="jdbc:mysql://localhost:3306/policias?serverTimezone=Europe/Madrid&useSSL=false";
		  conn =  DriverManager.getConnection(url,"root","abcd*1234");

		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		 }	
		}

	private void closeConnection() throws SQLException {
		System.out.println("Conexion Cerrada.");
		if (stmt != null)
			stmt.close();
		if (conn!= null)
			conn.close();
		System.out.println("------------------------");
	}

}
