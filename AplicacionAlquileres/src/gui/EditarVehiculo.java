package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class EditarVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarVehiculo frame = new EditarVehiculo();
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
	public EditarVehiculo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Matricula");
		lblNombreCompleto.setBounds(72, 50, 135, 15);
		contentPane.add(lblNombreCompleto);
		
		JLabel lblEmail = new JLabel("Precio/hora");
		lblEmail.setBounds(72, 77, 98, 15);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("Fecha Matriculación");
		lblDni.setBounds(72, 104, 148, 15);
		contentPane.add(lblDni);
		
		JLabel lblTelfono = new JLabel("Tipo");
		lblTelfono.setBounds(72, 131, 70, 15);
		contentPane.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Subtipo");
		lblDireccin.setBounds(72, 158, 70, 15);
		contentPane.add(lblDireccin);
		
		textField = new JTextField();
		textField.setBounds(225, 48, 170, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(225, 75, 170, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(225, 102, 170, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(225, 156, 170, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEditarVehiculo = new JLabel("Editar Vehiculo");
		lblEditarVehiculo.setBounds(177, 12, 114, 15);
		contentPane.add(lblEditarVehiculo);
		
		JButton btnCrear = new JButton("Guardar cambios");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCrear.setBounds(151, 197, 154, 25);
		contentPane.add(btnCrear);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(358, 0, 80, 25);
		contentPane.add(btnAtras);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Furgoneta");
		chckbxNewCheckBox.setBounds(161, 127, 103, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxTurismo = new JCheckBox("Turismo");
		chckbxTurismo.setBounds(268, 127, 91, 23);
		contentPane.add(chckbxTurismo);
		
		JCheckBox chckbxMoto = new JCheckBox("Moto");
		chckbxMoto.setBounds(363, 127, 75, 23);
		contentPane.add(chckbxMoto);
	}
}
