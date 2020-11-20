package view.boss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;

import model.Boss;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

public class BossAddNewUser {

	private JFrame frame;
	private Boss user;
	
	private JLabel nombrelbl;
	private JLabel apellidolbl;
	private JLabel segApellidolbl;
	private JLabel dnilbl;
	private JLabel rollbl;
	private JLabel passlbl;
	private JLabel userlbl;
	
	private JTextField nombretxt;
	private JTextField apellidotxt;
	private JTextField segApellidotxt;
	private JTextField dnitxt;
	private JTextField usertxt;
	
	private JPasswordField passtxt;
	
	private JComboBox<?> rolComboBox;
	
	private JButton backButton;
	private JButton registerButton;
	

	private JButton btnLogOut;
	/**
	 * Create the application.
	 */
	public BossAddNewUser(Boss user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1100, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUIComponents();
		setControllers();
	}
	
	public void setControllers() {
	
		
	}
	
	public void setUIComponents() {
		frame.setTitle("Jefe");
		frame.setMinimumSize(new Dimension(700, 500));

		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(233, 196, 106));
		topPanel.setBounds(100, 100, 100, 100);

		JPanel leftPanel = new JPanel();
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setBackground(new Color(233, 196, 106));

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(new Color(233, 196, 106));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));

		btnLogOut = new JButton("Cerrar Sesión");

		btnLogOut.setBorder(null);
		btnLogOut.setBackground(new Color(233, 196, 106));
		btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 12));
		bottomPanel.add(btnLogOut);

		JLabel lblLogOut = new JLabel("X");
		lblLogOut.setForeground(new Color(220, 20, 60));
		lblLogOut.setFont(new Font("SansSerif", Font.BOLD, 13));
		bottomPanel.add(lblLogOut);

		JPanel rightPanel = new JPanel();
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(new Color(233, 196, 106));

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{1061, 0};
		gbl_mainPanel.rowHeights = new int[]{84, 239, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel cabeceraPanel = new JPanel();
		cabeceraPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,50));
		GridBagConstraints gbc_cabeceraPanel = new GridBagConstraints();
		gbc_cabeceraPanel.fill = GridBagConstraints.BOTH;
		gbc_cabeceraPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cabeceraPanel.gridx = 0;
		gbc_cabeceraPanel.gridy = 0;
		mainPanel.add(cabeceraPanel, gbc_cabeceraPanel);
		
		JLabel datoslbl = new JLabel("Datos del nuevo empleado");
		datoslbl.setFont(new Font("SansSerif", Font.BOLD, 25));
		cabeceraPanel.add(datoslbl);
		
		JPanel datesPanel = new JPanel();
		datesPanel.setLayout(new GridLayout(1,2));
		GridBagConstraints gbc_datesPanel = new GridBagConstraints();
		gbc_datesPanel.fill = GridBagConstraints.BOTH;
		gbc_datesPanel.gridx = 0;
		gbc_datesPanel.gridy = 1;
		mainPanel.add(datesPanel, gbc_datesPanel);
		
		JPanel datesPanelLeft = new JPanel();
		datesPanel.add(datesPanelLeft);
		SpringLayout sl_datesPanelLeft = new SpringLayout();
		datesPanelLeft.setLayout(sl_datesPanelLeft);
		
		nombrelbl = new JLabel("Nombre");
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, nombrelbl, 29, SpringLayout.NORTH, datesPanelLeft);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, nombrelbl, 59, SpringLayout.WEST, datesPanelLeft);
		nombrelbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(nombrelbl);
		
		nombretxt = new JTextField();
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, nombretxt, -3, SpringLayout.NORTH, nombrelbl);
		nombretxt.setColumns(10);
		nombretxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(nombretxt);
		
		apellidolbl = new JLabel("Primer apellido");
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, apellidolbl, 30, SpringLayout.SOUTH, nombrelbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, apellidolbl, 0, SpringLayout.WEST, nombrelbl);
		apellidolbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(apellidolbl);
		
		apellidotxt = new JTextField();
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, nombretxt, 0, SpringLayout.WEST, apellidotxt);
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, apellidotxt, -3, SpringLayout.NORTH, apellidolbl);
		apellidotxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		apellidotxt.setColumns(10);
		datesPanelLeft.add(apellidotxt);
		
		segApellidolbl = new JLabel("Segundo apellido");
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, segApellidolbl, 30, SpringLayout.SOUTH, apellidolbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, segApellidolbl, 0, SpringLayout.WEST, nombrelbl);
		segApellidolbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(segApellidolbl);
		
		segApellidotxt = new JTextField();
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, apellidotxt, 0, SpringLayout.WEST, segApellidotxt);
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, segApellidotxt, -3, SpringLayout.NORTH, segApellidolbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, segApellidotxt, 89, SpringLayout.EAST, segApellidolbl);
		segApellidotxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		segApellidotxt.setColumns(10);
		datesPanelLeft.add(segApellidotxt);
		
		rollbl = new JLabel("Rol");
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, rollbl, 30, SpringLayout.SOUTH, segApellidolbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, rollbl, 0, SpringLayout.WEST, nombrelbl);
		rollbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(rollbl);
		
		rolComboBox = new JComboBox<>();
		sl_datesPanelLeft.putConstraint(SpringLayout.NORTH, rolComboBox, -3, SpringLayout.NORTH, rollbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.WEST, rolComboBox, 178, SpringLayout.EAST, rollbl);
		sl_datesPanelLeft.putConstraint(SpringLayout.EAST, rolComboBox, -135, SpringLayout.EAST, datesPanelLeft);
		rolComboBox.setModel(new DefaultComboBoxModel(new String[] { "Jefe", "Ventas", "Mecánico", "Mecánico Jefe" }));
		rolComboBox.setSelectedIndex(0);
		rolComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelLeft.add(rolComboBox);
		
		JPanel datesPanelRight = new JPanel();
		datesPanel.add(datesPanelRight);
		SpringLayout sl_datesPanelRight = new SpringLayout();
		datesPanelRight.setLayout(sl_datesPanelRight);
		
		dnilbl = new JLabel("DNI");
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, dnilbl, 29, SpringLayout.NORTH, datesPanelRight);
		sl_datesPanelRight.putConstraint(SpringLayout.WEST, dnilbl, 59, SpringLayout.WEST, datesPanelRight);
		dnilbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelRight.add(dnilbl);
		
		dnitxt = new JTextField();
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, dnitxt, -3, SpringLayout.NORTH, dnilbl);
		dnitxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dnitxt.setColumns(10);
		datesPanelRight.add(dnitxt);
		
		userlbl = new JLabel("Nombre de usuario");
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, userlbl, 30, SpringLayout.SOUTH, dnilbl);
		sl_datesPanelRight.putConstraint(SpringLayout.WEST, userlbl, 59, SpringLayout.WEST, datesPanelRight);
		userlbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelRight.add(userlbl);
		
		usertxt = new JTextField();
		sl_datesPanelRight.putConstraint(SpringLayout.WEST, dnitxt, 0, SpringLayout.WEST, usertxt);
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, usertxt, -3, SpringLayout.NORTH, userlbl);
		usertxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		usertxt.setColumns(10);
		datesPanelRight.add(usertxt);
		
		passlbl = new JLabel("Contraseña");
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, passlbl, 30, SpringLayout.SOUTH, userlbl);
		sl_datesPanelRight.putConstraint(SpringLayout.WEST, passlbl, 0, SpringLayout.WEST, dnilbl);
		passlbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelRight.add(passlbl);
		
		passtxt = new JPasswordField();
		sl_datesPanelRight.putConstraint(SpringLayout.EAST, passtxt, -135, SpringLayout.EAST, datesPanelRight);
		sl_datesPanelRight.putConstraint(SpringLayout.WEST, usertxt, 0, SpringLayout.WEST, passtxt);
		sl_datesPanelRight.putConstraint(SpringLayout.NORTH, passtxt, -3, SpringLayout.NORTH, passlbl);
		passtxt.setColumns(10);
		passtxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datesPanelRight.add(passtxt);
		
		JPanel bottonPanel = new JPanel();
		GridBagConstraints gbc_botonPanel = new GridBagConstraints();
		gbc_botonPanel.fill = GridBagConstraints.BOTH;
		gbc_botonPanel.gridx = 0;
		gbc_botonPanel.gridy = 2;
		mainPanel.add(bottonPanel, gbc_botonPanel);
		bottonPanel.setLayout(new FlowLayout(1, 250, 100));

		// Botones
		backButton = new JButton("Volver al menú");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		backButton.setBackground(new Color(244, 162, 97));
		backButton.setForeground(Color.WHITE);
		bottonPanel.add(backButton);

		registerButton = new JButton("Añadir empleado");
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		registerButton.setBackground(new Color(231, 111, 81));
		registerButton.setForeground(Color.WHITE);
		bottonPanel.add(registerButton);
		
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
