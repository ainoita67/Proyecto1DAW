package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import bdd.Conexion;
import bdd.DbUsuario;
import modelo.Sesion;
import modelo.Usuario;

/**
 * @author Pyto_Grupo_D
 * @version 1.0
 * 
 * Ventana que permite el inicio de sesión a los usuarios de la aplicación.
 * Pide introducir un DNI y contraseña, que son validadas. Si la autenticación
 * es correcta redirige al menú princial.
 * 
 */
public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	/**Panel principal de la ventana*/
	private JPanel contentPane;
	/**Campo de texto para ingresar el DNI*/
	private JTextField txtDni;
	/**Campo de texto para ingresar la contraseña*/
	private JTextField txtContrasea;

	/**
	 * Método principal. Lanza la aplicación y muestra el inicio de sesión.
	 * 
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
	 * Constructor que inicializa el inicio de sesión y sus partes.
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
		
		txtDni = new JTextField();
		txtDni.setText("DNI");
		txtDni.setBounds(164, 80, 114, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtDni.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent evt) {
		        if (txtDni.getText().equals("DNI")) {
		        	txtDni.setText("");
		        }
		    }
		    public void focusLost(FocusEvent evt) {
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
		
		txtContrasea.addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent evt) {
		        if (txtContrasea.getText().equals("Contraseña")) {
		        	txtContrasea.setText("");
		        }
		    }
		    public void focusLost(FocusEvent evt) {
		        if (txtContrasea.getText().isEmpty()) {
		        	txtContrasea.setText("Contraseña");
		        }
		    }
		});
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(161, 144, 117, 25);
		contentPane.add(btnEnviar);

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = txtDni.getText();
				String contrasena = txtContrasea.getText();
				
		        if (DbUsuario.verificarCredenciales(dni, contrasena)) {
		            try {
		                DbUsuario dbUsuario = new DbUsuario();
		                Usuario usuario = dbUsuario.ver1Usuario(dni);
		                if (usuario != null) {
		                    Sesion.setUsuarioActivo(usuario); 

		                    Menu menu = new Menu(); 
		                    menu.setVisible(true);

		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error al obtener los datos del usuario.");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "DNI o contraseña incorrectos.");
		        }		
			}
		});
	}

}
