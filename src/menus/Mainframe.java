package menus;

import javax.swing.JFrame;
import audio.BGM;

/*
 * @author Jack Marshman
 */

//TODO
//Comment correctly
public class Mainframe extends JFrame
{
	private static final long serialVersionUID = 1L;
	public BGM bgm = new BGM(50, "/Music/BGM_Asian_Gravedrum.wav");
	//public BGM bgm = new BGM(50, "/Music/Mask Off.wav");
	
	public  Mainframe()
	{
		
		super();
		this.setBounds(100, 100, 900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Start(this));
		this.setResizable(false);
		this.setVisible(true);
		//this.bgm.play();
		this.revalidate();
	}
	
	/*
	 * setMenu which treats each menu as a panel, and revalidates the
	 * Menuframe when a button is pressed:
	 * 1 = Start
	 * 2 = Controls
	 * 3 = Audio
	 * 4 = Host
	 * 5 = Find
	 * 6 = Pause
	 */
	/*
	 * Not sure about the System.out.println's so I've just commented them out for now - JM
	 */
	public void setMenu(int menuNum)
	{
		switch(menuNum)
		{
			case 1:
			{
				this.getContentPane().removeAll();
				this.setContentPane(new Start(this));
				this.revalidate();
				break;
			}
				
			case 2:
			{
				this.getContentPane().removeAll();
				this.setContentPane(new Control(this));
				this.revalidate();
				break;
			}
				
			case 3:
			{
				this.getContentPane().removeAll();
				this.setContentPane(new Audio(this, bgm));
				this.revalidate();
				break;
			}	
			
			case 4:
			{
				//System.out.println("got case 4");
				this.getContentPane().removeAll();
				this.setContentPane(new Host(this));
				this.revalidate();
				break;
			}
			
			case 5:
			{
				//System.out.println("got case 5");
				this.getContentPane().removeAll();
				this.setContentPane(new Find(this));
				this.revalidate();
				break;
			}
			
			case 6:
			{
				this.getContentPane().removeAll();
				this.setContentPane(new Pause(this));
				this.revalidate();
				break;
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Mainframe();
	}

}