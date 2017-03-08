package game;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * @author Jack Marshman
 */
public class Start extends JFrame
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
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try
				{
					Start frame = new Start();
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
	public Start()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button btnControl = new Button("Controls");
		btnControl.setForeground(Color.WHITE);
		btnControl.setBackground(Color.BLACK);
		btnControl.setBounds(10, 201, 90, 50);
		contentPane.add(btnControl);
		
		Button btnExit = new Button("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.BLACK);
		btnExit.addActionListener(e -> System.exit(0));
		
		btnExit.setBounds(334, 201, 90, 50);
		contentPane.add(btnExit);
		
		Button btnAudio = new Button("Audio");
		btnAudio.setForeground(Color.WHITE);
		btnAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		btnAudio.setBackground(Color.BLACK);
		btnAudio.setBounds(171, 201, 90, 50);
		contentPane.add(btnAudio);
		
		Button btnFindGame = new Button("Find Game");
		btnFindGame.setBackground(Color.BLACK);
		btnFindGame.setForeground(Color.WHITE);
		btnFindGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		btnFindGame.setFont(new Font("Chiller", Font.PLAIN, 20));
		btnFindGame.setBounds(10, 88, 414, 50);
		contentPane.add(btnFindGame);
		
		int controlImageWidth = 250;
		int controlImageHeight = 80;
		ImageIcon betrayalIcon = new ImageIcon("C:\\Users\\Owner\\Desktop\\betrayalLogo1.png"); // load the image to a imageIcon
		Image betrayalImage = betrayalIcon.getImage(); // transform it 
		Image rescaleBetrayalImage = betrayalImage.getScaledInstance(controlImageWidth, controlImageHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		betrayalIcon = new ImageIcon(rescaleBetrayalImage);  // transform it back
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(betrayalIcon);
		lblNewLabel.setBounds(96, 11, controlImageWidth, controlImageHeight);
		contentPane.add(lblNewLabel);
	}
}
