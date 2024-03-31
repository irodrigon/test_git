package view;

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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;

public class V_ModifyPolicemanUser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private Policeman p;
	private User u;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JToggleButton tglbtnSee;
	private JButton btnBack;
	private JComboBox<String> comboRango;
	private JButton btnUpload;
	private JButton btnSave;
	private JButton btnCancelar;
	private JLabel lblDni;
	private JLabel lblNombre_policia;
	private JLabel lblApellido_policia;
	private JLabel lblRango_1;
	private JLabel lblDataUserName;
	private JFileChooser fileChooser;
	private FileFilter filtro;
	private File file;
	private JLabel lblFiles;
	private JLabel lblSaveChanges;

	public V_ModifyPolicemanUser(Controller l, int id_user) {
		this.l = l;
		p = l.showPoliceman(id_user);
		u = l.returnUserById(id_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 984, 961);
		contentPane.add(contentPane_1);

		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblUserName.setBounds(204, 502, 104, 36);
		contentPane_1.add(lblUserName);

		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(318, 510, 316, 20);
		contentPane_1.add(textFieldUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblPassword.setBounds(204, 549, 104, 36);
		contentPane_1.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(318, 557, 316, 20);
		contentPane_1.add(passwordField);

		btnUpload = new JButton("Subir Foto");
		btnUpload.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnUpload.setBounds(318, 777, 316, 23);
		contentPane_1.add(btnUpload);

		lblFiles = new JLabel("");
		lblFiles.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblFiles.setBounds(72, 811, 770, 18);
		contentPane_1.add(lblFiles);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblNombre.setBounds(204, 596, 104, 36);
		contentPane_1.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblApellido.setBounds(204, 643, 104, 36);
		contentPane_1.add(lblApellido);

		JLabel lblRango = new JLabel("Rango:");
		lblRango.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblRango.setBounds(204, 690, 104, 36);
		contentPane_1.add(lblRango);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(318, 604, 316, 20);
		contentPane_1.add(textFieldNombre);

		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(318, 651, 316, 20);
		contentPane_1.add(textFieldApellido);

		btnSave = new JButton("Guardar Cambios");
		btnSave.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnSave.setBounds(204, 854, 132, 23);
		contentPane_1.add(btnSave);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnCancelar.setBounds(591, 854, 132, 23);
		contentPane_1.add(btnCancelar);

		lblSaveChanges = new JLabel("", SwingConstants.CENTER);
		lblSaveChanges.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblSaveChanges.setBounds(318, 921, 316, 29);
		contentPane_1.add(lblSaveChanges);

		btnBack = new JButton("Volver");
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setEnabled(false);
		btnBack.setBounds(393, 854, 132, 23);
		contentPane_1.add(btnBack);

		tglbtnSee = new JToggleButton("Ver");
		tglbtnSee.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		tglbtnSee.setBounds(659, 556, 64, 23);
		contentPane_1.add(tglbtnSee);

		JLabel lblAviso = new JLabel("Las fotos deberán tener una resolución de 474x711, si no, no se mostrarán bien.");
		lblAviso.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblAviso.setBounds(231, 748, 509, 18);
		contentPane_1.add(lblAviso);

		String[] rangoStrings = { "-", "Cabo", "Sargento", "Teniente", "Capitán", "Comandante" };
		comboRango = new JComboBox<String>(rangoStrings);
		comboRango.setBounds(318, 697, 316, 22);
		contentPane_1.add(comboRango);

		JLabel lblTitle = new JLabel("Modifica tus datos " + p.getNombre_policia(), SwingConstants.CENTER);
		lblTitle.setBounds(318, 11, 286, 35);
		contentPane_1.add(lblTitle);
		lblTitle.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));

		lblDni = new JLabel("DNI: " + p.getDni());
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblDni.setBounds(204, 238, 445, 43);
		contentPane_1.add(lblDni);

		lblNombre_policia = new JLabel("Nombre: " + p.getNombre_policia());
		lblNombre_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblNombre_policia.setBounds(204, 292, 445, 43);
		contentPane_1.add(lblNombre_policia);

		lblApellido_policia = new JLabel("Apellido: " + p.getApellido_policia());
		lblApellido_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblApellido_policia.setBounds(204, 347, 445, 43);
		contentPane_1.add(lblApellido_policia);

		lblRango_1 = new JLabel("Rango: " + p.getRango());
		lblRango_1.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblRango_1.setBounds(204, 405, 445, 43);
		contentPane_1.add(lblRango_1);

		lblDataUserName = new JLabel("Username: " + u.getUsername());
		lblDataUserName.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblDataUserName.setBounds(204, 192, 445, 43);
		contentPane_1.add(lblDataUserName);

		JLabel lblDatosActuales = new JLabel("Datos Actuales:", SwingConstants.CENTER);
		lblDatosActuales.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDatosActuales.setBounds(318, 109, 286, 35);
		contentPane_1.add(lblDatosActuales);

		JLabel lblDatosNuevos = new JLabel("Datos Nuevos:", SwingConstants.CENTER);
		lblDatosNuevos.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDatosNuevos.setBounds(318, 448, 286, 35);
		contentPane_1.add(lblDatosNuevos);

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

		if(o == btnBack) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}else if (o == btnCancelar) {
			V_Policemen vP = new V_Policemen(l, p.getId_user());
			vP.setVisible(true);
			this.dispose();
		} else if (o == btnSave && textFieldUserName.getText().equals("")
				|| new String(passwordField.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce al menos los datos de usuario.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave && textFieldNombre.getText().equals("") || textFieldApellido.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Introduce los datos de perfil.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave && comboRango.getSelectedItem().equals("-")) {
			JOptionPane.showMessageDialog(this, "Elige un rango disponible.", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (o == btnUpload) {

			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			filtro = new FileNameExtensionFilter("Imágenes jpg", "jpg");
			fileChooser.addChoosableFileFilter(filtro);
			int opcion = fileChooser.showOpenDialog(this);
			if (opcion == JFileChooser.APPROVE_OPTION) {
				// si ha pulsado Aceptar
				file = fileChooser.getSelectedFile();
				lblFiles.setText("Ha elegido el archivo " + fileChooser.getSelectedFile());
			} else if (opcion == JFileChooser.CANCEL_OPTION) {
				// si ha pulsado Cancelar
				lblFiles.setText("Ha pulsado Cancelar");
			} else if (opcion == JFileChooser.ERROR_OPTION) {
				// si ha producido un Error
				lblFiles.setText("Se ha producido un Error.");
			}
		} else if (o == btnSave && lblFiles.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un fotografía.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (o == btnSave && l.returnUserByName(textFieldUserName.getText())) {
			lblSaveChanges.setText("Este perfil ya existe, por favor crea otro perfil.");
		} else if (o == btnSave) {
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
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea modificar su perfil?");
			if(option == JOptionPane.YES_OPTION) {
				l.updateUser(textFieldUserName.getText(), new String(passwordField.getPassword()), u.getUser_id());
				l.updatePoliceman(blob, textFieldNombre.getText(), textFieldApellido.getText(),
					(String) comboRango.getSelectedItem(), p.getId_user());
				lblSaveChanges.setText("Modificación correcta.");
				btnBack.setEnabled(true);
				btnCancelar.setEnabled(false);
			}
		}
	}

}
