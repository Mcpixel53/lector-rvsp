package lector.main;

import lector.ui.LectorWindow;
import lector.ui.OneWord;

public class Control {

	private final ClipboardListener cbLis = new ClipboardListener();
	private final LectorWindow lectorWindow = new LectorWindow();;
	private final OneWord oneWord = lectorWindow.getOneWord();
	private int timeBetweenWords;

	Control() {
		Thread cbLisThread = new Thread(cbLis);
		cbLisThread.start();
	}
	
	void setWordsPerMinute(int wordsPerMinute) {
		float wpm = wordsPerMinute;
		timeBetweenWords = (int) (60000f/wpm);
	}
	
	private void displayText(String text) {
		String[] words = text.split("\\s");
		
		oneWord.setWord(words[0]);
		lectorWindow.showDisplay();
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
		lectorWindow.hideDisplay();
	}
	
	void work() {
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
}
