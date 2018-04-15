package ru.skysoftlab.swt;

import java.io.File;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Utils {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static Image getImage(Display disp, String name) {
		return new Image(disp, Utils.class.getClassLoader().getResourceAsStream(name));
	}

	public static File showOpenSaveFileDialog(Shell shell, String text, int swtType, String... filterExt) {
		FileDialog fd = new FileDialog(shell, swtType);
		fd.setText(text);
		if (isWindows()) {
			fd.setFilterPath("C:/");
		} else if (isMac()) {
			fd.setFilterPath("/");
		} else if (isUnix()) {
			fd.setFilterPath("/");
		} else if (isSolaris()) {
			fd.setFilterPath("/");
		} else {
			System.out.println("Your OS is not support!!");
		}
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		if (selected != null)
			return new File(selected);
		return null;
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
	
}
