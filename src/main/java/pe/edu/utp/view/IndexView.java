package pe.edu.utp.view;

import pe.edu.utp.utils.SwingUTP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IndexView {

    public IndexView() {
        // Inicializa la vista principal
        SwingUTP.runWindow("Botica App", 800, 600, true);

        // Crea un panel principal con un diseño de border
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Color de fondo claro

        // Crea una etiqueta de título
        JLabel titleLabel = new JLabel("Bienvenido a la Botica App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Estilo de fuente
        titleLabel.setForeground(new Color(0, 163, 224)); // Color del texto del título
        mainPanel.add(titleLabel, BorderLayout.NORTH); // Añade el título al panel principal

        // Crea un panel para los botones con un diseño de cuadrícula
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 filas, 2 columnas
        buttonPanel.setBackground(Color.WHITE); // Color de fondo blanco

        // Crea los botones
        JButton medicamentoButton = new JButton("Gestionar Medicamentos");
        JButton ventaButton = new JButton("Registrar Ventas");
        JButton alertaButton = new JButton("Alertas de Vencimiento");
        JButton reporteButton = new JButton("Generar Reportes");
        JButton configuracionButton = new JButton("Configuración");
        JButton salirButton = new JButton("Salir");

        // Añadiendo acciones a los botones
        SwingUTP.addClickEvent(medicamentoButton, (ActionEvent e) -> {
            new MedicamentoViews().setVisible(true); // Lógica para abrir MedicamentoView
        });

        SwingUTP.addClickEvent(ventaButton, (ActionEvent e) -> {
            new VentaView().setVisible(true); // Lógica para abrir VentaView
        });

        SwingUTP.addClickEvent(alertaButton, (ActionEvent e) -> {
            new AlertaView().setVisible(true); // Lógica para abrir AlertaView
        });

        SwingUTP.addClickEvent(reporteButton, (ActionEvent e) -> {
            new ReporteView().setVisible(true); // Lógica para abrir ReporteView
        });

        SwingUTP.addClickEvent(configuracionButton, (ActionEvent e) -> {
            new ConfiguracionView().setVisible(true); // Lógica para abrir ConfiguracionView
        });

        SwingUTP.addClickEvent(salirButton, (ActionEvent e) -> {
            System.exit(0); // Cierra la aplicación
        });

        // Añade los botones al panel de botones
        buttonPanel.add(medicamentoButton);
        buttonPanel.add(ventaButton);
        buttonPanel.add(alertaButton);
        buttonPanel.add(reporteButton);
        buttonPanel.add(configuracionButton);
        buttonPanel.add(salirButton);

        // Añade el panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Configuraciones adicionales de la interfaz de usuario
        // Establece márgenes y relleno
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Bordes vacíos

        // Añade el panel principal a la ventana
        SwingUTP.addControl(0, 0, mainPanel);
    }

    public static void main(String[] args) {
        // Crea una instancia de la vista principal para mostrar la aplicación
        new IndexView();
    }

    public void setVisible(boolean b) {
        // Método de marcador de posición, puedes implementar la lógica de visibilidad si es necesario
    }
}
