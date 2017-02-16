package game;
import javax.swing.JFrame;
import javax.swing.JPanel;

import network.Sender;
import network.ClientListener;

import java.awt.*;

public class ClientWindow extends JPanel{

	public ClientInputListener l;
	public ClientListener cl;
	
	private JFrame frame;
	
	public ClientWindow(Sender sender){
		super();
		
		frame = new JFrame("Hello World");
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,420);
		
		frame.add(this);
		
		l = new ClientInputListener(sender);
		frame.addKeyListener(l);
		frame.addMouseListener(l);
		frame.addMouseMotionListener(l);
		frame.addComponentListener(l);
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics g){
		cl.g = (Graphics2D)g;
	}
}
