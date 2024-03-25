package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import java.awt.Font;

public class V_Principal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JLabel lblPhotoEnter;
	private JButton btnEnter;
	private JButton btnQuit;

	/**
	 * Create the frame.
	 */
	public V_Principal(Controller l) {
		this.l = l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 20, 800, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblPhotoEnter = new JLabel();
		lblPhotoEnter.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/placapolicia.jpg")));
		lblPhotoEnter.setBounds(-120, 10,1024, 576);
		contentPane.add(lblPhotoEnter);
		
		btnEnter = new JButton("Entrar");
		btnEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnEnter.setBounds(215, 725, 89, 23);
		contentPane.add(btnEnter);
		btnEnter.addActionListener(this);
		
		btnQuit = new JButton("Salir");
		btnQuit.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnQuit.setBounds(449, 725, 96, 23);
		contentPane.add(btnQuit);
		btnQuit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == btnEnter) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}else if (o == btnQuit) {
			this.dispose();
		}
	}

}
