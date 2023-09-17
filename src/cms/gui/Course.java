package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import cms.dbtask.dbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.*;
import javax.swing.ImageIcon;
public class Course extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtfees;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
		setTitle("Course");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 360);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(26, 61, 142, 19);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtname.setBounds(206, 59, 186, 25);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course Fees");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(26, 118, 142, 25);
		contentPane.add(lblNewLabel_1);
		
		txtfees = new JTextField();
		txtfees.setBounds(206, 122, 186, 25);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Course Duration");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(26, 176, 163, 25);
		contentPane.add(lblNewLabel_2);
		
		txtduration = new JTextField();
		txtduration.setBounds(206, 179, 186, 26);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(194, 252, 114, 42);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cname=txtname.getText().trim();
		String cfees=txtfees.getText().trim();
		int fees=Integer.parseInt(cfees);
		String cduration=txtduration.getText().trim();
		
		if(cname.isEmpty()||cfees.isEmpty()||cduration.isEmpty())
		{
			JOptionPane.showConfirmDialog(this,"Please Enter the Details");
		}
		else
		{
			Connection con=dbConnection.openConnection();
			PreparedStatement ps=null;
			
			try
			{
				String insert_query="insert into course_details(Course_name,Course_duration,Course_fees)values(?,?,?)";
				ps=con.prepareStatement(insert_query);
				ps.setString(1, cname);
				ps.setString(2, cduration);
				ps.setInt(3, fees);
				
				int status = ps.executeUpdate();
				if(status>0)
				{
					JOptionPane.showMessageDialog(this, "Course Details Added Successfully");
					
					txtname.setText("");
					txtfees.setText("");
					txtduration.setText("");
				}
			}
			catch(SQLException se)
			{
				JOptionPane.showMessageDialog(this, cname +" this course is already exist");
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
				catch (SQLException se) 
				{
					se.printStackTrace();
					
				}
			}
		}
		
		
	}
}
