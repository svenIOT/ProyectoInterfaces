package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LoginView {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 900, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUIComponents();
		setControllers();
	}
	
	private void setControllers() {
		
	}

	private void setUIComponents() {
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
