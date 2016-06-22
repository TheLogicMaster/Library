package main;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
	public static double closeButtonRadiusSquared = 0;
	public static int cursorX, cursorY, windowH = 0, windowW = 0, closeButtonCount = 500, closeButtonIncrement = 1,
			closeButtonTimer = 0, currentProgram = 0, iconRows = 3, iconSize = 70, previousWindowW, previousWindowH;
	public static Insets titleHeight;
	public static JFrame frame = new JFrame("APK Library");
	public static MainThread mainThread = new MainThread();
	public static boolean overCloseButton = false, IsRunning = true, isFullscreen = true,
			previousFullscreenState = false, isDebugging = true;
	public static int[][] iconX = new int[iconRows][iconRows];
	public static int[][] iconY = new int[iconRows][iconRows];
	static Dimension screenRect;

	public static KeyListener keyListener = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			if ((int) e.getKeyChar() == 27) {
				switch (currentProgram) {
				case 0:
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					break;
				case 1:
					Apk_Library.back();
					break;
				case 2:
					Apk_Library.back();
					break;
				case 3:
					Apk_Library.back();
					break;
				case 4:
					Apk_Library.back();
					break;
				case 5:
					Apk_Library.back();
					break;
				}
				currentProgram = 0;
			}
		}
	};

	public static MouseAdapter mouseAdapter = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			switch (currentProgram) {
			case 0:
				for (int a = 0; a < iconRows; a++) {
					for (int b = 0; b < iconRows; b++) {
						if (Math.pow(cursorX - iconX[0][0], 2) + Math.pow(cursorY - iconY[0][0], 2) < 1225) {
							currentProgram = a * Main.iconRows + b + 1;
							switch (currentProgram) {
							case 1:
								Apk_Library.init();
								break;
							case 2:
								Apk_Library.init();
								break;
							case 3:
								Apk_Library.init();
								break;
							case 4:
								Apk_Library.init();
								break;
							case 5:
								Apk_Library.init();
								break;
							}
						}
					}
				}
				break;
			case 1:
				Apk_Library.click(cursorX, cursorY);
				break;
			case 2:
				Apk_Library.click(cursorX, cursorY);
				break;
			case 3:
				Apk_Library.click(cursorX, cursorY);
				break;
			case 4:
				Apk_Library.click(cursorX, cursorY);
				break;
			case 5:
				Apk_Library.click(cursorX, cursorY);
				break;
			}

			if (closeButtonRadiusSquared < 1225 && cursorY > 0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
			if (closeButtonRadiusSquared > 1225 && closeButtonRadiusSquared < 4900 && cursorY > 0
					&& closeButtonCount == 500
					&& cursorY < (Math.sqrt(closeButtonRadiusSquared) - 35) * Math.sin(1.54) + 24) {
				frame.setState(Frame.ICONIFIED);
			}
			if (closeButtonRadiusSquared > 1225 && closeButtonRadiusSquared < 4900 && cursorY > 0
					&& closeButtonCount == 500
					&& cursorY > (Math.sqrt(closeButtonRadiusSquared) - 35) * Math.sin(1.54) + 24) {
				if (isFullscreen) {
					isFullscreen = false;
				} else {
					isFullscreen = true;
				}
			}
		}
	};

	public static void main(String[] args) {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.addMouseListener(mouseAdapter);
		frame.addKeyListener(keyListener);
		GUI gui = new GUI();
		frame.add(gui);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(frame);
		frame.setVisible(true);
		screenRect = frame.getContentPane().getSize();
		windowH = screenRect.height - 30;
		windowW = screenRect.width;
		previousWindowW = windowW;
		previousWindowH = windowH;
		for (int a = 0; a < iconRows; a++) {
			for (int b = 0; b < iconRows; b++) {
				if ((iconRows & 1) == 1) { // odd
					iconX[a][b] = (int) (windowW / 2 + (1.5 * iconSize * a) - 1.5 * iconSize * (iconRows - 1) / 2);
					iconY[a][b] = (int) (windowH / 2 + (1.5 * iconSize * b) - 1.5 * iconSize * (iconRows - 1) / 2);
				} else { // even
					iconX[a][b] = (int) (windowW / 2 + (iconSize / 2 + 1.5 * iconSize * a)
							- 1.5 * iconSize * iconRows / 2);
					iconY[a][b] = (int) (windowH / 2 + (iconSize / 2 + 1.5 * iconSize * b)
							- 1.5 * iconSize * iconRows / 2);
				}
			}
		}
		mainThread.start();
	}

	public static void tick() {
		closeButtonRadiusSquared = Math.pow(cursorX - windowW, 2) + Math.pow(cursorY, 2);
		screenRect = frame.getContentPane().getSize();
		windowH = screenRect.height - 30;
		windowW = screenRect.width;
		Point point = frame.getMousePosition();
		if (point != null) {
			cursorX = (int) point.getX();
		}
		if (isFullscreen && point != null) {
			cursorY = (int) point.getY();
		} else {
			if (point != null) {
				cursorY = (int) point.getY() - 30;
			}
		}
		if (isFullscreen != previousFullscreenState) {
			previousFullscreenState = isFullscreen;
			frame.dispose();
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			if (isFullscreen) {
				frame.setUndecorated(true);
			} else {
				frame.setUndecorated(false);
			}
			GUI gui = new GUI();
			frame.add(gui);
			frame.setVisible(true);
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			gd.setFullScreenWindow(frame);
		}
		if (previousWindowW != windowW || previousWindowH != windowH) {
			previousWindowW = windowW;
			previousWindowH = windowH;
			for (int a = 0; a < iconRows; a++) {
				for (int b = 0; b < iconRows; b++) {
					if ((iconRows & 1) == 1) { // odd
						iconX[a][b] = (int) (windowW / 2 + (1.5 * iconSize * a) - 1.5 * iconSize * (iconRows - 1) / 2);
						iconY[a][b] = (int) (windowH / 2 + (1.5 * iconSize * b) - 1.5 * iconSize * (iconRows - 1) / 2);
					} else { // even
						iconX[a][b] = (int) (windowW / 2 + (iconSize / 2 + 1.5 * iconSize * a)
								- 1.5 * iconSize * iconRows / 2);
						iconY[a][b] = (int) (windowH / 2 + (iconSize / 2 + 1.5 * iconSize * b)
								- 1.5 * iconSize * iconRows / 2);
					}
				}
			}
		}
		if (Math.pow(cursorX - windowW, 2) + Math.pow(cursorY, 2) < 4900 && cursorY > 0) {
			overCloseButton = true;
		} else {
			overCloseButton = false;
		}
		if (closeButtonCount > 0 && closeButtonIncrement == -1) {
			closeButtonCount--;
		}
		if (closeButtonCount < 500 && closeButtonIncrement == 1) {
			closeButtonCount++;
		}
		if (overCloseButton && closeButtonCount > 0) {
			closeButtonIncrement = 1;
			closeButtonTimer = 0;
		}
		if (Math.pow(cursorX - windowW, 2) + Math.pow(cursorY, 2) < 200 && cursorY > 0) {
			closeButtonIncrement = 1;
		}
		if (overCloseButton == false && closeButtonIncrement == 1) {
			closeButtonTimer++;
			if (closeButtonTimer > 2000) {
				closeButtonTimer = 0;
				closeButtonIncrement = -1;
			}
		}
		switch (currentProgram) {
		case 0:

			break;
		case 1:
			Apk_Library.tick();
			break;
		case 2:
			Apk_Library.tick();
			break;
		case 3:
			Apk_Library.tick();
			break;
		case 4:
			Apk_Library.tick();
			break;
		case 5:
			Apk_Library.tick();
			break;
		}
		frame.repaint();
	}
}