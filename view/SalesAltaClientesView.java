package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class SalesAltaClientesView {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesAltaClientesView window = new SalesAltaClientesView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SalesAltaClientesView() {
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
	
	private void setControllers() {
		
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
		topPanel.setBounds(100,100,100,100);
		
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
		//Se añaden los componentes al bottomPanel
		JButton btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.setBorder(null);
		btnLogOut.setBackground(new Color(233, 196, 106));
		btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 12));
		bottomPanel.add(btnLogOut);
		
		JLabel lblLogOut = new JLabel("X");
		lblLogOut.setForeground(new Color(220, 20, 60));
		lblLogOut.setFont(new Font("SansSerif", Font.BOLD, 13));
		bottomPanel.add(lblLogOut);
		
		/*
		 * mainPanel. Dentro se crean otros paneles para añadir los distintos componentes.
		 */
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{1061, 0};
		gbl_mainPanel.rowHeights = new int[]{137, 298, 328, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		//Paneles que componen el mainPanel
		
		JPanel clientesPanel = new JPanel();
		GridBagConstraints gbc_clientesPanel = new GridBagConstraints();
		gbc_clientesPanel.fill = GridBagConstraints.BOTH;
		gbc_clientesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_clientesPanel.gridx = 0;
		gbc_clientesPanel.gridy = 0;
		mainPanel.add(clientesPanel, gbc_clientesPanel);
		clientesPanel.setLayout(new BorderLayout());
		clientesPanel.setPreferredSize(new Dimension(10, 50));
		
		//Añadir Jlabel a clientesPanel
		JLabel lblDatosClientes = new JLabel("Datos del cliente");
		lblDatosClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosClientes.setFont(new Font("SansSerif", Font.BOLD, 25));
		clientesPanel.add(lblDatosClientes, BorderLayout.CENTER);
		
		//Panel para rellenar datos de cliente.
		JPanel datosClientePanel = new JPanel();
		GridBagConstraints gbc_datosClientePanel = new GridBagConstraints();
		gbc_datosClientePanel.fill = GridBagConstraints.BOTH;
		gbc_datosClientePanel.insets = new Insets(0, 0, 5, 0);
		gbc_datosClientePanel.gridx = 0;
		gbc_datosClientePanel.gridy = 1;
		mainPanel.add(datosClientePanel, gbc_datosClientePanel);
		datosClientePanel.setLayout(new GridLayout(1,2));
		
		JPanel datosClientePanelLeft = new JPanel();
		datosClientePanel.add(datosClientePanelLeft);
		SpringLayout sl_datosClientePanelLeft = new SpringLayout();
		datosClientePanelLeft.setLayout(sl_datosClientePanelLeft);
		
		//Componentes panel derecho
		//Añadir Jlabel y JText para los distintos datos del ciente 
		JLabel lblCod = new JLabel("Código de cliente:");
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, lblCod, 26, SpringLayout.NORTH, datosClientePanelLeft);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, lblCod, 70, SpringLayout.WEST, datosClientePanelLeft);
		lblCod.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datosClientePanelLeft.add(lblCod);
				
		JTextField txtCod = new JTextField();
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, txtCod, -3, SpringLayout.NORTH, lblCod);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, txtCod, 74, SpringLayout.EAST, lblCod);
		txtCod.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtCod.setColumns(10);
		datosClientePanelLeft.add(txtCod);
						
		//Añadir Jlabel y JText para los distintos datos del ciente 
		JLabel lblNombreCliente = new JLabel("Nombre:");
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, lblNombreCliente, 26, SpringLayout.SOUTH, lblCod);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, lblNombreCliente, 0, SpringLayout.WEST, lblCod);
		lblNombreCliente.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datosClientePanelLeft.add(lblNombreCliente);
					
		JTextField txtNombre = new JTextField();
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, txtNombre, -3, SpringLayout.NORTH, lblNombreCliente);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, txtNombre, 0, SpringLayout.WEST, txtCod);
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtNombre.setColumns(10);
		datosClientePanelLeft.add(txtNombre);
							
		//Añadir Jlabel y JText para los distintos datos del ciente 
		JLabel lblApellidos = new JLabel("Apellidos:");
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, lblApellidos, 26, SpringLayout.SOUTH, lblNombreCliente);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, lblApellidos, 0, SpringLayout.WEST, lblCod);
		lblApellidos.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datosClientePanelLeft.add(lblApellidos);
								
		JTextField txtApellidos = new JTextField();
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, txtApellidos, -3, SpringLayout.NORTH, lblApellidos);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, txtApellidos, 0, SpringLayout.WEST, txtCod);
		txtApellidos.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtApellidos.setColumns(10);
		datosClientePanelLeft.add(txtApellidos);
		
		JLabel lblDni = new JLabel("DNI:");
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, lblDni, 26, SpringLayout.SOUTH, lblApellidos);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, lblDni, 0, SpringLayout.WEST, lblCod);
		lblDni.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datosClientePanelLeft.add(lblDni);
				
		JTextField txtDni = new JTextField();
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, txtDni, -3, SpringLayout.NORTH, lblDni);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, txtDni, 0, SpringLayout.WEST, txtCod);
		txtDni.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtDni.setColumns(10);
		datosClientePanelLeft.add(txtDni);
				
		//Añadir Jlabel y JText para los distintos datos del ciente 
		JLabel lblTelefono = new JLabel("Teléfono:");
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, lblTelefono, 26, SpringLayout.SOUTH, lblDni);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, lblTelefono, 0, SpringLayout.WEST, lblCod);
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 15));
		datosClientePanelLeft.add(lblTelefono);
										
		JTextField txtTelefono = new JTextField();
		sl_datosClientePanelLeft.putConstraint(SpringLayout.NORTH, txtTelefono, -3, SpringLayout.NORTH, lblTelefono);
		sl_datosClientePanelLeft.putConstraint(SpringLayout.WEST, txtTelefono, 0, SpringLayout.WEST, txtCod);
		txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtTelefono.setColumns(10);
		datosClientePanelLeft.add(txtTelefono);
		
		JPanel datosClientePanelRight = new JPanel();
		datosClientePanel.add(datosClientePanelRight);
		
		JPanel botonPanel = new JPanel();
		GridBagConstraints gbc_botonPanel = new GridBagConstraints();
		gbc_botonPanel.fill = GridBagConstraints.BOTH;
		gbc_botonPanel.gridx = 0;
		gbc_botonPanel.gridy = 2;
		mainPanel.add(botonPanel, gbc_botonPanel);
		botonPanel.setLayout(new FlowLayout(1,250,100));
		
		//Botones
		JButton volverButton = new JButton("Volver al menú");
		volverButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		volverButton.setBackground(new Color(238, 151, 129));
		volverButton.setForeground(Color.WHITE);
		botonPanel.add(volverButton);
		
		JButton registrarButton = new JButton("Añadir cliente");
		registrarButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		registrarButton.setBackground(new Color(244,162,97));
		registrarButton.setForeground(Color.WHITE);
		botonPanel.add(registrarButton);
			
	}

}
