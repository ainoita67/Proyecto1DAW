package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionarAlquileres extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarAlquileres frame = new GestionarAlquileres();
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
	public GestionarAlquileres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 65, 494, 283);
		contentPane.add(scrollPane);
		
		JLabel lblAlquileres = new JLabel("Alquileres");
		lblAlquileres.setBounds(12, 0, 89, 15);
		contentPane.add(lblAlquileres);
		
		JButton btnAadir = new JButton("añadir");
		btnAadir.setBounds(119, -5, 117, 25);
		contentPane.add(btnAadir);
		
		JButton btnEditar = new JButton("editar");
		btnEditar.setBounds(248, -5, 117, 25);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.setBounds(377, -5, 117, 25);
		contentPane.add(btnEliminar);
	}
}
