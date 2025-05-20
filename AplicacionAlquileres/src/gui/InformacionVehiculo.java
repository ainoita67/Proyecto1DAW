package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Alquiler;
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

public class InformacionVehiculo extends JFrame {

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
	
	private JTable tablaAlquileres;
	private JButton btnAtras;


	public InformacionVehiculo(Vehiculo vehiculo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformacionDelVehiculo = new JLabel("Información del vehiculo");
		lblInformacionDelVehiculo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblInformacionDelVehiculo.setBounds(122, 106, 390, 36);
		contentPane.add(lblInformacionDelVehiculo);

		JLabel lblMatrcula = new JLabel("Matrícula: ");
		lblMatrcula.setBounds(122, 186, 88, 15);
		contentPane.add(lblMatrcula);

		JLabel lblMarca = new JLabel("Marca: ");
		lblMarca.setBounds(122, 213, 70, 15);
		contentPane.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(122, 240, 70, 15);
		contentPane.add(lblModelo);

		JLabel lblPreciodia = new JLabel("Precio/Dia");
		lblPreciodia.setBounds(122, 267, 88, 15);
		contentPane.add(lblPreciodia);

		JLabel lblFechaDeMatriculacin = new JLabel("Fecha de matriculación:");
		lblFechaDeMatriculacin.setBounds(122, 294, 186, 15);
		contentPane.add(lblFechaDeMatriculacin);

		JLabel lblPlazas = new JLabel("Plazas:");
		lblPlazas.setBounds(122, 321, 70, 15);
		contentPane.add(lblPlazas);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(122, 348, 70, 15);
		contentPane.add(lblColor);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(122, 375, 70, 15);
		contentPane.add(lblTipo);

		JLabel lblPrximoMantenimiento = new JLabel("Próximo mantenimiento:");
		lblPrximoMantenimiento.setBounds(122, 429, 186, 15);
		contentPane.add(lblPrximoMantenimiento);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(336, 184, 176, 19);
		txtMatricula.setEditable(false); 
		contentPane.add(txtMatricula);

		txtMarca = new JTextField();
		txtMarca.setBounds(336, 211, 176, 19);
		txtMarca.setEditable(false); 
		contentPane.add(txtMarca);

		txtModelo = new JTextField();
		txtModelo.setBounds(336, 238, 176, 19);
		txtModelo.setEditable(false); 
		contentPane.add(txtModelo);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(336, 265, 176, 19);
		txtPrecio.setEditable(false); 
		contentPane.add(txtPrecio);

		txtFechaMat = new JTextField();
		txtFechaMat.setBounds(336, 292, 176, 19);
		txtFechaMat.setEditable(false); 
		contentPane.add(txtFechaMat);

		txtPlazas = new JTextField();
		txtPlazas.setBounds(336, 319, 176, 19);
		txtPlazas.setEditable(false);
		contentPane.add(txtPlazas);

		txtColor = new JTextField();
		txtColor.setBounds(336, 346, 176, 19);
		txtColor.setEditable(false); 
		contentPane.add(txtColor);

		txtTipo = new JTextField();
		txtTipo.setBounds(336, 373, 176, 19);
		txtTipo.setEditable(false); 
		contentPane.add(txtTipo);

		txtProxMant = new JTextField();
		txtProxMant.setBounds(336, 427, 176, 19);
		txtProxMant.setEditable(false);
		contentPane.add(txtProxMant);

		lblSubtipo = new JLabel("Subtipo:");
		lblSubtipo.setBounds(122, 402, 70, 15);
		contentPane.add(lblSubtipo);

		txtSubtipo = new JTextField();
		txtSubtipo.setBounds(336, 400, 176, 19);
		txtSubtipo.setEditable(false);
		contentPane.add(txtSubtipo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(576, 186, 474, 392);
		contentPane.add(scrollPane);
		
		tablaAlquileres = new JTable();
		scrollPane.setViewportView(tablaAlquileres);

		JLabel lblAlquileres = new JLabel("Alquileres");
		lblAlquileres.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAlquileres.setBounds(948, 138, 102, 36);
		contentPane.add(lblAlquileres);
		
		btnAtras = new JButton("Cerrar");
		btnAtras.setBounds(933, 79, 117, 25);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cargarDatosVehiculo(vehiculo);
		cargarTablaAlquileres(vehiculo);
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
	
	private void cargarTablaAlquileres(Vehiculo vehiculo) {
		String[] columnas = {"DNI", "Nombre", "Fecha Inicio", "Fecha Fin", "Total"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		try {
			DbAlquileres dbAlquileres = new DbAlquileres();
			ArrayList<Alquiler> lista = dbAlquileres.obtenerAlquileresPorVehiculo(vehiculo.getMatricula());

			for (Alquiler a : lista) {
				Object[] fila = {
					a.getCliente().getDNI(),
					a.getCliente().getNombre(),
					a.getFecha_ini(),
					a.getFecha_fin(),
					a.getTotal()
				};
				modelo.addRow(fila);
			}
			tablaAlquileres.setModel(modelo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
