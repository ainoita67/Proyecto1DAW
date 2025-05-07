package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar Sesión");
		lblIniciarSesin.setBounds(164, 53, 100, 15);
		contentPane.add(lblIniciarSesin);
		
		//campo DNI
		txtDni = new JTextField();
		txtDni.setText("DNI");
		txtDni.setBounds(164, 80, 114, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
		txtDni.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        if (txtDni.getText().equals("DNI")) {
		        	txtDni.setText("");
		        }
		    }
		    
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
		        if (txtDni.getText().isEmpty()) {
		        	txtDni.setText("DNI");
		        }
		    }
		});
		
		txtContrasea = new JTextField();
		txtContrasea.setText("Contraseña");
		txtContrasea.setBounds(164, 111, 114, 19);
		contentPane.add(txtContrasea);
		txtContrasea.setColumns(10);
		
		// Evento para borrar el "placeholder" cuando el usuario hace clic
		txtContrasea.addFocusListener(new java.awt.event.FocusAdapter() {
		    public void focusGained(java.awt.event.FocusEvent evt) {
		        if (txtContrasea.getText().equals("Contraseña")) {
		        	txtContrasea.setText("");
		        }
		    }
		    
		    public void focusLost(java.awt.event.FocusEvent evt) {
		        // Si el usuario deja el campo vacío, vuelve a mostrar el placeholder
		        if (txtContrasea.getText().isEmpty()) {
		        	txtContrasea.setText("Contraseña");
		        }
		    }
		});
		
		JButton btnEnviar = new JButton("enviar");
		btnEnviar.setBounds(161, 144, 117, 25);
		contentPane.add(btnEnviar);
	}
}
