package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;
import bdd.DbAlquileres;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InformacionCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni, txtNombre, txtTelefono, txtCorreo, txtDireccion;
	private JTable tablaVehiculos;
	private JButton btnCerrar;

	public InformacionCliente(Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Información del cliente");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitulo.setBounds(122, 106, 390, 36);
		contentPane.add(lblTitulo);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(122, 186, 88, 15);
		contentPane.add(lblDni);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(122, 213, 88, 15);
		contentPane.add(lblNombre);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(122, 240, 88, 15);
		contentPane.add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(122, 267, 88, 15);
		contentPane.add(lblCorreo);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(122, 294, 88, 15);
		contentPane.add(lblDireccion);

		txtDni = new JTextField();
		txtDni.setBounds(336, 184, 176, 19);
		txtDni.setEditable(false);
		contentPane.add(txtDni);

		txtNombre = new JTextField();
		txtNombre.setBounds(336, 211, 176, 19);
		txtNombre.setEditable(false);
		contentPane.add(txtNombre);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(336, 238, 176, 19);
		txtTelefono.setEditable(false);
		contentPane.add(txtTelefono);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(336, 265, 176, 19);
		txtCorreo.setEditable(false);
		contentPane.add(txtCorreo);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(336, 292, 176, 19);
		txtDireccion.setEditable(false);
		contentPane.add(txtDireccion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(576, 186, 574, 392);
		contentPane.add(scrollPane);

		tablaVehiculos = new JTable();
		scrollPane.setViewportView(tablaVehiculos);

		JLabel lblVehiculos = new JLabel("Vehículos alquilados");
		lblVehiculos.setFont(new Font("Dialog", Font.BOLD, 16));
		lblVehiculos.setBounds(950, 138, 200, 36);
		contentPane.add(lblVehiculos);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(1033, 79, 117, 25);
		contentPane.add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cargarDatosCliente(cliente);
		cargarTablaAlquileres(cliente);
	}

	private void cargarDatosCliente(Cliente cliente) {
		if (cliente == null) return;

		txtDni.setText(cliente.getDNI());
		txtNombre.setText(cliente.getNombre());
		txtTelefono.setText(cliente.getTfno());
		txtCorreo.setText(cliente.getCorreo());
		txtDireccion.setText(cliente.getDireccion());
	}

	private void cargarTablaAlquileres(Cliente cliente) {
		String[] columnas = {"Matricula", "Marca", "Modelo", "Tipo", "Fecha Inicio", "Fecha Fin", "Total"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		try {
			DbAlquileres dbAlquileres = new DbAlquileres();
			ArrayList<Alquiler> lista = dbAlquileres.obtenerAlquileresPorCliente(cliente.getDNI());

			for (Alquiler a : lista) {
				String tipo = "";
				if (a.getVehiculo() instanceof modelo.Furgoneta) {
					tipo = "Furgoneta";
				} else if (a.getVehiculo() instanceof modelo.Turismo) {
					tipo = "Turismo";
				} else if (a.getVehiculo() instanceof modelo.Moto) {
					tipo = "Moto";
				}
				
				Object[] fila = {
					a.getVehiculo().getMatricula(),
					a.getVehiculo().getMarca(),
					a.getVehiculo().getModelo(),
					tipo,
					a.getFecha_ini(),
					a.getFecha_fin(),
					a.getTotal()
				};
				modelo.addRow(fila);
			}
			tablaVehiculos.setModel(modelo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
