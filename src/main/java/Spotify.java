import java.awt.*;
import java.awt.event.KeyEvent;

public class Spotify {

	public static void ajoutMusique(String[][] songs, String playlist) {

		String app = "spotify.exe";
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(new String[]{app});
			Thread.sleep(10000);

			Robot robot = new Robot();
			execute(robot, KeyEvent.VK_TAB, 3);
			Thread.sleep(500);
			System.out.println("test");
			execute(robot, KeyEvent.VK_ENTER);
			System.out.println("test1");

			int i = 0;
			for (String[] song : songs) {
				String title = song[0];
				String artist = song[1];

				type(robot, title);

				execute(robot, KeyEvent.VK_SPACE);

				type(robot, artist);

				execute(robot, KeyEvent.VK_ENTER);
				execute(robot, KeyEvent.VK_TAB, 4);
				execute(robot, KeyEvent.VK_RIGHT, 3);
				execute(robot, KeyEvent.VK_ENTER);
				execute(robot, KeyEvent.VK_DOWN, 7);
				execute(robot, KeyEvent.VK_RIGHT);

				execute(robot, KeyEvent.VK_CONTROL);
				execute(robot, KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				type(robot, playlist);

				execute(robot, KeyEvent.VK_DOWN, 2);
				execute(robot, KeyEvent.VK_ENTER);

				execute(robot, KeyEvent.VK_SHIFT);
				execute(robot, KeyEvent.VK_TAB, 32);
				robot.keyRelease(KeyEvent.VK_SHIFT);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void type(Robot robot, String artist) throws IllegalAccessException, NoSuchFieldException {
		for (String s : artist.split("")) {
			try {
				if (isInt(s)) {
					execute(robot, KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.class.getDeclaredField("VK_" + s.toUpperCase()).getInt(KeyEvent.class));
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
				else
					robot.keyPress(KeyEvent.class.getDeclaredField("VK_" + s.toUpperCase()).getInt(KeyEvent.class));
			} catch (Exception e) {
				robot.keyPress(KeyEvent.VK_SPACE);
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void execute(Robot r, int keyCode, int times) {
		for (int i = 0; i < times; i++) {
			r.keyPress(keyCode);
			try {
			Thread.sleep(200);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void execute(Robot r, int keyCode) {
		execute(r, keyCode, 1);
	}

	public static boolean isInt (String s) {

		try {
			int i = Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}


