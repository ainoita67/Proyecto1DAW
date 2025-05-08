package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 193, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setBounds(22, 105, 184, 25);
		contentPane.add(btnGestionarClientes);
		
		btnGestionarClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAClientes();
		    }
		});
		
		JButton btnConsultarVehiculos = new JButton("Consultar Vehiculos");
		btnConsultarVehiculos.setBounds(22, 152, 184, 25);
		contentPane.add(btnConsultarVehiculos);
		
		JButton btnGestionarAlquileres = new JButton("Gestionar Alquileres");
		btnGestionarAlquileres.setBounds(22, 200, 184, 25);
		contentPane.add(btnGestionarAlquileres);
		
		JButton btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setBounds(228, 105, 199, 25);
		contentPane.add(btnGestionarEmpleados);
		
		JButton btnGestionarVehiculos = new JButton("Gestionar vehiculos");
		btnGestionarVehiculos.setBounds(228, 152, 199, 25);
		contentPane.add(btnGestionarVehiculos);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.setBounds(22, 27, 131, 25);
		contentPane.add(btnCerrarSesin);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(147, 66, 60, 15);
		contentPane.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(228, 66, 199, 15);
		contentPane.add(lblNewLabel);
	}
	
	private void irAClientes() {
	    GestionarClientes ventanaclientes = new GestionarClientes();
	    ventanaclientes.setVisible(true);
	}
}
