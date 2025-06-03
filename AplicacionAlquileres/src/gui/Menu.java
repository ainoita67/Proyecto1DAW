package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Sesion;
import modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

/**
 * @author Pyto_Grupo_D
 * @version 1.0
 * 
 * Ventana que muestra el menú y las opciones de la aplicación a los usuarios.
 * Los botones de Gestionar redirigen a ventanas de gestión de la aplicación.
 * El botón de cerrar sesión cierra la sesión del usuario actual.
 * 
 */
public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Método principal. Lanza la aplicación y muestra el menú.
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
	 * Constructor que inicializa el menú y sus partes.
	 */
	public Menu() {
		if (Sesion.getUsuarioActivo() == null) {
			JOptionPane.showMessageDialog(this, "Inicie sesión para acceder", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
			InicioSesion ventana = new InicioSesion();
			ventana.setVisible(true);
		    this.dispose();
		    return;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 193, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setBounds(22, 105, 184, 25);
		
		btnGestionarClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAClientes();
		    }
		});
		
		JButton btnGestionarAlquileres = new JButton("Gestionar Alquileres");
		btnGestionarAlquileres.setBounds(228, 105, 199, 25);
		btnGestionarAlquileres.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAAlquileres();
		    }
		});
		
		JButton btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setBounds(228, 152, 199, 25);
		
		btnGestionarEmpleados.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAUsuarios();
		    }
		});
		
		JButton btnGestionarVehiculos = new JButton("Gestionar Vehiculos");
		btnGestionarVehiculos.setBounds(22, 152, 184, 25);
		
		btnGestionarVehiculos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAVehiculos();
		    }
		});
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.setBounds(22, 27, 131, 25);
		contentPane.add(btnCerrarSesin);
		
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioSesion inicio = new InicioSesion();
				Sesion.cerrarSesionUsuario();
				inicio.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(147, 66, 60, 15);
		contentPane.add(lblUsuario);
		
		JLabel lblUsuarioactivo = new JLabel("");
		lblUsuarioactivo.setBounds(228, 66, 199, 15);
		contentPane.add(lblUsuarioactivo);
		
		JButton btnmantenimientos = new JButton("Mantenimientos");
		btnmantenimientos.setBounds(228, 199, 199, 25);
		
		btnmantenimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iraMantenimientos();
			}
		});

		Usuario usuario = modelo.Sesion.getUsuarioActivo();
		if (usuario != null) {
		    lblUsuarioactivo.setText(usuario.getNombre());
		    int rol = usuario.getRol();
		    if (rol==2) {
		    	btnGestionarEmpleados.setVisible(false);
		    }else if (rol==3) {
		    	
		    }
		}
		
		if (usuario != null) {
			contentPane.add(btnGestionarClientes);
			contentPane.add(btnGestionarAlquileres);
			contentPane.add(btnGestionarVehiculos);
		    if (usuario.getRol()==3) {
		    	contentPane.add(btnGestionarEmpleados);
				contentPane.add(btnmantenimientos);
			}
		}

	}
	
	/**
	 * Constructor que inicializa la ventana de GestionarClientes
	 */
	private void irAClientes() {
		GestionarClientes ventanaclientes = new GestionarClientes();
	    ventanaclientes.setVisible(true);
	    this.dispose();
	}
	
	/**
	 *  Método que inicializa la ventana de GestionarUsuarios
	 */
	private void irAUsuarios() {
		GestionarUsuarios ventanausuarios = new GestionarUsuarios();
	    ventanausuarios.setVisible(true);
	    this.dispose();
	}
	
	private void iraMantenimientos() {
		GestionarMantenimiento ventana = new GestionarMantenimiento();
	    ventana.setVisible(true);
	    this.dispose();
	}
	
	/**
	 *  Método que inicializa la ventana de GestionarVehiculos
	 */
	private void irAVehiculos() {
		GestionarVehiculos ventanavehiculos = new GestionarVehiculos();
	    ventanavehiculos.setVisible(true);
	    this.dispose();

	}
	
	/**
	 *  Método que inicializa la ventana de GestionarAlquileres
	 */
	private void irAAlquileres() {
	    GestionarAlquileres ventanaalquileres = new GestionarAlquileres();
	    ventanaalquileres.setVisible(true);
	    dispose();
	}
}
