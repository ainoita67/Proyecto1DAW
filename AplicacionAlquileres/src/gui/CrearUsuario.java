package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bdd.DbCliente;
import bdd.DbUsuario;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class CrearUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDni;
	private JTextField txtTfno;
	private JTextField txtDireccion;
	private JTextField txtContrasea;
	private DbCliente conexion;
	JRadioButton rdbtnEmpleado;
	JRadioButton rdbtnAdmin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario frame = new CrearUsuario();
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
	public CrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo");
		lblNombreCompleto.setBounds(73, 41, 135, 15);
		contentPane.add(lblNombreCompleto);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(73, 68, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(73, 95, 70, 15);
		contentPane.add(lblDni);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(73, 122, 70, 15);
		contentPane.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(73, 149, 70, 15);
		contentPane.add(lblDireccin);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(226, 39, 170, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(226, 66, 170, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(226, 93, 170, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtTfno = new JTextField();
		txtTfno.setBounds(226, 120, 170, 19);
		contentPane.add(txtTfno);
		txtTfno.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(226, 147, 170, 19);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		ButtonGroup grupoBotones= new ButtonGroup();
		
		rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.setBounds(195, 199, 94, 23);
		contentPane.add(rdbtnEmpleado);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Administador");
		rdbtnAdmin.setBounds(293, 199, 126, 23);
		contentPane.add(rdbtnAdmin);
		
		grupoBotones.add(rdbtnAdmin);
		grupoBotones.add(rdbtnEmpleado);
		
		JLabel lblCrearCliente = new JLabel("Crear Empleado");
		lblCrearCliente.setBounds(174, 5, 117, 15);
		contentPane.add(lblCrearCliente);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(73, 176, 111, 15);
		contentPane.add(lblContrasea);
		
		txtContrasea = new JTextField();
		txtContrasea.setColumns(10);
		txtContrasea.setBounds(226, 174, 170, 19);
		contentPane.add(txtContrasea);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(73, 203, 111, 15);
		contentPane.add(lblRol);
		
		
		JButton btnCrear = new JButton("+ Crear");
		btnCrear.setBounds(174, 230, 117, 25);
		contentPane.add(btnCrear);
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(359, 0, 79, 25);
		contentPane.add(btnAtras);
		
	}
	
	public void insertarUsuario()
    {
		String rol = "";
		if (rdbtnEmpleado.isSelected()) {
		    rol = "Empleado";
		} else if (rdbtnAdmin.isSelected()) {
		    rol = "Administrador";
		}
		
    	System.out.println("Coy a ajflf");
    	  
    	Usuario usuario = new Usuario(txtNombre.getText(), txtEmail.getText(), txtTfno.getText(), txtDni.getText(), txtDireccion.getText(),txtContrasea.getText(), rol);
           try {
               conexion = new DbUsuario();
               if (conexion.crearUsuario(usuario)) {
                   JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
               } else {
                   JOptionPane.showMessageDialog(null, "Error al insertar usuario");
               }
               conexion.cerrar();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
    }
}
