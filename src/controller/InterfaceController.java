package controller;

import model.News;
import java.util.ArrayList;

public interface InterfaceController {
	public ArrayList<News> showNews();
	public boolean logIn(String u, String p);
}
