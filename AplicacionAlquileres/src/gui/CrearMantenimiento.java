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

import bdd.DbVehiculo;
import modelo.Vehiculo;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;

public class CrearMantenimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFecha;
	private JTextArea textAreaDescripcion;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearMantenimiento frame = new CrearMantenimiento();
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
	public CrearMantenimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 46, 294, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNuevoMantenimiento = new JLabel("Nuevo mantenimiento");
		lblNuevoMantenimiento.setBounds(12, 0, 165, 15);
		contentPane.add(lblNuevoMantenimiento);
		
		JLabel lblVehiculos = new JLabel("vehiculos:");
		lblVehiculos.setBounds(12, 19, 110, 15);
		contentPane.add(lblVehiculos);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(318, 70, 114, 19);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(318, 46, 70, 15);
		contentPane.add(lblFecha);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(324, 101, 114, 15);
		contentPane.add(lblDescripcion);
		
		JButton btnAtras = new JButton("atras");
		btnAtras.setBounds(352, 14, 80, 25);
		contentPane.add(btnAtras);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(223, 9, 117, 25);
		contentPane.add(btnGuardar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(318, 132, 110, 119);
		contentPane.add(scrollPane_1);
		
		textAreaDescripcion = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescripcion);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadirMantenimiento();
			}
		});

		btnAtras.addActionListener(e -> {
			GestionarMantenimiento ventana = new GestionarMantenimiento();
			ventana.setVisible(true);
			dispose();
		});
		
		cargarTablaVehiculos();
	}
	
	public void cargarTablaVehiculos() {
	    // Definir las columnas
	    String[] columnas = {"Matrícula", "Marca", "Modelo"};

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
	        	
	            Object[] fila = {
	            	v.getMatricula(),
	            	v.getMarca(),
	            	v.getModelo()
	            };
	            modeloTabla.addRow(fila);
	        }

	        table.setModel(modeloTabla);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void añadirMantenimiento() {
		int filaSeleccionada = table.getSelectedRow();

	    if (filaSeleccionada != -1) {
	        String matriculaSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	    try {
	        if (textFieldFecha.getText().isEmpty() || textAreaDescripcion.getText().isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
	            return;
	        }
	        bdd.DbVehiculo db = new bdd.DbVehiculo();

	        LocalDate fecha = LocalDate.parse(textFieldFecha.getText());
	        String descripcion = textAreaDescripcion.getText();
	        
    		Vehiculo vehiculo = db.ver1Vehiculo(matriculaSeleccionado);

	        modelo.Mantenimiento m = new modelo.Mantenimiento(vehiculo, descripcion, fecha);

	        if (db.hacerMantenimiento(m)) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Mantenimiento insertado correctamente.");
	            dispose(); // cerrar ventana
	            GestionarMantenimiento ventana = new GestionarMantenimiento();
	            ventana.setVisible(true);
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(this, "Error al insertar el mantenimiento.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(this, "Error al insertar el mantenimiento.");
	    }
	    } else {
	        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
	    }
	}

}	

