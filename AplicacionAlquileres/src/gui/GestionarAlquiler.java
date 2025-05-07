package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


public class GestionarAlquiler extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtBuscarDni;
	private JTextField txtBuscarMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarAlquiler frame = new GestionarAlquiler();
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
	public GestionarAlquiler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 100, 400, 151);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Vehiculos");
		lblNewLabel_1.setBounds(27, 29, 70, 15);
		contentPane.add(lblNewLabel_1);
		
		txtBuscarMatricula = new JTextField();
		txtBuscarMatricula.setText("Buscar Matricula");
		txtBuscarMatricula.setBounds(104, 27, 114, 19);
		contentPane.add(txtBuscarMatricula);
		txtBuscarMatricula.setColumns(10);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
		txtBuscarMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        if (txtBuscarMatricula.getText().equals("Buscar Matricula")) {
		        	txtBuscarMatricula.setText("");
		        }
		    }
		    
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
		        if (txtBuscarMatricula.getText().isEmpty()) {
		        	txtBuscarMatricula.setText("Buscar Matricula");
		        }
		    }
		});
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(152, 63, 80, 25);
		contentPane.add(btnAadir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(245, 63, 80, 25);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(337, 63, 90, 25);
		contentPane.add(btnEliminar);
		
		JButton btnMen = new JButton("Menú");
		btnMen.setBounds(321, 0, 117, 25);
		contentPane.add(btnMen);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBounds(27, 0, 70, 15);
		contentPane.add(lblNewLabel);
		
		txtBuscarDni = new JTextField();
		txtBuscarDni.setText("Buscar DNI");
		txtBuscarDni.setColumns(10);
		txtBuscarDni.setBounds(104, 0, 114, 19);
		contentPane.add(txtBuscarDni);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
				txtBuscarDni.addFocusListener(new java.awt.event.FocusAdapter() {
				    public void focusGained(java.awt.event.FocusEvent evt) {
				        if (txtBuscarDni.getText().equals("Buscar DNI")) {
				        	txtBuscarDni.setText("");
				        }
				    }
				    
				    public void focusLost(java.awt.event.FocusEvent evt) {
				        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
				        if (txtBuscarDni.getText().isEmpty()) {
				        	txtBuscarDni.setText("Buscar DNI");
				        }
				    }
				});

				
				
				
	}
}
