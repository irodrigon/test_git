package controller;

import model.News;
import model.Policeman;
import model.User;

import java.sql.Blob;
import java.util.ArrayList;

public interface InterfaceController {
	public ArrayList<News> showNews();
	public User logIn(String u, String p);
	public User returnMaxUser();
	public Policeman returnMaxPoliceman();
	public boolean insertPoliceman(int id_policia,Blob foto_policia, String dni,String nombre_policia,String apellido_policia, String rango, int id_user);
	public boolean insertUser(int id,String u, String p);
	public boolean deleteUser(int id);
	public boolean deletePoliceman(String dni);
	public boolean relocateUp(int id);
	public boolean relocateDown(int id);
	public boolean returnUserByName(String name);
	public User returnUserById(int id);
	public boolean updateUser(String u,String p,int id);
	public boolean updatePoliceman(Blob foto_policia,String n,String a,String r,int id_user);
}
