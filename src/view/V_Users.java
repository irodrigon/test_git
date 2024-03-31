package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Toolkit;

public class V_Users extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JTextField textFieldUser;
	private JPasswordField textFieldPassword;
	private JButton btnEnter;
	private JButton btnQuit;
	private JButton btnNoticias;
	private JLabel lblWrong;
	private JButton btnCreate;
	private JToggleButton tglbtnSee;
	private int id_user;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public V_Users(Controller l) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(V_Users.class.getResource("/photos/pixelart.png")));
		this.l = l;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(620, 70, 800, 900);
		setFocusable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105,105,105));
		contentPane.setBorder(new RoundedBorder(5));


		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEnter = new JLabel("INTRODUCE TU USUARIO Y CONTRASEÑA:");
		lblEnter.setForeground(new Color(255, 255, 255));
		lblEnter.setBounds(215, 11, 358, 37);
		lblEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		contentPane.add(lblEnter);

		JLabel lblUser = new JLabel("USER:");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblUser.setBounds(120, 119, 80, 37);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblPassword.setBounds(120, 189, 105, 37);
		contentPane.add(lblPassword);

		btnEnter = new JButton("Entrar");
		btnEnter.setForeground(new Color(255, 255, 255));
		btnEnter.setBackground(new Color(116, 116, 116));
		btnEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnEnter.setBounds(47, 384, 130, 26);
		btnEnter.setBorder(new RoundedBorder(10));
		contentPane.add(btnEnter);

		btnQuit = new JButton("Salir");
		btnQuit.setForeground(new Color(255, 255, 255));
		btnQuit.setBackground(new Color(116, 116, 116));
		btnQuit.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnQuit.setBounds(637, 384, 130, 26);
		btnQuit.setBorder(new RoundedBorder(10));
		contentPane.add(btnQuit);

		textFieldUser = new JTextField();
		textFieldUser.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		textFieldUser.setBounds(261, 119, 246, 30);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(261, 189, 246, 30);
		textFieldPassword.setEchoChar('*');
		contentPane.add(textFieldPassword);

		btnNoticias = new JButton("Noticias");
		btnNoticias.setForeground(new Color(255, 255, 255));
		btnNoticias.setBackground(new Color(116, 116, 116));
		btnNoticias.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnNoticias.setBounds(337, 384, 130, 26);
		btnNoticias.setBorder(new RoundedBorder(10));
		contentPane.add(btnNoticias);

		lblWrong = new JLabel("", SwingConstants.CENTER);
		lblWrong.setForeground(new Color(255, 255, 255));
		lblWrong.setFont(new Font("Teko SemiBold", Font.PLAIN, 30));
		lblWrong.setBounds(97, 672, 523, 63);
		contentPane.add(lblWrong);

		btnCreate = new JButton("Crear cuenta de usuario");
		btnCreate.setForeground(new Color(255, 255, 255));
		btnCreate.setBackground(new Color(116, 116, 116));
		btnCreate.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnCreate.setBounds(293, 527, 195, 26);
		btnCreate.setBorder(new RoundedBorder(10));
		contentPane.add(btnCreate);

		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setForeground(new Color(255, 255, 255));
		tglbtnSee.setBackground(new Color(116, 116, 116));
		tglbtnSee.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		tglbtnSee.setBounds(517, 193, 50, 23);
		tglbtnSee.setBorder(new RoundedBorder(10));
		contentPane.add(tglbtnSee);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1.setBounds(-14, 0, 814, 900);
		lblNewLabel_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1);

		btnEnter.addActionListener(this);
		btnQuit.addActionListener(this);
		btnNoticias.addActionListener(this);
		btnCreate.addActionListener(this);
		tglbtnSee.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (tglbtnSee.isSelected()) {
					textFieldPassword.setEchoChar((char) 0);
				} else {
					textFieldPassword.setEchoChar('*');
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String username = textFieldUser.getText();
		String password = new String(textFieldPassword.getPassword());
		User u = l.logIn(username, password);
		if (o == btnNoticias) {
			V_Noticias vNews = new V_Noticias(l);
			vNews.setVisible(true);
			this.dispose();	
		}else if (o == btnQuit) {
			this.dispose();
		} else if (o == btnEnter) {
			if (u != null) {
				id_user = u.getUser_id();
				if(id_user == 1) {
					V_Admin vA = new V_Admin();
					vA.setVisible(true);
					this.dispose();
				}else {
					V_Policemen vP = new V_Policemen(l, id_user);
					vP.setVisible(true);
					this.dispose();
				}
			} else {
			lblWrong.setText("Usuario o contraseña incorrectos.");
			}
		}else if(o == btnCreate) {
			V_SignUp vS = new V_SignUp(l);
			vS.setVisible(true);
			this.dispose();
		}
		if (o == btnEnter && textFieldUser.getText().equals("")
				&& new String(textFieldPassword.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Los datos están vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
