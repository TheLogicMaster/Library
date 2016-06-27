package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.*;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public static double closeButtonDiameter;
	public static InputStream iS;
	BufferedImage img;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (Main.isDebugging) {
			g2d.drawString("X: " + String.valueOf(Main.cursorX), 100, 60);
			g2d.drawString("Y: " + String.valueOf(Main.cursorY), 100, 80);
			g2d.drawString("W: " + String.valueOf(Main.windowW), 100, 100);
			g2d.drawString("H: " + String.valueOf(Main.windowH), 100, 120);
			g2d.drawString("Count: " + String.valueOf(Main.closeButtonCount), 100, 140);
			g2d.drawString("Timer: " + String.valueOf(Main.closeButtonTimer), 100, 160);
			g2d.drawString("Increment: " + String.valueOf(Main.closeButtonIncrement), 100, 180);
			g2d.drawString("Over Close: " + Main.overCloseButton, 100, 200);
			g2d.drawString("Radius Squared: " + Main.closeButtonRadiusSquared, 100, 220);
			g2d.drawString("Is Fullscreen: " + Main.isFullscreen, 100, 240);
			g2d.drawString("Previous Fullscreen: " + Main.previousFullscreenState, 100, 260);
			g2d.drawString("Running from jar: " + Main.isJar, 100, 280);
		}
		switch (Main.currentProgram) {
		case 0:
			for (int a = 0; a < Main.iconRows; a++) {
				for (int b = 0; b < Main.iconRows; b++) {
					g2d.drawImage(Main.icon[a][b], Main.iconX[a][b] - Main.iconSize / 2,
							Main.iconY[a][b] - Main.iconSize / 2, null);
					g2d.drawOval(Main.iconX[a][b] - Main.iconSize / 2, Main.iconY[a][b] - Main.iconSize / 2,
							Main.iconSize, Main.iconSize);
				}
			}
			break;
		case 1:
			Apk_Library.graphics(g2d);
			break;
		case 2:
			Apk_Library.graphics(g2d);
			break;
		case 3:
			Apk_Library.graphics(g2d);
			break;
		case 4:
			Apk_Library.graphics(g2d);
			break;
		case 5:
			Apk_Library.graphics(g2d);
			break;
		}

		closeButtonDiameter = (double) Main.closeButtonCount / 500 * 140 * 8 / 10 + 28;
		g2d.drawOval((int) (Main.windowW - closeButtonDiameter / 2), -1 * (int) (closeButtonDiameter / 2),
				(int) closeButtonDiameter, (int) closeButtonDiameter);
		g2d.drawOval((int) (Main.windowW - closeButtonDiameter / 4), -1 * (int) (closeButtonDiameter / 4),
				(int) closeButtonDiameter / 2, (int) closeButtonDiameter / 2);
		g2d.drawLine((int) (Main.windowW - Math.sqrt(2 * Math.pow(closeButtonDiameter / 4, 2))),
				(int) (Math.sqrt(2 * Math.pow(closeButtonDiameter / 4, 2))),
				(int) (Main.windowW - Math.sqrt(2 * Math.pow(closeButtonDiameter / 8, 2))),
				(int) (Math.sqrt(2 * Math.pow(closeButtonDiameter / 8, 2))));
		g2d.dispose();
	}
}
