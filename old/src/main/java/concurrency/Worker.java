package concurrency; /*
											* Copyright (c) 2016
											* This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
											*/

import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {

	private Object lock = new Object();
	private volatile boolean quittingTime = false;

	public static void main(String... args) throws InterruptedException {

		final var worker = new Worker();
		worker.start();

		var t = new Timer(true); // Daemon thread
		t.schedule(
				new TimerTask() {

					@Override
					public void run() {
						worker.keepWorking();
					}
				},
				500);

		Thread.sleep(400);
		worker.quit();
	}

	// Only this method will run as a part of Work thread
	public void run() {
		while (!quittingTime) {
			working();
			System.out.println("Still working...");
		}

		System.out.println("Coffee is good !");
	}

	private void working() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}
	}

	synchronized void quit() throws InterruptedException {
		synchronized (lock) {
			quittingTime = true;
			System.out.println("Calling join");
			join(); // This is called as a part main thread, and join method is called on 'this', i.e.,
			// Worker object.
			// So it is hey main, stop and join at the end of Worker thread.
			System.out.println("Back from join");
		}
	}

	synchronized void keepWorking() {
		synchronized (lock) {
			quittingTime = false;
			System.out.println("Keep working");
		}
	}
}
