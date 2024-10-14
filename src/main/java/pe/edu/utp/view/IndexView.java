package pe.edu.utp.view;

import pe.edu.utp.utils.SwingUTP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IndexView {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public IndexView() {
        // Initialize the main window
        SwingUTP.runWindow("Botica App", 800, 600, true);

        // Create a main panel with a border layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light background color

        // Create a panel for the menu buttons
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left-aligned menu
        menuPanel.setBackground(new Color(0, 163, 224)); // Menu background color
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Spacing

        // Create buttons with icons
        JButton medicamentoButton = createStyledCardButton("Gestionar Medicamentos", "path/to/medicamento_icon.png");
        JButton ventaButton = createStyledCardButton("Registrar Ventas", "path/to/venta_icon.png");
        JButton alertaButton = createStyledCardButton("Alertas de Vencimiento", "path/to/alerta_icon.png");
        JButton reporteButton = createStyledCardButton("Generar Reportes", "path/to/reporte_icon.png");
        JButton configuracionButton = createStyledCardButton("Configuración", "path/to/configuracion_icon.png");
        JButton salirButton = createStyledCardButton("Salir", "path/to/salir_icon.png");

        // Add actions to the buttons
        SwingUTP.addClickEvent(medicamentoButton, (ActionEvent e) -> showContent("Medicamento"));
        SwingUTP.addClickEvent(ventaButton, (ActionEvent e) -> showContent("Venta"));
        SwingUTP.addClickEvent(alertaButton, (ActionEvent e) -> showContent("Alerta"));
        SwingUTP.addClickEvent(reporteButton, (ActionEvent e) -> showContent("Reporte"));
        SwingUTP.addClickEvent(configuracionButton, (ActionEvent e) -> showContent("Configuracion"));
        SwingUTP.addClickEvent(salirButton, (ActionEvent e) -> System.exit(0));

        // Add buttons to the menu panel
        menuPanel.add(medicamentoButton);
        menuPanel.add(ventaButton);
        menuPanel.add(alertaButton);
        menuPanel.add(reporteButton);
        menuPanel.add(configuracionButton);
        menuPanel.add(salirButton);

        // Add the menu panel to the main panel
        mainPanel.add(menuPanel, BorderLayout.NORTH);

        // Create a content panel with CardLayout for displaying the views
        contentPanel = new JPanel();
        cardLayout = new CardLayout(); // Initialize CardLayout
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(Color.WHITE); // White background for content
        mainPanel.add(contentPanel, BorderLayout.CENTER); // Add content panel to the center

        // Create and add views to the content panel
        contentPanel.add(new MedicamentoViews(), "Medicamento");
        contentPanel.add(new VentaView(), "Venta");
        contentPanel.add(new AlertaView(), "Alerta");
        contentPanel.add(new ReporteView(), "Reporte");
        contentPanel.add(new ConfiguracionView(), "Configuracion");

        // Show the default view (first view)
        cardLayout.show(contentPanel, "Medicamento");

        // Add the main panel to the window
        SwingUTP.addControl(0, 0, mainPanel);
    }

    private JButton createStyledCardButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath)); // Set icon
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 163, 224));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 50)); // Preferred button size

        // Add mouse hover effects
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 200)); // Hover background color
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 163, 224)); // Original background color
            }
        });

        return button;
    }

    private void showContent(String viewName) {
        cardLayout.show(contentPanel, viewName); // Switch to the selected view
    }

    public static void main(String[] args) {
        // Create an instance of the main view to show the application
        new IndexView();
    }

    public void setVisible(boolean b) {
        // Placeholder method, implement visibility logic if needed
    }
}
