package pe.edu.utp.view;

import pe.edu.utp.controllers.MedicamentoController;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Medicamento;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class InventarioView extends JFrame {
    private MedicamentoController inventarioController;
    private JTable tableMedicamentos;
    private DefaultTableModel tableModel;
    private JTextField stockMinimoField;
    private JButton verificarStockButton;
    private JButton actualizarStockButton;

    public InventarioView(MedicamentoController controller) {
        this.inventarioController = controller;
        setTitle("Gestión de Inventario - Botica App");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Personalización global
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 13));
        UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));

        // Encabezado con diseño
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(34, 139, 34));
        JLabel titleLabel = new JLabel("Gestión de Inventario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        add(headerPanel, BorderLayout.NORTH);

        // Panel de opciones superiores
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Acciones de Inventario"));
        stockMinimoField = new JTextField(10);
        verificarStockButton = new JButton("Verificar Stock Mínimo");
        actualizarStockButton = new JButton("Actualizar Stock");
        topPanel.add(new JLabel("Stock Mínimo:"));
        topPanel.add(stockMinimoField);
        topPanel.add(verificarStockButton);
        topPanel.add(actualizarStockButton);

        // Tabla estilizada
        String[] columnNames = {"ID", "Nombre", "Descripción", "Stock", "Precio Actual", "Fecha Ingreso", "Fecha Caducidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableMedicamentos = new JTable(tableModel);
        tableMedicamentos.setRowHeight(25);
        tableMedicamentos.setSelectionBackground(new Color(34, 139, 34));
        tableMedicamentos.setSelectionForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(tableMedicamentos);

        // Agregar paneles al layout principal
        add(topPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Cargar datos iniciales
        cargarInventarioCompleto();

        // Acciones de botones
        verificarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarStockMinimo();
            }
        });

        actualizarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarStock();
            }
        });
    }

    private void cargarInventarioCompleto() {
        List<Medicamento> medicamentos = inventarioController.obtenerInventarioCompleto();
        tableModel.setRowCount(0); // Limpiar tabla
        for (Medicamento med : medicamentos) {
            tableModel.addRow(new Object[]{
                    med.getIdMedicamento(),
                    med.getNombre(),
                    med.getDescripcion(),
                    med.getStock(),
                    med.getPrecioActual(),
                    med.getFechaIngreso(),
                    med.getFechaCaducidad()
            });
        }
    }

    private void verificarStockMinimo() {
        int stockMinimo;
        try {
            stockMinimo = Integer.parseInt(stockMinimoField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para el stock mínimo.");
            return;
        }

        List<Medicamento> medicamentos = inventarioController.verificarStockMinimo(stockMinimo);
        tableModel.setRowCount(0); // Limpiar tabla
        for (Medicamento med : medicamentos) {
            tableModel.addRow(new Object[]{
                    med.getIdMedicamento(),
                    med.getNombre(),
                    med.getDescripcion(),
                    med.getStock(),
                    med.getPrecioActual(),
                    med.getFechaIngreso(),
                    med.getFechaCaducidad()
            });
        }
    }

    private void actualizarStock() {
        int selectedRow = tableMedicamentos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un medicamento para actualizar el stock.");
            return;
        }

        int idMedicamento = (int) tableModel.getValueAt(selectedRow, 0);
        String nuevoStockStr = JOptionPane.showInputDialog(this, "Nuevo stock:");
        int nuevoStock;
        try {
            nuevoStock = Integer.parseInt(nuevoStockStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para el stock.");
            return;
        }

        inventarioController.actualizarStock(idMedicamento, nuevoStock);
        cargarInventarioCompleto();
        JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.");
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            MedicamentoController controller = new MedicamentoController(connection);
            InventarioView view = new InventarioView(controller);
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
