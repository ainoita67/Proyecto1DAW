package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bdd.DbCliente;
import bdd.DbVehiculo;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;


public class CrearAlquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableC;
	private JTable tableV;
	private JTextField txtBuscarDni;
	private JTextField txtBuscarMatricula;
	private JTextField txtFechaFin;
	private JTextField txtPrecio;
	private JTextField txtFechaInicio;
	private JButton btnBuscar;
	private JButton btnMostrarTodo;

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
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneC = new JScrollPane();
		scrollPaneC.setBounds(169, 71, 520, 644);
		contentPane.add(scrollPaneC);
		
		tableC = new JTable();
		scrollPaneC.setViewportView(tableC);
		cargarTablaClientes();
		
		txtBuscarDni = new JTextField();
		txtBuscarDni.setText("Buscar DNI");
		txtBuscarDni.setBounds(575, 40, 114, 19);
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
		btnAadir.setBounds(26, 163, 80, 25);
		contentPane.add(btnAadir);
		
		JButton btnMen = new JButton("Menú");
		btnMen.setBounds(26, 506, 131, 25);
		contentPane.add(btnMen);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(619, 13, 70, 15);
		contentPane.add(lblClientes);
		
		JScrollPane scrollPaneV = new JScrollPane();
		scrollPaneV.setBounds(701, 71, 626, 644);
		contentPane.add(scrollPaneV);
		
		tableV = new JTable();
		scrollPaneV.setViewportView(tableV);
		cargarTablaVehiculos(null, null, false);
		
		txtBuscarMatricula = new JTextField();
		txtBuscarMatricula.setText("Buscar Matricula");
		txtBuscarMatricula.setColumns(10);
		txtBuscarMatricula.setBounds(1213, 40, 114, 19);
		contentPane.add(txtBuscarMatricula);
		
		JLabel lblVehiculos = new JLabel("Vehiculos");
		lblVehiculos.setBounds(1257, 13, 70, 15);
		contentPane.add(lblVehiculos);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setText("Fecha Fin");
		txtFechaFin.setBounds(26, 101, 131, 19);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setText("Precio/Hora");
		txtPrecio.setBounds(26, 132, 131, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setText("Fecha Inicio");
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(26, 70, 131, 19);
		contentPane.add(txtFechaInicio);
		
		btnBuscar = new JButton("Buscar disponibilidad");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarDisponibilidad();
			}
		});
		btnBuscar.setBounds(26, 25, 208, 34);
		contentPane.add(btnBuscar);
		
		btnMostrarTodo = new JButton("Mostrar todo");
		btnMostrarTodo.setBounds(26, 469, 131, 25);
		contentPane.add(btnMostrarTodo);
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaVehiculos(null, null,false);
			}
		});
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

	        tableC.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void buscarDisponibilidad() {
		LocalDate fecha1 = LocalDate.parse(txtFechaInicio.getText());
		LocalDate fecha2 = LocalDate.parse(txtFechaFin.getText());
		cargarTablaVehiculos(fecha1, fecha2, true);

	}
	
	public void cargarTablaVehiculos(LocalDate fecha1, LocalDate fecha2, boolean filtrar) {
	    // Definir las columnas
	    String[] columnas = {"Matrícula", "Marca", "Modelo", "Precio/Dia", "F matriculacion", "prox mantenimiento", "Plazas", "Color"};

	    // Crear un modelo de tabla que no permita edición
	    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Ninguna celda es editable
	        }
	    };

	    try {
	        DbVehiculo dbVehiculo = new DbVehiculo();
	        ArrayList<Vehiculo> lista = dbVehiculo.obtenerVehiculos(fecha1, fecha2, filtrar);
	        
	        for (Vehiculo v : lista) {
	            Object[] fila = {
	            	v.getMatricula(),
	            	v.getMarca(),
	            	v.getModelo(),
	            	v.getPrecioH(),
	            	v.getF_matriculacion(),
	            	v.getProximo_mantenimiento(),
	            	v.getPlazas(),
	            	v.getColor()

	            };
	            modeloTabla.addRow(fila);
	        }

	        tableV.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
