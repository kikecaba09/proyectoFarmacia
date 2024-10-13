package pe.edu.utp.view;

import javax.swing.*;
import java.awt.*;

import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.models.Usuario;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private UsuarioDAO usuarioDAO;

    public LoginView() {
        setTitle("Login - Botica App");
        setSize(800, 500); // Adjust the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the background panel
        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(new BorderLayout());

        // Panel for the login form (60% width)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridBagLayout());
        panelIzquierdo.setBackground(Color.WHITE);

        // Font and color styles
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Color primaryColor = new Color(0, 163, 224); // Adjust this color as per your preference

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo
        JLabel lblLogo = new JLabel("LOGO");
        lblLogo.setFont(titleFont);
        lblLogo.setForeground(primaryColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelIzquierdo.add(lblLogo, gbc);

        // Window title
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(titleFont);
        lblTitulo.setForeground(primaryColor);
        gbc.gridy++;
        panelIzquierdo.add(lblTitulo, gbc);

        // Username label
        JLabel lblUsuario = new JLabel("USUARIO:");
        lblUsuario.setFont(labelFont);
        gbc.gridwidth = 1;
        gbc.gridy++;
        panelIzquierdo.add(lblUsuario, gbc);

        // Username text field
        txtUsuario = new JTextField(15);
        txtUsuario.setFont(labelFont);
        txtUsuario.setBorder(BorderFactory.createLineBorder(primaryColor, 2));
        gbc.gridx = 1;
        panelIzquierdo.add(txtUsuario, gbc);

        // Password label
        JLabel lblContrasena = new JLabel("CONTRASEÑA:");
        lblContrasena.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy++;
        panelIzquierdo.add(lblContrasena, gbc);

        // Password text field
        txtContrasena = new JPasswordField(15);
        txtContrasena.setFont(labelFont);
        txtContrasena.setBorder(BorderFactory.createLineBorder(primaryColor, 2));
        gbc.gridx = 1;
        panelIzquierdo.add(txtContrasena, gbc);

        // Login button
        btnLogin = new JButton("ENTRAR");
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        gbc.gridwidth = 2;
        gbc.gridy++;
        panelIzquierdo.add(btnLogin, gbc);

        // Panel for the image (40% width)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());
        JLabel lblImage = new JLabel(new ImageIcon("ruta/a/tu/imagen.png")); // Replace with the path to your image
        panelDerecho.add(lblImage, BorderLayout.CENTER);

        // Set preferred sizes to ensure correct proportions
        Dimension preferredSizeIzquierdo = new Dimension(480, 500); // 60% of 800px width
        Dimension preferredSizeDerecho = new Dimension(320, 500); // 40% of 800px width
        panelIzquierdo.setPreferredSize(preferredSizeIzquierdo);
        panelDerecho.setPreferredSize(preferredSizeDerecho);

        // Add panels to the background panel
        panelFondo.add(panelIzquierdo, BorderLayout.WEST);
        panelFondo.add(panelDerecho, BorderLayout.EAST);

        // Add the background panel to the frame
        add(panelFondo, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {
            // Get input data
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            // Validate credentials
            if (validarCredenciales(usuario, contrasena)) {
                // Show the main window and close the login window
                JOptionPane.showMessageDialog(LoginView.this, "Inicio de sesión exitoso");
                IndexView mainView = new IndexView(); // Instantiate the main window
                mainView.setVisible(true);
                dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(LoginView.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        Usuario user = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);
        return user != null;
    }
}
