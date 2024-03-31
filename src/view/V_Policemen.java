package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.Arsenal;
import model.Busca;
import model.Policeman;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Toolkit;

public class V_Policemen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private Policeman pol;
	private BufferedImage icon;
	private Image endImage;
	private ImageIcon img;
	private JButton btnBack;
	private JButton btnDeleteData;
	private JLabel lblChanges;
	private int id_user;
	private JButton btnModifyData;
	private JButton btnChooseWeapons;
	private JButton btnShowSuspects;
	private JButton btnShowWeapons;
	private ArrayList<Arsenal> weaponsList;
	private ArrayList<Arsenal> weaponsAvailable;
	private ArrayList<Busca> busquedas;
	private int count;
	private int id_policia;
	private JLabel lblNewLabel_1_1;

	/**
	 * Create the frame.
	 */
	public V_Policemen(Controller l, int id_user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(V_Policemen.class.getResource("/photos/pixelart.png")));
		setUndecorated(true);
		this.l = l;
		pol = l.showPoliceman(id_user);
		busquedas = l.weaponsAssigned(pol.getId_policia());
		weaponsList = l.showWeapons();
		weaponsAvailable = new ArrayList<Arsenal>();
		for (Arsenal a : weaponsList) {
			for (Busca b : busquedas) {
				if (a.getId_arsenal() == b.getId_arsenal()) {
					count++;
					weaponsAvailable.add(a);
				}
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnModifyData = new JButton("Modificar mis datos");
		btnModifyData.setForeground(new Color(255, 255, 255));
		btnModifyData.setBackground(new Color(116, 116, 116));
		btnModifyData.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnModifyData.setBounds(10, 816, 228, 23);
		contentPane.add(btnModifyData);

		btnChooseWeapons = new JButton("Elegir arsenal");
		btnChooseWeapons.setForeground(new Color(255, 255, 255));
		btnChooseWeapons.setBackground(new Color(116, 116, 116));
		btnChooseWeapons.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnChooseWeapons.setBounds(753, 816, 228, 23);
		contentPane.add(btnChooseWeapons);

		JLabel lblFoto_policia = new JLabel("");
		lblFoto_policia.setBounds(289, 36, 474, 520);
		contentPane.add(lblFoto_policia);

		Blob image = (Blob) pol.getFoto_policia();
		InputStream is;
		try {
			is = image.getBinaryStream(1, image.length());
			icon = ImageIO.read(is);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		try {
			// Retrieve Image
			File outputfile = new File("image.jpg");
			ImageIO.write(icon, "jpg", outputfile); // Write the Buffered Image into an output file
			endImage = ImageIO.read(new File("image.jpg")); // Opening again as an Image
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		img = new ImageIcon(endImage);
		lblFoto_policia.setIcon((Icon) img);

		JLabel lblNombre_policia = new JLabel("<dynamic>");
		lblNombre_policia.setForeground(new Color(255, 255, 255));
		lblNombre_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNombre_policia.setBounds(32, 629, 359, 43);
		contentPane.add(lblNombre_policia);

		btnBack = new JButton("Atrás");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBounds(453, 927, 89, 23);
		contentPane.add(btnBack);

		btnShowSuspects = new JButton("Ver criminales asignados");
		btnShowSuspects.setForeground(new Color(255, 255, 255));
		btnShowSuspects.setBackground(new Color(116, 116, 116));
		btnShowSuspects.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnShowSuspects.setBounds(259, 816, 228, 23);
		contentPane.add(btnShowSuspects);

		JLabel lblApellido_policia = new JLabel("<dynamic>");
		lblApellido_policia.setForeground(new Color(255, 255, 255));
		lblApellido_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblApellido_policia.setBounds(32, 666, 359, 43);
		contentPane.add(lblApellido_policia);

		JLabel lblRango = new JLabel("<dynamic>");
		lblRango.setForeground(new Color(255, 255, 255));
		lblRango.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblRango.setBounds(32, 700, 359, 43);
		contentPane.add(lblRango);
		lblNombre_policia.setText("Nombre: " + pol.getNombre_policia());
		lblApellido_policia.setText("Apellido: " + pol.getApellido_policia());
		lblRango.setText("Rango: " + pol.getRango());

		JLabel lblBienvenida = new JLabel("", SwingConstants.CENTER);
		lblBienvenida.setForeground(new Color(255, 255, 255));
		lblBienvenida.setFont(new Font("Teko SemiBold", Font.PLAIN, 30));
		lblBienvenida.setBounds(77, 0, 848, 35);
		contentPane.add(lblBienvenida);
		lblBienvenida.setText("Bienvenido " + pol.getNombre_policia());

		btnShowWeapons = new JButton("Ver mi arsenal");
		btnShowWeapons.setForeground(new Color(255, 255, 255));
		btnShowWeapons.setBackground(new Color(116, 116, 116));
		btnShowWeapons.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnShowWeapons.setBounds(508, 816, 228, 23);
		contentPane.add(btnShowWeapons);

		JLabel lblDni = new JLabel("");
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDni.setBounds(32, 587, 359, 43);
		contentPane.add(lblDni);
		lblDni.setText("DNI: " + pol.getDni());

		btnDeleteData = new JButton("Eliminar mi perfil");
		btnDeleteData.setForeground(new Color(255, 255, 255));
		btnDeleteData.setBackground(new Color(116, 116, 116));
		btnDeleteData.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnDeleteData.setBounds(382, 860, 228, 23);
		contentPane.add(btnDeleteData);

		lblChanges = new JLabel("", SwingConstants.CENTER);
		lblChanges.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblChanges.setForeground(new Color(255, 255, 255));
		lblChanges.setBounds(163, 741, 706, 35);
		contentPane.add(lblChanges);
		
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);

		btnBack.addActionListener(this);
		btnDeleteData.addActionListener(this);
		btnModifyData.addActionListener(this);
		btnShowWeapons.addActionListener(this);
		btnChooseWeapons.addActionListener(this);
		btnShowSuspects.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == btnBack) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		} else if (o == btnDeleteData) {
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar su perfil?");
			if (option == JOptionPane.YES_OPTION) {
				id_user = pol.getId_user();
				l.deletePoliceman(pol.getDni());
				l.deleteUser(id_user);
				if (id_user > 1) {
					l.relocateUp(id_user);
				} else {
					l.relocateDown(id_user);
				}
				lblChanges.setText("Usuario eliminado correctamente. No podrá volver a utilizar su perfil.");
				btnChooseWeapons.setEnabled(false);
				btnShowSuspects.setEnabled(false);
				btnShowWeapons.setEnabled(false);
				btnDeleteData.setEnabled(false);
				btnModifyData.setEnabled(false);
			} else if (option == JOptionPane.NO_OPTION) {

			} else if (option == JOptionPane.CANCEL_OPTION) {

			} else if (option == JOptionPane.CLOSED_OPTION) {

			}
		} else if (o == btnModifyData) {
			id_user = pol.getId_user();
			V_ModifyPolicemanUser vMpU = new V_ModifyPolicemanUser(l, id_user);
			vMpU.setVisible(true);
			this.dispose();
		} else if (o == btnShowWeapons) {
			id_user = pol.getId_user();
			V_ViewWeapons vVW = new V_ViewWeapons(l, id_user);
			vVW.setVisible(true);
			this.dispose();
		} else if (o == btnChooseWeapons) {
			if (count == 3) {
				JOptionPane.showMessageDialog(this,
						"Ya tiene tres artículos. No puede elegir más. El botón no estará disponible. Hable con su superior.",
						"Error.", JOptionPane.ERROR_MESSAGE);
				btnChooseWeapons.setEnabled(false);
			} else {
				id_user = pol.getId_user();
				V_ChooseWeapons VcW = new V_ChooseWeapons(l, id_user, count);
				VcW.setVisible(true);
				this.dispose();
			}
		}else if(o == btnShowSuspects) {
			id_user = pol.getId_user();
			id_policia = pol.getId_policia();
			V_Criminal vC = new V_Criminal(l, id_policia,id_user);
			vC.setVisible(true);
			this.dispose();
		}

	}
}
