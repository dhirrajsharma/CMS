package cms.gui;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.dbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import java.awt.*;
public class DeleteContact extends JFrame implements ActionListener, KeyListener
{

	private JPanel contentPane;
	private JTextField textnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteContact frame = new DeleteContact();
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
	public DeleteContact() {
		setTitle("DelectContact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblphoneno = new JLabel("Phone No");
		lblphoneno.setFont(new Font("Calibri", Font.BOLD, 18));
		lblphoneno.setForeground(new Color(0, 0, 0));
		lblphoneno.setBackground(new Color(255, 255, 255));
		lblphoneno.setBounds(35, 50, 81, 40);
		contentPane.add(lblphoneno);
		
		textnumber = new JTextField();
		textnumber.addKeyListener(this);
		textnumber.setFont(new Font("Calibri", Font.PLAIN, 18));
		textnumber.setBounds(126, 51, 161, 30);
		contentPane.add(textnumber);
		textnumber.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.addKeyListener(this);
		
		btndelete.setFont(new Font("Calibri", Font.BOLD, 18));
		btndelete.setBounds(302, 50, 100, 34);
		contentPane.add(btndelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		addData();		
		
	}
	
	void addData()
	{
String phone=textnumber.getText().trim();
		
		if(phone.length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please enter trhe number","Please LOGIN",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int choice=JOptionPane.showConfirmDialog(this, "Are you sure to delete this "+phone+ " number");
			//System.out.println(choice);
			if(choice==0)
			{
				Connection con=dbConnection.openConnection();
				PreparedStatement ps=null;
				
				
				try
				{
					String delete_query="delete from contact_details where phone1=?";
					ps=con.prepareStatement(delete_query);
					ps.setString(1, phone);
					//System.out.println(ps);
					int status=ps.executeUpdate();
					if(status>0)
						JOptionPane.showMessageDialog(this,phone+ " number deleted successfully");
					else
						JOptionPane.showMessageDialog(this,"No such "+phone+" Number exist");
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

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		char c=e.getKeyChar();
		if(e.getSource()==textnumber)
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
		
		if(e.getSource()==textnumber) 
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
