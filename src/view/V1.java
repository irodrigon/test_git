package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Blob;

import controller.Controller;
import model.News;
import java.awt.Font;

public class V1 extends JFrame implements ActionListener {

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
	private ImageIcon img;
	private News n;
	private int clicks;

	/**
	 * Create the frame.
	 */
	public V1(Controller l) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		btnPrevious = new JButton("Anterior");

		btnPrevious.setBounds(48, 897, 89, 23);
		contentPane.add(btnPrevious);

		btnNext = new JButton("Siguiente");

		btnNext.setBounds(852, 897, 89, 23);
		contentPane.add(btnNext);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(235, 750, 46, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(48, 11, 893, 649);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("", SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(235, 775, 550, 43);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(235, 843, 550, 57);
		contentPane.add(lblNewLabel_3);

		ns = l.showNews();
		it = ns.listIterator();
		n = it.next();
		
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);

		lblNewLabel.setText(String.valueOf(n.getID_administrador()));
		Blob image = (Blob) n.getImageNews();
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
		lblNewLabel_2.setText(String.valueOf(n.getTitulo()));
		lblNewLabel_3.setText(n.getDescripcion_noticia());
		// btnPrevious.setEnabled(false);
		// btnNext.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(it.nextIndex() == ns.size()-1){
			btnNext.setEnabled(false);
		}else {
			btnNext.setEnabled(true);
		}
		
		if(it.previousIndex() == 0) {
			btnPrevious.setEnabled(false);
		}else {
			btnPrevious.setEnabled(true);
		}
		Object o = e.getSource();
		if (o == btnNext) {
			if (it.hasNext()) {
				n = it.next();
				lblNewLabel.setText(String.valueOf(n.getId_new()));
				Blob image = (Blob) n.getImageNews();
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
				lblNewLabel_1.setIcon((Icon) img);
				lblNewLabel_2.setText(n.getTitulo());
				lblNewLabel_3.setText(n.getDescripcion_noticia());
			}
		} else if (o == btnPrevious) {
			if (it.hasPrevious()) {
				n = it.previous();
				lblNewLabel.setText(String.valueOf(n.getId_new()));
				Blob image = (Blob) n.getImageNews();
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
				lblNewLabel_1.setIcon((Icon) img);
				lblNewLabel_2.setText(n.getTitulo());
				lblNewLabel_3.setText(n.getDescripcion_noticia());
			}
		}

	}

}
