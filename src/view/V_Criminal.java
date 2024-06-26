package view;

import java.awt.EventQueue;
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
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.Arsenal;
import model.Busca;
import model.Criminal;
import model.Policeman;
import java.awt.Color;

public class V_Criminal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JButton btnBack;
	private JLabel lblNewLabel_1;
	private BufferedImage icon;
	private Image endImage;
	private ImageIcon img;
	private Criminal crim;
	private int id_user;
	private Policeman pol;
	private JLabel lblNewLabel_1_1;
	
	public V_Criminal(Controller l, int id_policia,int id_user) {
		setUndecorated(true);
		this.l = l;
		crim = l.returnSuspectById(id_policia);
		pol = l.showPoliceman(id_user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new RoundedBorder(20));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(289, 36, 474, 520);
		contentPane.add(lblNewLabel_1);

		Blob image = (Blob) crim.getFoto_criminal();
		InputStream is;
		try {
			is = image.getBinaryStream(1, image.length());
			icon = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// Retrieve Image
			File outputfile = new File("image.jpg");
			ImageIO.write(icon, "jpg", outputfile); // Write the Buffered Image into an output file
			endImage = ImageIO.read(new File("image.jpg")); // Opening again as an Image
		} catch (IOException e) {
			e.printStackTrace();
		}

		img = new ImageIcon(endImage);
		lblNewLabel_1.setIcon((Icon) img);
		

		btnBack = new JButton("Atrás");
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBounds(468, 903, 89, 27);
		contentPane.add(btnBack);
		
		JLabel lblDni = new JLabel("DNI: " + crim.getDni());
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDni.setBounds(147, 623, 445, 43);
		contentPane.add(lblDni);
		
		JLabel lblNombre_criminal = new JLabel("Nombre: " + crim.getNombre_criminal());
		lblNombre_criminal.setForeground(new Color(255, 255, 255));
		lblNombre_criminal.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNombre_criminal.setBounds(147, 677, 445, 43);
		contentPane.add(lblNombre_criminal);
		
		JLabel lblApellido_criminal = new JLabel("Apellido: " + crim.getApellido_criminal());
		lblApellido_criminal.setForeground(new Color(255, 255, 255));
		lblApellido_criminal.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblApellido_criminal.setBounds(147, 732, 445, 43);
		contentPane.add(lblApellido_criminal);
		
		JLabel lblDescripcion_criminal = new JLabel("Descripcion: " + crim.getDescripcion_criminal());
		lblDescripcion_criminal.setForeground(new Color(255, 255, 255));
		lblDescripcion_criminal.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblDescripcion_criminal.setBounds(147, 790, 445, 43);
		contentPane.add(lblDescripcion_criminal);
		
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);

		// btnNext.setEnabled(false);
		btnBack.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnBack) {
			id_user = pol.getId_user();
			V_Policemen vP = new V_Policemen(l, id_user);
			vP.setVisible(true);
			this.dispose();
		}
	}
}
