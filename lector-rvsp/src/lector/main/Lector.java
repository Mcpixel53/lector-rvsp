package lector.main;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Lector {
	
	public static final String APP_NAME = "Lector";

	private final ClipboardListener cbLis = new ClipboardListener();
	private final LectorWindow window = new LectorWindow();;
	private final OneWord oneWord = window.getOneWord();
	private int timeBetweenWords;

	private Lector() {
		Thread cbLisThread = new Thread(cbLis);
		cbLisThread.start();
	}
	
	private void setWordsPerMinute(int wordsPerMinute) {
		float wpm = wordsPerMinute;
		timeBetweenWords = (int) (60000f/wpm);
	}
	
	private void displayText(String text) {
		String[] words = text.split("\\s");
		
		oneWord.setWord(words[0]);
		window.showDisplay();
		try {
			Thread.sleep(240);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(String w : words) {
			if(!w.equals("")) {
				oneWord.setWord(w);
				try {
					long pause = 0;
					if(w.charAt(w.length()-1) == ',') pause = 150;
					if(w.charAt(w.length()-1) == ':') pause = 150;
					if(w.charAt(w.length()-1) == '.') pause = 200;
					Thread.sleep(timeBetweenWords + pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			Thread.sleep(240);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		window.hideDisplay();
	}
	
	private void work() {
		synchronized(cbLis) {
			while(true) {
				try {
					cbLis.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				displayText(cbLis.getContent());
			}
		}
	}
	
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

	public static void main(String[] args) {
		
		lockInstance();
		
		Lector lector = new Lector();
		lector.setWordsPerMinute(600);
		lector.work();
	}
}
