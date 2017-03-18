package menus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.BGM;
import game.Main;
import network.Client;

/*
 * @author Jack Marshman
 */


//TODO
//Create and add graphics to new JButtons w/ Miruna
//Comment Start.java correctly
//Add graphics to resources
public class Start extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	public JButton btnFindGame;
	public JButton btnJoinGame;
	public JButton btnControls;
	public JButton btnAudio;
	public JButton btnExit;
	private BGM click;
	public String test = null; //JUnit
	
	public Start(Mainframe m)
	{
		
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		 * Background
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/betrayal_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
		
		/*
		 * Find Game button
		 */
		btnFindGame = new JButton();
		btnFindGame.setBounds(42, 176, 360, 100);
		ImageIcon btnFindGameIcon = new ImageIcon(new ImageIcon("Resources/Images/find_game_button.png").getImage().getScaledInstance(360, 100, Image.SCALE_DEFAULT));
		btnFindGame.setIcon(btnFindGameIcon);
		btnFindGame.setBorderPainted(false);
		btnFindGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				click.playOnce();
				//test="find";
				m.setMenu(5);
			}
		});
		add(btnFindGame);
		
		/*
		 * Host Game button
		 */
		btnJoinGame = new JButton();
		btnJoinGame.setBounds(486, 176, 360, 100);
		ImageIcon btnJoinGameIcon = new ImageIcon(new ImageIcon("Resources/Images/join_game_button.png").getImage().getScaledInstance(360, 100, Image.SCALE_DEFAULT));
		btnJoinGame.setIcon(btnJoinGameIcon);
		btnJoinGame.setBorderPainted(false);
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				Main.main(null);
				String[] args = new String[] {"localhost", "4444"};
			
						}
		});
		add(btnJoinGame);
		
		/*
		 * Controls button
		 */
		btnControls = new JButton();
		btnControls.setBounds(20, 450, 180, 100);
		ImageIcon btnControlsIcon = new ImageIcon(new ImageIcon("Resources/Images/controls_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnControls.setIcon(btnControlsIcon);
		btnControls.setBorderPainted(false);
		btnControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				click.playOnce();
				test="control";
				m.setMenu(2);
			}
		});
		add(btnControls);
		
		/*
		 * Audio button
		 */
		btnAudio = new JButton();
		btnAudio.setBounds(351, 450, 180, 100);
		ImageIcon btnAudioIcon = new ImageIcon(new ImageIcon("Resources/Images/audio_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnAudio.setIcon(btnAudioIcon);
		btnAudio.setBorderPainted(false);
		btnAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test="audio";
				m.setMenu(3);
			}
		});
		add(btnAudio);
		
		/*
		 * Exit button
		 */
		btnExit = new JButton();
		btnExit.setBounds(694, 450, 180, 100);
		ImageIcon btnExitIcon = new ImageIcon(new ImageIcon("Resources/Images/exit_button.png").getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
		btnExit.setIcon(btnExitIcon);
		btnExit.setBorderPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				test = "exit";
				System.exit(0);
			}
		});
		add(btnExit);
		
		
	}
}