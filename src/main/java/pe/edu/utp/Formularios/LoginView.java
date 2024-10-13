package pe.edu.utp.Formularios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pe.edu.utp.Dao.UsuarioDAO;
import pe.edu.utp.models.Usuario;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private UsuarioDAO usuarioDAO;

    public LoginView() {
        // Configuración inicial de la ventana
        setTitle("Login - Botica App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fondo del panel
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Color de fondo personalizado
                g.setColor(new Color(204, 229, 255)); // Un azul suave
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelFondo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 51, 102)); // Color oscuro
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelFondo.add(lblTitulo, gbc);

        // Etiqueta de usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFondo.add(lblUsuario, gbc);

        // Campo de texto para el usuario
        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFondo.add(txtUsuario, gbc);

        // Etiqueta de contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFondo.add(lblContrasena, gbc);

        // Campo de texto para la contraseña
        txtContrasena = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFondo.add(txtContrasena, gbc);

        // Botón de login
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(0, 153, 0)); // Verde
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelFondo.add(btnLogin, gbc);

        // Agregar ActionListener al botón de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Validar las credenciales
                if (validarCredenciales(usuario, contrasena)) {
                    // Mostrar la ventana principal y cerrar la de login
                    JOptionPane.showMessageDialog(LoginView.this, "Inicio de sesión exitoso");
                    IndexView mainView = new IndexView(); // Instanciar la ventana principal
                    mainView.setVisible(true);
                    dispose(); // Cerrar la ventana de login
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar el panel al centro de la ventana
        add(panelFondo, BorderLayout.CENTER);
    }

    // Método para validar las credenciales del usuario
    private boolean validarCredenciales(String usuario, String contrasena) {
        // Llamar al DAO para verificar el usuario y la contraseña
        Usuario user = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);
        return user != null; // Si el usuario es nulo, las credenciales son incorrectas
    }
}
