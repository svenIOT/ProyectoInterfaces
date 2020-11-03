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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dao.VehicleDAO;
import model.Vehicle;

public class SalesAddVehicleView {

	private JFrame frame;
	private JComboBox<?> vehicleTypeComboBox;
	private JComboBox<?> fuelVehicleComboBox;
	private JButton returnButton;
	private JButton addVehicleButton;
	private JButton btnLogOut;
	private JTextField vehicleLicenseTxt;
	private JTextField frameNumberTxt;
	private JTextField brandTxt;
	private JTextField modelTxt;
	private JTextField priceTxt;
	private VehicleDAO vehicleDAO;

	/**
	 * Create the application.
	 */
	public SalesAddVehicleView() {
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

	private void setControllers() {
		// Añadir vehículo
		addVehicleButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				var vehicle = createVehicle();
				if (vehicle != null) {
					vehicleDAO.addVehicle(vehicle, vehicleTypeComboBox.getSelectedItem().toString(),
							vehicleLicenseTxt.getText());
					JOptionPane.showMessageDialog(frame, "Vehículo añadido", "Success!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

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

		JPanel vehiclesPanel = new JPanel();
		GridBagConstraints gbc_vehiclesPanel = new GridBagConstraints();
		gbc_vehiclesPanel.fill = GridBagConstraints.BOTH;
		gbc_vehiclesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_vehiclesPanel.gridx = 0;
		gbc_vehiclesPanel.gridy = 0;
		mainPanel.add(vehiclesPanel, gbc_vehiclesPanel);
		vehiclesPanel.setLayout(new BorderLayout());
		vehiclesPanel.setPreferredSize(new Dimension(10, 50));

		// Añadir Jlabel a clientesPanel
		JLabel vehiclesDatesLbl = new JLabel("Añadir vehículo");
		vehiclesDatesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		vehiclesDatesLbl.setFont(new Font("SansSerif", Font.BOLD, 25));
		vehiclesPanel.add(vehiclesDatesLbl, BorderLayout.CENTER);

		// Panel para rellenar datos de cliente.
		JPanel vehiclesDatesPanel = new JPanel();
		GridBagConstraints gbc_vehiclesDatesPanel = new GridBagConstraints();
		gbc_vehiclesDatesPanel.fill = GridBagConstraints.BOTH;
		gbc_vehiclesDatesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_vehiclesDatesPanel.gridx = 0;
		gbc_vehiclesDatesPanel.gridy = 1;
		mainPanel.add(vehiclesDatesPanel, gbc_vehiclesDatesPanel);
		vehiclesDatesPanel.setLayout(new GridLayout(1, 2));

		JPanel vehiclesDatesPanelLeft = new JPanel();
		vehiclesDatesPanel.add(vehiclesDatesPanelLeft);
		SpringLayout sl_vehiclesDatesPanelLeft = new SpringLayout();
		vehiclesDatesPanelLeft.setLayout(sl_vehiclesDatesPanelLeft);

		// Componentes panel derecho
		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel vehicleTypeLbl = new JLabel("Tipo de vehículo:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, vehicleTypeLbl, 26, SpringLayout.NORTH,
				vehiclesDatesPanelLeft);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleTypeLbl, 70, SpringLayout.WEST,
				vehiclesDatesPanelLeft);
		vehicleTypeLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(vehicleTypeLbl);

		vehicleTypeComboBox = new JComboBox<>();
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleTypeComboBox, 74, SpringLayout.EAST,
				vehicleTypeLbl);
		vehicleTypeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Coche", "Motocicleta", "Ciclomotor" }));
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, vehicleTypeComboBox, -3, SpringLayout.NORTH,
				vehicleTypeLbl);
		vehicleTypeComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(vehicleTypeComboBox);

		JLabel vehicleLicenseLbl = new JLabel("Matrícula:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, vehicleLicenseLbl, 26, SpringLayout.SOUTH,
				vehicleTypeLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleLicenseLbl, 0, SpringLayout.WEST,
				vehicleTypeLbl);
		vehicleLicenseLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(vehicleLicenseLbl);

		vehicleLicenseTxt = new JTextField();
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleLicenseTxt, 126, SpringLayout.EAST, vehicleLicenseLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.EAST, vehicleLicenseTxt, -106, SpringLayout.EAST, vehiclesDatesPanelLeft);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.EAST, vehicleTypeComboBox, 0, SpringLayout.EAST,
				vehicleLicenseTxt);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, vehicleLicenseTxt, -3, SpringLayout.NORTH,
				vehicleLicenseLbl);
		vehicleLicenseTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehicleLicenseTxt.setColumns(10);
		vehiclesDatesPanelLeft.add(vehicleLicenseTxt);

		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel frameNumberLbl = new JLabel("Número de bastidor:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, frameNumberLbl, 26, SpringLayout.SOUTH,
				vehicleLicenseLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, frameNumberLbl, 0, SpringLayout.WEST,
				vehicleTypeLbl);
		frameNumberLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(frameNumberLbl);

		frameNumberTxt = new JTextField();
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, frameNumberTxt, -3, SpringLayout.NORTH,
				frameNumberLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, frameNumberTxt, 53, SpringLayout.EAST, frameNumberLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.EAST, frameNumberTxt, -106, SpringLayout.EAST, vehiclesDatesPanelLeft);
		frameNumberTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		frameNumberTxt.setColumns(10);
		vehiclesDatesPanelLeft.add(frameNumberTxt);

		JLabel brandLbl = new JLabel("Marca:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, brandLbl, 26, SpringLayout.SOUTH, frameNumberLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, brandLbl, 0, SpringLayout.WEST, vehicleTypeLbl);
		brandLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(brandLbl);

		brandTxt = new JTextField();
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, brandTxt, 146, SpringLayout.EAST, brandLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.EAST, brandTxt, -106, SpringLayout.EAST, vehiclesDatesPanelLeft);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, brandTxt, -3, SpringLayout.NORTH, brandLbl);
		brandTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		brandTxt.setColumns(10);
		vehiclesDatesPanelLeft.add(brandTxt);

		JLabel modelLbl = new JLabel("Modelo:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, modelLbl, 26, SpringLayout.SOUTH, brandLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, modelLbl, 0, SpringLayout.WEST, vehicleTypeLbl);
		modelLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(modelLbl);

		modelTxt = new JTextField();
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, modelTxt, -3, SpringLayout.NORTH, modelLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, modelTxt, 137, SpringLayout.EAST, modelLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.EAST, modelTxt, -106, SpringLayout.EAST, vehiclesDatesPanelLeft);
		modelTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		modelTxt.setColumns(10);
		vehiclesDatesPanelLeft.add(modelTxt);

		JPanel vehiclesDatesPanelRight = new JPanel();
		vehiclesDatesPanel.add(vehiclesDatesPanelRight);
		SpringLayout sl_vehiclesDatesPanelRight = new SpringLayout();
		vehiclesDatesPanelRight.setLayout(sl_vehiclesDatesPanelRight);

		JLabel fuelVehicleLbl = new JLabel("Tipo de combustible:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, fuelVehicleLbl, 26, SpringLayout.NORTH,
				vehiclesDatesPanelRight);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, fuelVehicleLbl, 70, SpringLayout.WEST,
				vehiclesDatesPanelRight);
		fuelVehicleLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(fuelVehicleLbl);

		fuelVehicleComboBox = new JComboBox<>();
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, fuelVehicleComboBox, 74, SpringLayout.EAST, fuelVehicleLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.EAST, fuelVehicleComboBox, -81, SpringLayout.EAST, vehiclesDatesPanelRight);
		fuelVehicleComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "Diesel", "Gasolina", "Eléctrico", "Híbrido" }));
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, fuelVehicleComboBox, -3, SpringLayout.NORTH,
				fuelVehicleLbl);
		fuelVehicleComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(fuelVehicleComboBox);

		JLabel priceLbl = new JLabel("Precio:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, priceLbl, 26, SpringLayout.SOUTH, fuelVehicleLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, priceLbl, 0, SpringLayout.WEST, fuelVehicleLbl);
		priceLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(priceLbl);

		priceTxt = new JTextField();
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.EAST, priceTxt, 0, SpringLayout.EAST, fuelVehicleComboBox);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, priceTxt, 163, SpringLayout.EAST, priceLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, priceTxt, -3, SpringLayout.NORTH, priceLbl);
		priceTxt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		priceTxt.setColumns(10);
		vehiclesDatesPanelRight.add(priceTxt);

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

	/**
	 * Crea un vehículo con los datos de los campos de texto y comboBox
	 * 
	 * @return Objeto vehículo
	 */
	private Vehicle createVehicle() {
		Vehicle vehicle = null;
		var numFrameCar = frameNumberTxt.getText();
		var brand = brandTxt.getText();
		var model = modelTxt.getText();
		var fuel = fuelVehicleComboBox.getSelectedItem().toString();
		var price = priceTxt.getText();

		if (frameNumberTxt.getText().isBlank() || brandTxt.getText().isBlank() || modelTxt.getText().isBlank()
				|| priceTxt.getText().isBlank()) {
			JOptionPane.showMessageDialog(frame, "Error, los campos no pueden estar vacios, ni contener solo espacios",
					"Warning!", JOptionPane.ERROR_MESSAGE);
		} else {
			vehicle = new Vehicle(numFrameCar, brand, model, fuel, price);
		}

		return vehicle;
	}

	public JFrame getFrame() {
		return frame;
	}

}
