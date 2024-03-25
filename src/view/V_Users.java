package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class V_Users extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JButton btnEnter;
	private JButton btnQuit;
	private JButton btnNoticias;

	/**
	 * Create the frame.
	 */
	public V_Users(Controller l) {
		this.l = l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 20, 800, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnter = new JLabel("INTRODUCE TU USUARIO Y CONTRASEÑA");
		lblEnter.setBounds(276, 10, 231, 26);
		lblEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		contentPane.add(lblEnter);
		
		JLabel lblUser = new JLabel("USER:");
		lblUser.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblUser.setBounds(120, 132, 46, 14);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblPassword.setBounds(120, 202, 80, 14);
		contentPane.add(lblPassword);
		
		btnEnter = new JButton("Entrar");
		btnEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnEnter.setBounds(120, 384, 130, 26);
		contentPane.add(btnEnter);
		
		btnQuit = new JButton("Salir");
		btnQuit.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnQuit.setBounds(490, 384, 130, 26);
		contentPane.add(btnQuit);
		
		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		textFieldUser.setBounds(261, 129, 246, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(261, 199, 246, 20);
		contentPane.add(textFieldPassword);
		
		btnNoticias = new JButton("Noticias");
		btnNoticias.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnNoticias.setBounds(310, 384, 130, 26);
		contentPane.add(btnNoticias);
		
		btnEnter.addActionListener(this);
		btnQuit.addActionListener(this);
		btnNoticias.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == btnNoticias) {
			V_Noticias vNews = new V_Noticias(l);
			vNews.setVisible(true);
			this.dispose();
		}else if(o == btnQuit) {
			this.dispose();
		}else if(o == btnQuit && l.logIn(textFieldUser.getText(), textFieldPassword.getText())) {
			
		}
		
	}
}
