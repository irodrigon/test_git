package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.w3c.dom.DOMImplementationSource;

import controller.Controller;
import model.Arsenal;
import model.Busca;
import model.Policeman;

import javax.swing.JTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class V_ChooseWeapons extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Controller l;
	private Policeman pol;
	private int id_user;
	private ArrayList<Arsenal> weapons;
	private JScrollPane scroll;
	private int count;
	private DefaultTableModel model;
	private JButton btnConfirmar;
	private JButton btnBack;
	private JButton btnCancel;
	private JCheckBox chb;
	private ArrayList<Busca> busquedas;
	private ArrayList<Arsenal> weaponsAvailable;
	private JLabel lblCambios;
	private int guarda;
	private JLabel lblNewLabel_1_1;

	public V_ChooseWeapons(Controller l, int id_user,int count) {
		setUndecorated(true);
		this.l = l;
		pol = l.showPoliceman(id_user);
		this.count = count;
		guarda = this.count;
		weapons = l.showWeapons();
		busquedas = l.weaponsAssigned(pol.getId_policia());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 20, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] columnNames = { "Nombre:", "Descripcion:", "Elegir"};
		model = new DefaultTableModel(null, columnNames) {

			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int c) {
				switch (c) {
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		
		weaponsAvailable = new ArrayList<Arsenal>();
		for (Arsenal a : weapons) {
			for (Busca b : busquedas) {
				if (a.getId_arsenal() != b.getId_arsenal()) {
					weaponsAvailable.add(a);
				}
			}
		}
		
		for (int i = 0; i < weaponsAvailable.size(); i++) {
			String nombre = weaponsAvailable.get(i).getNombre_arsenal();
			String descripcion = weaponsAvailable.get(i).getDescripcion_arsenal();
			Object[] data = { nombre, descripcion, false};
			model.addRow(data);
		}

		table = new JTable(model);
		table.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table.setBackground(new Color(116,116,116));
		table.setForeground(new Color(255,255,255));
		table.setBounds(10, 169, 964, 557);
		table.getTableHeader().setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		table.getTableHeader().setBackground(new Color(116,116,116));
		table.getTableHeader().setForeground(new Color(255,255,255));
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 169, 964, 557);
		contentPane.add(scroll);
		chb = new JCheckBox();
		chb.addActionListener(this);
		
		DefaultCellEditor editor = new DefaultCellEditor(chb) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				Component tableCellEditorComponent = super.getTableCellEditorComponent(table, value, isSelected, row,
						column);
				((JCheckBox) tableCellEditorComponent).setHorizontalAlignment(JCheckBox.CENTER);
				return tableCellEditorComponent;
			}
		};
		table.getColumnModel().getColumn(2).setCellEditor(editor);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(new Color(116, 116, 116));
		btnConfirmar.setBounds(130, 777, 104, 23);
		contentPane.add(btnConfirmar);

		btnBack = new JButton("Volver");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnBack.setBackground(new Color(116, 116, 116));
		btnBack.setEnabled(false);
		btnBack.setBounds(452, 777, 89, 23);
		contentPane.add(btnBack);

		btnCancel = new JButton("Cancelar");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		btnCancel.setBackground(new Color(116, 116, 116));
		btnCancel.setBounds(781, 777, 89, 23);
		contentPane.add(btnCancel);
		
		lblCambios = new JLabel("");
		lblCambios.setFont(new Font("Teko SemiBold", Font.PLAIN, 17));
		lblCambios.setBounds(372, 840, 226, 32);
		contentPane.add(lblCambios);
		lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(V_Principal.class.getResource("/photos/ladrillos.jpg")));
		lblNewLabel_1_1.setBounds(-60, -73, 1066, 1135);
		lblNewLabel_1_1.setBorder(new RoundedBorder(20));
		contentPane.add(lblNewLabel_1_1);

		btnConfirmar.addActionListener(this);
		btnBack.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == chb && chb.isSelected()) {
			count++;
		}else if(o == chb && !chb.isSelected()) {
			count--;
		}
		if(count > 3) {
			JOptionPane.showMessageDialog(this, "El agente no puede seleccionar más de tres artículos.", "Error", JOptionPane.ERROR_MESSAGE);
			for(int i = 0; i < table.getRowCount(); i++) {
				chb.setSelected(false);
				table.setValueAt(false, i, 2);
				count = guarda;
			}
		}
		if(o == btnCancel) {
			V_Policemen vP = new V_Policemen(l, pol.getId_user());
			vP.setVisible(true);
			this.dispose();
		}else if(o == btnBack) {
			V_Policemen vP = new V_Policemen(l, pol.getId_user());
			vP.setVisible(true);
			this.dispose();
		}else if(o == btnConfirmar) {
			if (!chb.isSelected()) {
				JOptionPane.showMessageDialog(this, "Seleccione algún artículo.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				for(int i = 0; i < table.getRowCount();i ++) {
					if(table.getValueAt(i, 2).equals(true)) {
						l.moreWeapons(pol.getId_policia(),weaponsAvailable.get(i).getId_arsenal());
						lblCambios.setText("Cambios guardados correctamente.");
						btnConfirmar.setEnabled(false);
						btnCancel.setEnabled(false);
						btnBack.setEnabled(true);
					}
				}
			}
		}
	}
}
