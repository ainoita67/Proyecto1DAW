package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CrearUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDni;
	private JTextField txtTfno;
	private JTextField txtDireccion;
	private JTextField txtContrasea;

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
		
		JLabel lblCrearCliente = new JLabel("Crear Empleado");
		lblCrearCliente.setBounds(174, 5, 117, 15);
		contentPane.add(lblCrearCliente);
		
		JButton btnCrear = new JButton("+ Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCrear.setBounds(174, 230, 117, 25);
		contentPane.add(btnCrear);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(359, 0, 79, 25);
		contentPane.add(btnAtras);
		
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
		
		JCheckBox chckbxEmpleado = new JCheckBox("Empleado");
		chckbxEmpleado.setBounds(169, 199, 94, 23);
		contentPane.add(chckbxEmpleado);
		
		JCheckBox chckbxAdministrador = new JCheckBox("Administrador");
		chckbxAdministrador.setBounds(267, 199, 129, 23);
		contentPane.add(chckbxAdministrador);
	}
}
