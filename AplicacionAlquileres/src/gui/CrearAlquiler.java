package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


public class CrearAlquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarDni;
	private JTextField txtBuscarMatricula;
	private JTextField txtFechaFin;
	private JTextField txtPrecio;
	private JTextField txtFechaInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearAlquiler frame = new CrearAlquiler();
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
	public CrearAlquiler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 71, 114, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
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
		btnAadir.setBounds(358, 152, 80, 25);
		contentPane.add(btnAadir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(358, 189, 80, 25);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(348, 226, 90, 25);
		contentPane.add(btnEliminar);
		
		JButton btnMen = new JButton("Menú");
		btnMen.setBounds(321, 0, 117, 25);
		contentPane.add(btnMen);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(47, 13, 70, 15);
		contentPane.add(lblClientes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(163, 71, 114, 180);
		contentPane.add(scrollPane_1);
		
		txtBuscarMatricula = new JTextField();
		txtBuscarMatricula.setText("Buscar Matricula");
		txtBuscarMatricula.setColumns(10);
		txtBuscarMatricula.setBounds(163, 40, 114, 19);
		contentPane.add(txtBuscarMatricula);
		
		JLabel lblVehiculos = new JLabel("Vehiculos");
		lblVehiculos.setBounds(183, 13, 70, 15);
		contentPane.add(lblVehiculos);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setText("Fecha Fin");
		txtFechaFin.setBounds(324, 70, 114, 19);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setText("Precio/Hora");
		txtPrecio.setBounds(324, 101, 114, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setText("Fecha Inicio");
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(324, 40, 114, 19);
		contentPane.add(txtFechaInicio);
	}
}
