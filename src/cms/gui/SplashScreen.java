package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class SplashScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	public void loadFrame()
	{
		//we wish to create a thread using anonymous local inner class
		Thread t = new Thread(new Runnable() //anonymous class object
		{
				
			
			@Override
			public void run()
			{
				//anonymous class body
				try
				{
					Thread.sleep(4000);
					Login login=new Login();
					login.setVisible(true);
					SplashScreen.this.dispose();//outer class object
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			
		}
		);  //Thread class constructor close
		t.start();
		
	}
	
	
	
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CMS WELCOMES YOU");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(71, 46, 547, 61);
		contentPane.add(lblNewLabel);
		loadFrame();
	}
	
}
