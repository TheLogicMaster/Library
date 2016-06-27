package main;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Apk_Library {

	public static String apkHome = "C:\\Programming\\Mobile\\APK";
	public static boolean dirNotFound, graphicsUpdate;

	public static void init() {
		// System.out.println(Main.runCommand("echo 1"));
	}

	public static void tick() {
		Path path = Paths.get(apkHome);
		dirNotFound = !Files.exists(path);
	}

	public static void graphics(Graphics2D g2d) {
		g2d.setFont(new Font("Courier New", Font.PLAIN, 36));
		g2d.drawString("APK Library", Main.windowW / 2 - 80, 30);
		if (dirNotFound) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));//8:5 
			g2d.drawImage(Main.image[0], Main.windowW / 2 - Main.windowW / 3, Main.windowH / 2 - Main.windowH / 3,
					Main.windowW / 3 * 2, Main.windowH / 3 * 2, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.2f));
		}
	}

	public static void back() {

	}

	public static void click(int x, int y) {
		if (dirNotFound) {

		} else {

		}
	}
}
