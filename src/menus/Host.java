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
import javax.swing.border.EmptyBorder;

import audio.BGM;

public class Host extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnBack;
	public JButton btnStartGame;
	public JLabel lblLocalIP;
	public JLabel lblPlayers;

	public Host(Mainframe m)
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
		
		/*
		 * StartGame button
		 */
		JButton btnStartGame = new JButton();
		btnStartGame.setText("Start Game");
		btnStartGame.setBorderPainted(false);
		btnStartGame.setBounds(334, 200, 90, 50);
		btnStartGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//m.setMenu(4);
			}
		});
		add(btnStartGame);
		
		JLabel lblLocalIP = new JLabel("Local IP:[###.###.#.#]");
		lblLocalIP.setForeground(Color.WHITE);
		lblLocalIP.setBackground(Color.BLACK);
		lblLocalIP.setFont(new Font("Calibri", Font.BOLD, 20));
		lblLocalIP.setBounds(124, 68, 183, 39);
		add(lblLocalIP);
		
		lblPlayers = new JLabel("Players:[             ]");
		setForeground(Color.WHITE);
		setFont(new Font("Calibri", Font.BOLD, 20));
		setBackground(Color.BLACK);
		setBounds(124, 94, 183, 112);
		add(lblPlayers);
	}

}
