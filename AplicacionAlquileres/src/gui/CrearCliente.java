package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import bdd.DbCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDni;
	private JTextField txtTfno;
	private JTextField txtDireccion;
	private DbCliente conexion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCliente frame = new CrearCliente();
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
	public CrearCliente() {
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
		
		JLabel lblCrearCliente = new JLabel("Crear Cliente");
		lblCrearCliente.setBounds(177, 12, 98, 15);
		contentPane.add(lblCrearCliente);
		
		JButton btnCrear = new JButton("+ Crear");
		btnCrear.setBounds(174, 198, 117, 25);
		contentPane.add(btnCrear);
		
		btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             insertarCliente();
            }
        });
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(359, 0, 79, 25);
		contentPane.add(btnAtras);
		
		btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	irAGestionar();
            }
        });
	}
	
    public void insertarCliente()
    {
  	
       try {
    	   Cliente cliente = new Cliente(txtDni.getText(), txtNombre.getText(), txtTfno.getText(), txtEmail.getText(), txtDireccion.getText());
    	   
           conexion = new DbCliente();
           if (conexion.crearCliente(cliente)) {
               JOptionPane.showMessageDialog(null, "Cliente insertado correctamente");
               dispose(); // Cerrar ventana después de guardar
	           GestionarClientes ventanagestionar = new GestionarClientes();
	           ventanagestionar.setVisible(true);
           } else {
               JOptionPane.showMessageDialog(null, "Error al insertar cliente");
           }
           conexion.cerrar();
    	    
       } catch (SQLException ex) {
           ex.printStackTrace();
       }
    }
    
    private void irAGestionar() {
	    GestionarClientes ventanagestionar = new GestionarClientes();
	    ventanagestionar.setVisible(true);
	}

}
