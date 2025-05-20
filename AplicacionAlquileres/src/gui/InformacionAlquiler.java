package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bdd.DbAlquileres;
import javax.swing.JButton;

public class InformacionAlquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtPrecio;
	private JTextField txtFechaMat;
	private JTextField txtPlazas;
	private JTextField txtColor;
	private JTextField txtTipo;
	private JTextField txtProxMant;
	private JTextField txtSubtipo;
	private JLabel lblSubtipo; 
	
	private JButton btnAtras;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtFini;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtFfin;
	private JLabel lblPrecio;
	private JTextField txtPtotal;


	public InformacionAlquiler(Alquiler alquiler) {
		Vehiculo vehiculo = alquiler.getVehiculo();
		Cliente cliente = alquiler.getCliente();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformacionDelVehiculo = new JLabel("Información del alquiler");
		lblInformacionDelVehiculo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblInformacionDelVehiculo.setBounds(122, 106, 390, 36);
		contentPane.add(lblInformacionDelVehiculo);

		JLabel lblMatrcula = new JLabel("Matrícula: ");
		lblMatrcula.setBounds(122, 267, 88, 15);
		contentPane.add(lblMatrcula);

		JLabel lblMarca = new JLabel("Marca: ");
		lblMarca.setBounds(122, 294, 70, 15);
		contentPane.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(122, 321, 70, 15);
		contentPane.add(lblModelo);

		JLabel lblPreciodia = new JLabel("Precio/Dia");
		lblPreciodia.setBounds(122, 348, 88, 15);
		contentPane.add(lblPreciodia);

		JLabel lblFechaDeMatriculacin = new JLabel("Fecha de matriculación:");
		lblFechaDeMatriculacin.setBounds(122, 375, 186, 15);
		contentPane.add(lblFechaDeMatriculacin);

		JLabel lblPlazas = new JLabel("Plazas:");
		lblPlazas.setBounds(122, 402, 70, 15);
		contentPane.add(lblPlazas);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(122, 429, 70, 15);
		contentPane.add(lblColor);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(122, 456, 70, 15);
		contentPane.add(lblTipo);

		JLabel lblPrximoMantenimiento = new JLabel("Próximo mantenimiento:");
		lblPrximoMantenimiento.setBounds(122, 510, 186, 15);
		contentPane.add(lblPrximoMantenimiento);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(336, 265, 176, 19);
		txtMatricula.setEditable(false); 
		contentPane.add(txtMatricula);

		txtMarca = new JTextField();
		txtMarca.setBounds(336, 292, 176, 19);
		txtMarca.setEditable(false); 
		contentPane.add(txtMarca);

		txtModelo = new JTextField();
		txtModelo.setBounds(336, 319, 176, 19);
		txtModelo.setEditable(false); 
		contentPane.add(txtModelo);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(336, 346, 176, 19);
		txtPrecio.setEditable(false); 
		contentPane.add(txtPrecio);

		txtFechaMat = new JTextField();
		txtFechaMat.setBounds(336, 373, 176, 19);
		txtFechaMat.setEditable(false); 
		contentPane.add(txtFechaMat);

		txtPlazas = new JTextField();
		txtPlazas.setBounds(336, 400, 176, 19);
		txtPlazas.setEditable(false);
		contentPane.add(txtPlazas);

		txtColor = new JTextField();
		txtColor.setBounds(336, 427, 176, 19);
		txtColor.setEditable(false); 
		contentPane.add(txtColor);

		txtTipo = new JTextField();
		txtTipo.setBounds(336, 454, 176, 19);
		txtTipo.setEditable(false); 
		contentPane.add(txtTipo);

		txtProxMant = new JTextField();
		txtProxMant.setBounds(336, 508, 176, 19);
		txtProxMant.setEditable(false);
		contentPane.add(txtProxMant);

		lblSubtipo = new JLabel("Subtipo:");
		lblSubtipo.setBounds(122, 483, 70, 15);
		contentPane.add(lblSubtipo);

		txtSubtipo = new JTextField();
		txtSubtipo.setBounds(336, 481, 176, 19);
		txtSubtipo.setEditable(false);
		contentPane.add(txtSubtipo);
		
		btnAtras = new JButton("Cerrar");
		btnAtras.setBounds(933, 79, 117, 25);
		contentPane.add(btnAtras);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(660, 265, 88, 15);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setText((String) null);
		txtDni.setEditable(false);
		txtDni.setBounds(874, 263, 176, 19);
		contentPane.add(txtDni);
		
		txtNombre = new JTextField();
		txtNombre.setText((String) null);
		txtNombre.setEditable(false);
		txtNombre.setBounds(874, 290, 176, 19);
		contentPane.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setText((String) null);
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(874, 317, 176, 19);
		contentPane.add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.setText((String) null);
		txtCorreo.setEditable(false);
		txtCorreo.setBounds(874, 344, 176, 19);
		contentPane.add(txtCorreo);
		
		txtDireccion = new JTextField();
		txtDireccion.setText((String) null);
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(874, 371, 176, 19);
		contentPane.add(txtDireccion);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(660, 373, 88, 15);
		contentPane.add(lblDireccion);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(660, 346, 88, 15);
		contentPane.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(660, 319, 88, 15);
		contentPane.add(lblTelefono);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(660, 292, 88, 15);
		contentPane.add(lblNombre);
		
		txtFini = new JTextField();
		txtFini.setBounds(227, 181, 176, 19);
		contentPane.add(txtFini);
		txtFini.setEditable(false); 
		txtFini.setColumns(10);
		
		lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBounds(122, 183, 126, 15);
		contentPane.add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setBounds(442, 183, 126, 15);
		contentPane.add(lblFechaFin);
		
		txtFfin = new JTextField();
		txtFfin.setColumns(10);
		txtFfin.setBounds(536, 181, 176, 19);
		contentPane.add(txtFfin);
		txtFfin.setEditable(false); 
		
		lblPrecio = new JLabel("Precio total:");
		lblPrecio.setBounds(730, 183, 126, 15);
		contentPane.add(lblPrecio);
		
		txtPtotal = new JTextField();
		txtPtotal.setColumns(10);
		txtPtotal.setBounds(874, 181, 176, 19);
		txtPtotal.setEditable(false); 

		contentPane.add(txtPtotal);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cargarDatosVehiculo(vehiculo);
		cargarDatosCliente(cliente);
		cargarDatosAlquiler(alquiler);
	}

	public void cargarDatosVehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) return;

		txtMatricula.setText(vehiculo.getMatricula());
		txtMarca.setText(vehiculo.getMarca());
		txtModelo.setText(vehiculo.getModelo());
		txtPrecio.setText(String.valueOf(vehiculo.getPrecioH()));
		txtFechaMat.setText(vehiculo.getF_matriculacion().toString());
		txtColor.setText(vehiculo.getColor());
		txtPlazas.setText(String.valueOf(vehiculo.getPlazas()));
		txtProxMant.setText(vehiculo.getProximo_mantenimiento().toString());

		if (vehiculo instanceof modelo.Furgoneta) {
			txtTipo.setText("Furgoneta");
			txtSubtipo.setText(((modelo.Furgoneta) vehiculo).getTipo());
			txtSubtipo.setVisible(true);
			lblSubtipo.setVisible(true);
		} else if (vehiculo instanceof modelo.Turismo) {
			txtTipo.setText("Turismo");
			txtSubtipo.setText(((modelo.Turismo) vehiculo).getTipo());
			txtSubtipo.setVisible(true);
			lblSubtipo.setVisible(true);
		} else if (vehiculo instanceof modelo.Moto) {
			txtTipo.setText("Moto");
			txtSubtipo.setText("");
			txtSubtipo.setVisible(false); 
			lblSubtipo.setVisible(false); 
		}
	}
	
	private void cargarDatosCliente(Cliente cliente) {
		if (cliente == null) return;

		txtDni.setText(cliente.getDNI());
		txtNombre.setText(cliente.getNombre());
		txtTelefono.setText(cliente.getTfno());
		txtCorreo.setText(cliente.getCorreo());
		txtDireccion.setText(cliente.getDireccion());
	}
	
	private void cargarDatosAlquiler(Alquiler alquiler) {
		if (alquiler == null) return;

		txtFini.setText(alquiler.getFecha_ini().toString());
		txtFfin.setText(alquiler.getFecha_fin().toString());
		txtPtotal.setText(String.valueOf(alquiler.getTotal()));
	}
}
