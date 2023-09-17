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
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.*;

public class SearchContact extends JFrame implements ActionListener, KeyListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone1;
	private JTextField txtemail;
	private	JTextArea txtaddress;
	private JTextField txtphone2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchContact frame = new SearchContact();
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
	public SearchContact() {
		setTitle("Search Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(77, 51, 94, 25);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.addKeyListener(null);
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtname.setBounds(201, 51, 220, 25);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("OR");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(257, 87, 65, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone No.1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(47, 130, 124, 25);
		contentPane.add(lblNewLabel_2);
		
		txtphone1 = new JTextField();
		txtphone1.addKeyListener(this);
		txtphone1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtphone1.setBounds(201, 129, 220, 27);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.addActionListener(this);
		btnsearch.addKeyListener(this);
		btnsearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnsearch.setBounds(235, 174, 135, 30);
		contentPane.add(btnsearch);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(66, 277, 94, 25);
		contentPane.add(lblNewLabel_3);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtemail.setText("");
		txtemail.setBounds(201, 277, 220, 25);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		 txtaddress = new JTextArea();
		 txtaddress.addKeyListener(this);
		txtaddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtaddress.setBounds(201, 320, 220, 60);
		contentPane.add(txtaddress);
		
		JLabel lblNewLabel_4 = new JLabel("Address\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(66, 338, 94, 25);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone No.2");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(48, 227, 123, 25);
		contentPane.add(lblNewLabel_5);
		
		txtphone2 = new JTextField();
		txtphone2.addKeyListener(this);
		txtphone2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtphone2.setBounds(201, 227, 220, 26);
		contentPane.add(txtphone2);
		txtphone2.setColumns(10);
	}
	
	
	
	void addData()
	{
		String name=txtname.getText().trim();
		String phone=txtphone1.getText();
		if(name.isEmpty() && phone.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please enter phone or name for searching");
		}
		else
		{
			Connection con=dbConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			try
			{
				String sql="select * from contact_details where name=? or phone1=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, phone);
				rs=ps.executeQuery();
				if(rs.next())
				{
					String cname=rs.getString("name");
					String ph1=rs.getString("phone1");
					String ph2=rs.getString("phone2");
					String em=rs.getString("email");
					String add=rs.getString("address");
					
					txtaddress.setText(add);
					txtemail.setText(em);
					txtname.setText(cname);
					txtphone1.setText(ph1);
					txtphone2.setText(ph2);
					
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No such contact found");
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			finally
			{
				try
				{
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
						
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
