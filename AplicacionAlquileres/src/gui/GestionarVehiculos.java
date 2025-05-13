package gui;

import java.awt.EventQueue;
import java.time.LocalDate;
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
import javax.swing.JOptionPane;


public class GestionarVehiculos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarVehiculos frame = new GestionarVehiculos();
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
	public GestionarVehiculos() {
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
		
		txtBuscarMatricula = new JTextField();
		txtBuscarMatricula.setText("Buscar Matricula");
		txtBuscarMatricula.setBounds(27, 40, 114, 19);
		contentPane.add(txtBuscarMatricula);
		txtBuscarMatricula.setColumns(10);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
		txtBuscarMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        if (txtBuscarMatricula.getText().equals("Buscar Matricula")) {
		        	txtBuscarMatricula.setText("");
		        }
		    }
		    
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
		        if (txtBuscarMatricula.getText().isEmpty()) {
		        	txtBuscarMatricula.setText("Buscar Matricula");
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
		
		JLabel lblNewLabel = new JLabel("Vehiculos");
		lblNewLabel.setBounds(46, 13, 70, 15);
		contentPane.add(lblNewLabel);
	}
	
	public void cargarTablaClientes() {
	    // Definir las columnas
	    String[] columnas = {"Matrícula", "Marca", "Modelo", "Precio/Hora", "F matriculacion", "prox mantenimiento", "Plazas", "Color"};

	    // Crear un modelo de tabla que no permita edición
	    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Ninguna celda es editable
	        }
	    };

	    try {
	        DbVehiculo dbVehiculo = new DbVehiculo();
	        ArrayList<Vehiculo> lista = dbVehiculo.verTodosVehiculos();
	        
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

	        table.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void editarVehiculo() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String matriculaSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        try {
	            DbVehiculo dbVehiculo = new DbVehiculo();
	            Vehiculo vehiculoSeleccionado = dbVehiculo.ver1Vehiculo(matriculaSeleccionado);

	            if (vehiculoSeleccionado != null) {
	                EditarVehiculo ventanaEditar = new EditarVehiculo(vehiculoSeleccionado);
	                ventanaEditar.setVisible(true);
	                dispose(); // Cerrar
	            } else {
	                JOptionPane.showMessageDialog(this, "No se encontró el vehiculo.");
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al obtener datos.");
	        }

	    } else {
	        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
	    }
	}
}
