package cms.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import java.awt.*;
public class Login extends JFrame implements ActionListener, KeyListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/cms/images/srmcem.jpg")));
		setTitle("LoginFrame\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 10, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel.setBounds(32, 55, 100, 31);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.addKeyListener(this);
		txtid.setFont(new Font("Calibri", Font.PLAIN, 25));
		txtid.setForeground(new Color(255, 0, 0));
		txtid.setBackground(new Color(255, 255, 128));
		txtid.setBounds(160, 55, 264, 31);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_1.setBounds(32, 115, 116, 31);
		contentPane.add(lblNewLabel_1);
		
		txtpassword = new JPasswordField();
		txtid.addActionListener(this);
		txtpassword.setForeground(new Color(255, 0, 0));
		txtpassword.setFont(new Font("Calibri", Font.PLAIN, 25));
		txtpassword.setBackground(new Color(255, 255, 128));
		txtpassword.setBounds(160, 115, 264, 31);
		contentPane.add(txtpassword);
		
		JButton btnsubmit = new JButton("Submit");
			btnsubmit.addActionListener(this); //Run time polymorphism with interface
				//register the listener with button
		//btnsubmit.setIcon(new ImageIcon(Login.class.getResource("/cms/images/srmcem.jpg")));
		btnsubmit.addActionListener(this);
		btnsubmit.setBackground(new Color(255, 255, 128));
		btnsubmit.setFont(new Font("Calibri", Font.BOLD, 25));
		btnsubmit.setBounds(190, 195, 135, 39);
		contentPane.add(btnsubmit);
		
		JLabel lblNewLabel_2 = new JLabel("");
		//lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/cms/images/userid.png")));
		lblNewLabel_2.setBounds(467, 29, 166, 312);
		contentPane.add(lblNewLabel_2);
	}
	void addData()
	{
		//System.out.println("Button is being clicked");
				String id=txtid.getText().trim();//getText method is used to fetch the value,,, trime() removes leading and trailing space 
				
				char[]pass=txtpassword.getPassword();
				String password = String.valueOf(pass).trim();
				//System.out.println(id +" "+password);
				
				if(id.length()==0 || password.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Please provide ID and Password");
				}
				else
				{
					if(id.equalsIgnoreCase("srmcem") && password.equals("srmcem"))
					{
						UserDashBoard user=new UserDashBoard();
						user.setVisible(true);
						
						this.dispose();//to dispose(close) the frame
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Invalid Credentials", "Login Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		addData();
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10)//enter key code
		{
			addData();
		}
		
		if(e.getSource()==txtpassword) 
		{
			if(e.getKeyCode() == KeyEvent.VK_TAB)
			{
				e.consume();
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
