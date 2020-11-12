
package view;

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
import dao.VehicleDAO;

import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Cursor;

public class MechanicalLandingView {

	private JFrame frame;
	private JButton btnLogOut;
	private JButton repairBtn;
	private JButton clientBtn;
	private JButton finishRepairBtn;
	private JTable vehiclesRepairTable;

	private VehicleDAO vehicleDAO;
	private ClientDAO clientDAO;

	private boolean isBoss;

	/**
	 * Create the application.
	 */
	public MechanicalLandingView(boolean isBoss) {
		this.isBoss = isBoss;
		initialize();
		vehicleDAO = new VehicleDAO();
		clientDAO = new ClientDAO();
		
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

		// Al abrir la ventana se rellena con los datos de todos los vehículos posibles
		// para reparar
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				// Insertar los vehiculos en la tabla vehiculos
				var vehiclesList = vehicleDAO.getVehicles();
				if (vehiclesList != null) {
					for (var i = 0; i < vehiclesList.size(); ++i) {
						tableModel.addRow(new Object[] { vehiclesList.get(i).getTipoVehiculo(),
								vehiclesList.get(i).getNum_bastidor(), vehiclesList.get(i).getMarca(),
								vehiclesList.get(i).getModelo(), vehiclesList.get(i).getCombustible() });
					}
				}
			}
		});

		// Reparar vehículo
		repairBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Estaría bien implementar que si selecciona un vehículo de la tabla, se cargan esos datos en la nueva
				// ventana, sino los campos estarán vacíos
				if (vehiclesRepairTable.getSelectedRow() != -1) {
					// TODO: new MechanicalVehicleRepairView(params).getFrame().setVisible(true);
					frame.dispose();
				} else {
					// TODO: new MechanicalVehicleRepairView().getFrame().setVisible(true);
				}
			}
		});

		// Finalizar reparación
		finishRepairBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO
			}
		});

		// Ficha del cliente
		clientBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (vehiclesRepairTable.getSelectedRow() != -1) {
					var client = clientDAO.searchClientByFrameNumber(
							String.valueOf(tableModel.getValueAt(vehiclesRepairTable.getSelectedRow(), 2)));
					// TODO: new MechanicalClientDetailsView(client);
					frame.dispose();
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
		gbl_bodyPanel.columnWidths = new int[] { 398, 661, 0 };
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
		// Comprobar si es jefe o no para desactivar el botón añadir vehículo a reparar
		repairBtn.setEnabled(isBoss);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, repairBtn, 106, SpringLayout.NORTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, repairBtn, 88, SpringLayout.WEST,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, repairBtn, 171, SpringLayout.NORTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.EAST, repairBtn, 291, SpringLayout.WEST,
				mainActionsPanelLeft);
		repairBtn.setForeground(Color.WHITE);
		repairBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		repairBtn.setBackground(new Color(231, 111, 81));
		mainActionsPanelLeft.add(repairBtn);

		clientBtn = new JButton("Ficha cliente");
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, clientBtn, -194, SpringLayout.SOUTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, clientBtn, 0, SpringLayout.WEST, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, clientBtn, -129, SpringLayout.SOUTH,
				mainActionsPanelLeft);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.EAST, clientBtn, 0, SpringLayout.EAST, repairBtn);
		clientBtn.setForeground(Color.WHITE);
		clientBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		clientBtn.setBackground(new Color(231, 111, 81));
		mainActionsPanelLeft.add(clientBtn);

		finishRepairBtn = new JButton("Acabar reparación");
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.NORTH, finishRepairBtn, 77, SpringLayout.SOUTH, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.WEST, finishRepairBtn, 0, SpringLayout.WEST, repairBtn);
		sl_mainActionsPanelLeft.putConstraint(SpringLayout.SOUTH, finishRepairBtn, -79, SpringLayout.NORTH, clientBtn);
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
		gbl_todayWorkPanelRight.columnWidths = new int[] { 0, 0 };
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
		vehiclesRepairTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Tipo veh\u00EDculo", "N\u00FAmero de bastidor", "Marca", "Modelo", "Combustible" }) {
			Class[] columnTypes = new Class[] { String.class, Integer.class, String.class, String.class, String.class };

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
		vehiclesRepairTable.setFont(new Font("SansSerif", Font.PLAIN, 18));
		tableScrollPane.setViewportView(vehiclesRepairTable);

	}

	public JFrame getFrame() {
		return frame;
	}
}
