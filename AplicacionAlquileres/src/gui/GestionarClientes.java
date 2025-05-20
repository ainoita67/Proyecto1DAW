package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class GestionarClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarDni;
	private DbCliente conexion;

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
		
		cargarTablaClientes();
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(310, 60, 80, 25);
		contentPane.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaCrearCliente();
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(416, 60, 80, 25);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarCliente();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(525, 60, 90, 25);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCliente();
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
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setBounds(77, 40, 270, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnVerVehiculo = new JButton("Ver Cliente");
		btnVerVehiculo.setBounds(640, 60, 130, 25);
		contentPane.add(btnVerVehiculo);
		btnVerVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verCliente();
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

	        table.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void editarCliente() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        try {
	            DbCliente dbCliente = new DbCliente();
	            Cliente clienteSeleccionado = dbCliente.ver1Cliente(dniSeleccionado);

	            if (clienteSeleccionado != null) {
	                EditarCliente ventanaEditar = new EditarCliente(clienteSeleccionado);
	                ventanaEditar.setVisible(true);
	                dispose(); // Cerrar
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
	
	private void abrirVentanaCrearCliente() {
	    CrearCliente ventanaCrear = new CrearCliente();
	    ventanaCrear.setVisible(true);
	    dispose();
	}
	
	private void eliminarCliente() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        // Mostrar confirmación
	        int confirmacion = JOptionPane.showConfirmDialog(this, 
	                "¿Estás seguro de que quieres eliminar al cliente con DNI: " + dniSeleccionado + "?", 
	                "Confirmar eliminación", 
	                JOptionPane.YES_NO_OPTION);

	        // Si elige "Sí" (YES_OPTION)
	        if (confirmacion == JOptionPane.YES_OPTION) {
	            try {
	                conexion = new DbCliente();
	                if (conexion.eliminarCliente(dniSeleccionado)) {
	                    JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
	                    dispose(); // Cerrar ventana actual
	                    GestionarClientes ventanagestionar = new GestionarClientes();
	                    ventanagestionar.setVisible(true);
	                } else {
	                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el cliente.");
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
		GestionarClientes ventanacliente = new GestionarClientes();
	    Menu ventanamenu = new Menu();
	    ventanamenu.setVisible(true);
	    this.dispose();
	}
	
	private void verCliente() {
	    int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        try {
	            DbCliente dbCliente = new DbCliente();
	            Cliente clienteSeleccionado = dbCliente.ver1Cliente(dniSeleccionado);

	            if (clienteSeleccionado != null) {
	                InformacionCliente ventanaVer = new InformacionCliente(clienteSeleccionado);
	                ventanaVer.setVisible(true);
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
}
