package controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Arsenal;
import model.Busca;
import model.Criminal;
import model.News;
import model.Policeman;
import model.User;

public class Controller implements InterfaceController {

	private Connection conn;
	private PreparedStatement stmt;
	private final String SHOW_NEWS = "SELECT * FROM NOTICIA";
	private final String SHOW_SUSPECTS_BY_ID = "SELECT * FROM CRIMINAL WHERE id_policia = ?";
	private final String RETURN_MAX_USER = "SELECT * FROM USERS WHERE ID_USER = (SELECT MAX(ID_USER) FROM USERS)";
	private final String SELECT_USER = "SELECT * FROM users WHERE username=? AND password=?";
	private final String IDENTIFY_POLICEMAN = "SELECT * FROM POLICIA WHERE ID_user= ?";
	private final String INSERT_USER = "INSERT INTO USERS VALUES(?,?,?)";
	private final String INSERT_POLICEMAN = "INSERT INTO POLICIA VALUES(?,?,?,?,?,?,?)";
	private final String RETURN_MAX_POLICEMAN = "SELECT * FROM POLICIA WHERE ID_policia = (SELECT MAX(ID_policia) FROM policia)";
	private final String DELETE_POLICEMAN = "DELETE FROM POLICIA WHERE dni=?";
	private final String DELETE_USER = "DELETE FROM USERS WHERE ID_user=?";
	private final String RELOCATEUP = "UPDATE USERS SET ID_user = ID_user-1 where ID_user > ? AND NOT ID_user = 1";
	private final String RELOCATEDOWN = "UPDATE USERS SET ID_user = ID_user+1 where ID_user < ? AND NOT ID_user = 1";
	private final String RETURN_USER_BY_NAME = "SELECT * FROM USERS WHERE username = ?";
	private final String RETURN_USER_BY_ID = "SELECT * FROM USERS WHERE id_user = ?";
	private final String UPDATE_USER = "UPDATE USERS SET username = ?,password = ? WHERE ID_USER = ?";
	private final String UPDATE_POLICEMAN ="UPDATE POLICIA SET foto_policia=?,nombre_policia = ?,apellido_policia = ?,rango = ? WHERE id_user = ?";
	private final String SHOW_WEAPONS ="SELECT * FROM ARSENAL";
	private final String WEAPONS_ASSIGNED = "SELECT * FROM BUSCA WHERE ID_POLICIA = ?";
	private final String MORE_WEAPONS = "INSERT INTO BUSCA VALUES(?,?)";
	
