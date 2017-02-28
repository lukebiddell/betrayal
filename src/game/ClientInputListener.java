package game;

import java.awt.event.*;

import network.Sender;

import java.awt.Point;

public class ClientInputListener implements MouseMotionListener, MouseListener, KeyListener, ComponentListener {

	private static final int KEY_COUNT = 256;
	
	public Sender sender;

	public ClientInputListener(Sender sender) {

		this.sender = sender;
	}

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			sender.addToQueue(3);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		case MouseEvent.BUTTON3:
			sender.addToQueue(5);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			sender.addToQueue(2);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		case MouseEvent.BUTTON3:
			sender.addToQueue(4);
			sender.addToQueue(-1);
			sender.addToQueue(-1);
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		sender.addToQueue(6);
		sender.addToQueue(e.getX());
		sender.addToQueue(e.getY());
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		sender.addToQueue(6);
		sender.addToQueue(e.getX());
		sender.addToQueue(e.getY());
	}

	public synchronized void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {


			sender.addToQueue(1);
			sender.addToQueue(keyCode);
			sender.addToQueue(-1);

		}

	}

	public synchronized void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode >= 0 && keyCode < KEY_COUNT) {

			
			sender.addToQueue(0);
			sender.addToQueue(keyCode);
			sender.addToQueue(-1);

		}

	}

	public void keyTyped(KeyEvent e) {

		// Not needed

	}
	
	public void componentResized(ComponentEvent e) {
	
			
			sender.addToQueue(7);
			sender.addToQueue(e.getComponent().getWidth());
			sender.addToQueue(e.getComponent().getHeight());
	
	}
	
	
	public void componentHidden(ComponentEvent e){}
	public void componentMoved(ComponentEvent e){}
	public void componentShown(ComponentEvent e){}
}
