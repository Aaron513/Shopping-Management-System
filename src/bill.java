import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


public class bill extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtquant; 
	private JTextArea textArea;
	DefaultTableModel model;
	private double Uprice, Prodtot , Grdtot;
	int i = 0;
	int AvailQty;
	/**
	 * Launch the application.
	 */
	
	Connection con =null;
	Statement st = null;
	ResultSet rs = null;
	private JTable disptable;
	public void SelectItem()
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM products.products");
			disptable.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	int prid, newqty;
	public void update() {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "Aaron05@2003$");
		    String Query = "UPDATE products.products set Quantity='"+newqty+"'"+"where Productid="+prid;                                
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);
			SelectItem(); 
		}catch (SQLException ae) {
			ae.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					bill frame = new bill();
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
	public bill() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1183, 690);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(136, 29, 1023, 624);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Billing Point");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(406, 11, 166, 48);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(664, 57, 161, 28);
		panel.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(66, 124, 169, 31);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtquant = new JTextField();
		txtquant.setBounds(66, 201, 169, 31);
		panel.add(txtquant);
		txtquant.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(66, 102, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(66, 176, 71, 14);
		panel.add(lblNewLabel_3);
		
		Button addbtn = new Button("ADD");
		addbtn.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				
				 if(txtquant.getText().isEmpty()||txtname.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(addbtn, "Missing Info!"); 
				 }
				 else if(AvailQty <= Integer.valueOf(txtquant.getText())){
					 JOptionPane.showMessageDialog(addbtn, "Not enough in stock");
				 }
				 else {
					 String txt = textArea.getText();
					 Prodtot = Uprice * Integer.valueOf(txtquant.getText());
				     newqty = AvailQty - Integer.valueOf(txtquant.getText());
					 Grdtot = Grdtot + Prodtot;
					 i++;
					 if(i == 1) {
							textArea.setText(txt+"===================COMPUTER SHOP===================\n"+" NUM      PRODUCT           PRICE           QUANTITY      TOTAL\n"+i+"                "+txtname.getText()+"               "+Uprice+"                "+txtquant.getText()+"                    "+Prodtot+"\n");							 
						 }					 
						 else { 
						   textArea.setText(txt+i+"                "+txtname.getText()+"               "+Uprice+"                "+txtquant.getText()+"                    "+Prodtot+"\n");							 							                                                                                                 												
						 }
					 JLabel grttot = new JLabel("Total: "+Grdtot);
					 /*grttot.setBounds(851, 321, 147, 28);
					 panel.add(grttot);*/
					update();
					txtname.setText("");
					txtquant.setText("");
				 }									
			}
		});
		addbtn.setBackground(new Color(123, 104, 238));
		addbtn.setForeground(new Color(255, 255, 255));
		addbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		addbtn.setActionCommand("");
		addbtn.setBounds(66, 295, 84, 33);
		panel.add(addbtn);
		
		Button clearbtn = new Button("CLEAR");
		clearbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtname.setText("");
				txtquant.setText("");
			}
		});
		clearbtn.setBackground(new Color(123, 104, 238));
		clearbtn.setForeground(new Color(255, 255, 255));
		clearbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		clearbtn.setBounds(227, 295, 99, 31);
		panel.add(clearbtn);
		
		Button printbtn = new Button("PRINT");
		printbtn.setForeground(new Color(255, 255, 255));
		printbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		printbtn.setBackground(new Color(123, 104, 238));
		printbtn.setBounds(914, 463, 84, 33);
		panel.add(printbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(504, 95, 494, 129);
		panel.add(scrollPane);


		disptable = new JTable();
		disptable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)disptable.getModel();
				int Myindex = disptable.getSelectedRow();
				prid = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				txtname.setText(model.getValueAt(Myindex, 1).toString());
				AvailQty = Integer.valueOf(model.getValueAt(Myindex, 2).toString()); 
				Uprice = Double.valueOf(model.getValueAt(Myindex, 3).toString());
			}
			});
		model =new DefaultTableModel();
		Object[] column = {"Product ID","Name","Quantity","Price","Description"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		disptable.setModel(model);
		SelectItem();
		scrollPane.setViewportView(disptable);
	
		Button refreshbtn = new Button("REFRESH");
		refreshbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtname.setText("");
				txtquant.setText("");
				textArea.setText("");
			}
		});
		refreshbtn.setForeground(Color.WHITE);
		refreshbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 15));
		refreshbtn.setBackground(new Color(123, 104, 238));
		refreshbtn.setBounds(128, 402, 115, 31);
		panel.add(refreshbtn);
			
		textArea = new JTextArea();
		textArea.setBounds(406, 234, 419, 368);
		panel.add(textArea);
		
		JLabel loglbl = new JLabel("Logout");
		loglbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Role().setVisible(true);
		    	this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		loglbl.setForeground(Color.WHITE);
		loglbl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		loglbl.setBounds(34, 526, 109, 50);
		contentPane.add(loglbl);
	}
}
