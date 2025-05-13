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
        setBounds(100, 100, 788, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 71, 733, 250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        cargarTablaUsuarios(2); 

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

        JButton btnCargarEmpleados = new JButton("Ver Empleados");
        btnCargarEmpleados.setBounds(440, 37, 139, 25);
        contentPane.add(btnCargarEmpleados);
        btnCargarEmpleados.addActionListener(e -> cargarTablaUsuarios(2));

        JButton btnCargarAdmins = new JButton("Ver Adminstadores");
        btnCargarAdmins.setBounds(591, 37, 169, 25);
        contentPane.add(btnCargarAdmins);
        btnCargarAdmins.addActionListener(e -> cargarTablaUsuarios(3));
    }
    
    private void abrirVentanaCrearUsuario() {
	    CrearUsuario ventanaCrear = new CrearUsuario();
	    ventanaCrear.setVisible(true);
	}
    
	private void editarUsuario() {
	    int filaSeleccionada = table.getSelectedRow();
	    
	    if (filaSeleccionada != -1) {
	        String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();

	        try {
	            DbUsuario dbUsuario = new DbUsuario();
	            Usuario usuarioSeleccionado = dbUsuario.ver1Usuario(dniSeleccionado);

	            if (usuarioSeleccionado != null) {
	                EditarEmpleado ventanaEditar = new EditarEmpleado(usuarioSeleccionado);
	                ventanaEditar.setVisible(true);
	                dispose(); // Cerrar
	            } else {
	                JOptionPane.showMessageDialog(this, "No se encontró el cliente.");
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error al obtener datos.");
	        }

	    } else {
	        JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
	    }
	}
    
	public void cargarTablaUsuarios(int rol) {
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
        }
    }

    private void eliminarUsuario() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            String dniSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
            int rol = table.getValueAt(filaSeleccionada, 5).equals("Empleado") ? 2 : 3;
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario con DNI: " + dniSeleccionado + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    conexion = new DbUsuario();
                    if (conexion.eliminarUsuario(dniSeleccionado, rol)) {
                        JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                        cargarTablaUsuarios(rol);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar usuario.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error de conexión.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
        }
    }
}

