package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClientDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SalesSearchAndListClientView {

	private JFrame frame;
	private JButton btnLogOut;
	private JButton btnBackToMenu;
	private JTextField textFieldSearch;
	private JTable clientTable;
	private JButton btnSearch;
	private ClientDAO clientDAO;

	/**
	 * Crea la aplicación
	 */
	public SalesSearchAndListClientView() {
		initialize();
		clientDAO = new ClientDAO();
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
		// Al abrir la ventana se rellena la tabla con TODOS los clientes
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				// Insertar los clientes en la tabla clientes
				var clientList = clientDAO.fillTable();
				if (clientList != null) {
					for (var i = 0; i < clientList.size(); ++i) {
						var tableModel = (DefaultTableModel) clientTable.getModel();
						tableModel.addRow(new Object[] { clientList.get(i).getClientCod() , clientList.get(i).getDni(),
								clientList.get(i).getNombre(), clientList.get(i).getApellidos(), clientList.get(i).getTelefono() });
					}
				}
			}
		});

		// Botón buscar cliente
		btnSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Reiniciar el contenido de la tabla clientes
				var rows = clientTable.getRowCount();
				clientTable.removeRowSelectionInterval(0, rows);

				// Buscar cliente por dni
				var dni = textFieldSearch.getText();
				var clientResult = clientDAO.searchClient(dni);

				// Insertar el cliente devuelto en la tabla clientes
				if (clientResult != null) {
					var tableModel = (DefaultTableModel) clientTable.getModel();
					tableModel.addRow(new Object[] { clientResult.getClientCod(), clientResult.getDni(),
							clientResult.getNombre(), clientResult.getApellidos(), clientResult.getTelefono() });
				} else {
					JOptionPane.showMessageDialog(frame, "Cliente no encontrado, revise el DNI", "Warning!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// Volver al menú principal
		btnBackToMenu.addMouseListener(new MouseAdapter() {
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
		mainPanel.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel);
		headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 20));

		btnBackToMenu = new JButton("Volver al menú");
		btnBackToMenu.setForeground(Color.WHITE);
		btnBackToMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnBackToMenu.setBackground(new Color(244, 162, 97));
		headerPanel.add(btnBackToMenu);

		JLabel lblMainMenu = new JLabel("Ficha del cliente");
		lblMainMenu.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(lblMainMenu);

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		mainPanel.add(searchPanel);
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 30));

		JLabel lblSearch = new JLabel("DNI");
		lblSearch.setFont(new Font("SansSerif", Font.BOLD, 18));
		searchPanel.add(lblSearch);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textFieldSearch.setText("99999999X");
		searchPanel.add(textFieldSearch);
		textFieldSearch.setColumns(20);

		btnSearch = new JButton("Buscar cliente");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSearch.setBorderPainted(false);
		btnSearch.setBackground(new Color(231, 111, 81));
		searchPanel.add(btnSearch);

		JPanel listPanel = new JPanel();
		listPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		mainPanel.add(listPanel);
		listPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

		clientTable = new JTable();
		clientTable.setFont(new Font("SansSerif", Font.BOLD, 15));
		clientTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "DNI", "Nombre", "Apellidos", "Tel\u00E9fono"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		clientTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		clientTable.getColumnModel().getColumn(0).setMaxWidth(555);
		clientTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		clientTable.getColumnModel().getColumn(1).setMaxWidth(555);
		clientTable.getColumnModel().getColumn(2).setPreferredWidth(180);
		clientTable.getColumnModel().getColumn(2).setMaxWidth(555);
		clientTable.getColumnModel().getColumn(3).setPreferredWidth(250);
		clientTable.getColumnModel().getColumn(3).setMaxWidth(555);
		clientTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		clientTable.getColumnModel().getColumn(4).setMaxWidth(555);
		listPanel.add(clientTable);

	}

	public JFrame getFrame() {
		return frame;
	}

}
