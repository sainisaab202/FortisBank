package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bus.Account;
import bus.FileHandler;
import bus.IAccount;
import bus.ICustomer;
import bus.Manager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FortisBankClientMenuGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FortisBankClientMenuGUI frame = new FortisBankClientMenuGUI();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FortisBankClientMenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(146, 11, 120, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setBounds(42, 54, 203, 24);
		contentPane.add(lblWelcome);
		
		//setting client
		lblWelcome.setText("Welcome "+FortisBankGUI.c.getName());
		
		JRadioButton radWithdraw = new JRadioButton("Withdraw");
		radWithdraw.setBounds(75, 231, 89, 23);
		contentPane.add(radWithdraw);
		
		JRadioButton radDeposit = new JRadioButton("Deposit");
		radDeposit.setBounds(168, 231, 98, 23);
		contentPane.add(radDeposit);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radWithdraw);
		group.add(radDeposit);

		radWithdraw.setSelected(true);
		
		
		JLabel lblNewLabel_1 = new JLabel("Select Type of transaction :");
		lblNewLabel_1.setBounds(42, 210, 155, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox cboAccount = new JComboBox();
		cboAccount.setBounds(125, 160, 120, 22);
		contentPane.add(cboAccount);
		
		for (Account acc : FortisBankGUI.c.getAccounts().getAllAccounts()) {
			cboAccount.addItem(acc.getAccountNumber()+" ("+acc.getAccType()+")");
		}
		
		JLabel lblNewLabel_1_1 = new JLabel("Select Account :");
		lblNewLabel_1_1.setBounds(42, 135, 155, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnShowInfo = new JButton("Show");
		btnShowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strAcc = cboAccount.getSelectedItem().toString();
				int accNum = Integer.parseInt(strAcc.substring(0, 4));
				
				JOptionPane.showMessageDialog(btnShowInfo, client.FortisBankGUI.c.getAccount(accNum));
			}
		});
		btnShowInfo.setBounds(294, 160, 89, 23);
		contentPane.add(btnShowInfo);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter Amount :");
		lblNewLabel_1_2.setBounds(42, 281, 155, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				String value = txtAmount.getText();
	            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == ke.VK_BACK_SPACE) {
	            	txtAmount.setEditable(true);
	            } else {
	            	txtAmount.setEditable(false);
	            	JOptionPane.showMessageDialog(null, "Enter only numeric digits(0-9)", "Information", JOptionPane.WARNING_MESSAGE);
	            	txtAmount.setEditable(true);
	            }
			}
		});
		txtAmount.setBounds(78, 311, 86, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("$");
		lblNewLabel_1_2_1.setBounds(171, 314, 41, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAmount.getText().length() < 1) {
					
					JOptionPane.showMessageDialog(btnConfirm, "Please, Enter the amount to proceed with transaction!");
					txtAmount.requestFocus();
					
				}else {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to Proceed? ", "CONFIRMATION", JOptionPane.YES_NO_OPTION);

				    if (JOptionPane.YES_OPTION == result) {
				    	String strAcc = cboAccount.getSelectedItem().toString();
						int accNum = Integer.parseInt(strAcc.substring(0, 4));
						
						int amount = Integer.parseInt(txtAmount.getText());
						
						
						IAccount acc = (IAccount)client.FortisBankGUI.c.getAccount(accNum);
				    	
						try {
							if(radDeposit.isSelected()) {
								acc.deposit(amount);
								
								//saving the transaction in our file and we don't have to call database request because its already saved
								FileHandler.writeManagerToFile((Manager)FortisBankGUI.manager);
								
								JOptionPane.showMessageDialog(btnConfirm, "Transaction approved!");
								txtAmount.setText("");
							}else if(radWithdraw.isSelected()) {
								
								acc.withdraw(amount);
								
								//saving the transaction in our file and we don't have to call database request because its already saved
								FileHandler.writeManagerToFile((Manager)FortisBankGUI.manager);

								JOptionPane.showMessageDialog(btnConfirm, "Transaction approved!");
								txtAmount.setText("");
							}else {
								JOptionPane.showMessageDialog(btnConfirm, "Please, Select the type of transaction you want to do?");
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(btnConfirm, e2.getMessage());
						}
						
				     } else if (JOptionPane.NO_OPTION == result) {
				                System.out.println("No");
				     }else{
				            System.out.println("Nothing");
				    }
				}
				
				
			}
		});
		btnConfirm.setBounds(294, 310, 89, 23);
		contentPane.add(btnConfirm);
	}
}
