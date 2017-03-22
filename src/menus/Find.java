package menus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import audio.BGM;
import game.Main;
import network.TCPClient;


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
	public Font txtFont = new Font("Calibri", 24, 24);
	

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
		ImageIcon btnJoinGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/join_game_button_2.png", 180, 100);
		btnJoinGame.setIcon(btnJoinGameIcon);
		btnJoinGame.setBorderPainted(false);
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				String n =  txtNickname.getText();
				
				
				m.lobbym.ownsetNickname(n);
						
					
						try {
							new TCPClient(4444, txtHostIP.getText());
							m.lobbym.setIP(txtHostIP.getText());
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					m.setMenu(7);
				
					txtNickname.setText("Couldnt find host");
					txtHostIP.setText("Couldnt find host");
				
				
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
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button_2.png", 180, 100);
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
	
		lblNickname = new JLabel();
		lblNickname.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/nickname_label_2.jpg", 436, 124));
		lblNickname.setBounds(231, 153, 431, 118);
		add(lblNickname);
		
		lblHostIP = new JLabel();
		lblHostIP.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/host_ip_label_2.jpg", 436, 124));
		lblHostIP.setBounds(231, 309, 431, 124);
		add(lblHostIP);
		
		txtNickname = new JTextField("Set Nickname");
		txtNickname.setBounds(471, 207, 170, 23);
		txtNickname.setColumns(10);
		txtNickname.setFont(txtFont);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNickname.addFocusListener(new FocusListener(){
			public void focusLost(FocusEvent args0)
			{
		        if(txtNickname.getText().trim().equals(""))
		        {
		        	txtNickname.setText("Set Nickname");
		        }
		        
		        else
		        {}
		    }
			
			public void focusGained(FocusEvent args0)
			{
		        if(txtNickname.getText().trim().equals("Set Nickname"))
		        {
		        	txtNickname.setText("");
		        }
		        
		        else
		        {}
		    }
			});
		
		add(txtNickname);
		
		txtHostIP = new JTextField("XXX.XXX.X.X");
		txtHostIP.setBounds(471, 366, 170, 23);
		txtHostIP.setColumns(10);
		txtHostIP.setFont(txtFont);
		txtHostIP.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtHostIP.addFocusListener(new FocusListener(){
		public void focusLost(FocusEvent args0)
		{
	        if(txtHostIP.getText().trim().equals(""))
	        {
	        	txtHostIP.setText("XXX.XXX.X.X");
	        }
	        
	        else
	        {}
	    }
		
		public void focusGained(FocusEvent args0)
		{
	        if(txtHostIP.getText().trim().equals("XXX.XXX.X.X"))
	        {
	        	txtHostIP.setText("");
	        }
	        
	        else
	        {}
	    }
		});
		
		add(txtHostIP);
		
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
