package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemColor;

public class V_Principal extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller l;
	private JLabel lblPhotoEnter;
	private JButton btnEnter;
	private JButton btnQuit;
	private JLabel lblNewLabel;
	private JLabel lblEntrar;
	private JLabel lblSalir;
	private JLabel lblNewLabel_1;
	

	/**
	 * Create the frame.
	 */
	public V_Principal(Controller l) {
		setBackground(new Color(105,105,105));
		setUndecorated(true);
		setBounds(640,70,700,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setForeground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(V_Principal.class.getResource("/photos/pixelart.png")));
		this.l = l;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(122, 128, 133));
		contentPane.setBorder(new RoundedBorder(5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblPhotoEnter = new JLabel();
		lblPhotoEnter.setBackground(new Color(28, 63, 227));
		lblPhotoEnter.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/placapolicial.jpg")));
		lblPhotoEnter.setBounds(112, 211,474, 355);
		lblPhotoEnter.setBorder(new RoundedBorder(2));
		
		contentPane.add(lblPhotoEnter);
		
		btnEnter = new JButton("");
		btnEnter.setBackground(new Color(138, 138, 138));
		btnEnter.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnEnter.setBounds(167, 678, 89, 23);
		btnEnter.setBorder(new RoundedBorder(20));
		contentPane.add(btnEnter);
		btnEnter.addActionListener(this);
		
		btnQuit = new JButton("");
		btnQuit.setBackground(new Color(138, 138, 138));
		btnQuit.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnQuit.setBounds(442, 678, 96, 23);
		btnQuit.setBorder(new RoundedBorder(20));
		contentPane.add(btnQuit);
		
		lblNewLabel = new JLabel("BIENVENIDO, AGENTE.",SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblNewLabel.setBounds(112, 108, 474, 41);
		contentPane.add(lblNewLabel);
		
		lblEntrar = new JLabel("ENTRAR", SwingConstants.CENTER);
		lblEntrar.setForeground(Color.WHITE);
		lblEntrar.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblEntrar.setBounds(140, 640, 144, 41);
		contentPane.add(lblEntrar);
		
		lblSalir = new JLabel("SALIR", SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Teko SemiBold", Font.PLAIN, 25));
		lblSalir.setBounds(420, 640, 144, 41);
		contentPane.add(lblSalir);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1.setBounds(-17, 0, 717, 800);
		lblNewLabel_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1);
		
		btnQuit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == btnEnter) {
			V_Users vU = new V_Users(l);
			vU.setVisible(true);
			this.dispose();
		}else if (o == btnQuit) {
			this.dispose();
		}
	}
}
