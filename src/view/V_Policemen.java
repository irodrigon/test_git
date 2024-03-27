package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class V_Policemen extends JFrame implements ActionListener{

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
	private String user_name;

	/**
	 * Create the frame.
	 */
	public V_Policemen(Controller l,int id_user) {
		this.l = l;
		pol = l.showPoliceman(id_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnModifyData = new JButton("Modificar mis datos");
		btnModifyData.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnModifyData.setBounds(10, 816, 228, 23);
		contentPane.add(btnModifyData);
		
		btnChooseWeapons = new JButton("Elegir arsenal");
		btnChooseWeapons.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnChooseWeapons.setBounds(753, 816, 228, 23);
		contentPane.add(btnChooseWeapons);
		
		JLabel lblFoto_policia = new JLabel("");
		lblFoto_policia.setBounds(289, 36, 474,520);
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
		lblNombre_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblNombre_policia.setBounds(32, 629, 359, 43);
		contentPane.add(lblNombre_policia);
		
		btnBack = new JButton("Atrás");
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBounds(453, 927, 89, 23);
		contentPane.add(btnBack);
		
		btnShowSuspects = new JButton("Ver criminales asignados");
		btnShowSuspects.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnShowSuspects.setBounds(259, 816, 228, 23);
		contentPane.add(btnShowSuspects);
		
		JLabel lblApellido_policia = new JLabel("<dynamic>");
		lblApellido_policia.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblApellido_policia.setBounds(32, 666, 359, 43);
		contentPane.add(lblApellido_policia);
		
		JLabel lblRango = new JLabel("<dynamic>");
		lblRango.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblRango.setBounds(32, 700, 359, 43);
		contentPane.add(lblRango);
		lblNombre_policia.setText("Nombre: " + pol.getNombre_policia());
		lblApellido_policia.setText("Apellido: " + pol.getApellido_policia());
		lblRango.setText("Rango: " + pol.getRango());
		
		JLabel lblBienvenida = new JLabel("",SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblBienvenida.setBounds(77, 0, 848, 35);
		contentPane.add(lblBienvenida);
		lblBienvenida.setText("Bienvenido " + pol.getNombre_policia());
		
		btnShowWeapons = new JButton("Ver arsenal");
		btnShowWeapons.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnShowWeapons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowWeapons.setBounds(508, 816, 228, 23);
		contentPane.add(btnShowWeapons);
		
		JLabel lblDni = new JLabel("");
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblDni.setBounds(32, 587, 359, 43);
		contentPane.add(lblDni);
		lblDni.setText("DNI: " + pol.getDni());
		
		btnDeleteData = new JButton("Eliminar mi perfil");
		btnDeleteData.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnDeleteData.setBounds(382, 860, 228, 23);
		contentPane.add(btnDeleteData);
		
		lblChanges = new JLabel("",SwingConstants.CENTER);
		lblChanges.setBounds(163, 754, 600, 22);
		contentPane.add(lblChanges);
		
		
		btnBack.addActionListener(this);
		btnDeleteData.addActionListener(this);
		btnModifyData.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == btnBack) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}else if(o == btnDeleteData ) {
			int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar su perfil?");
			if(option == JOptionPane.YES_OPTION) {
				id_user = pol.getId_user();
				l.deletePoliceman(pol.getDni());
				l.deleteUser(id_user);
				if(id_user > 1) {
					l.relocateUp(id_user);
				}else {
					l.relocateDown(id_user);
				}
				lblChanges.setText("Usuario eliminado correctamente. No podrá volver a utilizar su perfil.");
				btnChooseWeapons.setEnabled(false);
				btnShowSuspects.setEnabled(false);
				btnShowWeapons.setEnabled(false);
				btnDeleteData.setEnabled(false);
				btnModifyData.setEnabled(false);
			}else if(option == JOptionPane.NO_OPTION) {
				
			}else if(option == JOptionPane.CANCEL_OPTION) {
				
			}else if(option == JOptionPane.CLOSED_OPTION) {
				
			}
		}else if(o == btnModifyData) {
			id_user = pol.getId_user();
			V_ModifyPolicemanUser vMpU = new V_ModifyPolicemanUser(l,id_user);
			vMpU.setVisible(true);
			this.dispose();
		}
		
	}
}
