package game;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Button;
import java.awt.Color;

/*
 * @author Jack Marshman
 */
public class Control extends JFrame 
{
	/**
	 * Initial JPanel
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try
				{
					Control frame = new Control();
					frame.setVisible(true);
				} 
				
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Control()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button btnBack = new Button("Back");
		btnBack.setBackground(Color.BLACK);
		btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(10, 201, 90, 50);
		
		//Doesn't work.
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Start();
				
			}
		});
		
		contentPane.add(btnBack);
		
		Button btnExit = new Button("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.BLACK);
		btnExit.addActionListener(e -> System.exit(0));
		
		btnExit.setBounds(334, 201, 90, 50);
		contentPane.add(btnExit);
		
	
		//Partial solution to re-scaling problem
		int controlImageWidth = 350;
		int controlImageHeight = 120;
		ImageIcon controlIcon = new ImageIcon("C:\\Users\\Owner\\Desktop\\controls4.png"); // load the image to a imageIcon
		Image controlImage = controlIcon.getImage(); // transform it 
		Image rescaleControlImage = controlImage.getScaledInstance(controlImageWidth, controlImageHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		controlIcon = new ImageIcon(rescaleControlImage);  // transform it back
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(controlIcon);
		lblNewLabel.setBounds(44, 26, 363, 210);
		contentPane.add(lblNewLabel);
	}
}
