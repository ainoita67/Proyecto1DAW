package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bdd.DbUsuario;
import modelo.Usuario;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GestionarUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtBuscarDni;
    private DbUsuario conexion;
    private int currentRol = 0; // 0 para todos, 2 para empleados, 3 para admins

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GestionarUsuarios frame = new GestionarUsuarios();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionarUsuarios() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 904, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 71, 855, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Cargar todos los usuarios al iniciar
        cargarTodosUsuarios();

        txtBuscarDni = new JTextField("Buscar DNI");
        txtBuscarDni.setBounds(27, 40, 114, 19);
        contentPane.add(txtBuscarDni);
        txtBuscarDni.setColumns(10);

        JButton btnAadir = new JButton("Añadir");
        btnAadir.setBounds(153, 37, 80, 25);
        contentPane.add(btnAadir);
        btnAadir.addActionListener(e -> abrirVentanaCrearUsuario());

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(245, 37, 80, 25);
        contentPane.add(btnEditar);
        btnEditar.addActionListener(e -> editarUsuario());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(337, 37, 90, 25);
        contentPane.add(btnEliminar);
        btnEliminar.addActionListener(e -> eliminarUsuario());

        // Botón para ver todos los usuarios
        JButton btnCargarTodos = new JButton("Ver Todos");
        btnCargarTodos.setBounds(440, 37, 105, 25);
        contentPane.add(btnCargarTodos);
        btnCargarTodos.addActionListener(e -> cargarTodosUsuarios());

        JButton btnCargarEmpleados = new JButton("Ver Empleados");
        btnCargarEmpleados.setBounds(557, 37, 144, 25);
        contentPane.add(btnCargarEmpleados);
        btnCargarEmpleados.addActionListener(e -> cargarTablaUsuarios(2));

        JButton btnCargarAdmins = new JButton("Ver Adminstadores");
        btnCargarAdmins.setBounds(713, 37, 169, 25);
        contentPane.add(btnCargarAdmins);
        btnCargarAdmins.addActionListener(e -> cargarTablaUsuarios(3));
        
        JLabel lblTitle = new JLabel("Gestión de Usuarios");
        lblTitle.setBounds(395, 2, 150, 20);
        contentPane.add(lblTitle);
        
        JButton btnMen = new JButton("Menú");
		btnMen.setBounds(765, 0, 117, 25);
		contentPane.add(btnMen);
		
		btnMen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        irAMenu();
		    }
		});
        
    }
    
    private void abrirVentanaCrearUsuario() {
        CrearUsuario ventanaCrear = new CrearUsuario();
        ventanaCrear.setVisible(true);
    }
    
    private void editarUsuario() {
        int filaSeleccionada = table.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
            int rol = table.getValueAt(filaSeleccionada, 5).toString().equals("Empleado") ? 2 : 3;

            try {
                DbUsuario dbUsuario = new DbUsuario();
                Usuario usuarioSeleccionado = dbUsuario.ver1Usuario(dniSeleccionado, rol);

                if (usuarioSeleccionado != null) {
                    EditarEmpleado ventanaEditar = new EditarEmpleado(usuarioSeleccionado);
                    ventanaEditar.setVisible(true);
                    dispose(); // Cerrar ventana actual
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el usuario.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener datos del usuario: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
        }
    }
    
    // Método para cargar usuarios según el rol
    public void cargarTablaUsuarios(int rol) {
        this.currentRol = rol;
        String[] columnas = {"DNI", "Nombre", "Teléfono", "Correo", "Dirección", "Rol"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        try {
            DbUsuario dbUsuario = new DbUsuario();
            ArrayList<Usuario> lista = dbUsuario.verTodosUsuarios(rol);
            for (Usuario u : lista) {
                Object[] fila = {u.getDNI(), u.getNombre(), u.getTfno(), u.getCorreo(), u.getDireccion(), u.getRol() == 2 ? "Empleado" : "Admin"};
                modeloTabla.addRow(fila);
            }
            table.setModel(modeloTabla);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
        }
    }
    
    // Método nuevo para cargar todos los usuarios
    public void cargarTodosUsuarios() {
        this.currentRol = 0; // 0 indica todos los roles
        String[] columnas = {"DNI", "Nombre", "Teléfono", "Correo", "Dirección", "Rol"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        try {
            DbUsuario dbUsuario = new DbUsuario();
            
            // Cargar empleados (rol 2)
            ArrayList<Usuario> listaEmpleados = dbUsuario.verTodosUsuarios(2);
            for (Usuario u : listaEmpleados) {
                Object[] fila = {u.getDNI(), u.getNombre(), u.getTfno(), u.getCorreo(), u.getDireccion(), "Empleado"};
                modeloTabla.addRow(fila);
            }
            
            // Cargar administradores (rol 3)
            ArrayList<Usuario> listaAdmins = dbUsuario.verTodosUsuarios(3);
            for (Usuario u : listaAdmins) {
                Object[] fila = {u.getDNI(), u.getNombre(), u.getTfno(), u.getCorreo(), u.getDireccion(), "Admin"};
                modeloTabla.addRow(fila);
            }
            
            table.setModel(modeloTabla);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar todos los usuarios: " + e.getMessage());
        }
    }

    private void eliminarUsuario() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
            int rol = table.getValueAt(filaSeleccionada, 5).toString().equals("Empleado") ? 2 : 3;
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario con DNI: " + dniSeleccionado + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    conexion = new DbUsuario();
                    if (conexion.eliminarUsuario(dniSeleccionado, rol)) {
                        JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                        // Si estamos viendo todos los usuarios, recargamos todos
                        if (currentRol == 0) {
                            cargarTodosUsuarios();
                        } else {
                            cargarTablaUsuarios(rol);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar usuario.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
        }
    }
    
    private void irAMenu() {
    	GestionarUsuarios ventanausuario = new GestionarUsuarios();
	    Menu ventanamenu = new Menu();
	    ventanamenu.setVisible(true);
	    ventanausuario.setVisible(false);
	}
}