	@Override
	public Policeman returnMaxPoliceman() {
		ResultSet rs = null;
		Policeman pol = null;

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = conn.prepareStatement(RETURN_MAX_POLICEMAN);

			rs = stmt.executeQuery();

			if (rs.next()) {
				pol = new Policeman();
				pol.setId_policia(rs.getInt("id_policia"));
				pol.setFoto_policia(rs.getBlob("foto_policia"));
				pol.setDni(rs.getString("dni"));
				pol.setNombre_policia(rs.getString("nombre_policia"));
				pol.setApellido_policia(rs.getString("apellido_policia"));
				pol.setRango(rs.getString("rango"));
				pol.setId_user(rs.getInt("id_user"));
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

		return pol;
	}

	@Override
	public User returnMaxUser() {
		ResultSet rs = null;
		User us = null;

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = conn.prepareStatement(RETURN_MAX_USER);

			rs = stmt.executeQuery();

			if (rs.next()) {
				us = new User();
				us.setUser_id(rs.getInt("ID_user"));
				us.setUsername(rs.getString("username"));
				us.setPassword(rs.getString("password"));
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

		return us;
	}

	@Override
	public boolean insertPoliceman(int id_policia, Blob foto_policia, String dni, String nombre_policia,
			String apellido_policia, String rango, int id_user) {

		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(INSERT_POLICEMAN);

			stmt.setInt(1, id_policia);
			stmt.setBlob(2, foto_policia);
			stmt.setString(3, dni);
			stmt.setString(4, nombre_policia);
			stmt.setString(5, apellido_policia);
			stmt.setString(6, rango);
			stmt.setInt(7, id_user);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean insertUser(int id, String u, String p) {

		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(INSERT_USER);

			stmt.setInt(1, id);
			stmt.setString(2, u);
			stmt.setString(3, p);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	public Policeman showPoliceman(int id_user) {
		ResultSet rs = null;
		Policeman pol = null;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(IDENTIFY_POLICEMAN);

			// Cargamos los parámetros
			stmt.setInt(1, id_user);

			rs = stmt.executeQuery();

			if (rs.next()) {
				pol = new Policeman();
				pol.setId_policia(rs.getInt("ID_policia"));
				pol.setFoto_policia(rs.getBlob("foto_policia"));
				pol.setDni(rs.getString("dni"));
				pol.setNombre_policia(rs.getString("nombre_policia"));
				pol.setApellido_policia(rs.getString("apellido_policia"));
				pol.setRango(rs.getString("rango"));
				pol.setId_user(rs.getInt("ID_user"));
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

		return pol;
	}

	@Override
	public User logIn(String u, String p) {
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
				us.setUser_id(rs.getInt("ID_user"));
				us.setUsername(u);
				us.setPassword(p);
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

		return us;
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

			while (rs.next()) {
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

	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/policias?serverTimezone=Europe/Madrid&useSSL=false";
			conn = DriverManager.getConnection(url, "root", "abcd*1234");

		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	private void closeConnection() throws SQLException {
		System.out.println("Conexion Cerrada.");
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
		System.out.println("------------------------");
	}

	@Override
	public boolean deleteUser(int id) {

		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(DELETE_USER);

			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean deletePoliceman(String dni) {
		// TODO Auto-generated method stub
		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(DELETE_POLICEMAN);

			stmt.setString(1, dni);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean relocateUp(int id) {
		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(RELOCATEUP);

			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean relocateDown(int id) {
		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(RELOCATEDOWN);

			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean returnUserByName(String name) {
		ResultSet rs = null;
		User user = null;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(RETURN_USER_BY_NAME);

			// Cargamos los parámetros
			stmt.setString(1, name);

			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt("id_user"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return true;
			} else {
				user = null;

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
	public User returnUserById(int id) {
		ResultSet rs = null;
		User user = null;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(RETURN_USER_BY_ID);

			// Cargamos los parámetros
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt("id_user"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
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

		return user;
	}

	@Override
	public boolean updateUser(String u, String p, int id) {
		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(UPDATE_USER);

			stmt.setString(1, u);
			stmt.setString(2, p);
			stmt.setInt(3, id);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public boolean updatePoliceman(Blob foto_policia,String n, String a, String r, int id_user) {
		boolean cambios = false;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(UPDATE_POLICEMAN);
			
			stmt.setBlob(1, foto_policia);
			stmt.setString(2, n);
			stmt.setString(3, a);
			stmt.setString(4, r);
			stmt.setInt(5, id_user);

			if (stmt.executeUpdate() == 1)
				cambios = true;

		} catch (SQLException e1) {
			System.out.println("Error de SQL");
			e1.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e1) {
				System.out.println("Error en el cierre de la BD");
				e1.printStackTrace();
			}
		}

		return cambios;
	}

	@Override
	public ArrayList<Arsenal> showWeapons() {
		ResultSet rs = null;
		Arsenal a = null;
		ArrayList<Arsenal> weaponsList = new ArrayList<Arsenal>();

		this.openConnection();
		try {
			stmt = conn.prepareStatement(SHOW_WEAPONS);

			rs = stmt.executeQuery();

			while (rs.next()) {
				a = new Arsenal();
				a.setId_arsenal(rs.getInt("id_arsenal"));
				a.setFoto_arsenal(rs.getBlob("foto_arsenal"));
				a.setNombre_arsenal(rs.getString("nombre_arsenal"));
				a.setDescripcion_arsenal(rs.getString("descripcion_arsenal"));
				a.setId_administrador(rs.getInt("id_administrador"));
				weaponsList.add(a);
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

		return weaponsList;
	}

	@Override
	public ArrayList<Busca> weaponsAssigned(int id_policia) {
		ResultSet rs = null;
		Busca busca = null;
		ArrayList<Busca> busquedas = new ArrayList<Busca>();

		this.openConnection();

		try {
			stmt = conn.prepareStatement(WEAPONS_ASSIGNED);

			// Cargamos los parámetros
			stmt.setInt(1, id_policia);

			rs = stmt.executeQuery();

			while(rs.next()) {
				busca = new Busca();
				busca.setId_policia(rs.getInt("id_Policia"));
				busca.setId_arsenal(rs.getInt("id_arsenal"));
				busquedas.add(busca);
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

		return busquedas;
		
	}

	@Override
	public boolean moreWeapons(int id_policia, int id_arsenal) {
			boolean cambios = false;

			this.openConnection();

			try {
				stmt = conn.prepareStatement(MORE_WEAPONS);

				stmt.setInt(1, id_policia);
				stmt.setInt(2, id_arsenal);

				if (stmt.executeUpdate() == 1)
					cambios = true;

			} catch (SQLException e1) {
				System.out.println("Error de SQL");
				e1.printStackTrace();
			} finally {

				try {
					this.closeConnection();
				} catch (SQLException e1) {
					System.out.println("Error en el cierre de la BD");
					e1.printStackTrace();
				}
			}

			return cambios;
	}

	@Override
	public Criminal returnSuspectById(int id_policia) {
		ResultSet rs = null;
		Criminal crim = null;

		this.openConnection();

		try {
			stmt = conn.prepareStatement(SHOW_SUSPECTS_BY_ID);

			// Cargamos los parámetros
			stmt.setInt(1, id_policia);

			rs = stmt.executeQuery();

			if (rs.next()) {
				crim = new Criminal();
				crim .setId_criminal(rs.getInt("id_criminal"));
				crim.setFoto_criminal(rs.getBlob("foto_criminal"));
				crim.setDni(rs.getString("dni"));
				crim.setNombre_criminal(rs.getString("nombre_criminal"));
				crim.setApellido_criminal(rs.getString("apellido_criminal"));
				crim.setDescripcion_criminal(rs.getString("descripcion_criminal"));
				crim.setId_administrador(rs.getInt("id_administrador"));
				crim.setId_policia(rs.getInt("id_policia"));
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

		return crim;
	}
	
	

}
