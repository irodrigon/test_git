package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.News;

public class V1 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JButton btnPrevious;
	private JButton btnNext;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private ArrayList<News> ns;
	private ListIterator<News> it;
	private BufferedImage icon;
	private Image endImage;

	/**
	 * Create the frame.
	 */
	public V1(Controller l) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		btnPrevious = new JButton("Anterior");

		btnPrevious.setBounds(10, 118, 89, 23);
		contentPane.add(btnPrevious);

		btnNext = new JButton("Siguiente");

		btnNext.setBounds(345, 118, 89, 23);
		contentPane.add(btnNext);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(176, 21, 46, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(172, 46, 400, 400);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(176, 71, 46, 14);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(176, 96, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Username:");
		lblNewLabel_4.setBounds(87, 21, 75, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Password:");
		lblNewLabel_4_1.setBounds(87, 46, 75, 14);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Edad:");
		lblNewLabel_4_1_1.setBounds(87, 71, 75, 14);
		contentPane.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_2 = new JLabel("DNI:");
		lblNewLabel_4_1_2.setBounds(87, 96, 75, 14);
		contentPane.add(lblNewLabel_4_1_2);

		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);

		ns = l.showNews();

		lblNewLabel.setText(String.valueOf(ns.get(0).getId_new()));
		Blob image = (Blob) ns.get(0).getImageNews();
		InputStream is;
		try {
			is = image.getBinaryStream(1, image.length());			
			icon=ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
		    // Retrieve Image
		    File outputfile = new File("image.jpg");
		    ImageIO.write(icon, "jpg", outputfile); // Write the Buffered Image into an output file
		    endImage  = ImageIO.read(new File("image.jpg")); // Opening again as an Image
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		ImageIcon img = new ImageIcon(endImage);
		//lblNewLabel_2.setText(String.valueOf(ns.get(0).getEdad()));
		//lblNewLabel_3.setText(ns.get(0).getDni());
		// btnPrevious.setEnabled(false);
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		lblNewLabel_1.setIcon((Icon) img);
		it = ns.listIterator();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		News n = new News();
		Object o = e.getSource();

		if (o == btnNext) {
			if (it.hasNext()) {
				it.next();
				n = it.next();
				/*lblNewLabel.setText(user.getUsername());
				lblNewLabel_1.setText(user.getPassword());
				lblNewLabel_2.setText(String.valueOf(user.getEdad()));
				lblNewLabel_3.setText(user.getDni());*/
			}
		} else if (o == btnPrevious) {
			if (it.hasPrevious()) {
				it.previous();
				n = it.previous();
				/*lblNewLabel.setText(user.getUsername());
				lblNewLabel_1.setText(user.getPassword());
				lblNewLabel_2.setText(String.valueOf(user.getEdad()));
				lblNewLabel_3.setText(user.getDni());*/
			}
		}
	}

}
