package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import bus.FileHandler;
import bus.ICustomer;
import bus.IManager;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FortisBankGUI {

	static IManager manager;
	static ICustomer c;
	
	private JFrame frame;
	private JTextField txtCustomerNumber;
	private JPasswordField txtPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//will read file
					manager = (IManager)FileHandler.readManagerFromFile();
					
					FortisBankGUI window = new FortisBankGUI();
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
	public FortisBankGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fortis Bank Application");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(98, 3, 237, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Number :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(64, 75, 137, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(64, 121, 137, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtCustomerNumber = new JTextField();
		txtCustomerNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCustomerNumber.setBounds(218, 79, 126, 25);
		frame.getContentPane().add(txtCustomerNumber);
		txtCustomerNumber.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(218, 121, 126, 25);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int customerNumber = Integer.parseInt(txtCustomerNumber.getText());
				
				int pin = Integer.parseInt(txtPassword.getText());
				
				//checking if customer exists
				c = manager.searchCustomer(customerNumber);
				if(c!=null) {
					//checking if customer enter valid id and pin
					if(c.isValidLogin(customerNumber, pin)){
						//here customer successful login
						client.FortisBankClientMenuGUI aClient = new FortisBankClientMenuGUI();
						aClient.setVisible(true);
						
						//this will close the login frame
						frame.setVisible(false);
						
					}else {
						JOptionPane.showMessageDialog(frame, "customerNumber and pin does not match");
					}
				}else {
					JOptionPane.showMessageDialog(frame, "Incorrect Information!");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(218, 197, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton txtClear = new JButton("Clear");
		txtClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCustomerNumber.setText("");
				txtPassword.setText("");
			}
		});
		txtClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtClear.setBounds(112, 199, 89, 23);
		frame.getContentPane().add(txtClear);
		
		JLabel lblNewLabel_2 = new JLabel("CLIENT - LOGIN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(132, 39, 169, 25);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
