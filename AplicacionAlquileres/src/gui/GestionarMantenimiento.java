package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bdd.DbCliente;
import bdd.DbVehiculo;
import modelo.Cliente;
import modelo.Mantenimiento;
import modelo.Vehiculo;

import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GestionarMantenimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DbVehiculo conexion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarMantenimiento frame = new GestionarMantenimiento();
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
	public GestionarMantenimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblMantenimiento = new JLabel("Mantenimiento");
		lblMantenimiento.setBounds(47, 20, 107, 15);
		
		JButton btnMenu = new JButton("Menú");
		btnMenu.setBounds(757, 10, 73, 25);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 47, 783, 405);
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.setBounds(172, 10, 88, 25);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(278, 10, 82, 25);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(378, 10, 90, 25);
		
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		contentPane.add(btnAnadir);
		contentPane.add(btnEditar);
		contentPane.add(btnEliminar);
		contentPane.add(lblMantenimiento);
		contentPane.add(btnMenu);
		
		btnAnadir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        abrirVentanaCrearMantenimiento();
		    }
		});
		
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        editarMantenimiento();
		    }
		});
		
		btnEliminar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	eliminarMantenimiento();
		    }
		});
		
		btnMenu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAMenu();
		    }
		});

		
		cargarTablaMantenimientos();
	}
	
	public void cargarTablaMantenimientos() {
	    String[] columnas = {"Matricula", "Fecha", "Descripción"};
	    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    try {
	        DbVehiculo dbVehiculo = new DbVehiculo();
	        ArrayList<Mantenimiento> lista = dbVehiculo.verMantenimientos();

	        for (Mantenimiento m : lista) {
	            Object[] fila = {
	                m.getVehiculo().getMatricula(),
	                m.getFecha(),
	                m.getDescripcion()
	            };
	            modeloTabla.addRow(fila);
	        }

	        table.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public void editarMantenimiento() {
		int filaSeleccionada = table.getSelectedRow();
		
		  if (filaSeleccionada != -1) {
			  String matriculaSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
			  String descripcion = table.getValueAt(filaSeleccionada, 2).toString();
			  LocalDate fecha = LocalDate.parse(table.getValueAt(filaSeleccionada, 1).toString());
			  

			  try {
				  DbVehiculo dbVehiculo = new DbVehiculo();
				  Vehiculo vehiculoSeleccionado = dbVehiculo.ver1Vehiculo(matriculaSeleccionado);
				  
				  Mantenimiento mant = new Mantenimiento(vehiculoSeleccionado, descripcion, fecha);
				  
				  if(vehiculoSeleccionado != null) {
					  EditarMantenimiento ventanaEditar = new EditarMantenimiento(mant);
		              ventanaEditar.setVisible(true);
		              dispose(); 
				  } else {
					  JOptionPane.showMessageDialog(this, "No se encontró el cliente.");
		          }
				  
			  } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Error al obtener datos.");
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
		    }
	}
	
	private void abrirVentanaCrearMantenimiento() {
	    CrearMantenimiento ventanaCrear = new CrearMantenimiento();
	    ventanaCrear.setVisible(true);
	    dispose();
	}
	
	private void eliminarMantenimiento() {
		int filaSeleccionada = table.getSelectedRow();
		
		if (filaSeleccionada != -1) {
			  String matriculaSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
			  LocalDate fecha = LocalDate.parse(table.getValueAt(filaSeleccionada, 1).toString());
		     
			 int confirmacion = JOptionPane.showConfirmDialog(this, 
		                "¿Estás seguro de que quieres eliminar el mantenimiento del vehiculo con mátricula: " + matriculaSeleccionado + "?", 
		                "Confirmar eliminación", 
		                JOptionPane.YES_NO_OPTION);
			 
			 if (confirmacion == JOptionPane.YES_OPTION) {
		            try {
		                conexion = new DbVehiculo();
		                if (conexion.eliminarMantenimiento(matriculaSeleccionado, fecha)) {
		                    JOptionPane.showMessageDialog(this, "Mantenimiento eliminado correctamente.");
		                    cargarTablaMantenimientos();
		                } else {
		                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el mantenimiento.");
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(this, "Error al obtener datos.");
		            }
		        } else {
		            // Si elige "No", no hacemos nada
		            JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
		    }
	}
	
	private void irAMenu() {
	    Menu ventana = new Menu();
	    ventana.setVisible(true);
	    this.dispose();
	}
	

	
}
		
