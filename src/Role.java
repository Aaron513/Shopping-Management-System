import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Role extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Role frame = new Role();
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
	public Role() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 30, 424, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Button mangbtn = new Button("Manager");
		mangbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 18));
		mangbtn.setForeground(new Color(255, 255, 255));
		mangbtn.setBackground(new Color(112, 128, 144));
		mangbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		mangbtn.setBounds(88, 91, 231, 40);
		panel.add(mangbtn);
		
		Button selbtn = new Button("Seller");
		selbtn.setFont(new Font("Berlin Sans FB", Font.BOLD, 18));
		selbtn.setForeground(new Color(255, 255, 255));
		selbtn.setBackground(new Color(112, 128, 144));
		selbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelLogin().setVisible(true);
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		selbtn.setBounds(88, 149, 231, 40);
		panel.add(selbtn);
		
		JLabel lblNewLabel = new JLabel("Select Your Role");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(110, 11, 190, 49);
		panel.add(lblNewLabel);
	}
}
