package view.sales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dao.VehicleDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;

public class SalesVehicleDetailsView {
	
	
	private String numberFrame;
	private String vehicleType;
	private JFrame frame;
	private JButton returnButton;
	private JLabel resultVehicleTypeLbl;
	private JLabel resultVehicleLicenseLbl;
	private JLabel resultFrameNumberLbl;
	private JLabel resultBrandLbl;
	private JLabel resultModelLbl;
	private JLabel resultFuelVehicleLbl;
	private JLabel resultPriceLbl;
	private JLabel resultOwnerLbl;
	private JLabel resultSalesClientLbl;
	private JLabel resultconcessionaireLbl;
	private VehicleDAO vehicleDAO;

	/**
	 * Crea la aplicación
	 * @param i 
	 */
	public SalesVehicleDetailsView(String numberFrame, String vehicleType) {
		this.numberFrame = numberFrame;
		this.vehicleType = vehicleType;
		initialize();
		vehicleDAO = new VehicleDAO();
	}

	/**
	 * Inicializa el contenido del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUIComponents();
		setControllers();
	}

	/**
	 * Contiene los controladores
	 */
	private void setControllers() {
		// Al abrir la ventana carga los datos del vehículo seleccionado por num_bastidor
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				if(vehicleType.equalsIgnoreCase("coche")) {
					var car = vehicleDAO.searchCar(numberFrame);
					resultVehicleTypeLbl.setText("Coche");
					resultBrandLbl.setText(car.getMarca());
					resultconcessionaireLbl.setText(car.getConcesionario());
					resultFrameNumberLbl.setText(car.getNum_bastidor());
					resultFuelVehicleLbl.setText(car.getCombustible());
					resultModelLbl.setText(car.getModelo());
					resultOwnerLbl.setText(car.getNombreCliente());
					resultPriceLbl.setText(car.getPrecio());
					resultSalesClientLbl.setText(car.getNombreVentas());
					resultVehicleLicenseLbl.setText(car.getMat_coche());
					
				} else if (vehicleType.equalsIgnoreCase("motocicleta")) {
					var motorcycle = vehicleDAO.searchMotorcycle(numberFrame);
					resultVehicleTypeLbl.setText("Motocicleta");
					resultBrandLbl.setText(motorcycle.getMarca());
					resultconcessionaireLbl.setText(motorcycle.getConcesionario());
					resultFrameNumberLbl.setText(motorcycle.getNum_bastidor());
					resultFuelVehicleLbl.setText(motorcycle.getCombustible());
					resultModelLbl.setText(motorcycle.getModelo());
					resultOwnerLbl.setText(motorcycle.getNombreCliente());
					resultPriceLbl.setText(motorcycle.getPrecio());
					resultSalesClientLbl.setText(motorcycle.getNombreVentas());
					resultVehicleLicenseLbl.setText(motorcycle.getMat_moto());
				} else {
					var moped = vehicleDAO.searchMoped(numberFrame);
					resultVehicleTypeLbl.setText("Ciclomotor");
					resultBrandLbl.setText(moped.getMarca());
					resultconcessionaireLbl.setText(moped.getConcesionario());
					resultFrameNumberLbl.setText(moped.getNum_bastidor());
					resultFuelVehicleLbl.setText(moped.getCombustible());
					resultModelLbl.setText(moped.getModelo());
					resultOwnerLbl.setText(moped.getNombreCliente());
					resultPriceLbl.setText(moped.getPrecio());
					resultSalesClientLbl.setText(moped.getNombreVentas());
					resultVehicleLicenseLbl.setText(moped.getMat_ciclo());
				}
				
			}
		});
		
		// Botón cerrar
		returnButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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

		JLabel lblBlank = new JLabel(" ");
		lblBlank.setForeground(new Color(220, 20, 60));
		lblBlank.setFont(new Font("SansSerif", Font.BOLD, 9));
		bottomPanel.add(lblBlank);

		/*
		 * mainPanel. Dentro se crean otros paneles para añadir los distintos
		 * componentes.
		 */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 857, 0 };
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
		GridBagLayout gbl_vehiclesDatesPanel = new GridBagLayout();
		gbl_vehiclesDatesPanel.columnWidths = new int[] { 340, 432, 0 };
		gbl_vehiclesDatesPanel.rowHeights = new int[] { 267, 0 };
		gbl_vehiclesDatesPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_vehiclesDatesPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		vehiclesDatesPanel.setLayout(gbl_vehiclesDatesPanel);

		JPanel vehiclesDatesPanelLeft = new JPanel();
		GridBagConstraints gbc_vehiclesDatesPanelLeft = new GridBagConstraints();
		gbc_vehiclesDatesPanelLeft.fill = GridBagConstraints.BOTH;
		gbc_vehiclesDatesPanelLeft.insets = new Insets(0, 0, 0, 5);
		gbc_vehiclesDatesPanelLeft.gridx = 0;
		gbc_vehiclesDatesPanelLeft.gridy = 0;
		vehiclesDatesPanel.add(vehiclesDatesPanelLeft, gbc_vehiclesDatesPanelLeft);
		SpringLayout sl_vehiclesDatesPanelLeft = new SpringLayout();
		vehiclesDatesPanelLeft.setLayout(sl_vehiclesDatesPanelLeft);

		// Componentes panel derecho
		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel vehicleTypeLbl = new JLabel("Tipo de vehículo:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleTypeLbl, 28, SpringLayout.WEST,
				vehiclesDatesPanelLeft);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.SOUTH, vehicleTypeLbl, -220, SpringLayout.SOUTH,
				vehiclesDatesPanelLeft);
		vehicleTypeLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelLeft.add(vehicleTypeLbl);

		JLabel vehicleLicenseLbl = new JLabel("Matrícula:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, vehicleLicenseLbl, 22, SpringLayout.SOUTH,
				vehicleTypeLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, vehicleLicenseLbl, 0, SpringLayout.WEST,
				vehicleTypeLbl);
		vehicleLicenseLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelLeft.add(vehicleLicenseLbl);

		// Añadir Jlabel y JText para los distintos datos del ciente
		JLabel frameNumberLbl = new JLabel("Número de bastidor:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, frameNumberLbl, 23, SpringLayout.SOUTH,
				vehicleLicenseLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, frameNumberLbl, 0, SpringLayout.WEST,
				vehicleTypeLbl);
		frameNumberLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelLeft.add(frameNumberLbl);

		JLabel brandLbl = new JLabel("Marca:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, brandLbl, 19, SpringLayout.SOUTH, frameNumberLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, brandLbl, 0, SpringLayout.WEST, vehicleTypeLbl);
		brandLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelLeft.add(brandLbl);

		JLabel modelLbl = new JLabel("Modelo:");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, modelLbl, 20, SpringLayout.SOUTH, brandLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, modelLbl, 0, SpringLayout.WEST, vehicleTypeLbl);
		modelLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelLeft.add(modelLbl);

		resultVehicleTypeLbl = new JLabel("");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, resultVehicleTypeLbl, 0, SpringLayout.NORTH,
				vehicleTypeLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, resultVehicleTypeLbl, 24, SpringLayout.EAST,
				vehicleTypeLbl);
		resultVehicleTypeLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(resultVehicleTypeLbl);

		resultVehicleLicenseLbl = new JLabel("");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, resultVehicleLicenseLbl, 0, SpringLayout.NORTH,
				vehicleLicenseLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, resultVehicleLicenseLbl, 0, SpringLayout.WEST,
				resultVehicleTypeLbl);
		resultVehicleLicenseLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(resultVehicleLicenseLbl);

		resultFrameNumberLbl = new JLabel("");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, resultFrameNumberLbl, 0, SpringLayout.NORTH,
				frameNumberLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, resultFrameNumberLbl, 6, SpringLayout.EAST,
				frameNumberLbl);
		resultFrameNumberLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(resultFrameNumberLbl);

		resultBrandLbl = new JLabel("");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, resultBrandLbl, 0, SpringLayout.NORTH, brandLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, resultBrandLbl, 0, SpringLayout.WEST,
				resultVehicleTypeLbl);
		resultBrandLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(resultBrandLbl);

		resultModelLbl = new JLabel("");
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.NORTH, resultModelLbl, 0, SpringLayout.NORTH, modelLbl);
		sl_vehiclesDatesPanelLeft.putConstraint(SpringLayout.WEST, resultModelLbl, 0, SpringLayout.WEST,
				resultVehicleTypeLbl);
		resultModelLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelLeft.add(resultModelLbl);

		JPanel vehiclesDatesPanelRight = new JPanel();
		GridBagConstraints gbc_vehiclesDatesPanelRight = new GridBagConstraints();
		gbc_vehiclesDatesPanelRight.fill = GridBagConstraints.BOTH;
		gbc_vehiclesDatesPanelRight.gridx = 1;
		gbc_vehiclesDatesPanelRight.gridy = 0;
		vehiclesDatesPanel.add(vehiclesDatesPanelRight, gbc_vehiclesDatesPanelRight);
		SpringLayout sl_vehiclesDatesPanelRight = new SpringLayout();
		vehiclesDatesPanelRight.setLayout(sl_vehiclesDatesPanelRight);

		JLabel fuelVehicleLbl = new JLabel("Tipo combustible:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, fuelVehicleLbl, 25, SpringLayout.NORTH,
				vehiclesDatesPanelRight);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, fuelVehicleLbl, 28, SpringLayout.WEST,
				vehiclesDatesPanelRight);
		fuelVehicleLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelRight.add(fuelVehicleLbl);

		JLabel priceLbl = new JLabel("Precio:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, priceLbl, 0, SpringLayout.WEST, fuelVehicleLbl);
		priceLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelRight.add(priceLbl);

		resultFuelVehicleLbl = new JLabel("");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, resultFuelVehicleLbl, 0, SpringLayout.NORTH,
				fuelVehicleLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, resultFuelVehicleLbl, 6, SpringLayout.EAST,
				fuelVehicleLbl);
		resultFuelVehicleLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(resultFuelVehicleLbl);

		resultPriceLbl = new JLabel("");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, resultPriceLbl, 70, SpringLayout.NORTH, vehiclesDatesPanelRight);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, priceLbl, 0, SpringLayout.NORTH, resultPriceLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, resultPriceLbl, 0, SpringLayout.WEST,
				resultFuelVehicleLbl);
		resultPriceLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(resultPriceLbl);

		JLabel clientOwnerLbl = new JLabel("Propietario:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, clientOwnerLbl, 20, SpringLayout.SOUTH, priceLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, clientOwnerLbl, 0, SpringLayout.WEST,
				fuelVehicleLbl);
		clientOwnerLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelRight.add(clientOwnerLbl);

		resultOwnerLbl = new JLabel("");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, resultOwnerLbl, 0, SpringLayout.NORTH,
				clientOwnerLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.EAST, resultOwnerLbl, 0, SpringLayout.EAST,
				resultPriceLbl);
		resultOwnerLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(resultOwnerLbl);

		JLabel salesClientLbl = new JLabel("Atendido por:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, salesClientLbl, 20, SpringLayout.SOUTH,
				clientOwnerLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, salesClientLbl, 0, SpringLayout.WEST,
				fuelVehicleLbl);
		salesClientLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelRight.add(salesClientLbl);

		resultSalesClientLbl = new JLabel("");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, resultSalesClientLbl, 0, SpringLayout.WEST,
				resultFuelVehicleLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.SOUTH, resultSalesClientLbl, 0, SpringLayout.SOUTH,
				salesClientLbl);
		resultSalesClientLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(resultSalesClientLbl);

		JLabel concessionaireLbl = new JLabel("Concesionario:");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.NORTH, concessionaireLbl, 23, SpringLayout.SOUTH,
				salesClientLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, concessionaireLbl, 0, SpringLayout.WEST,
				fuelVehicleLbl);
		concessionaireLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesDatesPanelRight.add(concessionaireLbl);

		resultconcessionaireLbl = new JLabel("");
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.WEST, resultconcessionaireLbl, 0, SpringLayout.WEST,
				resultFuelVehicleLbl);
		sl_vehiclesDatesPanelRight.putConstraint(SpringLayout.SOUTH, resultconcessionaireLbl, 0, SpringLayout.SOUTH,
				concessionaireLbl);
		resultconcessionaireLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesDatesPanelRight.add(resultconcessionaireLbl);

		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_botonPanel = new GridBagConstraints();
		gbc_botonPanel.fill = GridBagConstraints.BOTH;
		gbc_botonPanel.gridx = 0;
		gbc_botonPanel.gridy = 2;
		mainPanel.add(buttonPanel, gbc_botonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] { 276, 0, 0, 113, 0 };
		gbl_buttonPanel.rowHeights = new int[] { 100, 29, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		buttonPanel.setLayout(gbl_buttonPanel);

		// Botones
		returnButton = new JButton("    Cerrar    ");
		returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		returnButton.setBackground(new Color(244, 162, 97));
		returnButton.setForeground(Color.WHITE);
		GridBagConstraints gbc_returnButton = new GridBagConstraints();
		gbc_returnButton.insets = new Insets(0, 0, 5, 5);
		gbc_returnButton.anchor = GridBagConstraints.WEST;
		gbc_returnButton.gridx = 1;
		gbc_returnButton.gridy = 0;
		buttonPanel.add(returnButton, gbc_returnButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	
}
