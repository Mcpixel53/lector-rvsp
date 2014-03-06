package lector.main;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Lector {
	
	public static final String APP_NAME = "Lector";

	private static Control control;

	private static void lockInstance() {
		try {
			if(!SingleInstanceLock.lock()) {
				JOptionPane.showMessageDialog(null, "The program is already running", APP_NAME, JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to create lock file", APP_NAME, JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	public static Control getControl() {
		return control;
	}

	public static void main(String[] args) {
		
		lockInstance();
		
		control = new Control();
		control.setWordsPerMinute(600);
		control.work();
	}
}
