package cms.gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.dbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.sql.*;
public class Contact extends JFrame implements ActionListener, KeyListener 
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone1;
	private JTextField txtphone2;
	private JTextArea txtaddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { //thread will execute run method
				try {
					Contact frame = new Contact();
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
	public Contact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contact.class.getResource("/cms/images/phone.png")));
		setTitle("Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);//to place a window in center
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("Name");
		lblname.setFont(new Font("Calibri", Font.BOLD, 25));
		lblname.setBounds(32, 61, 81, 33);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		
		txtname.addKeyListener(this);
		
		txtname.setFont(new Font("Calibri", Font.BOLD, 18));
		txtname.setBounds(174, 61, 280, 33);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Calibri", Font.BOLD, 25));
		lblemail.setBounds(32, 111, 81, 33);
		contentPane.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setBounds(174, 111, 280, 33);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblphone1 = new JLabel("Phone1");
		lblphone1.setFont(new Font("Calibri", Font.BOLD, 25));
		lblphone1.setBounds(32, 157, 81, 33);
		contentPane.add(lblphone1);
		
		txtphone1 = new JTextField();
		
		txtphone1.addKeyListener(this);
		
		txtphone1.setFont(new Font("Calibri", Font.BOLD, 18));
		txtphone1.setBounds(174, 158, 280, 33);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		JLabel lblphone2 = new JLabel("Phone2");
		lblphone2.setFont(new Font("Calibri", Font.BOLD, 25));
		lblphone2.setBounds(32, 216, 81, 33);
		contentPane.add(lblphone2);
		
		txtphone2 = new JTextField();
		
		txtphone2.addKeyListener(this);
		
		txtphone2.setFont(new Font("Calibri", Font.BOLD, 18));
		txtphone2.setBounds(174, 217, 280, 33);
		contentPane.add(txtphone2);
		txtphone2.setColumns(10);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setFont(new Font("Calibri", Font.BOLD, 25));
		lbladdress.setBounds(32, 284, 90, 33);
		contentPane.add(lbladdress);
		
		txtaddress = new JTextArea();
		txtaddress.addKeyListener(this);
		txtaddress.setBounds(174, 260, 280, 66);
		contentPane.add(txtaddress);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/name.png")));
		lblNewLabel.setBounds(108, 49, 55, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/email.png")));
		lblNewLabel_1.setBounds(108, 105, 55, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/phone.png")));
		lblNewLabel_2.setBounds(108, 155, 55, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/phone.png")));
		lblNewLabel_3.setBounds(108, 216, 55, 39);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Contact.class.getResource("/cms/images/address.png")));
		lblNewLabel_4.setBounds(117, 274, 46, 52);
		contentPane.add(lblNewLabel_4);
		
		JButton btnsubmit = new JButton("SUBMIT");
		
		btnsubmit.setFont(new Font("Calibri", Font.BOLD, 30));
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setBounds(210, 355, 146, 62);
		contentPane.add(btnsubmit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		addData();
		
	}
	void addData()
	{
		String name=txtname.getText().trim();
		String email=txtemail.getText().trim();
		String phone1=txtphone1.getText().trim();
		String phone2=txtphone2.getText().trim();
		String address=txtaddress.getText().trim();
		
		if(name.isEmpty()||email.isEmpty()||phone1.isEmpty()||address.isEmpty())
		{
			JOptionPane.showConfirmDialog(this, "Please Provide Data");
		}
		else
		{
			Connection con=dbConnection.openConnection();
			PreparedStatement ps=null;
			
			
			try
			{
				String insert_query="insert into contact_details(name,email,phone1,phone2,address,date)values(?,?,?,?,?,?)";
				ps=con.prepareStatement(insert_query);//passes the query to DBMS and DBMS compiler 
				//compiles the query and store into a buffer->refered by ps
				java.util.Date d=new java.util.Date();//current date
				//conversion of util date in sql date
				java.sql.Date sd=new java.sql.Date(d.getTime());// converion of util date into sql date
				ps.setString(1, name);//values are fetch from control are getting set for the particular column
				ps.setString(2, email);
				ps.setString(3, phone1);
				ps.setString(4, phone2);
				ps.setString(5, address);
				ps.setDate(6, sd);
				//System.out.println(ps);
				
				int status= ps.executeUpdate();//fire insert/update/delete query
				if(status>0)
				{
			
					JOptionPane.showMessageDialog(this, "Contact Added Successfully");
					
					txtaddress.setText("");
					txtemail.setText("");
					txtname.setText("");
					txtphone1.setText("");
					txtphone2.setText("");
				}
				
			}
				
			catch(SQLException se)
			{
				JOptionPane.showMessageDialog(this,phone1+" phone number already exist");
				se.printStackTrace();
			}
			finally
			{
				try
				{
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		char c=e.getKeyChar();
		
		
		//System.out.println("Typed character is "+c);
		
		if(e.getSource()==txtname)//return the object that is generating the event
		{
			if(!(Character.isAlphabetic(c)||c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE))
			{
				e.consume();//it will discard the input other than alphabets
				JOptionPane.showMessageDialog(this, "Only alphabets are allowed");
				
			}
		}
		if(e.getSource()==txtphone1||e.getSource()==txtphone2)
		{
			if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ))
			{
				e.consume();//it will discard the input other than digits
				
				JOptionPane.showMessageDialog(this, "Only Digits are allowed");
				
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode()==10)//enter key code
		{
			addData();
		}
		
		if(e.getSource()==txtaddress) 
		{
			if(e.getKeyCode() == KeyEvent.VK_TAB)
			{
				e.consume();
				KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
		
	}
}
