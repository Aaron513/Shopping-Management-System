import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

public class Products extends JFrame {

	private JPanel contentPane;
	private JTextField txtprod;
	private JTextField txtname;
	private JTextField txtprice;
	private JTextField txtquant;
	private JTextField txtdesp;
	private JTable disptable;
	DefaultTableModel model;
	
	
	/**
	 * Launch the application.
	 */
	Connection con =null;
	Statement st = null;
	ResultSet rs = null;
	
	public void SelectItem()

	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
			st = con.createStatement();
			rs = st.executeQuery("select * from products.products");
			disptable.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Products frame = new Products();
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
	public Products() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(149, 28, 835, 533);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Products");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(323, 0, 232, 58);
		panel.add(lblNewLabel);
		
		txtprod = new JTextField();
		txtprod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprod.setBounds(32, 87, 169, 25);
		panel.add(txtprod);
		txtprod.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Product Id");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(32, 62, 72, 14);
		panel.add(lblNewLabel_3);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtname.setBounds(32, 148, 169, 25);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(32, 123, 72, 14);
		panel.add(lblNewLabel_4);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtprice.setBounds(262, 87, 169, 25);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Price");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(262, 63, 72, 14);
		panel.add(lblNewLabel_5);
		
		txtquant = new JTextField();
		txtquant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtquant.setBounds(262, 148, 169, 25);
		panel.add(txtquant);
		txtquant.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Quantity\r\n");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(262, 124, 72, 14);
		panel.add(lblNewLabel_6);
		
		txtdesp = new JTextField();
		txtdesp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtdesp.setBounds(510, 87, 300, 25);
		panel.add(txtdesp);
		txtdesp.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Description");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(510, 63, 92, 14);
		panel.add(lblNewLabel_7);
		
		Button addbtn = new Button("ADD");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtprod.getText().isEmpty() || txtname.getText().isEmpty() || txtdesp.getText().isEmpty() || txtquant.getText().isEmpty() || txtprice.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(addbtn,"Missing Info");
				}else
				{
					try {
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
					    PreparedStatement add = con.prepareStatement("INSERT INTO products VALUES(?,?,?,?,?)");
					    add.setInt(1, Integer.valueOf(txtprod.getText()));
					    add.setString(2, txtname.getText());
					    add.setInt(3, Integer.valueOf(txtquant.getText()));
					    add.setInt(4, Integer.valueOf(txtprice.getText()));
					    add.setString(5, txtdesp.getText());
					    int row = add.executeUpdate();
					    SelectItem();
				        JOptionPane.showMessageDialog(addbtn, "Item Added Successfully!");   
				        con.close();				       
					    txtprod.setText("");
						txtname.setText("");
						txtquant.setText("");
						txtprice.setText("");
						txtdesp.setText("");
					}catch (Exception ae) {
						ae.printStackTrace();
					}
				}
			}
		});
		addbtn.setForeground(Color.WHITE);
		addbtn.setBackground(new Color(106, 90, 205));
		addbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		addbtn.setBounds(103, 209, 84, 33);
		panel.add(addbtn);
		
		Button editbtn = new Button("EDIT");
		editbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		editbtn.setForeground(Color.WHITE);
		editbtn.setBackground(new Color(106, 90, 205));
		editbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtprod.getText().isEmpty() || txtname.getText().isEmpty() || txtdesp.getText().isEmpty() || txtquant.getText().isEmpty() || txtprice.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(editbtn,  "Missing Info");
				}else
				{
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
					    String Query = "UPDATE products.products set Name='"+txtname.getText()+"'"+",Quantity='"+txtquant.getText()+"'"+",Price='"+txtprice.getText()+"'"+",Description='"+txtdesp.getText()+"'"+"where Productid="+txtprod.getText();                                
						Statement Add = con.createStatement();
						Add.executeUpdate(Query);
					    JOptionPane.showMessageDialog(editbtn, "Item Editted Successfully!");
					    txtprod.setText("");
						txtname.setText("");
						txtquant.setText("");
						txtprice.setText("");
						txtdesp.setText("");
						SelectItem(); 
					}catch (SQLException ae) {
						ae.printStackTrace();
					}
				}
			}
		});
		editbtn.setBounds(250, 209, 84, 33);
		panel.add(editbtn);
		
		Button clearbtn = new Button("CLEAR");
		clearbtn.setBackground(new Color(106, 90, 205));
		clearbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		clearbtn.setForeground(Color.WHITE);
		clearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtprod.setText("");
				txtname.setText("");
				txtquant.setText("");
				txtprice.setText("");
				txtdesp.setText("");
			}
		});
		clearbtn.setBounds(406, 209, 84, 33);
		panel.add(clearbtn);
		
		Button delbtn = new Button("DELETE");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtprod.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(delbtn, "Missing Info");
				}else
				{
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
					    String pid = txtprod.getText();
					    String Query = "DELETE FROM products.products where Productid="+pid;
					    Statement Add = con.createStatement();
					    Add.executeUpdate(Query);
					    JOptionPane.showMessageDialog(delbtn, "Deleted Successfully");
					    txtprod.setText("");
						txtname.setText("");
						txtquant.setText("");
						txtprice.setText("");
						txtdesp.setText("");
					    SelectItem();
					}catch (Exception ae) {
						ae.printStackTrace();
					}
				}
			}
		});
		delbtn.setForeground(Color.WHITE);
		delbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		delbtn.setBackground(new Color(106, 90, 205));
		delbtn.setBounds(555, 209, 84, 33);
		panel.add(delbtn);
		
		JLabel lblNewLabel_9 = new JLabel("Product List");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_9.setBounds(318, 272, 127, 33);
		panel.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 305, 778, 184);
		panel.add(scrollPane);
		
		
		disptable = new JTable();
		disptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)disptable.getModel();
				int Myindex = disptable.getSelectedRow();
				txtprod.setText(model.getValueAt(Myindex, 0).toString());
				txtname.setText(model.getValueAt(Myindex, 1).toString());
				txtquant.setText(model.getValueAt(Myindex, 2).toString());
				txtprice.setText(model.getValueAt(Myindex, 3).toString());
				txtdesp.setText(model.getValueAt(Myindex, 4).toString());
			}
			});
		model =new DefaultTableModel();
		Object[] column = {"Product ID","Name","Quantity","Price","Description"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		disptable.setModel(model);
		SelectItem();
		scrollPane.setViewportView(disptable);
		disptable.setBorder(new LineBorder(new Color(0, 0, 0)));
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Logout");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Role().setVisible(true);
		    	this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(36, 493, 84, 44);
		contentPane.add(lblNewLabel_1);
	}
}