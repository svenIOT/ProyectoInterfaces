package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	/**
	 * Crea la aplicación
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Inicializa el contenido del frame, los componentes de la interfaz de usuario
	 * y los controladores
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		setUIComponents();
		setControllers();
	}

	/**
	 * Contiene los controladores
	 */
	private void setControllers() {
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (true) { // TODO: rellenar if con el método de usuarioDAO correspondiente
					// Comprobar que tipo de usuario entró (ventas o mecánico)
					if (true) { // TODO: rellenar if 
						new SalesLandingView().getFrame().setVisible(true);
						frame.dispose();
					} else { // si es de mecánica (próximamente en otro sprint)
						
					}
				} else {
					JOptionPane.showMessageDialog(frame, null, "Usuario o contraseña incorrecto",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Contiene los componentes de la interfaz de usuario
	 */
	private void setUIComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(233, 196, 106));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtUsuario.setText("Usuario");
		txtUsuario.setBounds(295, 210, 191, 30);
		mainPanel.add(txtUsuario);
		txtUsuario.setColumns(10);

		btnLogin = new JButton("Acceder");

		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(231, 111, 81));
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnLogin.setBounds(295, 323, 191, 30);
		mainPanel.add(btnLogin);

		txtPassword = new JPasswordField("Usuario");
		txtPassword.setBounds(295, 259, 191, 30);
		mainPanel.add(txtPassword);
	}

	public JFrame getFrame() {
		return frame;
	}

}
