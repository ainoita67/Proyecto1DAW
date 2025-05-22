package gui;

import java.awt.EventQueue;
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
import modelo.Usuario;
import modelo.Vehiculo;

import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;


public class GestionarVehiculos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 97, 1209, 600);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cargarTablaVehiculos();
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(310, 60, 80, 25);
		contentPane.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaCrearVehiculo();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(416, 60, 80, 25);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarVehiculo();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(525, 60, 90, 25);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarVehiculo();
			}
		});
		
		JButton btnMen = new JButton("Menú");
		btnMen.setBounds(1160, 60, 117, 25);
		contentPane.add(btnMen);
		btnMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				irAMenu();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Vehiculos");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setBounds(77, 40, 270, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnVerVehiculo = new JButton("Ver Vehiculo");
		btnVerVehiculo.setBounds(640, 60, 130, 25);
		contentPane.add(btnVerVehiculo);
		btnVerVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verVehiculo();
			}
		});
		
		Usuario usuario = modelo.Sesion.getUsuarioActivo();
		if (usuario != null) {
		    
		    int rol = usuario.getRol();
		    
		    if (rol==2) {
		    	btnAadir.setVisible(false);
		    	btnEditar.setVisible(false);
		    	btnEliminar.setVisible(false);
		    }else if(rol==3){
		    
		    }
		}
	}
	
	public void cargarTablaVehiculos() {
	    // Definir las columnas
	    String[] columnas = {"Matrícula", "Marca", "Modelo", "Precio/Dia", "F matriculacion", "prox mantenimiento", "Plazas", "Color", "Tipo", "Subtipo"};

	    // Crear un modelo de tabla que no permita edición
	    DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Ninguna celda es editable
	        }
	    };

	    try {
	        DbVehiculo dbVehiculo = new DbVehiculo();
	        ArrayList<Vehiculo> lista = dbVehiculo.obtenerVehiculos(null, null, false);
	        
	        for (Vehiculo v : lista) {
	        	
	        	String tipo = "";
	            String subtipo = "";

	            if (v instanceof modelo.Turismo) {
	                tipo = "Turismo";
	                subtipo = ((modelo.Turismo) v).getTipo();
	            } else if (v instanceof modelo.Furgoneta) {
	                tipo = "Furgoneta";
	                subtipo = ((modelo.Furgoneta) v).getTipo();
	            } else if (v instanceof modelo.Moto) {
	                tipo = "Moto";
	                subtipo = "";
	            }
	        	
	            Object[] fila = {
	            	v.getMatricula(),
	            	v.getMarca(),
	            	v.getModelo(),
	            	v.getPrecioH(),
	            	v.getF_matriculacion(),
	            	v.getProximo_mantenimiento(),
	            	v.getPlazas(),
	            	v.getColor(),
	            	tipo,
	                subtipo

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
	
	private void verVehiculo() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String matriculaSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        try {
	            DbVehiculo dbVehiculo = new DbVehiculo();
	            Vehiculo vehiculoSeleccionado = dbVehiculo.ver1Vehiculo(matriculaSeleccionado);

	            if (vehiculoSeleccionado != null) {
	                InformacionVehiculo ventanaVer = new InformacionVehiculo(vehiculoSeleccionado);
	                ventanaVer.setVisible(true);
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
	
	private void abrirVentanaCrearVehiculo() {
		CrearVehiculo ventanaCrear = new CrearVehiculo();
	    ventanaCrear.setVisible(true);
	    dispose();
	}
	
	private void eliminarVehiculo() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String matriculaSeleccionada = table.getValueAt(filaSeleccionada, 0).toString();

	        int confirmacion = JOptionPane.showConfirmDialog(this,
	                "¿Estás seguro de que quieres eliminar el vehículo con matrícula: " + matriculaSeleccionada + "?",
	                "Confirmar eliminación",
	                JOptionPane.YES_NO_OPTION);

	        if (confirmacion == JOptionPane.YES_OPTION) {
	            try {
	                DbVehiculo dbVehiculo = new DbVehiculo();
	                boolean exito = dbVehiculo.eliminarVehiculo(matriculaSeleccionada);

	                if (exito) {
	                    JOptionPane.showMessageDialog(this, "Vehículo eliminado correctamente.");
	                    cargarTablaVehiculos(); // Recargar la tabla
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el vehículo.");
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error al eliminar el vehículo.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
	    }
	}
	
	private void irAMenu() {
	    Menu ventanamenu = new Menu();
	    ventanamenu.setVisible(true);
	    dispose();
	}

}
