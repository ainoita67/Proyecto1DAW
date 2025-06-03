package gui;

import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.Mantenimiento;
import modelo.Vehiculo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarMantenimiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFecha;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldMatricula;
	private Mantenimiento mantenimientoActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				EditarMantenimiento frame = new EditarMantenimiento(null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public EditarMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimientoActual = mantenimiento;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEditarMantenimiento = new JLabel("Editar mantenimiento");
		lblEditarMantenimiento.setBounds(12, 0, 165, 15);
		contentPane.add(lblEditarMantenimiento);

		JLabel lblMatricula = new JLabel("Vehículo (matrícula):");
		lblMatricula.setBounds(12, 30, 150, 15);
		contentPane.add(lblMatricula);

		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(12, 50, 150, 19);
		textFieldMatricula.setEditable(false); // No editable
		contentPane.add(textFieldMatricula);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 80, 70, 15);
		contentPane.add(lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(12, 100, 150, 19);
		contentPane.add(textFieldFecha);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(12, 130, 114, 15);
		contentPane.add(lblDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(12, 150, 200, 90);
		contentPane.add(textAreaDescripcion);

		JButton btnGuardar = new JButton("Guardar cambios");
		btnGuardar.setBounds(240, 50, 160, 25);
		contentPane.add(btnGuardar);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(240, 10, 80, 25);
		contentPane.add(btnAtras);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarMantenimiento();
			}
		});

		btnAtras.addActionListener(e -> {
			GestionarMantenimiento ventana = new GestionarMantenimiento();
			ventana.setVisible(true);
			dispose();
		});

		cargarDatosMantenimiento(mantenimientoActual);
	}

	private void cargarDatosMantenimiento(Mantenimiento mantenimiento) {
		if (mantenimiento != null) {
			textFieldMatricula.setText(mantenimiento.getVehiculo().getMatricula());
			textFieldFecha.setText(mantenimiento.getFecha().toString());
			textAreaDescripcion.setText(mantenimiento.getDescripcion());
		}
	}

	private void actualizarMantenimiento() {
		try {
			if (textFieldFecha.getText().isEmpty() || textAreaDescripcion.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
				return;
			}

			LocalDate fecha = LocalDate.parse(textFieldFecha.getText());
			String descripcion = textAreaDescripcion.getText();

			// Actualizamos el objeto
			mantenimientoActual.setFecha(fecha);
			mantenimientoActual.setDescripcion(descripcion);

			// Llamamos a la BDD
			bdd.DbVehiculo db = new bdd.DbVehiculo();
			if (db.actualizarMantenimiento(mantenimientoActual)) {
				JOptionPane.showMessageDialog(this, "Mantenimiento actualizado correctamente.");
				dispose();
				GestionarMantenimiento ventana = new GestionarMantenimiento();
				ventana.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Error al actualizar el mantenimiento.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al actualizar el mantenimiento.");
		}
	}
}
