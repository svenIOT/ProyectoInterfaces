package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JCalendar;

import dao.VehicleDAO;

public class SalesAddSellingPropositionView {

	private JFrame frame;
	private JComboBox<?> sellingPropositionComboBox;
	private JButton returnButton;
	private JButton addVehicleButton;
	private JButton btnLogOut;
	private JTextField frameNumberTxt;
	private JTextField priceTxt;
	private JCalendar calendar;
	

	private VehicleDAO vehicleDAO;

	/**
	 * Create the application.
	 */
	public SalesAddSellingPropositionView() {
		initialize();
		vehicleDAO = new VehicleDAO();
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
	
	/**
	 * Contiene los controladores
	 */
	private void setControllers() {
		

		// Volver al menú principal
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SalesLandingView().getFrame().setVisible(true);
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

		/*
		 * Paneles externos. Solo hay que añadir en el bottomPanel
		 */
		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(233, 196, 106));
		topPanel.setBounds(100, 100, 100, 100);

		JPanel leftPanel = new JPanel();
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setBackground(new Color(233, 196, 106));

		JPanel rightPanel = new JPanel();
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(new Color(233, 196, 106));

		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(new Color(233, 196, 106));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		// Se añaden los componentes al bottomPanel
		btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(new Color(233, 196, 106));
		btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 12));
		bottomPanel.add(btnLogOut);

		JLabel lblLogOut = new JLabel("X");
		lblLogOut.setForeground(new Color(220, 20, 60));
		lblLogOut.setFont(new Font("SansSerif", Font.BOLD, 13));
		bottomPanel.add(lblLogOut);

		/*
		 * mainPanel. Dentro se crean otros paneles para añadir los distintos
		 * componentes.
		 */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 1061, 0 };
		gbl_mainPanel.rowHeights = new int[] { 137, 298, 328, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		// Paneles que componen el mainPanel

		JPanel headerPanel = new JPanel();
		GridBagConstraints gbc_headerPanel = new GridBagConstraints();
		gbc_headerPanel.fill = GridBagConstraints.BOTH;
		gbc_headerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headerPanel.gridx = 0;
		gbc_headerPanel.gridy = 0;
		mainPanel.add(headerPanel, gbc_headerPanel);
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setPreferredSize(new Dimension(10, 50));

		// Añadir Jlabel a clientesPanel
		JLabel MainLbl = new JLabel("Añadir propuesta de venta");
		MainLbl.setHorizontalAlignment(SwingConstants.CENTER);
		MainLbl.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(MainLbl, BorderLayout.CENTER);

		// Panel para rellenar datos de cliente.
		JPanel bodyPanel = new JPanel();
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		mainPanel.add(bodyPanel, gbc_bodyPanel);
		bodyPanel.setLayout(new GridLayout(1, 2));

		JPanel sellingPropositionPanelLeft = new JPanel();
		bodyPanel.add(sellingPropositionPanelLeft);
		SpringLayout sl_sellingPropositionPanelLeft = new SpringLayout();
		sellingPropositionPanelLeft.setLayout(sl_sellingPropositionPanelLeft);

		// Componentes panel derecho
		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel sellingPropositionLbl = new JLabel("Cliente:");
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, sellingPropositionLbl, 26, SpringLayout.NORTH,
				sellingPropositionPanelLeft);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, sellingPropositionLbl, 70, SpringLayout.WEST,
				sellingPropositionPanelLeft);
		sellingPropositionLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		sellingPropositionPanelLeft.add(sellingPropositionLbl);

		sellingPropositionComboBox = new JComboBox<>();
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.EAST, sellingPropositionComboBox, -106, SpringLayout.EAST, sellingPropositionPanelLeft);
		sellingPropositionComboBox.setSelectedIndex(0);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, sellingPropositionComboBox, -3, SpringLayout.NORTH,
				sellingPropositionLbl);
		sellingPropositionComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		sellingPropositionPanelLeft.add(sellingPropositionComboBox);

		JLabel dateLbl = new JLabel("Válido hasta:");
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, dateLbl, 26, SpringLayout.SOUTH,
				sellingPropositionLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, dateLbl, 0, SpringLayout.WEST,
				sellingPropositionLbl);
		dateLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		sellingPropositionPanelLeft.add(dateLbl);
		
		

		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel frameNumberLbl = new JLabel("Número de bastidor:");
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, frameNumberLbl, 26, SpringLayout.SOUTH,
				dateLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, frameNumberLbl, 0, SpringLayout.WEST,
				sellingPropositionLbl);
		frameNumberLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		sellingPropositionPanelLeft.add(frameNumberLbl);

		frameNumberTxt = new JTextField();
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, sellingPropositionComboBox, 0, SpringLayout.WEST, frameNumberTxt);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, frameNumberTxt, -3, SpringLayout.NORTH,
				frameNumberLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, frameNumberTxt, 53, SpringLayout.EAST,
				frameNumberLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.EAST, frameNumberTxt, -106, SpringLayout.EAST,
				sellingPropositionPanelLeft);
		frameNumberTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		frameNumberTxt.setColumns(10);
		sellingPropositionPanelLeft.add(frameNumberTxt);
		
		JLabel priceLbl = new JLabel("Precio:");
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.NORTH, priceLbl, 33, SpringLayout.SOUTH, frameNumberLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, priceLbl, 0, SpringLayout.WEST, sellingPropositionLbl);
		priceLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		sellingPropositionPanelLeft.add(priceLbl);
		
		priceTxt = new JTextField();
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.WEST, priceTxt, 0, SpringLayout.WEST, sellingPropositionComboBox);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.SOUTH, priceTxt, 0, SpringLayout.SOUTH, priceLbl);
		sl_sellingPropositionPanelLeft.putConstraint(SpringLayout.EAST, priceTxt, 0, SpringLayout.EAST, sellingPropositionComboBox);
		priceTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		priceTxt.setColumns(10);
		sellingPropositionPanelLeft.add(priceTxt);

		JPanel sellingPropositionPanelRight = new JPanel();
		bodyPanel.add(sellingPropositionPanelRight);
		SpringLayout sl_sellingPropositionPanelRight = new SpringLayout();
		sellingPropositionPanelRight.setLayout(sl_sellingPropositionPanelRight);

		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_botonPanel = new GridBagConstraints();
		gbc_botonPanel.fill = GridBagConstraints.BOTH;
		gbc_botonPanel.gridx = 0;
		gbc_botonPanel.gridy = 2;
		mainPanel.add(buttonPanel, gbc_botonPanel);
		buttonPanel.setLayout(new FlowLayout(1, 250, 100));

		// Botones
		returnButton = new JButton("Volver al menú");
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		returnButton.setBackground(new Color(244, 162, 97));
		returnButton.setForeground(Color.WHITE);
		buttonPanel.add(returnButton);

		addVehicleButton = new JButton("Añadir vehículo");
		addVehicleButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		addVehicleButton.setBackground(new Color(231, 111, 81));
		addVehicleButton.setForeground(Color.WHITE);
		buttonPanel.add(addVehicleButton);

	}

	

	public JFrame getFrame() {
		return frame;
	}

}
