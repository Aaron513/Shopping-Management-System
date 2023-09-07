import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.util.StringUtils;

import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

	protected static final String SELECT = null;
	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField txtpass;
    
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
					Login frame = new Login();
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
	public Login() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button loginbtn = new Button("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String Query = ("select * from products.adminlogin");
					if (txtuser.getText().isEmpty() || txtpass.getPassword().equals("")){
						JOptionPane.showMessageDialog(loginbtn, "Missing Info");
					} 
					else {
						try {
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
							st = con.createStatement();
							rs = st.executeQuery(Query);
						    if (rs.next()) {
						    	new Products().setVisible(true);
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
			}
		});
		loginbtn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setBackground(new Color(0, 0, 139));
		loginbtn.setBounds(413, 435, 300, 40);
		contentPane.add(loginbtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 364, 511);
		contentPane.add(panel);
		
		txtuser = new JTextField();
		txtuser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtuser.setBounds(413, 220, 335, 40);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Admin username");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(413, 195, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(413, 280, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		txtpass = new JPasswordField();
		txtpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpass.setBounds(413, 305, 335, 40);
		contentPane.add(txtpass);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_3.setBounds(413, 11, 110, 45);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Manager");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(413, 123, 126, 40);
		contentPane.add(lblNewLabel);
	}
}
