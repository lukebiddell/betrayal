package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import audio.BGM;

/*
 * @author Jack Marshman
 */
public class Pause extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Mainframe m;
	private BGM click;
	public JButton btnReturnGame;
	public JButton btnDisconnect;
	public JLabel background;

	public Pause(Mainframe m)
	{
		super();
		this.m = m;
		click = new BGM(10,"/Music/SFX_Click.wav");
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/*
		 * Return to Game button
		 * Graphic Needed
		 */
		btnReturnGame = new JButton("Return to Game");
		//btnReturnGame.setBounds();
		ImageIcon btnReturnGameIcon = MenuButtonHandler.loadImageIcon("", 180,100);
		btnReturnGame.setIcon(btnReturnGameIcon);
		btnReturnGame.setBorderPainted(false);
		btnReturnGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//TO BE IMPLEMENTED
			}
		});
		add(btnReturnGame);
		
		/*
		 * Disconnect from Game button
		 * Graphic Needed
		 */
		btnDisconnect = new JButton();
		//btnDisconnect.setBounds();
		ImageIcon btnDisconnectIcon = MenuButtonHandler.loadImageIcon("", 180,100);
		btnDisconnect.setIcon(btnDisconnectIcon);
		btnDisconnect.setBorderPainted(false);
		btnDisconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//TO BE IMPLEMENTED
			}
		});
		add(btnDisconnect);
		
		/*
		 * Background JLabel
		 * Graphic Needed
		 */
		background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("", 900,600);
		background.setIcon(backgroundIcon);
		add(background);
	}

}
