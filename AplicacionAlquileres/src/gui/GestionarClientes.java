package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bdd.DbCliente;
import modelo.Cliente;

import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


public class GestionarClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarDni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarClientes frame = new GestionarClientes();
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
	public GestionarClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 71, 400, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cargarTablaClientes();
		
		txtBuscarDni = new JTextField();
		txtBuscarDni.setText("Buscar DNI");
		txtBuscarDni.setBounds(27, 40, 114, 19);
		contentPane.add(txtBuscarDni);
		txtBuscarDni.setColumns(10);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
		txtBuscarDni.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        if (txtBuscarDni.getText().equals("Buscar DNI")) {
		        	txtBuscarDni.setText("");
		        }
		    }
		    
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
		        if (txtBuscarDni.getText().isEmpty()) {
		        	txtBuscarDni.setText("Buscar DNI");
		        }
		    }
		});
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(153, 37, 80, 25);
		contentPane.add(btnAadir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(245, 37, 80, 25);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(337, 37, 90, 25);
		contentPane.add(btnEliminar);
		
		JButton btnMen = new JButton("Menú");
		btnMen.setBounds(321, 0, 117, 25);
		contentPane.add(btnMen);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(47, 13, 70, 15);
		contentPane.add(lblClientes);
	}
	
	public void cargarTablaClientes() {
	    // Definir las columnas
	    String[] columnas = {"DNI", "Nombre", "Teléfono", "Correo", "Dirección"};

	    // Crear un modelo de tabla que no permita edición
	    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Ninguna celda es editable
	        }
	    };

	    try {
	        DbCliente dbCliente = new DbCliente();
	        ArrayList<Cliente> lista = dbCliente.verTodosClientes();

	        for (Cliente c : lista) {
	            Object[] fila = {
	                c.getDNI(),
	                c.getNombre(),
	                c.getTfno(),
	                c.getCorreo(),
	                c.getDireccion()
	            };
	            modeloTabla.addRow(fila);
	        }

	        table.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
