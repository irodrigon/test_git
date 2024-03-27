package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Policeman;
import model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

public class V_ModifyPolicemanUser extends JFrame {

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
	private JComboBox comboRango;
	private JButton btnUpload;
	private JButton btnSave;
	private JButton btnCancelar;
	private JLabel lblDni;
	private JLabel lblNombre_policia;
	private JLabel lblApellido_policia;
	private JLabel lblRango_1;
	private JLabel lblDataUserName;
	
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
		
		JLabel lblFiles = new JLabel("");
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
		
		JLabel lblSaveChanges = new JLabel("", SwingConstants.CENTER);
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
		
		JLabel lblTitle = new JLabel("Modifica tus datos " + p.getNombre_policia(),SwingConstants.CENTER);
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
		
		lblDataUserName = new JLabel("Username: "+ u.getUsername());
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
	}
}
