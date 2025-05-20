package gui;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bdd.DbAlquileres;
import modelo.Alquiler;

public class GestionarAlquileres extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public GestionarAlquileres() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(68, 97, 1209, 600);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        cargarTablaAlquileres();

        JLabel lblTitulo = new JLabel("Alquileres");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 25));
        lblTitulo.setBounds(77, 40, 270, 45);
        contentPane.add(lblTitulo);

        JButton btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(310, 60, 80, 25);
        btnAñadir.addActionListener(e -> abrirVentanaCrearAlquiler());
        contentPane.add(btnAñadir);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(402, 60, 90, 25);
        btnEliminar.addActionListener(e -> eliminarAlquiler());
        contentPane.add(btnEliminar);

        JButton btnVer = new JButton("Ver Alquiler");
        btnVer.setBounds(504, 60, 130, 25);
        btnVer.addActionListener(e -> verAlquiler());
        contentPane.add(btnVer);

        JButton btnMenu = new JButton("Menú");
        btnMenu.setBounds(1160, 60, 117, 25);
        btnMenu.addActionListener(e -> irAMenu());
        contentPane.add(btnMenu);
    }

    public void cargarTablaAlquileres() {
        String[] columnas = {"Cliente (DNI)", "Vehículo", "Fecha Inicio", "Fecha Fin", "Total (€)"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            DbAlquileres db = new DbAlquileres();
            ArrayList<Alquiler> lista = db.obtenerAlquileres();
            for (Alquiler a : lista) {
                Object[] fila = {
                    a.getCliente().getDNI(),
                    a.getVehiculo().getMatricula(),
                    a.getFecha_ini(),
                    a.getFecha_fin(),
                    a.getTotal()
                };
                modeloTabla.addRow(fila);
            }
            table.setModel(modeloTabla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaCrearAlquiler() {
        CrearAlquiler ventana = new CrearAlquiler();
        ventana.setVisible(true);
        dispose();
    }


    private void eliminarAlquiler() {
        int fila = table.getSelectedRow();
        if (fila != -1) {
            String dni = table.getValueAt(fila, 0).toString();
            String matricula = table.getValueAt(fila, 1).toString();
            LocalDate fechaIni = LocalDate.parse(table.getValueAt(fila, 2).toString());

            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que quieres eliminar el alquiler de " + dni + "?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    DbAlquileres db = new DbAlquileres();
                    if (db.eliminarAlquiler(dni, matricula, fechaIni)) {
                        JOptionPane.showMessageDialog(this, "Alquiler eliminado.");
                        cargarTablaAlquileres();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
        }
    }

    private void verAlquiler() {
        int fila = table.getSelectedRow();
        if (fila != -1) {
            String dni = table.getValueAt(fila, 0).toString();
            String matricula = table.getValueAt(fila, 1).toString();
            LocalDate fechaIni = LocalDate.parse(table.getValueAt(fila, 2).toString());

            try {
                DbAlquileres db = new DbAlquileres();
                for (Alquiler a : db.obtenerAlquileres()) {
                    if (a.getCliente().getDNI().equals(dni) &&
                        a.getVehiculo().getMatricula().equals(matricula) &&
                        a.getFecha_ini().equals(fechaIni)) {
                        InformacionAlquiler ventana = new InformacionAlquiler(a);
                        ventana.setVisible(true);
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar alquiler.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
        }
    }

    private void irAMenu() {
        Menu ventana = new Menu();
        ventana.setVisible(true);
        dispose();
    }
}
