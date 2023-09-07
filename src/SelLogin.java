import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SelLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JPasswordField txtpass1;

	/**
	 * Launch the application.
	 */
	
	Connection con =null;
	Statement st = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelLogin frame = new SelLogin();
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
	public SelLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seller");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(318, 114, 98, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seller username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(318, 193, 98, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(318, 291, 73, 14);
		contentPane.add(lblNewLabel_2);
		
		txtname = new JTextField();
		txtname.setBounds(318, 218, 335, 40);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblNewLabel_3.setBounds(318, 22, 117, 42);
		contentPane.add(lblNewLabel_3);
		
		txtpass1 = new JPasswordField();
		txtpass1.setBounds(318, 316, 335, 40);
		contentPane.add(txtpass1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 294, 473);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Button loginbtn = new Button("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Query = ("select * from products.login");
				if (txtname.getText().isEmpty() || txtpass1.getPassword().equals("")){
					JOptionPane.showMessageDialog(loginbtn, "Missing Info");
				} 
				else {
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
						st = con.createStatement();
						rs = st.executeQuery(Query);
					    if (rs.next()) {
					    	new bill().setVisible(true);
					    	this.dispose();
					    }else {
							JOptionPane.showMessageDialog(loginbtn, "Invalid Username or password!");	
					    }					
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
				}		
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		loginbtn.setBackground(new Color(0, 0, 0));
		loginbtn.setBounds(356, 398, 300, 40);
		contentPane.add(loginbtn);
	}
}
