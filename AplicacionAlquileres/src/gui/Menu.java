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
		
		JButton btnGestionarAlquileres = new JButton("Gestionar Alquileres");
		btnGestionarAlquileres.setBounds(228, 105, 199, 25);
		contentPane.add(btnGestionarAlquileres);
		btnGestionarAlquileres.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAAlquileres();
		    }
		});
		
		JButton btnGestionarEmpleados = new JButton("Gestionar Empleados");
		btnGestionarEmpleados.setBounds(228, 152, 199, 25);
		contentPane.add(btnGestionarEmpleados);
		
		btnGestionarEmpleados.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAUsuarios();
		    }
		});
		
		JButton btnGestionarVehiculos = new JButton("Gestionar Vehiculos");
		btnGestionarVehiculos.setBounds(22, 152, 184, 25);
		contentPane.add(btnGestionarVehiculos);
		
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
				Sesion.cerrarSesionRol();
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

		Usuario usuario = modelo.Sesion.getUsuarioActivo();
		if (usuario != null) {
		    lblUsuarioactivo.setText(usuario.getNombre());
		    
		    int rol = usuario.getRol();
		    
		    if (rol==2) {
		    	btnGestionarEmpleados.setVisible(false);
		    }else if (rol==3) {
		    	
		    }
		}
		
	}
	
	private void irAClientes() {
		if (Sesion.getUsuarioActivo() != null) {
			Menu ventanamenu = new Menu();
		    GestionarClientes ventanaclientes = new GestionarClientes();
		    ventanaclientes.setVisible(true);
		    this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Inicie sesión para acceder", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void irAUsuarios() {
		if (Sesion.getUsuarioActivo() != null) {
			Menu ventanamenu = new Menu();
		    GestionarUsuarios ventanausuarios = new GestionarUsuarios();
		    ventanausuarios.setVisible(true);
		    this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Inicie sesión para acceder", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void irAVehiculos() {
		if (Sesion.getUsuarioActivo() != null) {
			Menu ventanamenu = new Menu();
		    GestionarVehiculos ventanavehiculos = new GestionarVehiculos();
		    ventanavehiculos.setVisible(true);
		    this.dispose();
		}else{
			JOptionPane.showMessageDialog(this, "Inicie sesión para acceder", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void irAAlquileres() {
	    GestionarAlquileres ventanaalquileres = new GestionarAlquileres();
	    ventanaalquileres.setVisible(true);
	    dispose();
	}
}
