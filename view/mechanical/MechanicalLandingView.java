
package view.mechanical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import dao.RepairDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import model.Mechanical;
import model.Repair;
import view.LoginView;

import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class MechanicalLandingView {

	private JFrame frame;
	private JButton btnLogOut;
	private JButton repairBtn;
	private JButton clientBtn;
	private JButton finishRepairBtn;
	private JTable vehiclesRepairTable;
	private JLabel lblUser;

	private ClientDAO clientDAO;
	private UserDAO userDAO;
	private RepairDAO repairDAO;
	private VehicleDAO vehicleDAO;

	private Mechanical user;
	private boolean isBoss;

	/**
	 * Create the application.
	 * 
	 * @param user
	 */
	public MechanicalLandingView(Mechanical user, boolean isBoss) {
		this.user = user;
		this.isBoss = isBoss;
		clientDAO = new ClientDAO();
		repairDAO = new RepairDAO();
		vehicleDAO = new VehicleDAO();
		userDAO = new UserDAO();
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

	/**
	 * Contiene los controladores
	 */
	private void setControllers() {
		var tableModel = (DefaultTableModel) vehiclesRepairTable.getModel();
		
		// Datos del DAO
		var clients = clientDAO.getClients();
		var vehicles = vehicleDAO.getVehicles();

		// Al abrir la ventana se rellena con los datos de todas las reparaciones
		// posibles, también cargan los datos del usuario en la vista
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				// Obtener todas las reparaciones
				var repairsList = repairDAO.getRepairs();
				if (repairsList != null) {
					// Obtener todos los mecánicos
					var mechanicals = userDAO.getMechanicals();
					// Insertar las reparaciones en la tabla
					for (var i = 0; i < repairsList.size(); ++i) {
						
						// Conseguir datos (apellidos) del mecánico desde su código de mecánico
						var mechanicalCod = repairsList.get(i).getCod_mecanico();
						
						// Filtra el mecánico con el código de la iteración actual del bucle for (para
						// añadirlo en la tabla con sus datos y no con código)
						var selectedMechanical = mechanicals.stream()
								.filter(mech -> mech.getCod_mecanico() == mechanicalCod).collect(Collectors.toList());
						
						// Ordenadar por fecha de salida (para el trabajo del día)
						repairsList.stream().map(Repair::getFecha_salida).collect(Collectors.toList());
						
						tableModel.addRow(new Object[] { repairsList.get(i).getCod_reparacion(),
								selectedMechanical.get(0).getApellidos(), repairsList.get(i).getNum_bastidor(),
								repairsList.get(i).getFecha_entrada(), repairsList.get(i).getFecha_salida(),
								repairsList.get(i).getPiezas() });
					}
				}
				
				// Cargar datos de usuario
				lblUser.setText("Bienvenido/a " + user.getNombre() + " " + user.getApellidos());
			}
		});

		// Añadir reparación de vehículo
		repairBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MechanicalAddRepairView(user, isBoss).getFrame().setVisible(true); 
				frame.dispose();
			}
		});

		// Finalizar reparación
		finishRepairBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (vehiclesRepairTable.getSelectedRow() != -1) {
					var lastCheck = JOptionPane.showConfirmDialog(frame,
							"¿Está seguro de que desea finalizar esta reparación? Se avisará al cliente para que pase a recogerlo", "¡Atención!", 1, 0);
					// 1 sí, 0 no
					if(lastCheck == 1) {
						repairDAO.finishRepair((int) tableModel.getValueAt(vehiclesRepairTable.getSelectedRow(), 1));
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Seleccione la reparación de la tabla que desea finalizar",
							"Warning!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Ficha con los datos del cliente
		clientBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (vehiclesRepairTable.getSelectedRow() != -1) {
					// Buscar datos del cliente desde el número de bastidor del vehículo seleccionado
					var vehicleNumber = String.valueOf(tableModel.getValueAt(vehiclesRepairTable.getSelectedRow(), 2));
					
					// Filtro el vehículo con el número de bastidor seleccionado
					var selectedVehicle = vehicles.stream().filter(vehicle -> vehicle.getNum_bastidor().equalsIgnoreCase(vehicleNumber)).collect(Collectors.toList());
					
					// Filtro el cliente propietario del vehículo anterior
					var selectedClient = clients.stream().filter(client -> client.getClientCod() == selectedVehicle.get(0).getCod_cliente()).collect(Collectors.toList());
					
					new MechanicalClientDetailsView(selectedClient.get(0)).getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Seleccione un elemento de la tabla para ver el cliente",
							"Warning!", JOptionPane.ERROR_MESSAGE);
				}
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
		frame.setTitle("Departamento de mecánica");
		frame.setMinimumSize(new Dimension(700, 500));

		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(233, 196, 106));
		topPanel.setBounds(100, 100, 100, 100);
		
		JLabel lblIconUser = new JLabel("");
		lblIconUser.setIcon(new ImageIcon(MechanicalLandingView.class.getResource("/assets/img/icon_user.png")));
		lblIconUser.setAlignmentY(0.0f);
		lblIconUser.setLocation(5, 0);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 5));
		topPanel.add(lblIconUser);
		
		lblUser = new JLabel("");
		lblUser.setFont(new Font("SansSerif", Font.BOLD, 15));
		topPanel.add(lblUser);

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

		btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(new Color(233, 196, 106));
		btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 12));
		bottomPanel.add(btnLogOut);

		JLabel lblLogOut = new JLabel("X");
		lblLogOut.setForeground(new Color(220, 20, 60));
		lblLogOut.setFont(new Font("SansSerif", Font.BOLD, 13));
		bottomPanel.add(lblLogOut);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 1061, 0 };
		gbl_mainPanel.rowHeights = new int[] { 119, 586, 0 };
		gbl_mainPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JPanel headerPanel = new JPanel();
		GridBagConstraints gbc_headerPanel = new GridBagConstraints();
		gbc_headerPanel.fill = GridBagConstraints.BOTH;
		gbc_headerPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headerPanel.gridx = 0;
		gbc_headerPanel.gridy = 0;
		mainPanel.add(headerPanel, gbc_headerPanel);
		headerPanel.setLayout(new BorderLayout());
		headerPanel.setPreferredSize(new Dimension(10, 50));

		JLabel lblMain = new JLabel("Menú principal");
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(lblMain, BorderLayout.CENTER);

		JPanel bodyPanel = new JPanel();
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		mainPanel.add(bodyPanel, gbc_bodyPanel);
		GridBagLayout gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[] { 227, 751, 0 };
		gbl_bodyPanel.rowHeights = new int[] { 586, 0 };
		gbl_bodyPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_bodyPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		bodyPanel.setLayout(gbl_bodyPanel);

		JPanel mainActionsPanelLeft = new JPanel();
		GridBagConstraints gbc_mainActionsPanelLeft = new GridBagConstraints();
		gbc_mainActionsPanelLeft.fill = GridBagConstraints.BOTH;
		gbc_mainActionsPanelLeft.insets = new Insets(0, 0, 0, 5);
		gbc_mainActionsPanelLeft.gridx = 0;
		gbc_mainActionsPanelLeft.gridy = 0;
		bodyPanel.add(mainActionsPanelLeft, gbc_mainActionsPanelLeft);
		SpringLayout sl_mainActionsPanelLeft = new SpringLayout();
		mainActionsPanelLeft.setLayout(sl_mainActionsPanelLeft);

		repairBtn = new JButton("Nueva reparación");
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, repairBtn, 109, SpringLayout.NORTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, repairBtn, 10, SpringLayout.WEST,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, repairBtn, 174, SpringLayout.NORTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.EAST, repairBtn, 214, SpringLayout.WEST,
				mainActionsPanelLeft);
		// Comprobar si es jefe o no para desactivar el botón añadir vehículo a reparar
		repairBtn.setEnabled(isBoss);
		repairBtn.setForeground(Color.WHITE);
		repairBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		repairBtn.setBackground(new Color(231, 111, 81));
		mainActionsPanelLeft.add(repairBtn);

		clientBtn = new JButton("Ficha cliente");
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, clientBtn, 395, SpringLayout.NORTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, clientBtn, 0, SpringLayout.WEST, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, clientBtn, -126, SpringLayout.SOUTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.EAST, clientBtn, 0, SpringLayout.EAST, repairBtn);
		clientBtn.setForeground(Color.WHITE);
		clientBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		clientBtn.setBackground(new Color(231, 111, 81));
		mainActionsPanelLeft.add(clientBtn);

		finishRepairBtn = new JButton("Acabar reparación");
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, finishRepairBtn, 77, SpringLayout.SOUTH, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, finishRepairBtn, 0, SpringLayout.WEST, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, finishRepairBtn, -76, SpringLayout.NORTH, clientBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.EAST, finishRepairBtn, 0, SpringLayout.EAST, repairBtn);
		finishRepairBtn.setForeground(Color.WHITE);
		finishRepairBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		finishRepairBtn.setBackground(new Color(244, 162, 97));
		mainActionsPanelLeft.add(finishRepairBtn);

		JPanel todayWorkPanelRight = new JPanel();
		GridBagConstraints gbc_todayWorkPanelRight = new GridBagConstraints();
		gbc_todayWorkPanelRight.fill = GridBagConstraints.BOTH;
		gbc_todayWorkPanelRight.gridx = 1;
		gbc_todayWorkPanelRight.gridy = 0;
		bodyPanel.add(todayWorkPanelRight, gbc_todayWorkPanelRight);
		GridBagLayout gbl_todayWorkPanelRight = new GridBagLayout();
		gbl_todayWorkPanelRight.columnWidths = new int[] { 832, 0 };
		gbl_todayWorkPanelRight.rowHeights = new int[] { 0, 0 };
		gbl_todayWorkPanelRight.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_todayWorkPanelRight.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		todayWorkPanelRight.setLayout(gbl_todayWorkPanelRight);

		JScrollPane tableScrollPane = new JScrollPane();
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridx = 0;
		gbc_tableScrollPane.gridy = 0;
		todayWorkPanelRight.add(tableScrollPane, gbc_tableScrollPane);

		vehiclesRepairTable = new JTable();
		vehiclesRepairTable.setRowHeight(30);
		vehiclesRepairTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vehiclesRepairTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Código reparación",
				"Mecánico asignado", "N\u00FAmero de bastidor", "Fecha entrada", "Fecha salida", "Piezas" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		vehiclesRepairTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
		vehiclesRepairTable.getTableHeader().setForeground(Color.WHITE);
		vehiclesRepairTable.getTableHeader().setBackground(new Color(244, 162, 97));
		vehiclesRepairTable.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tableScrollPane.setViewportView(vehiclesRepairTable);
		
		JLabel label = new JLabel("New label");
		tableScrollPane.setColumnHeaderView(label);

	}

	public JFrame getFrame() {
		return frame;
	}
}
