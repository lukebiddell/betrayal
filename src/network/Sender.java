package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//user Sender
//sends to sender

public class Sender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<Integer> queue;

	public Sender(DataOutputStream out) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<Integer>();
	}

	public void run() {

		try {
			while (true) {
				out.write(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue(Integer input) {
			queue.add(input);
	
	}
}
