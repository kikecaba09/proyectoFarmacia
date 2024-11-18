package pe.edu.utp.view;

import pe.edu.utp.controllers.ReporteController;
import pe.edu.utp.ejecucion.ConexionBD;
import pe.edu.utp.models.Venta;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class ReporteView extends JFrame {
    private ReporteController reporteController;
    private JTable tableReporte;
    private DefaultTableModel tableModel;
    private JTextField mesField;
    private JTextField anioField;
    private JButton generarReporteButton;

    public ReporteView(ReporteController controller) {
        this.reporteController = controller;

        // Configuración de la ventana principal
        setTitle("Generación de Reportes - Sistema de Ventas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);

        // Colores personalizados
        Color primaryColor = new Color(33, 150, 243); // Azul
        Color secondaryColor = new Color(245, 245, 245); // Gris claro
        Color accentColor = new Color(255, 87, 34); // Naranja

        // Fuentes personalizadas
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Panel superior con título y formulario
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(primaryColor);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Generación de Reportes Mensuales");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        formPanel.setBackground(primaryColor);
        formPanel.add(createLabeledField("Mes (MM):", mesField = new JTextField(5), labelFont));
        formPanel.add(createLabeledField("Año (YYYY):", anioField = new JTextField(5), labelFont));
        generarReporteButton = createButton("Generar Reporte", buttonFont, accentColor, Color.WHITE);
        formPanel.add(generarReporteButton);
        topPanel.add(formPanel, BorderLayout.SOUTH);

        // Tabla de resultados
        String[] columnNames = {"ID Venta", "Fecha", "Total", "Usuario"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableReporte = new JTable(tableModel);
        tableReporte.setRowHeight(30);
        tableReporte.setFont(new Font("Arial", Font.PLAIN, 12));
        tableReporte.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableReporte.getTableHeader().setBackground(primaryColor);
        tableReporte.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(tableReporte);

        // Diseño principal
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Acción del botón
        generarReporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
    }

    private void generarReporte() {
        String mes = mesField.getText();
        String anio = anioField.getText();

        if (mes.isEmpty() || anio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa los campos de mes y año.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Venta> ventas = reporteController.generarReporteMensual(mes, anio);
        tableModel.setRowCount(0); // Limpiar tabla
        for (Venta venta : ventas) {
            tableModel.addRow(new Object[]{
                    venta.getIdVenta(),
                    venta.getFecha(),
                    String.format("S/ %.2f", venta.getTotal()),
                    venta.getIdUsuario()
            });
        }
    }

    private JPanel createLabeledField(String labelText, JTextField textField, Font font) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(new Color(33, 150, 243)); // Fondo azul
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(Color.WHITE);
        textField.setPreferredSize(new Dimension(80, 30));
        textField.setFont(font);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JButton createButton(String text, Font font, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
            ReporteController controller = new ReporteController(connection);
            ReporteView view = new ReporteView(controller);
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
