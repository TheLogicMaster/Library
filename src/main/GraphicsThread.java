package main;

public class GraphicsThread extends Thread {

	public static long millis, millisPrev;

	@Override
	public void run() {
		while (Main.IsRunning) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Main.frame.repaint();
		}
	}

}
