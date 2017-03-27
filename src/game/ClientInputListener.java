package game;

import java.awt.event.*;

import network.ClientSender;

public class ClientInputListener implements MouseMotionListener, MouseListener, KeyListener, ComponentListener {

	private static final int KEY_COUNT = 256;
	
	public ClientSender sender;

	public ClientInputListener(ClientSender sender) {

		this.sender = sender;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			int[] ints = new int[]{
					3,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		case MouseEvent.BUTTON3:
			ints = new int[]{
					5,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			int[] ints = new int[]{
					2,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		case MouseEvent.BUTTON3:
			ints = new int[]{
					4,
					-1,
					-1
			};
			sender.addToQueue(ints);
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int[] ints = new int[]{
				6,
				e.getX(),
				e.getY()
		};
		sender.addToQueue(ints);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int[] ints = new int[]{
				6,
				e.getX(),
				e.getY()
		};
		sender.addToQueue(ints);
		}

	@Override
	public synchronized void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			int[] ints = new int[]{
					1,
					keyCode,
					-1
			};

			sender.addToQueue(ints);
			

		}

	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			int[] ints = new int[]{
					0,
					keyCode,
					-1
			};
			
			sender.addToQueue(ints);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

		// Not needed

	}
	
	@Override
	public void componentResized(ComponentEvent e) {
	
			int[] ints = new int[]{
					7,
					e.getComponent().getWidth(),
					e.getComponent().getHeight()
			};
			sender.addToQueue(ints);
	}
	
	
	@Override
	public void componentHidden(ComponentEvent e){}
	@Override
	public void componentMoved(ComponentEvent e){}
	@Override
	public void componentShown(ComponentEvent e){}
}
