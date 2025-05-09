package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CrearVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtPrecio;
	private JTextField txtFechaMatr;
	private JTextField txtSubtipo;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(218, 47, 143, 19);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblCrearVehiculo = new JLabel("Crear Vehiculo");
		lblCrearVehiculo.setBounds(169, 7, 114, 15);
		contentPane.add(lblCrearVehiculo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(347, 0, 78, 29);
		contentPane.add(btnAtras);
		
		JLabel lblNombreCompleto = new JLabel("Matricula");
		lblNombreCompleto.setBounds(57, 49, 143, 15);
		contentPane.add(lblNombreCompleto);
		
		JLabel lblEmail = new JLabel("Precio/hora");
		lblEmail.setBounds(57, 76, 143, 15);
		contentPane.add(lblEmail);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(218, 74, 143, 19);
		contentPane.add(txtPrecio);
		
		txtFechaMatr = new JTextField();
		txtFechaMatr.setColumns(10);
		txtFechaMatr.setBounds(218, 105, 143, 19);
		contentPane.add(txtFechaMatr);
		
		JLabel lblDNI = new JLabel("Fecha Matriculación");
		lblDNI.setBounds(57, 107, 171, 15);
		contentPane.add(lblDNI);
		
		JLabel lblTelefono = new JLabel("Tipo");
		lblTelefono.setBounds(57, 134, 55, 15);
		contentPane.add(lblTelefono);
		
		txtSubtipo = new JTextField();
		txtSubtipo.setColumns(10);
		txtSubtipo.setBounds(218, 163, 143, 19);
		contentPane.add(txtSubtipo);
		
		JLabel lblDireccion = new JLabel("Subtipo");
		lblDireccion.setBounds(57, 165, 143, 15);
		contentPane.add(lblDireccion);
		
		JButton btnCrear = new JButton("+ Crear");
		btnCrear.setBounds(157, 208, 116, 29);
		contentPane.add(btnCrear);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Moto");
		chckbxNewCheckBox.setBounds(348, 132, 65, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxFurgoneta = new JCheckBox("Furgoneta");
		chckbxFurgoneta.setBounds(157, 132, 99, 23);
		contentPane.add(chckbxFurgoneta);
		
		JCheckBox chckbxTurismo = new JCheckBox("Turismo");
		chckbxTurismo.setBounds(260, 132, 129, 23);
		contentPane.add(chckbxTurismo);
	}
}
