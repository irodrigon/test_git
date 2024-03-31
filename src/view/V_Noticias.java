package view;


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
import model.News;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class V_Noticias extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnBack;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private ArrayList<News> ns;
	private ListIterator<News> it;
	private BufferedImage icon;
	private Image endImage;
	private ImageIcon img;
	private News n;
	private JLabel lblNewLabel;
	private JLabel lblAnterior;
	private JLabel lblAtrs;
	private JLabel lblSiguiente;

	/**
	 * Create the frame.
	 */
	public V_Noticias(Controller l) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(V_Noticias.class.getResource("/photos/pixelart.png")));
		this.l = l;
		setBackground(new Color(105,105,105));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105,105,105));
		contentPane.setBorder(new RoundedBorder(10));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		btnPrevious = new JButton("");
		btnPrevious.setBackground(new Color(138, 138, 138));
		btnPrevious.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));

		btnPrevious.setBounds(48, 831, 142, 23);
		btnPrevious.setBorder(new RoundedBorder(10));
		contentPane.add(btnPrevious);

		btnNext = new JButton("");
		btnNext.setBackground(new Color(138, 138, 138));
		btnNext.setBorder(new RoundedBorder(10));
		btnNext.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));

		btnNext.setBounds(807, 831, 134, 23);
		contentPane.add(btnNext);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(48, 65, 893, 610);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("", SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(235, 686, 550, 43);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(223, 725, 550, 57);
		contentPane.add(lblNewLabel_3);

		ns = this.l.showNews();
		it = ns.listIterator();
		n = it.next();
		
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
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
		lblNewLabel_1.setBorder(new RoundedBorder(2));
		btnPrevious.setEnabled(false);
		
		btnBack = new JButton("");
		btnBack.setBackground(new Color(138, 138, 138));
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBorder(new RoundedBorder(10));
		btnBack.setBounds(466, 831, 89, 23);
		contentPane.add(btnBack);
		
		lblNewLabel = new JLabel("NOTICIAS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNewLabel.setBounds(451, 11, 89, 43);
		contentPane.add(lblNewLabel);
		
		lblAnterior = new JLabel("ANTERIOR");
		lblAnterior.setForeground(Color.WHITE);
		lblAnterior.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblAnterior.setBounds(71, 865, 89, 43);
		contentPane.add(lblAnterior);
		
		lblAtrs = new JLabel("ATRÁS");
		lblAtrs.setForeground(Color.WHITE);
		lblAtrs.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblAtrs.setBounds(476, 865, 65, 43);
		contentPane.add(lblAtrs);
		
		lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblSiguiente.setBounds(827, 865, 94, 43);
		contentPane.add(lblSiguiente);
		// btnNext.setEnabled(false);
		btnBack.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(it.nextIndex() >= ns.size() && !it.hasNext()){
			btnNext.setEnabled(false);
		}else {
			btnNext.setEnabled(true);
		}
		
		if(it.nextIndex() == ns.size()) {
			it.previous();
		}
		
		if(it.previousIndex() == -1) {
			it.next();
		}
		
		if(it.previousIndex() == -1 && !it.hasPrevious()){
			btnNext.setEnabled(false);
		}else {
			btnNext.setEnabled(true);
		}
		
		Object o = e.getSource();
		if (o == btnNext) {
			if (it.hasNext()) {
				btnPrevious.setEnabled(true);
				n = it.next();
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
		
		if(o == btnBack){
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}

	}
}

