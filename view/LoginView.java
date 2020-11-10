package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UserDAO;
import model.Employee;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LoginView {

	private JFrame frame;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private UserDAO userDAO;

	/**
	 * Crea la aplicación
	 */
	public LoginView() {
		initialize();
		userDAO = new UserDAO();
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
				var user = createEmployee();
				if (userDAO.login(user)) {
					// Comprobar que tipo de usuario entra (ventas o mecánico)
					if (userDAO.isSalesEmployee(user)) {
						new SalesLandingView().getFrame().setVisible(true);
						frame.dispose();
					} else if (userDAO.isBossMechanical(user)) {
						new MechanicalBossLandingView().getFrame().setVisible(true);
						frame.dispose();
					} else {
						//new MechanicalLandingView().getFrame().setVisible(true);
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrecto", "Warning!",
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

		txtUser = new JTextField();
		txtUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtUser.setText("Usuario");
		txtUser.setBounds(300, 327, 191, 30);
		mainPanel.add(txtUser);
		txtUser.setColumns(10);

		btnLogin = new JButton("Acceder");

		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(231, 111, 81));
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnLogin.setBounds(300, 440, 191, 30);
		mainPanel.add(btnLogin);

		txtPassword = new JPasswordField("Usuario");
		txtPassword.setBounds(300, 376, 191, 30);
		mainPanel.add(txtPassword);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(LoginView.class.getResource("/assets/img/logo.png")));
		lblLogo.setBounds(-21, 10, 817, 307);
		mainPanel.add(lblLogo);
	}
	
	/**
	 * Crea un usuario con los datos de los textField
	 * @return
	 */
	private Employee createEmployee() {
		var username = txtUser.getText();
		var password = new String (txtPassword.getPassword());
		return new Employee(username, password);
	}

	public JFrame getFrame() {
		return frame;
	}
}
