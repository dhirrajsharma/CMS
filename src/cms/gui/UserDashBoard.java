package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
public class UserDashBoard extends JFrame implements WindowListener, ActionListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashBoard frame = new UserDashBoard();
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
	public UserDashBoard()
	{
		
		
		this.addWindowListener(this);//first this is source and second for the class which implements listeners
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserDashBoard.class.getResource("/cms/images/name.png")));
		setTitle("UserDashBoard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnContacts = new JMenu("Contacts");
		mnContacts.setIcon(new ImageIcon(UserDashBoard.class.getResource("/cms/images/name.png")));
		menuBar.add(mnContacts);
		
		JMenuItem mi_add = new JMenuItem("Add");
		mi_add.addActionListener(this);
		mnContacts.add(mi_add);
		
		JMenuItem mi_update = new JMenuItem("Update");
		mi_update.addActionListener(this);
		mnContacts.add(mi_update);
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		mi_delete.addActionListener(this);
		mnContacts.add(mi_delete);
		
		JMenuItem mi_search = new JMenuItem("Search");
		mi_search.addActionListener(this);
		mnContacts.add(mi_search);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		JOptionPane.showMessageDialog(this,"Thank you for using this");
		Login login=new Login();
		login.setVisible(true);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("Clicked");
		String caption=e.getActionCommand();//it returns the text written on a button or menuItem
		//System.out.println(caption);
		
		
		//binding with menu
		if(caption.equalsIgnoreCase("Add")) 
		{
			Contact c=new Contact();
			c.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Update")) 
		{
			UpdateContact uc=new UpdateContact();
			uc.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Delete")) 
		{
			DeleteContact dc=new DeleteContact();
			dc.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Search")) 
		{
			SearchContact sc=new SearchContact();
			sc.setVisible(true);
		}
			
		
		
	}
}
