package view.sales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.VehicleDAO;
import model.Sales;
import view.LoginView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SalesSearchAndListVehiclesView {

	private JFrame frame;
	private JButton btnLogOut;
	private JButton btnBackToMenu;
	private JTextField textFieldSearch;
	private JTable vehicleTable;
	private JButton btnSearch;
	private JButton btnDetallesDelVehiculo;
	private VehicleDAO vehicleDAO;
	private ButtonGroup group1;
	private JRadioButton carsRadioButton;
	private JRadioButton motorcyclesRadioButton;
	private JRadioButton mopedsRadioButton;

	private Sales user;

	/**
	 * Crea la aplicación
	 * 
	 * @param user
	 */
	public SalesSearchAndListVehiclesView(Sales user) {
		this.user = user;
		vehicleDAO = new VehicleDAO();
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
		var tableModel = (DefaultTableModel) vehicleTable.getModel();
		
		// Filtrar los vehículos que están a la venta
		var onSaleVehicles = vehicleDAO.getVehicles().stream().filter(vehicle -> vehicle.getCod_cliente() == 0).collect(Collectors.toList());
		
		// Filtrar los coches que están a la venta
		var onSaleCars = vehicleDAO.getCars().stream().filter(car -> car.getCod_cliente() == 0).collect(Collectors.toList());
		
		// Filtrar las motos que están a la venta
		var onSaleMotorcycles = vehicleDAO.getMotorcycles().stream().filter(motorcycle -> motorcycle.getCod_cliente() == 0).collect(Collectors.toList());
		
		// Filtrar los ciclomotores que están a la venta
		var onSaleMopeds = vehicleDAO.getMopeds().stream().filter(moped -> moped.getCod_cliente() == 0).collect(Collectors.toList());

		// Al abrir la ventana se rellena con los datos de coche
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				// Insertar los coches (por defecto) en la tabla
				if (onSaleCars != null) {
					for (var i = 0; i < onSaleCars.size(); ++i) {
						tableModel.addRow(new Object[] { onSaleCars.get(i).getNum_bastidor(),
								onSaleCars.get(i).getMarca(), onSaleCars.get(i).getModelo(),
								onSaleCars.get(i).getCombustible(), onSaleCars.get(i).getPrecio() });
					}
				}
			}
		});

		// Radiobutton filtrado por coches
		carsRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// Reiniciar el contenido de la tabla
				clearTable(tableModel);
				
				if (onSaleCars != null) {
					for (var i = 0; i < onSaleCars.size(); ++i) {
						tableModel.addRow(new Object[] { onSaleCars.get(i).getNum_bastidor(),
								onSaleCars.get(i).getMarca(), onSaleCars.get(i).getModelo(),
								onSaleCars.get(i).getCombustible(), onSaleCars.get(i).getPrecio() });
					}
				}
			}
		});

		// Radiobutton filtrado por coches
		motorcyclesRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// Reiniciar el contenido de la tabla
				clearTable(tableModel);
				
				if (onSaleMotorcycles != null) {
					for (var i = 0; i < onSaleMotorcycles.size(); ++i) {
						tableModel.addRow(new Object[] { onSaleMotorcycles.get(i).getNum_bastidor(),
								onSaleMotorcycles.get(i).getMarca(), onSaleMotorcycles.get(i).getModelo(),
								onSaleMotorcycles.get(i).getCombustible(), onSaleMotorcycles.get(i).getPrecio() });
					}
				}
			}
		});

		// Radiobutton filtrado por coches
		mopedsRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// Reiniciar el contenido de la tabla
				clearTable(tableModel);
				
				if (onSaleMopeds != null) {
					for (var i = 0; i < onSaleMopeds.size(); ++i) {
						tableModel.addRow(new Object[] { onSaleMopeds.get(i).getNum_bastidor(),
								onSaleMopeds.get(i).getMarca(), onSaleMopeds.get(i).getModelo(),
								onSaleMopeds.get(i).getCombustible(), onSaleMopeds.get(i).getPrecio() });
					}
				}
			}
		});

		// Botón buscar vehículo
		btnSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				

				// Buscar vehículo por num bastidor
				var num_bastidor = textFieldSearch.getText();
				
				// Filtra el vehículo con el número de bastidor del txtField
				var vehicleResult = onSaleVehicles.stream().filter(vehicle -> vehicle.getNum_bastidor().equalsIgnoreCase(num_bastidor)).collect(Collectors.toList());

				if (vehicleResult.size() > 0) {
					// Reiniciar el contenido de la tabla vehículos
					clearTable(tableModel);
					
					// Insertar el vehículo devuelto en la tabla vehículos
					tableModel.addRow(new Object[] { vehicleResult.get(0).getNum_bastidor(), vehicleResult.get(0).getMarca(),
							vehicleResult.get(0).getModelo(), vehicleResult.get(0).getCombustible(), vehicleResult.get(0).getPrecio() });

					// Poner el radioButton en la categoría que corresponde
					if (vehicleResult.get(0).getTipoVehiculo().equalsIgnoreCase("coche")) {
						carsRadioButton.setSelected(true);
					} else if (vehicleResult.get(0).getTipoVehiculo().equalsIgnoreCase("motocicleta")) {
						motorcyclesRadioButton.setSelected(true);
					} else {
						mopedsRadioButton.setSelected(true);
					}

				} else {
					JOptionPane.showMessageDialog(frame, "Vehículo no encontrado, revise el número de bastidor",
							"Warning!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// Botón ver detalles del vehículo
		btnDetallesDelVehiculo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Abrir detalles según la fila elegida
				if (vehicleTable.getSelectedRow() != -1) {
					new SalesVehicleDetailsView(String.valueOf(tableModel.getValueAt(vehicleTable.getSelectedRow(), 0))).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Haga clic en un vehículo de la tabla para ver más detalles",
							"Warning!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Volver al menú principal
		btnBackToMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SalesLandingView(user).getFrame().setVisible(true);
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
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
		gbl_mainPanel.columnWidths = new int[] { 1063, 0 };
		gbl_mainPanel.rowHeights = new int[] { 145, 110, 420, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JPanel headerPanel = new JPanel();
		GridBagConstraints gbc_headerPanel = new GridBagConstraints();
		gbc_headerPanel.fill = GridBagConstraints.BOTH;
		gbc_headerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headerPanel.gridx = 0;
		gbc_headerPanel.gridy = 0;
		mainPanel.add(headerPanel, gbc_headerPanel);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 20));

		btnBackToMenu = new JButton("Volver al menú");
		btnBackToMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBackToMenu.setForeground(Color.WHITE);
		btnBackToMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnBackToMenu.setBackground(new Color(244, 162, 97));
		headerPanel.add(btnBackToMenu);

		JLabel lblMainMenu = new JLabel("Lista de vehículos");
		lblMainMenu.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(lblMainMenu);

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.insets = new Insets(0, 0, 5, 0);
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 1;
		mainPanel.add(searchPanel, gbc_searchPanel);
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 30));

		JLabel lblSearch = new JLabel("Número de bastidor");
		lblSearch.setFont(new Font("SansSerif", Font.BOLD, 18));
		searchPanel.add(lblSearch);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldSearch.setText("1234567890");
		searchPanel.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		btnSearch = new JButton("Buscar vehículo");
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSearch.setBorderPainted(false);
		btnSearch.setBackground(new Color(231, 111, 81));
		searchPanel.add(btnSearch);

		JPanel filterPanel = new JPanel();
		searchPanel.add(filterPanel);
		filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

		JLabel lblTypes = new JLabel("Filtros");
		lblTypes.setFont(new Font("SansSerif", Font.BOLD, 18));
		filterPanel.add(lblTypes);

		carsRadioButton = new JRadioButton();
		carsRadioButton.setSelected(true);
		carsRadioButton.setText("Coches");
		carsRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 12));

		motorcyclesRadioButton = new JRadioButton();
		motorcyclesRadioButton.setText("Motos");
		motorcyclesRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 12));

		mopedsRadioButton = new JRadioButton();
		mopedsRadioButton.setText("Ciclomotores");
		mopedsRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 12));

		filterPanel.add(carsRadioButton);
		filterPanel.add(motorcyclesRadioButton);
		filterPanel.add(mopedsRadioButton);

		group1 = new ButtonGroup();
		group1.add(carsRadioButton);
		group1.add(motorcyclesRadioButton);
		group1.add(mopedsRadioButton);

		JPanel listPanel = new JPanel();
		listPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_listPanel = new GridBagConstraints();
		gbc_listPanel.fill = GridBagConstraints.BOTH;
		gbc_listPanel.gridx = 0;
		gbc_listPanel.gridy = 2;
		mainPanel.add(listPanel, gbc_listPanel);
		listPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

		vehicleTable = new JTable();
		vehicleTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
		vehicleTable.getTableHeader().setForeground(Color.WHITE);
		vehicleTable.getTableHeader().setBackground(new Color(244, 162, 97));
		vehicleTable.setPreferredScrollableViewportSize(new Dimension(950, 400));
		vehicleTable.setFont(new Font("SansSerif", Font.BOLD, 15));
		vehicleTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Número de bastidor", "Marca", "Modelo", "Combustible", "Precio" }) {
					private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		vehicleTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		vehicleTable.getColumnModel().getColumn(0).setMaxWidth(555);
		vehicleTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		vehicleTable.getColumnModel().getColumn(1).setMaxWidth(555);
		vehicleTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		vehicleTable.getColumnModel().getColumn(2).setMaxWidth(555);
		vehicleTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		vehicleTable.getColumnModel().getColumn(3).setMaxWidth(555);
		vehicleTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		vehicleTable.getColumnModel().getColumn(4).setMaxWidth(555);

		btnDetallesDelVehiculo = new JButton("Detalles vehículo");
		btnDetallesDelVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDetallesDelVehiculo.setForeground(Color.WHITE);
		btnDetallesDelVehiculo.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDetallesDelVehiculo.setBorderPainted(false);
		btnDetallesDelVehiculo.setBackground(new Color(231, 111, 81));
		listPanel.add(btnDetallesDelVehiculo);

		JScrollPane tableScrollPane = new JScrollPane(vehicleTable);
		tableScrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableScrollPane.setPreferredSize(new Dimension(1000, 402));
		tableScrollPane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		listPanel.add(tableScrollPane);

	}

	/**
	 * Elimina todas las filas de la tabla
	 * 
	 * @param tableModel DefaultTableModel
	 */
	private void clearTable(DefaultTableModel tableModel) {
		// Reiniciar el contenido de la tabla vehículos
		var rows = vehicleTable.getRowCount();
		// Si hay filas las elimina
		if (rows > 0) {
			for (var i = (rows - 1); i > -1; --i) {
				tableModel.removeRow(i);
			}
		}
	}

	public JFrame getFrame() {
		return frame;
	}

}