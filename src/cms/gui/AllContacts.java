package cms.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import cms.dbtask.dbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JButton;
public class AllContacts extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllContacts frame = new AllContacts();
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
	
	
	public void filledRecord() 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try
		{
			String sql="select * from contact_details";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			
			TableModel t=DbUtils.resultSetToTableModel(rs);
				table.setModel(t);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public AllContacts()
	{
		con=dbConnection.openConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 27, 613, 345);
		contentPane.add(scrollPane);
		
		JButton btncontacts = new JButton("Show Contacts");
		btncontacts.addActionListener(this);
		btncontacts.setBackground(new Color(255, 128, 128));
		btncontacts.setFont(new Font("Tahoma", Font.BOLD, 15));
		btncontacts.setBounds(271, 405, 154, 34);
		contentPane.add(btncontacts);
		
		
		
		
		
		table = new JTable();
		
			JTableHeader header =table.getTableHeader();
			header.setForeground(new Color(120,100,130));
			header.setFont(new Font("Lucida Consol",Font.BOLD,18));
			header.setBackground(Color.WHITE);
				
//			filledRecord();
//			scrollPane.setViewportView(table);	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		filledRecord();
		scrollPane.setViewportView(table);
		
	}
}
