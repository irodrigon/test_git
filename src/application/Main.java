package application;

import controller.Controller;
import view.V1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		V1 frame = new V1(c);
		frame.setVisible(true);
	}

}