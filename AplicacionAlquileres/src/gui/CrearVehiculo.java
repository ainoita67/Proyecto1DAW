package gui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Vehiculo;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.awt.Font;

public class CrearVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtPrecio;
	private JTextField txtFechaMatr;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtColor;
	
	private JRadioButton radioFurgo;
	private JRadioButton radioTurismo;
	private JRadioButton radioMoto;
	private ButtonGroup grupoTipo;
	
	private JLabel lblSubtipo;
	private JTextField txtSubtipo;
	
	private JSpinner spinnerPlazas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearVehiculo frame = new CrearVehiculo();
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
	public CrearVehiculo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(98, 127, 135, 15);
		contentPane.add(lblMatricula);
		
		JLabel lblPrecio = new JLabel("Precio/hora");
		lblPrecio.setBounds(98, 210, 98, 15);
		contentPane.add(lblPrecio);
		
		JLabel lblFechaMatr = new JLabel("Fecha Matriculación");
		lblFechaMatr.setBounds(454, 125, 148, 15);
		contentPane.add(lblFechaMatr);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(454, 210, 70, 15);
		contentPane.add(lblTipo);
		
		lblSubtipo = new JLabel("Subtipo");
		lblSubtipo.setBounds(98, 271, 70, 15);
		contentPane.add(lblSubtipo);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(251, 125, 170, 19);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(251, 208, 170, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtFechaMatr = new JTextField();
		txtFechaMatr.setBounds(607, 123, 170, 19);
		contentPane.add(txtFechaMatr);
		txtFechaMatr.setColumns(10);
		
		txtSubtipo = new JTextField();
		txtSubtipo.setBounds(251, 269, 170, 19);
		contentPane.add(txtSubtipo);
		txtSubtipo.setColumns(10);
		
		// Ocultar por defecto
		lblSubtipo.setVisible(false);
		txtSubtipo.setVisible(false);
		
		JLabel lblCrearVehiculo = new JLabel("Crear Vehiculo");
		lblCrearVehiculo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCrearVehiculo.setBounds(170, 49, 200, 15);
		contentPane.add(lblCrearVehiculo);
		
		radioFurgo = new JRadioButton("Furgoneta");
		radioFurgo.setBounds(502, 206, 103, 23);
		contentPane.add(radioFurgo);

		radioTurismo = new JRadioButton("Turismo");
		radioTurismo.setBounds(615, 206, 97, 23);
		contentPane.add(radioTurismo);

		radioMoto = new JRadioButton("Moto");
		radioMoto.setBounds(716, 206, 61, 23);
		contentPane.add(radioMoto);

		// Agrupación de botones
		grupoTipo = new ButtonGroup();
		grupoTipo.add(radioFurgo);
		grupoTipo.add(radioTurismo);
		grupoTipo.add(radioMoto);
		
		radioFurgo.addActionListener(e -> actualizarSubtipo());
		radioTurismo.addActionListener(e -> actualizarSubtipo());
		radioMoto.addActionListener(e -> actualizarSubtipo());
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(98, 154, 70, 15);
		contentPane.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(98, 181, 70, 15);
		contentPane.add(lblModelo);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(251, 152, 170, 19);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(251, 179, 170, 19);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblPlazas = new JLabel("plazas");
		lblPlazas.setBounds(454, 152, 70, 15);
		contentPane.add(lblPlazas);
		
		spinnerPlazas = new JSpinner();
		spinnerPlazas.setBounds(607, 152, 170, 20);
		contentPane.add(spinnerPlazas);
		
		txtColor = new JTextField();
		txtColor.setBounds(607, 179, 114, 19);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(454, 181, 70, 15);
		contentPane.add(lblColor);
		
		JButton btnCrear = new JButton("Guardar cambios");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirVehiculo();
			}
		});
		btnCrear.setBounds(370, 334, 154, 25);
		contentPane.add(btnCrear);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(750, 24, 80, 25);
		contentPane.add(btnAtras);
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				irAGestionar();
			}
		});
		
		JLabel lblAaaammdd = new JLabel("AAAA-MM-DD");
		lblAaaammdd.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAaaammdd.setBounds(607, 100, 105, 15);
		contentPane.add(lblAaaammdd);
		
	}
	
	public void actualizarSubtipo() {
		boolean mostrar = radioFurgo.isSelected() || radioTurismo.isSelected();
		lblSubtipo.setVisible(mostrar);
		txtSubtipo.setVisible(mostrar);
	}
	

	private void añadirVehiculo() {
	    try {
	        if (txtMatricula.getText().isEmpty() || txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() ||
	            txtPrecio.getText().isEmpty() || txtFechaMatr.getText().isEmpty() || txtColor.getText().isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Rellena todos los campos obligatorios.");
	            return;
	        }

	        String matricula = txtMatricula.getText();
	        String marca = txtMarca.getText();
	        String modelo = txtModelo.getText();
	        double precio = Double.parseDouble(txtPrecio.getText());
	        LocalDate fechaMatr = LocalDate.parse(txtFechaMatr.getText());
	        String color = txtColor.getText();
	        int plazas = (int) spinnerPlazas.getValue();
	        String subtipo = txtSubtipo.getText();

	        Vehiculo vehiculo = null;

	        if (radioFurgo.isSelected()) {
	            modelo.Furgoneta furgo = new modelo.Furgoneta(matricula, marca, modelo, precio, fechaMatr, null, color, plazas, subtipo);
	            vehiculo = furgo;
	        } else if (radioTurismo.isSelected()) {
	            modelo.Turismo tur = new modelo.Turismo(matricula, marca, modelo, precio, fechaMatr, null, color, plazas, subtipo);
	            vehiculo = tur;
	        } else if (radioMoto.isSelected()) {
	            modelo.Moto moto = new modelo.Moto(matricula, marca, modelo, precio, fechaMatr, null, color, plazas);
	            vehiculo = moto;
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un tipo de vehículo.");
	            return;
	        }

	        bdd.DbVehiculo db = new bdd.DbVehiculo();
	        if (db.crearVehiculo(vehiculo)) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Vehículo insertado correctamente.");
	            dispose();
	            GestionarVehiculos ventanaGestion = new GestionarVehiculos();
	            ventanaGestion.setVisible(true);
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(this, "Error al insertar vehículo.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(this, "Error al insertar el vehículo.");
	    }
	}

	
	private void irAGestionar() {
	    GestionarVehiculos ventanagestionar = new GestionarVehiculos();
	    ventanagestionar.setVisible(true);
	}
}
