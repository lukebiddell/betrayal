package lobby;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LobbySender extends Thread {
		private DataOutputStream out;
		private BlockingQueue<String> queue;

		public LobbySender(DataOutputStream out) {
			this.out = out;
			this.queue = new LinkedBlockingQueue<String>();

		}

		public void run() {

			try {
				while (true) {
					out.writeUTF(queue.take());
				}
			} catch (IOException e) {
				e.printStackTrace();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void addToQueue(String input) {
			
			try {
				queue.put(input);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
