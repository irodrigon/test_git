package application;

import controller.Controller;
import view.V_Principal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		V_Principal frame = new V_Principal(c);
		frame.setVisible(true);
	}

}