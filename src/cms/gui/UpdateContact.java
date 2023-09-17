package cms.gui;

import java.awt.EventQueue;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;


import javax.swing.border.LineBorder;

import cms.dbtask.dbConnection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.sql.*;
public class UpdateContact extends JFrame implements ItemListener, ActionListener, KeyListener
{

	private JPanel contentPane;
	private JTextField txtphone1;
	private JTextField txtphone2;
	private JTextField txtemail;
	private JComboBox<String>cmbname;
	private JTextArea txtaddress;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateContact frame = new UpdateContact();
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
	
	public void fillCombo()//instance method
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			String select_query="select name from contact_details";// select name mean only read column data
			ps=con.prepareStatement(select_query);
			rs=ps.executeQuery();//it is used to execute query 
			while(rs.next()==true)
			{
				String cname=rs.getString("name");//fetch the value from the specified column
				//System.out.println(cname);
				
				
				cmbname.addItem(cname);//adding item in a combo box
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
	
	Connection con;
	public UpdateContact()
	{
		con=dbConnection.openConnection();
		
		
		
		
		setTitle("UpdateContact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 582);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new LineBorder(SystemColor.info, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbname = new JComboBox();
		cmbname.addItemListener(this);
		
		cmbname.setModel(new DefaultComboBoxModel(new String[] {"Select Name"}));
		cmbname.setBounds(327, 52, 150, 37);
		fillCombo();//calling method to show items in combo box
		contentPane.add(cmbname);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(72, 71, 94, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Phone1");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(72, 140, 83, 31);
		contentPane.add(lblNewLabel_1);
		
		txtphone1 = new JTextField();
		txtphone1.addKeyListener(this);
		txtphone1.setBounds(327, 140, 150, 31);
		contentPane.add(txtphone1);
		txtphone1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone2\r\n");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(72, 227, 80, 21);
		contentPane.add(lblNewLabel_2);
		
		txtphone2 = new JTextField();
		txtphone2.addKeyListener(this);
		txtphone2.setBounds(329, 217, 150, 31);
		contentPane.add(txtphone2);
		txtphone2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_3.setBounds(72, 305, 83, 31);
		contentPane.add(lblNewLabel_3);
		
		txtemail = new JTextField();
		txtemail.setBounds(327, 305, 253, 31);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address\r\n");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_4.setBounds(72, 403, 94, 31);
		contentPane.add(lblNewLabel_4);
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.addActionListener(this);
		btnupdate.addKeyListener(this);
		btnupdate.setBackground(new Color(153, 255, 153));
		btnupdate.setForeground(new Color(0, 0, 0));
		btnupdate.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnupdate.setBounds(641, 453, 150, 43);
		contentPane.add(btnupdate);
		
		txtaddress = new JTextArea();
		txtaddress.addKeyListener(this);
		txtaddress.setBounds(327, 387, 253, 47);
		contentPane.add(txtaddress);
	}
	
	void addData()
	{
		String name=(String)cmbname.getSelectedItem();
		String email=txtemail.getText();
		String phone1=txtphone1.getText();
		String phone2=txtphone2.getText();
		String address=txtaddress.getText();
		
		if(email.isEmpty()||phone1.isEmpty()||address.isEmpty()||name.equalsIgnoreCase("Select Name"))
		{
			JOptionPane.showMessageDialog(this, "PLease provide meaningfull data for update");
		}
		else
		{
			PreparedStatement ps=null;
			try
			{
				String update_query="update contact_details set email=?,phone1=?,phone2=?,address=? where name=?";
				ps=con.prepareStatement(update_query);
				ps.setString(1, email);
				ps.setString(2, phone1);
				ps.setString(3, phone2);
				ps.setString(4, address);
				ps.setString(5, name);
				
				
				int status=ps.executeUpdate();
				
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, "Contact for "+name+" Updated successfully");
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
	public void itemStateChanged(ItemEvent e) 
	{
		System.out.println("Item Selected");
		int state=e.getStateChange();
		//System.out.println(state);
		if(state==1)
		{
			String cname=(String)cmbname.getSelectedItem();//to fetch the data from JcomboBox
			//System.out.println("name is "+name);
			
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try
			{
				String sql="select * from contact_details where name=?";//query
				ps=con.prepareStatement(sql);
				ps.setString(1, cname);
				rs=ps.executeQuery();
				rs.next();//put cursor on resultant line
				
				//to read the value from a column
				String em=rs.getString("email");
				String ph1=rs.getString("phone1");
				String ph2=rs.getString("phone2");
				String add=rs.getString("address");
				
				txtaddress.setText(add);
				txtemail.setText(em);
				txtphone1.setText(ph1);
				txtphone2.setText(ph2);
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
	public void keyTyped(KeyEvent e) 
	{	
		char c=e.getKeyChar();
		
		
		//System.out.println("Typed character is "+c);
		
		
		if(e.getSource()==txtphone1||e.getSource()==txtphone2)
		{
			if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE ))
			{
				e.consume();//it will discard the input other than alphabets
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
