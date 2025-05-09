package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDni;
	private JTextField txtTfno;
	private JTextField txtDireccion;
	private Cliente clienteActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCliente frame = new EditarCliente(null);
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
	public EditarCliente(Cliente cliente) {
		this.clienteActual = cliente;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Nombre Completo");
		lblNombreCompleto.setBounds(72, 50, 135, 15);
		contentPane.add(lblNombreCompleto);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(72, 77, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(72, 104, 70, 15);
		contentPane.add(lblDni);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(72, 131, 70, 15);
		contentPane.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(72, 158, 70, 15);
		contentPane.add(lblDireccin);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(225, 48, 170, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(225, 75, 170, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(225, 102, 170, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtTfno = new JTextField();
		txtTfno.setBounds(225, 129, 170, 19);
		contentPane.add(txtTfno);
		txtTfno.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(225, 156, 170, 19);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblEditarCliente = new JLabel("Editar Cliente");
		lblEditarCliente.setBounds(177, 12, 98, 15);
		contentPane.add(lblEditarCliente);
		
		JButton btnEditar = new JButton("Guardar cambios");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCambios();
			}
		});
		btnEditar.setBounds(174, 198, 117, 25);
		contentPane.add(btnEditar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(358, 0, 80, 25);
		contentPane.add(btnAtras);
		
		btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	irAGestionar();
            }
        });
		
		cargarDatosCliente(cliente);
	}
	
	public void cargarDatosCliente(Cliente cliente) {
	    txtNombre.setText(cliente.getNombre());
	    txtEmail.setText(cliente.getCorreo());
	    txtDni.setText(cliente.getDNI());
	    txtTfno.setText(String.valueOf(cliente.getTfno()));
	    txtDireccion.setText(cliente.getDireccion());
	    
	    txtDni.setEditable(false);
	}

	private void guardarCambios() {
	    try {
	        // Actualizar el objeto cliente con los valores nuevos
	        clienteActual.setNombre(txtNombre.getText());
	        clienteActual.setCorreo(txtEmail.getText());
	        clienteActual.setTfno(txtTfno.getText());
	        clienteActual.setDireccion(txtDireccion.getText());
	        
	        // Llamar a la base de datos para actualizar
	        bdd.DbCliente db = new bdd.DbCliente();
	        
	        if (db.actualizarCliente(clienteActual)) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
	            dispose(); // Cerrar ventana después de guardar
	            GestionarClientes ventanagestionar = new GestionarClientes();
	            ventanagestionar.setVisible(true);
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo actualizar el cliente.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el cliente.");
	    }
	}
	
	private void irAGestionar() {
	    GestionarClientes ventanagestionar = new GestionarClientes();
	    ventanagestionar.setVisible(true);
	}
}
