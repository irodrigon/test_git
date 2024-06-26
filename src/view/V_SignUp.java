package view;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.Policeman;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Toolkit;

public class V_SignUp extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JTextField textUserName;
	private JPasswordField passwordField;
	private JLabel lblPassword;
	private JLabel lblUserName;
	private JButton btnUpload;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private JLabel lblFiles;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblRango;
	private JTextField textFieldName;
	private JTextField textFieldLastName;
	private JComboBox<String> comboRango;
	private JButton btnCancelar;
	private JButton btnSave;
	private JLabel lblSaveChanges;
	private Controller l;
	private int id_user;
	private int id_pol;
	private JLabel lblDni;
	private JTextField textFieldDni;
	private File file;
	private JButton btnBack;
	private JToggleButton tglbtnSee;
	private JLabel lblNewLabel_1_1;
	
	public V_SignUp(Controller l) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(V_SignUp.class.getResource("/photos/pixelart.png")));
		setUndecorated(true);
		this.l = l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("CREAR USUARIO",SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblTitulo.setBounds(97, 24, 820, 36);
		contentPane.add(lblTitulo);
		
		lblUserName = new JLabel("Username:");
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblUserName.setBounds(204, 99, 104, 36);
		contentPane.add(lblUserName);
		
		textUserName = new JTextField();
		textUserName.setBounds(318, 99, 316, 28);
		contentPane.add(textUserName);
		textUserName.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblPassword.setBounds(204, 146, 104, 36);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(318, 147, 316, 28);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		btnUpload = new JButton("Subir Foto");
		btnUpload.setForeground(new Color(255, 255, 255));
		btnUpload.setBackground(new Color(116, 116, 116));
		btnUpload.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnUpload.setBounds(318, 416, 316, 23);
		contentPane.add(btnUpload);
		
		lblFiles = new JLabel("");
		lblFiles.setForeground(new Color(255, 255, 255));
		lblFiles.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblFiles.setBounds(204, 475, 770, 28);
		contentPane.add(lblFiles);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNombre.setBounds(204, 242, 104, 36);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblApellido.setBounds(204, 289, 104, 36);
		contentPane.add(lblApellido);
		
		lblRango = new JLabel("Rango:");
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblRango.setBounds(204, 334, 104, 36);
		contentPane.add(lblRango);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(318, 248, 316, 28);
		contentPane.add(textFieldName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(318, 295, 316, 28);
		contentPane.add(textFieldLastName);
		
		String[] rangoStrings = { "-", "Cabo", "Sargento", "Teniente", "Capitán", "Comandante" };
		comboRango = new JComboBox<String>(rangoStrings);
		comboRango.setBounds(318, 335, 316, 28);
		contentPane.add(comboRango);
		
		btnSave = new JButton("Guardar Cambios");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(116, 116, 116));
		btnSave.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnSave.setBounds(204, 559, 132, 23);
		contentPane.add(btnSave);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(116, 116, 116));
		btnCancelar.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnCancelar.setBounds(574, 559, 132, 23);
		contentPane.add(btnCancelar);
		
		lblSaveChanges = new JLabel("",SwingConstants.CENTER);
		lblSaveChanges.setForeground(new Color(255, 255, 255));
		lblSaveChanges.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblSaveChanges.setBounds(296, 631, 316, 29);
		contentPane.add(lblSaveChanges);
		
		lblDni = new JLabel("DNI:");
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDni.setBounds(204, 195, 104, 36);
		contentPane.add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(318, 201, 316, 28);
		contentPane.add(textFieldDni);
		
		btnBack = new JButton("Volver");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBounds(390, 559, 132, 23);
		btnBack.setEnabled(false);
		contentPane.add(btnBack);
		
		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setForeground(new Color(255, 255, 255));
		tglbtnSee.setBackground(new Color(116, 116, 116));
		tglbtnSee.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		tglbtnSee.setBounds(647, 146, 59, 23);
		contentPane.add(tglbtnSee);
		
		JLabel lblAviso = new JLabel("Las fotos deberán tener una resolución de 474x711, si no, no se mostrarán bien.");
		lblAviso.setForeground(new Color(255, 255, 255));
		lblAviso.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblAviso.setBounds(234, 387, 509, 18);
		contentPane.add(lblAviso);
		
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);
		
		btnUpload.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnSave.addActionListener(this);
		btnBack.addActionListener(this);
		comboRango.addActionListener(this);
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
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		User us = new User();
		Policeman pol = new Policeman();
		if (o == btnCancelar) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
			}else if(o == btnBack) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}else if(o == btnSave && textUserName.getText().equals("")
				||new String(passwordField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce al menos los datos de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(o==btnSave && textFieldDni.getText().equals("") || textFieldName.getText().equals("")
				|| textFieldLastName.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Introduce los datos de perfil.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(o == btnSave && comboRango.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(this, "Elige un rango disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(o == btnUpload) {
		
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			filtro = new FileNameExtensionFilter("Imágenes jpg","jpg");
			fileChooser.addChoosableFileFilter(filtro);
			int opcion = fileChooser.showOpenDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION){
				// si ha pulsado Aceptar
				file = fileChooser.getSelectedFile();
				lblFiles.setText("Ha elegido el archivo "+fileChooser.getSelectedFile());
				}
				else if (opcion == JFileChooser.CANCEL_OPTION){
				// si ha pulsado Cancelar
				lblFiles.setText("Ha pulsado Cancelar");
				}
				else if (opcion == JFileChooser.ERROR_OPTION){
				// si ha producido un Error
				lblFiles.setText("Se ha producido un Error.");
				}
		}else if(o == btnSave && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(o == btnSave && l.returnUserByName(textUserName.getText())){ 
				lblSaveChanges.setText("Este perfil ya existe, por favor crea otro perfil.");
		}else if(o == btnSave) {
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar su perfil?");
			if(option == JOptionPane.YES_OPTION) {
				us = l.returnMaxUser();
				l.insertUser(us.getUser_id()+1,textUserName.getText(),new String(passwordField.getPassword()));
				us = l.returnMaxUser();
				id_user = us.getUser_id();
				id_user = id_user ++;
				FileInputStream is = null;
				try {
					is = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Blob blob = null;
				try {
					blob = new Blob(is.readAllBytes(), null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pol = l.returnMaxPoliceman();
				id_pol = pol.getId_policia();
				id_pol = id_pol+1;
				l.insertPoliceman(id_pol,blob, textFieldDni.getText(), textFieldName.getText(), textFieldLastName.getText(), (String) comboRango.getSelectedItem(), id_user);
				lblSaveChanges.setText("Cambios guardados.");
				btnBack.setEnabled(true);
			}else if(option == JOptionPane.NO_OPTION) {
				
			}else if(option == JOptionPane.CANCEL_OPTION) {
				
			}else if(option == JOptionPane.CLOSED_OPTION) {
				
			}
			
		}
		
	}
}
