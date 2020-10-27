package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SalesLandingView {

	private JFrame frame;
	private JButton btnLogOut;
	private JComboBox<?> customerComboBox;
	private JComboBox<?> vehicleComboBox;

	/**
	 * Crea la aplicaci�n
	 */
	public SalesLandingView() {
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
		// Combobox de clientes
		customerComboBox.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (customerComboBox.getSelectedItem().toString().equals("Alta cliente")) {
					// TODO: abrir ventana alta cliente
					customerComboBox.setSelectedIndex(0);
				}
				if (customerComboBox.getSelectedItem().toString().equals("Listar cliente")) {
					// TODO: abrir ventana buscar cliente
					customerComboBox.setSelectedIndex(0);
				}
			}
		});

		// Combobox de veh�culos
		vehicleComboBox.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (vehicleComboBox.getSelectedItem().toString().equals("Alta veh�culo")) {
					// TODO: abrir ventana alta cliente
					vehicleComboBox.setSelectedIndex(0);
				}
				if (vehicleComboBox.getSelectedItem().toString().equals("Listar veh�culo")) {
					// TODO: abrir ventana buscar cliente
					vehicleComboBox.setSelectedIndex(0);
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

		btnLogOut = new JButton("Cerrar Sesi�n");

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
		mainPanel.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel headerPanel = new JPanel();
		mainPanel.add(headerPanel);

		JLabel lblNewLabel = new JLabel("Men\u00FA Principal");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		headerPanel.add(lblNewLabel);

		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		mainPanel.add(customerPanel);
		customerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 80));

		JLabel lblCustomers = new JLabel("Gesti\u00F3n de clientes");
		lblCustomers.setFont(new Font("SansSerif", Font.BOLD, 18));
		customerPanel.add(lblCustomers);

		customerComboBox = new JComboBox();
		customerComboBox.setModel(new DefaultComboBoxModel(new String[] {"Elige una", "Alta cliente", "Listar cliente"}));
		customerComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		customerPanel.add(customerComboBox);

		JPanel vehiclesPanel = new JPanel();
		vehiclesPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		mainPanel.add(vehiclesPanel);
		vehiclesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 80));

		JLabel lblGestinDeVehculos = new JLabel("Gesti\u00F3n de veh\u00EDculos");
		lblGestinDeVehculos.setFont(new Font("SansSerif", Font.BOLD, 18));
		vehiclesPanel.add(lblGestinDeVehculos);

		vehicleComboBox = new JComboBox();
		vehicleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Elige una", "Alta veh\u00EDculo", "Listar veh\u00EDculo"}));
		vehicleComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		vehiclesPanel.add(vehicleComboBox);

		JPanel salesPanel = new JPanel();
		salesPanel.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		mainPanel.add(salesPanel);
		salesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 80));

		JLabel lblSales = new JLabel("Propuestas de venta");
		lblSales.setFont(new Font("SansSerif", Font.BOLD, 18));
		salesPanel.add(lblSales);

		JComboBox salesComboBox = new JComboBox();
		salesComboBox.setModel(new DefaultComboBoxModel(new String[] { "In Process..." }));
		salesComboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		salesPanel.add(salesComboBox);

	}

	public JFrame getFrame() {
		return frame;
	}

}
