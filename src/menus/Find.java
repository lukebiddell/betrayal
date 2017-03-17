package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import audio.BGM;
import game.Main;
import network.Client;

public class Find extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnBack;
	public JButton btnJoinGame;
	public JTextField textField;

	public Find(Mainframe m)
	{
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		background.setIcon(new ImageIcon(new ImageIcon("Resources/Images/betrayal_background.png").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
		add(background);
		/*
		 * Back button
		 */
		JButton btnBack = new JButton();
		btnBack.setText("Back");
		btnBack.setBounds(10, 200, 90, 50);
		ImageIcon btnBackIcon = new ImageIcon(new ImageIcon("Resources/Images/back_button.png").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//test = "back";
				m.setMenu(1);
			}
		});
		add(btnBack);
		
		JButton btnJoinGame = new JButton();
		ImageIcon btnJoinIcon = new ImageIcon(new ImageIcon("Resources/Images/join_game_button.png").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));
		btnJoinGame.setIcon(btnJoinIcon);
		btnJoinGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) 
			{
				click.playOnce();
				String[] args = new String[2];
				args[0] = "localhost";
				args[1] = "4444";
				Client.main(args);
				//m.setMenu(5);
			}
		});
		btnJoinGame.setText("Join Game");
		btnJoinGame.setBorderPainted(false);
		btnJoinGame.setBounds(334, 200, 90, 50);
		add(btnJoinGame);
		
		JLabel lblGameIp = new JLabel("Game IP:");
		lblGameIp.setForeground(Color.WHITE);
		lblGameIp.setFont(new Font("Calibri", Font.BOLD, 20));
		lblGameIp.setBackground(Color.BLACK);
		lblGameIp.setBounds(130, 82, 90, 39);
		add(lblGameIp);
		
		JTextField textField = new JTextField();
		textField.setBounds(222, 92, 86, 20);
		textField.setColumns(10);
		add(textField);
		
		
		
	}

}
