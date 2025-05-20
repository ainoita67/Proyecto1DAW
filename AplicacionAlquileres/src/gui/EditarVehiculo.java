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

public class EditarVehiculo extends JFrame {

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
	
	private Vehiculo vehiculoactual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarVehiculo frame = new EditarVehiculo(null);
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
	public EditarVehiculo(Vehiculo vehiculo) {
		this.vehiculoactual = vehiculo;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(98, 127, 135, 15);
		contentPane.add(lblMatricula);
		
		JLabel lblPrecio = new JLabel("Precio/dia");
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
		
		JLabel lblEditarVehiculo = new JLabel("Editar Vehiculo");
		lblEditarVehiculo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblEditarVehiculo.setBounds(170, 49, 200, 15);
		contentPane.add(lblEditarVehiculo);
		
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
				guardarCambiosVehiculo();
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
		
		cargarDatosVehiculo(vehiculo);
	}
	
	public void actualizarSubtipo() {
		boolean mostrar = radioFurgo.isSelected() || radioTurismo.isSelected();
		lblSubtipo.setVisible(mostrar);
		txtSubtipo.setVisible(mostrar);
	}
	
	public void cargarDatosVehiculo(Vehiculo vehiculo) {
	    if (vehiculo == null) return;

	    txtMatricula.setText(vehiculo.getMatricula());
	    txtMatricula.setEditable(false);

	    txtMarca.setText(vehiculo.getMarca());
	    txtModelo.setText(vehiculo.getModelo());
	    txtPrecio.setText(String.valueOf(vehiculo.getPrecioH()));
	    txtFechaMatr.setText(vehiculo.getF_matriculacion().toString());
	    txtColor.setText(vehiculo.getColor());
	    spinnerPlazas.setValue(vehiculo.getPlazas());

	    if (vehiculo instanceof modelo.Furgoneta) {
	        radioFurgo.setSelected(true);
	        txtSubtipo.setText(((modelo.Furgoneta) vehiculo).getTipo());
	    } else if (vehiculo instanceof modelo.Turismo) {
	        radioTurismo.setSelected(true);
	        txtSubtipo.setText(((modelo.Turismo) vehiculo).getTipo());
	    } else if (vehiculo instanceof modelo.Moto) {
	        radioMoto.setSelected(true);
	    }
	    
	    radioFurgo.setEnabled(false);
	    radioTurismo.setEnabled(false);
	    radioMoto.setEnabled(false);

	    actualizarSubtipo();
	}

	private void guardarCambiosVehiculo() {
	    try {
	        if (txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Rellena todos los campos obligatorios.");
	            return;
	        }

	        // Actualizar los valores del objeto vehiculoactual
	        vehiculoactual.setMarca(txtMarca.getText());
	        vehiculoactual.setModelo(txtModelo.getText());
	        vehiculoactual.setPrecioH(Double.parseDouble(txtPrecio.getText()));
	        vehiculoactual.setColor(txtColor.getText());
	        vehiculoactual.setPlazas((int) spinnerPlazas.getValue());

	        // Fecha de matriculación
	        LocalDate fechaMat = LocalDate.parse(txtFechaMatr.getText());
	        vehiculoactual.setF_matriculacion(fechaMat);

	        // Subtipo según clase
	        if (vehiculoactual instanceof modelo.Furgoneta) {
	            ((modelo.Furgoneta) vehiculoactual).setTipo(txtSubtipo.getText());
	        } else if (vehiculoactual instanceof modelo.Turismo) {
	            ((modelo.Turismo) vehiculoactual).setTipo(txtSubtipo.getText());
	        }

	        // Guardar en base de datos
	        bdd.DbVehiculo db = new bdd.DbVehiculo();
	        if (db.actualizarVehiculo(vehiculoactual)) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente.");
	            dispose(); // cerrar esta ventana
	            GestionarVehiculos ventanaGestion = new GestionarVehiculos();
	            ventanaGestion.setVisible(true);
	        } else {
	            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo actualizar el vehículo.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el vehículo.");
	    }
	}
	
	private void irAGestionar() {
	    GestionarVehiculos ventanagestionar = new GestionarVehiculos();
	    ventanagestionar.setVisible(true);
	}
}
