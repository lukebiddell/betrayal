package game;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JSlider;

/*
 * @author Jack Marshman
 */

//TODO
//Finish general menu layout
//Convert all AWT buttons to JButtons
//Create and add graphics to new JButtons
//Refactor image rescaling code
//Add events and sounds to button clicks
//Integrate into game
//Comment Audio.java correctly
//Add graphics to resources
public class Audio extends JFrame
{
	/**
	 * Initial JFrame
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
					Audio frame = new Audio();
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
	public Audio() 
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
				//new Start();
				
			}
		});
		
		contentPane.add(btnBack);
		
		Button btnExit = new Button("Exit");
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(334, 201, 90, 50);
		btnExit.addActionListener(e -> System.exit(0));
		contentPane.add(btnExit);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Mute/Unmute");
		tglbtnNewToggleButton.setBounds(148, 36, 121, 23);
		contentPane.add(tglbtnNewToggleButton);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("50");
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(25);
		slider.setBounds(106, 70, 200, 26);
		contentPane.add(slider);
	}
}
