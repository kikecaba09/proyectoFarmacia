package pe.edu.utp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IndexView {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public IndexView() {
        // Crear la ventana principal JFrame
        JFrame frame = new JFrame("Botica App");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setLayout(new BorderLayout());

        // Crear un panel para el menú lateral (diseño vertical)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Diseño vertical
        menuPanel.setBackground(new Color(45, 45, 45)); // Fondo oscuro del sidebar
        menuPanel.setPreferredSize(new Dimension(300, 100)); // Ancho fijo del sidebar

        // Crear y añadir la etiqueta del logo
        ImageIcon logoIcon = new ImageIcon("src/main/resources/logo.png"); // Ruta real de la imagen del logo
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH); // Escalar el logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el logo en el sidebar
        menuPanel.add(logoLabel);
        menuPanel.add(Box.createVerticalStrut(20)); // Espacio entre logo y encabezado

        // Añadir una etiqueta de encabezado al sidebar
        JLabel sidebarHeader = new JLabel("Botica Corazón de Jesús");
        sidebarHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        sidebarHeader.setForeground(Color.WHITE);
        sidebarHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaciado alrededor del texto
        sidebarHeader.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el encabezado
        menuPanel.add(sidebarHeader);

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1)); // Diseño vertical para botones sin margen

        // Crear botones sin íconos
        JButton medicamentoButton = createStyledSidebarButton("Medicamentos");
        JButton ventaButton = createStyledSidebarButton("Ventas");
        JButton alertaButton = createStyledSidebarButton("Alertas");
        JButton reporteButton = createStyledSidebarButton("Reportes");
        JButton configuracionButton = createStyledSidebarButton("Configuración");
        JButton cerrarSesionButton = createStyledSidebarButton("Cerrar Sesión");

        // Añadir acciones a los botones
        medicamentoButton.addActionListener((ActionEvent e) -> showContent("Medicamento"));
        ventaButton.addActionListener((ActionEvent e) -> showContent("Venta"));
        alertaButton.addActionListener((ActionEvent e) -> showContent("Alerta"));
        reporteButton.addActionListener((ActionEvent e) -> showContent("Reporte"));
        configuracionButton.addActionListener((ActionEvent e) -> showContent("Configuracion"));
        cerrarSesionButton.addActionListener((ActionEvent e) -> System.exit(0)); // Cerrar la aplicación al salir

        // Añadir botones al panel de botones
        buttonPanel.add(medicamentoButton);
        buttonPanel.add(ventaButton);
        buttonPanel.add(alertaButton);
        buttonPanel.add(reporteButton);
        buttonPanel.add(configuracionButton);
        buttonPanel.add(cerrarSesionButton); // Añadir botón de cerrar sesión

        // Ajustar el tamaño de los botones para que ocupen todo el ancho
        for (Component button : buttonPanel.getComponents()) {
            button.setPreferredSize(new Dimension(280, 50)); // Ajustar el tamaño del botón
        }

        // Añadir el panel de botones al menú
        menuPanel.add(buttonPanel);
        menuPanel.add(Box.createVerticalGlue()); // Empujar el contenido hacia la parte superior

        // Añadir el sidebar al panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.WEST);

        // Crear un panel de contenido con CardLayout para mostrar diferentes vistas
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(Color.WHITE); // Fondo blanco para el contenido
        mainPanel.add(contentPanel, BorderLayout.CENTER); // Añadir panel de contenido al centro

        // Añadir contenido con una descripción y una imagen para la vista predeterminada
        contentPanel.add(createCompanyInfoView(), "Inicio");
        contentPanel.add(createContentView("Vista de Medicamentos"), "Medicamento");
        contentPanel.add(createContentView("Vista de Ventas"), "Venta");
        contentPanel.add(createContentView("Vista de Alertas"), "Alerta");
        contentPanel.add(createContentView("Vista de Reportes"), "Reporte");
        contentPanel.add(createContentView("Configuración de la aplicación"), "Configuracion");

        // Mostrar la vista predeterminada (información de la empresa)
        cardLayout.show(contentPanel, "Inicio");

        // Añadir el panel principal al JFrame y hacerlo visible
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Método auxiliar para crear una vista de información de la empresa con descripción ampliada e imagen
    private JPanel createCompanyInfoView() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espacios entre componentes

        // Crear un área de texto para la descripción de la empresa
        JTextPane descriptionArea = new JTextPane();
        descriptionArea.setEditable(false);
        descriptionArea.setContentType("text/html");
        descriptionArea.setFont(new Font("Verdana", Font.PLAIN, 15)); // Cambiar a Verdana y tamaño 15
        descriptionArea.setText("<html><body style='text-align: justify;'>"
                + "<p>Bienvenido a la botica Corazón de Jesús.</p>"
                + "<p>Nuestra empresa se dedica a proporcionar medicamentos de alta calidad, "
                + "siempre con el objetivo de mejorar la salud y bienestar de nuestros clientes. "
                + "Contamos con un equipo de profesionales comprometidos y atentos para brindarte "
                + "la mejor experiencia de compra.</p>"
                + "<p>En Botica Corazón de Jesús ofrecemos una amplia gama de productos farmacéuticos y servicios "
                + "relacionados con la salud, siempre asegurándonos de cumplir con las normativas "
                + "más estrictas en la industria. Nuestra misión es facilitar el acceso a productos "
                + "médicos esenciales de forma rápida, eficiente y confiable.</p>"
                + "<p>Además, brindamos servicios de consulta médica en línea y entrega a domicilio "
                + "para garantizar que nuestros clientes tengan acceso a sus medicamentos sin "
                + "complicaciones, desde la comodidad de sus hogares.</p>"
                + "<p>Gracias por confiar en Botica App, tu salud es nuestra prioridad.</p>"
                + "</body></html>");

        // Limitar el tamaño del área de texto
        descriptionArea.setPreferredSize(new Dimension(650, 300));
        descriptionArea.setMaximumSize(new Dimension(600, 200)); // Limitar el tamaño máximo
        descriptionArea.setMargin(new Insets(8, 8, 8, 8)); // Ajustar márgenes

        // Añadir el área de texto al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el componente
        panel.add(descriptionArea, gbc);

        // Crear y añadir la etiqueta de la imagen
        ImageIcon companyImage = new ImageIcon("src/main/resources/botica.png"); // Ruta real de la imagen
        JLabel imageLabel = new JLabel(companyImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Espacio fijo entre el texto y la imagen
        gbc.gridx = 0; // Asegurarse de que la imagen esté en la misma columna
        gbc.gridy = 1; // Colocarla en la siguiente fila
        gbc.insets = new Insets(20, 10, 10, 10); // Espacio fijo
        gbc.anchor = GridBagConstraints.CENTER; // Centrar la imagen
        panel.add(imageLabel, gbc);

        return panel;
    }

    // Método auxiliar para crear una vista de contenido básica
    private JPanel createContentView(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(title, SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }

    // Método auxiliar para crear botones estilizados para el menú lateral
    private JButton createStyledSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente del botón
        button.setForeground(Color.BLACK); // Color del texto
        button.setBackground(new Color(12, 65, 238)); // Color de fondo
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Sin borde
        button.setFocusable(false); // Sin enfoque en el botón
        button.setMaximumSize(new Dimension(300, 50)); // Asegurar que los botones tengan un tamaño máximo

        button.setPreferredSize(new Dimension(280, 50)); // Ajustar el tamaño preferido del botón

        // Cambiar color de fondo al pasar el ratón
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(53, 213, 19)); // Color al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(12, 65, 238)); // Color original
            }
        });

        return button;
    }

    // Método para mostrar el contenido correspondiente basado en el botón presionado
    private void showContent(String content) {
        cardLayout.show(contentPanel, content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IndexView::new);
    }

    public void setVisible(boolean b) {
    }
}
