package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GestionarMantenimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarMantenimiento frame = new GestionarMantenimiento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionarMantenimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblMantenimiento = new JLabel("Mantenimiento");
		lblMantenimiento.setBounds(47, 20, 107, 15);
		
		JButton btnMenu = new JButton("Menú");
		btnMenu.setBounds(757, 10, 73, 25);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 47, 783, 405);
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.setBounds(172, 10, 88, 25);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(278, 10, 82, 25);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(378, 10, 90, 25);
		
		JButton btnMantenimiento = new JButton("Ver Mantenimiento");
		btnMantenimiento.setBounds(486, 10, 179, 25);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		contentPane.add(btnAnadir);
		contentPane.add(btnEditar);
		contentPane.add(btnEliminar);
		contentPane.add(btnMantenimiento);
		contentPane.add(lblMantenimiento);
		contentPane.add(btnMenu);
	}
	
	
}
