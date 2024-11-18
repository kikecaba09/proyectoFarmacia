package pe.edu.utp.view;

import pe.edu.utp.controllers.MedicamentoController;
import pe.edu.utp.models.Medicamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class MedicamentoView extends JFrame {

    private JTable table;
    private JButton btnAgregar, btnEliminar, btnGuardarCambios;
    private MedicamentoController controller;

    public MedicamentoView(MedicamentoController controller) {
        this.controller = controller;

        // Configuración de la ventana principal
        setTitle("Gestión de Medicamentos");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Fondo blanco

        // Configurar encabezado
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 102, 204)); // Azul corporativo
        panelHeader.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel lblTitulo = new JLabel("Gestión de Medicamentos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblTitulo, BorderLayout.CENTER);

        // Panel de tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes internos

        // Crear JTable
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio", "Stock"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Hacer todas las columnas editables excepto el ID
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0, 4 -> Integer.class; // ID y Stock como enteros
                    case 3 -> Double.class; // Precio como decimal
                    default -> String.class; // Las demás como texto
                };
            }
        };

        table = new JTable(model);
        table.setRowHeight(25); // Altura de las filas
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 102, 204)); // Azul en encabezados
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setGridColor(new Color(224, 224, 224));

        JScrollPane scrollPane = new JScrollPane(table);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        btnAgregar = crearBoton("Agregar", new Color(0, 153, 51), Color.WHITE);
        btnEliminar = crearBoton("Eliminar", new Color(204, 0, 0), Color.WHITE);
        btnGuardarCambios = crearBoton("Guardar Cambios", new Color(0, 102, 204), Color.WHITE);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnGuardarCambios);

        // Agregar componentes
        add(panelHeader, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Cargar los medicamentos en la tabla
        cargarMedicamentos();

        // Configurar acciones de los botones
        configurarEventos();

        // Mostrar la ventana
        setVisible(true);
    }

    private JButton crearBoton(String texto, Color bgColor, Color fgColor) {
        JButton boton = new JButton(texto);
        boton.setBackground(bgColor);
        boton.setForeground(fgColor);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(160, 40));
        return boton;
    }

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{0, "Nuevo Medicamento", "Descripción", 0.0, 0});
        });

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id = (int) table.getValueAt(filaSeleccionada, 0);
                if (id != 0) {
                    controller.eliminarMedicamento(id);
                }
                ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
            } else {
                mostrarMensaje("Seleccione un medicamento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnGuardarCambios.addActionListener(e -> guardarCambios());
    }

    private void guardarCambios() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = (int) model.getValueAt(i, 0);
            String nombre = (String) model.getValueAt(i, 1);
            String descripcion = (String) model.getValueAt(i, 2);
            double precio = (double) model.getValueAt(i, 3);
            int stock = (int) model.getValueAt(i, 4);

            Medicamento medicamento = new Medicamento(id, nombre, descripcion, precio, stock);

            if (id == 0) {
                controller.agregarMedicamento(medicamento);
            } else {
                controller.editarMedicamento(medicamento);
            }
        }
        cargarMedicamentos();
    }

    private void cargarMedicamentos() {
        List<Medicamento> medicamentos = controller.obtenerTodosLosMedicamentos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (Medicamento medicamento : medicamentos) {
            model.addRow(new Object[]{
                    medicamento.getIdMedicamento(),
                    medicamento.getNombre(),
                    medicamento.getDescripcion(),
                    medicamento.getPrecioActual(),
                    medicamento.getStock()
            });
        }
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }

    public static void main(String[] args) {
        Connection connection = null;
        MedicamentoController controller = new MedicamentoController(connection);
        new MedicamentoView(controller);
    }
}
