package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import audio.BGM;
import game.Main;


/*
 * @author Jack Marshman
 */
public class Find extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnJoinGame;
	public JButton btnBack;
	public JLabel lblNickname;
	public JLabel lblHostIP;
	public JTextField txtNickname;
	public JTextField txtHostIP;
	public Font txtFont = new Font("Times New Roman", 30, 14);
	

	public Find(Mainframe m)
	{
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		 * Join Game Button
		 */
		btnJoinGame = new JButton();
		btnJoinGame.setBounds(694, 450, 180, 100);
		ImageIcon btnJoinGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/join_game_button.png", 180, 100);
		btnJoinGame.setIcon(btnJoinGameIcon);
		btnJoinGame.setBorderPainted(false);
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//TO BE IMPLEMENTED
				
				/*
				 String[] args = new String[2];
				args[0] = "localhost";
				args[1] = "4444";
				Client.main(args);
				//m.setMenu(5);
				 */
			}
		});
		add(btnJoinGame);
		
		/*
		 * Back button
		 */
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button.png", 180, 100);
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
		
		lblNickname = new JLabel("New label");
		lblNickname.setIcon(new ImageIcon("C:\\Users\\Owner\\Desktop\\TP Resources\\nickname_label.jpg"));
		lblNickname.setBounds(10, 163, 386, 176);
		add(lblNickname);
		
		JLabel lblHostIP = new JLabel("New label");
		lblHostIP.setIcon(new ImageIcon("C:\\Users\\Owner\\Desktop\\TP Resources\\host_ip_label.jpg"));
		lblHostIP.setBounds(488, 163, 386, 176);
		add(lblHostIP);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(199, 235, 149, 33);
		txtNickname.setColumns(10);
		txtNickname.setFont(txtFont);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(txtNickname);
		
		txtHostIP = new JTextField();
		txtHostIP.setBounds(677, 235, 149, 33);
		txtHostIP.setColumns(10);
		txtHostIP.setFont(txtFont);
		txtHostIP.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtHostIP.addFocusListener(new FocusListener(){
		public void focusLost(FocusEvent args0)
		{
	        if(txtHostIP.getText().trim().equals(""))
	        {
	        	txtHostIP.setText("Host IP");
	        }
	        
	        else
	        {}
	    }
		
		public void focusGained(FocusEvent args0)
		{
	        if(txtHostIP.getText().trim().equals("Host IP"))
	        {
	        	txtHostIP.setText("");
	        }
	        
	        else
	        {}
	    }
		});

		add(txtHostIP);
		
		
		/*
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
		*/
		
		/*
		 * Background JLabel
		 */
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
	}

}
