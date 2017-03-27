package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.BGM;
import menus.Mainframe;

/*
 * @author Jack Marshman
 */


public class Control extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	
	public JButton btnBack;
	public JButton btnExit;
	public String test = null; //JUnit
	private BGM click;
	
	public Control(Mainframe m) 
	{
		
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
				
		/*
		 * Back button
		 */
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button_2.png", 180, 100);
		
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "back";
				m.setMenu(1);
			}
		});
		add(btnBack);
		
		/*
		 * Exit button
		 */		
		btnExit = new JButton();
		btnExit.setBounds(694, 450, 180, 100);
		ImageIcon btnExitIcon = MenuButtonHandler.loadImageIcon("Resources/Images/exit_button_2.png", 180, 100);
		btnExit.setIcon(btnExitIcon);
		btnExit.setBorderPainted(false);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "exit";
				System.exit(0);
			}
		});
		add(btnExit);
		
		/*
		 * Controls graphic
		 */
		int controlsWidth = 700;
		int controlsHeight = 240;
		JLabel controlsLabel = new JLabel();
		controlsLabel.setBounds(95, 172, controlsWidth, controlsHeight);
		ImageIcon controlsIcon = MenuButtonHandler.loadImageIcon("Resources/Images/controls_final.jpg", controlsWidth, controlsHeight); 
		controlsLabel.setIcon(controlsIcon);
		add(controlsLabel);	
		
		/*
		 * Background JLabel
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/controls_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
	}
}
