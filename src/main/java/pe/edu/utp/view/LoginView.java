package pe.edu.utp.view;

import javax.swing.*;
import java.awt.*;
import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.models.Usuario;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private UsuarioDAO usuarioDAO;

    public LoginView() {
        setTitle("Login - Botica App");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridBagLayout());
        panelIzquierdo.setBackground(Color.WHITE);

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Color primaryColor = new Color(0, 163, 224);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblImage = new JLabel(new ImageIcon("src/main/resources/logo.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelIzquierdo.add(lblImage, gbc);

        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(titleFont);
        lblTitulo.setForeground(primaryColor);
        gbc.gridy++;
        panelIzquierdo.add(lblTitulo, gbc);

        JLabel lblUsuario = new JLabel("USUARIO:");
        lblUsuario.setFont(labelFont);
        gbc.gridwidth = 1;
        gbc.gridy++;
        panelIzquierdo.add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        txtUsuario.setFont(labelFont);
        txtUsuario.setBorder(BorderFactory.createLineBorder(primaryColor, 2));
        gbc.gridx = 1;
        panelIzquierdo.add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("CONTRASEÑA:");
        lblContrasena.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy++;
        panelIzquierdo.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(15);
        txtContrasena.setFont(labelFont);
        txtContrasena.setBorder(BorderFactory.createLineBorder(primaryColor, 2));
        gbc.gridx = 1;
        panelIzquierdo.add(txtContrasena, gbc);

        btnLogin = new JButton("ENTRAR");
        btnLogin.setBackground(primaryColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        gbc.gridwidth = 2;
        gbc.gridy++;
        panelIzquierdo.add(btnLogin, gbc);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());

        // Redimensionar la imagen antes de agregarla
        JLabel lblImage2 = new JLabel(resizeImage("src/main/resources/img.png", 320, 500));
        panelDerecho.add(lblImage2, BorderLayout.CENTER);

        Dimension preferredSizeIzquierdo = new Dimension(480, 500);
        Dimension preferredSizeDerecho = new Dimension(320, 500);
        panelIzquierdo.setPreferredSize(preferredSizeIzquierdo);
        panelDerecho.setPreferredSize(preferredSizeDerecho);

        panelFondo.add(panelIzquierdo, BorderLayout.WEST);
        panelFondo.add(panelDerecho, BorderLayout.EAST);

        add(panelFondo, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            if (validarCredenciales(usuario, contrasena)) {
                JOptionPane.showMessageDialog(LoginView.this,
                        "Inicio de sesión exitoso");
                IndexView mainView = new IndexView();
                mainView.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginView.this,
                        "Credenciales incorrectas", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validarCredenciales(String usuario, String contrasena) {
        Usuario user = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);
        return user != null;
    }
}
