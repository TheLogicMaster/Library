package main;

public class MainThread extends Thread{
	
	@Override
	public void run(){
		while (Main.IsRunning) {
			try {
				Thread.sleep(1);
        	} catch (InterruptedException e) {}
			Main.tick();
		}
	}
}
