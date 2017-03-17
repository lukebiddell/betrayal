package menus;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import audio.BGM;
import menus.Mainframe;

/*
 * @author Jack Marshman
 */

//TODO
//Finish general menu layout
//Create and add graphics to new JButtons w/ Miruna
//Add graphics to resources
public class Audio extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mainframe m;

	public BGM click;
	public JButton btnBack;
	public JButton btnExit;
	public JToggleButton tglbtnNewToggleButton;
	
	public String test = null; //for junit 
	
	public Audio(Mainframe m) 
	{
		super();
		this.m = m;
		
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		/*
		 * Back button
		 */
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnBack.setIcon(btnBackIcon);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test="back";
				m.setMenu(1);
			}
		});
		add(btnBack);
		
		/*
		 * Exit button		
		 */
		btnExit = new JButton();
		btnExit.setBounds(694, 450, 180, 100);
		ImageIcon btnExitIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnExit.setIcon(btnExitIcon);
//		btnExit.addActionListener(e -> System.exit(0));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "exit";
				System.exit(0);
			}
		});
		add(btnExit);
		
		
		/*
		 * To be completed
		 */
		tglbtnNewToggleButton = new JToggleButton("Mute/Unmute");
		tglbtnNewToggleButton.setBounds(148, 36, 121, 23);
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "mute";
			}
		});
		add(tglbtnNewToggleButton);
		
		/*
		 * To be completed
		 */
		JSlider slider = new JSlider();
		slider.setToolTipText("50");
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(25);
		slider.setBounds(106, 70, 200, 26);
		add(slider);
		
		/*
		 * Audio title graphic
		 */
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(246, 11, 426, 128);
		ImageIcon titleIcon = new ImageIcon(new ImageIcon("Resources/Images/audioLogo1.png").getImage().getScaledInstance(426, 128, Image.SCALE_DEFAULT));
		titleLabel.setIcon(titleIcon);
		add(titleLabel);
	}
}
