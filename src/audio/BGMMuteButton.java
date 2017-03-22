package audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import menus.MenuButtonHandler;

/*
 * @author Farrah Aina Mohd Zulkifli
 * @author Jack Marshman - Changes to the class are aesthetic 
 */

public class BGMMuteButton extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public JButton mute;
	
	/**
	 * Alternatively set boolean value to muteStatus when button is clicked
	 * @param model
	 */
	public BGMMuteButton(BGMModel model){
		
		super();
		//setLayout(null);
		
		mute = new JButton("Mute/Unmute");	
		
		
		
		
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(model.getMuteStatus()==true){
					model.setMuteStatus(false);
				}else if (model.getMuteStatus()==false){
					model.setMuteStatus(true);
				}
				
			}
		});
		
		add(mute);
	}

}
