package view.mechanical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Mechanical;
import view.LoginView;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.UserDAO;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MechanicalAddRepairView {

	private JFrame frame;
	private JButton btnLogOut;
	private JButton btnBackToMenu;
	private JTextField textField;
	private JDateChooser inicialDateChooser;
	private JDateChooser finishDateChooser;
	private JComboBox<Object> vehicleTypeComboBox;
	
	private UserDAO userDAO;

	private Mechanical user;
	private boolean isBoss;

	/**
	 * Crea la aplicación
	 */
	public MechanicalAddRepairView(Mechanical user, boolean isBoss) {
		this.user = user;
		this.isBoss = isBoss;
		this.userDAO = new UserDAO();
		initialize();
	}

	/**
	 * Inicializa el contenido del frame, los componentes de la interfaz de usuario
	 * y los controladores
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1100, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUIComponents();
		setControllers();
	}

	/**
	 * Contiene los controladores
	 */
	private void setControllers() {
		// Obtener datos DAO
		var mechanicals = userDAO.getMechanicals();
		
		// Rellenar el comboBox con los mecánicos según el tipo de vehículo elegido
		vehicleTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				
			}
		});

		// Volver al menú principal
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MechanicalLandingView(user, isBoss).getFrame().setVisible(true);
				frame.dispose();
			}
		});

		// Volver al login
		btnLogOut.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new LoginView().getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}

	/**
	 * Contiene los componentes de la interfaz de usuario
	 */
	private void setUIComponents() {
		frame.setTitle("Departamento de ventas");
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
		gbl_mainPanel.columnWidths = new int[] { 1053, 0 };
		gbl_mainPanel.rowHeights = new int[] { 145, 581, 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JPanel headerPanel = new JPanel();
		GridBagConstraints gbc_headerPanel = new GridBagConstraints();
		gbc_headerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headerPanel.anchor = GridBagConstraints.WEST;
		gbc_headerPanel.fill = GridBagConstraints.VERTICAL;
		gbc_headerPanel.gridx = 0;
		gbc_headerPanel.gridy = 0;
		mainPanel.add(headerPanel, gbc_headerPanel);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 20));

		btnBackToMenu = new JButton("Volver al menú");
		btnBackToMenu.setForeground(Color.WHITE);
		btnBackToMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnBackToMenu.setBackground(new Color(244, 162, 97));
		headerPanel.add(btnBackToMenu);

		JLabel lblMainMenu = new JLabel("Añadir reparación");
		lblMainMenu.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(lblMainMenu);

		JPanel bodyPanel = new JPanel();
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		mainPanel.add(bodyPanel, gbc_bodyPanel);
		bodyPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel leftDataPanel = new JPanel();
		bodyPanel.add(leftDataPanel);
		GridBagLayout gbl_leftDataPanel = new GridBagLayout();
		gbl_leftDataPanel.columnWidths = new int[]{499, 0};
		gbl_leftDataPanel.rowHeights = new int[]{112, 334, 0, 0};
		gbl_leftDataPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_leftDataPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		leftDataPanel.setLayout(gbl_leftDataPanel);
		
		JLabel lblTextArea = new JLabel("Descripción de la reparación (piezas):");
		lblTextArea.setFont(new Font("SansSerif", Font.BOLD, 18));
		GridBagConstraints gbc_lblTextArea = new GridBagConstraints();
		gbc_lblTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_lblTextArea.gridx = 0;
		gbc_lblTextArea.gridy = 0;
		leftDataPanel.add(lblTextArea, gbc_lblTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		leftDataPanel.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		
		JPanel rightDataPanel = new JPanel();
		bodyPanel.add(rightDataPanel);
		GridBagLayout gbl_rightDataPanel = new GridBagLayout();
		gbl_rightDataPanel.columnWidths = new int[]{257, 236, 0};
		gbl_rightDataPanel.rowHeights = new int[]{114, 85, 75, 75, 76, 70, 0};
		gbl_rightDataPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_rightDataPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		rightDataPanel.setLayout(gbl_rightDataPanel);
		
		JLabel lblRepairData = new JLabel("Datos de la reparación:");
		lblRepairData.setFont(new Font("SansSerif", Font.BOLD, 18));
		GridBagConstraints gbc_lblRepairData = new GridBagConstraints();
		gbc_lblRepairData.anchor = GridBagConstraints.EAST;
		gbc_lblRepairData.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepairData.gridx = 0;
		gbc_lblRepairData.gridy = 0;
		rightDataPanel.add(lblRepairData, gbc_lblRepairData);
		
		JLabel vehicleTypeLbl = new JLabel("Tipo de vehículo:");
		vehicleTypeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		vehicleTypeLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_vehicleTypeLbl = new GridBagConstraints();
		gbc_vehicleTypeLbl.anchor = GridBagConstraints.CENTER;
		gbc_vehicleTypeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_vehicleTypeLbl.gridx = 0;
		gbc_vehicleTypeLbl.gridy = 1;
		rightDataPanel.add(vehicleTypeLbl, gbc_vehicleTypeLbl);
		
		vehicleTypeComboBox = new JComboBox<Object>();
		vehicleTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Elige uno", "Coche", "Motocicleta", "Ciclomotor"}));
		vehicleTypeComboBox.setSelectedIndex(-1);
		vehicleTypeComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_vehicleTypeComboBox = new GridBagConstraints();
		gbc_vehicleTypeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_vehicleTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_vehicleTypeComboBox.gridx = 1;
		gbc_vehicleTypeComboBox.gridy = 1;
		rightDataPanel.add(vehicleTypeComboBox, gbc_vehicleTypeComboBox);
		
		JLabel frameNumberLbl = new JLabel("Número de bastidor:");
		frameNumberLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_frameNumberLbl = new GridBagConstraints();
		gbc_frameNumberLbl.anchor = GridBagConstraints.CENTER;
		gbc_frameNumberLbl.insets = new Insets(0, 0, 5, 5);
		gbc_frameNumberLbl.gridx = 0;
		gbc_frameNumberLbl.gridy = 2;
		rightDataPanel.add(frameNumberLbl, gbc_frameNumberLbl);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		rightDataPanel.add(textField, gbc_textField);
		
		JLabel lblStartDate = new JLabel("Fecha comienzo:");
		lblStartDate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.CENTER;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 3;
		rightDataPanel.add(lblStartDate, gbc_lblStartDate);
		
		inicialDateChooser = new JDateChooser();
		inicialDateChooser.getCalendarButton().setPreferredSize(new Dimension(50, 25));
		inicialDateChooser.getCalendarButton().setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 3;
		rightDataPanel.add(inicialDateChooser, gbc_dateChooser);
		
		JLabel lblFinishDate = new JLabel("Fecha finalización:");
		lblFinishDate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFinishDate = new GridBagConstraints();
		gbc_lblFinishDate.anchor = GridBagConstraints.CENTER;
		gbc_lblFinishDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblFinishDate.gridx = 0;
		gbc_lblFinishDate.gridy = 4;
		rightDataPanel.add(lblFinishDate, gbc_lblFinishDate);
		
		finishDateChooser = new JDateChooser();
		finishDateChooser.getCalendarButton().setPreferredSize(new Dimension(50, 25));
		finishDateChooser.getCalendarButton().setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		GridBagConstraints gbc_finishDateChooser = new GridBagConstraints();
		gbc_finishDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_finishDateChooser.gridx = 1;
		gbc_finishDateChooser.gridy = 4;
		rightDataPanel.add(finishDateChooser, gbc_finishDateChooser);
		
		JLabel lblMecnicoAsignado = new JLabel("Mecánico asignado:");
		lblMecnicoAsignado.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMecnicoAsignado = new GridBagConstraints();
		gbc_lblMecnicoAsignado.anchor = GridBagConstraints.CENTER;
		gbc_lblMecnicoAsignado.insets = new Insets(0, 0, 0, 5);
		gbc_lblMecnicoAsignado.gridx = 0;
		gbc_lblMecnicoAsignado.gridy = 5;
		rightDataPanel.add(lblMecnicoAsignado, gbc_lblMecnicoAsignado);
		
		JComboBox<Object> mechanicalComboBox = new JComboBox<Object>();
		mechanicalComboBox.setSelectedIndex(-1);
		mechanicalComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		GridBagConstraints gbc_mechanicalComboBox = new GridBagConstraints();
		gbc_mechanicalComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_mechanicalComboBox.gridx = 1;
		gbc_mechanicalComboBox.gridy = 5;
		rightDataPanel.add(mechanicalComboBox, gbc_mechanicalComboBox);

	}

	public JFrame getFrame() {
		return frame;
	}

}