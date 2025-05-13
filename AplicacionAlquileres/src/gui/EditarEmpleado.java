package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class EditarEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDni;
	private JTextField txtTfno;
	private JTextField txtDireccion;
	private JTextField txtContrasea;
	private Usuario usuarioActual;
	private JCheckBox chckbxEmpleado;
	private JCheckBox chckbxAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarEmpleado frame = new EditarEmpleado(null);
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
	public EditarEmpleado(Usuario usuario) {
		this.usuarioActual = usuario;
		
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
		
		JLabel lblEditarEmpleado = new JLabel("Editar Empleado");
		lblEditarEmpleado.setBounds(174, 5, 117, 15);
		contentPane.add(lblEditarEmpleado);
		
		JButton btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCambios();
			}
		});
		btnGuardar.setBounds(138, 230, 180, 25);
		contentPane.add(btnGuardar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	irAGestionar();
            }
        });
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
		
		chckbxEmpleado = new JCheckBox("Empleado");
		chckbxEmpleado.setBounds(169, 199, 94, 23);
		contentPane.add(chckbxEmpleado);
		
		chckbxAdmin = new JCheckBox("Administrador");
		chckbxAdmin.setBounds(267, 199, 129, 23);
		contentPane.add(chckbxAdmin);
	
		cargarDatosUsuario(usuario);
	}

	public void cargarDatosUsuario(Usuario usuario) {
		if (usuario != null) {
			txtNombre.setText(usuario.getNombre());
			txtEmail.setText(usuario.getCorreo());
			txtDni.setText(usuario.getDNI());
			txtTfno.setText(String.valueOf(usuario.getTfno()));
			txtDireccion.setText(usuario.getDireccion());
			txtContrasea.setText(usuario.getContrasea());
			
			// Cargar roles - esto dependerá de cómo estén implementados los roles en tu modelo
			if (usuario.isAdmin()) {
				chckbxAdmin.setSelected(true);
			}
			if (usuario.isEmpleado()) {
				chckbxEmpleado.setSelected(true);
			}
			
			txtDni.setEditable(false);
		}
	}
	
	private void guardarCambios() {
		try {
			// Actualizar el objeto usuario con los valores nuevos
			usuarioActual.setNombre(txtNombre.getText());
			usuarioActual.setCorreo(txtEmail.getText());
			usuarioActual.setTfno(txtTfno.getText());
			usuarioActual.setDireccion(txtDireccion.getText());
			usuarioActual.setContrasea(txtContrasea.getText());
			
			// Actualizar roles según checkboxes
			usuarioActual.setAdmin(chckbxAdmin.isSelected());
			usuarioActual.setEmpleado(chckbxEmpleado.isSelected());
			
			// Llamar a la base de datos para actualizar
			bdd.DbUsuario db = new bdd.DbUsuario();
			
			if (db.actualizarUsuario(usuarioActual)) {
				javax.swing.JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.");
				dispose(); // Cerrar ventana después de guardar
				GestionarUsuarios ventanagestionar = new GestionarUsuarios();
				ventanagestionar.setVisible(true);
			} else {
				javax.swing.JOptionPane.showMessageDialog(this, "No se pudo actualizar el empleado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el empleado.");
		}
	}
	
	private void irAGestionar() {
		GestionarUsuarios ventanagestionar = new GestionarUsuarios();
		ventanagestionar.setVisible(true);
	}
}