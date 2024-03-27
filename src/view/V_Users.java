package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

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

		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(261, 199, 246, 20);
		textFieldPassword.setEchoChar('*');
		contentPane.add(textFieldPassword);

		btnNoticias = new JButton("Noticias");
		btnNoticias.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnNoticias.setBounds(310, 384, 130, 26);
		contentPane.add(btnNoticias);

		lblWrong = new JLabel("", SwingConstants.CENTER);
		lblWrong.setFont(new Font("Teko SemiBold", Font.PLAIN, 30));
		lblWrong.setBounds(97, 672, 523, 63);
		contentPane.add(lblWrong);

		btnCreate = new JButton("Crear cuenta de usuario");
		btnCreate.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnCreate.setBounds(230, 528, 277, 26);
		contentPane.add(btnCreate);

		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		tglbtnSee.setBounds(552, 198, 121, 23);
		contentPane.add(tglbtnSee);

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
