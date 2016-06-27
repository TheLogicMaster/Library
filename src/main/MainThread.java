package main;

public class MainThread extends Thread {

	public static long millis, millisPrev;

	@Override
	public void run() {
		while (Main.IsRunning) {
			Main.tick();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
