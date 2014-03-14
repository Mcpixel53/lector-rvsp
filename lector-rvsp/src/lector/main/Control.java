package lector.main;

import lector.ui.LectorWindow;
import lector.ui.OneWord;

public class Control {

	private final ClipboardListener cbLis = new ClipboardListener();
	private final LectorWindow lectorWindow = new LectorWindow();;
	private final OneWord oneWord = lectorWindow.getOneWord();
	private float timeBetweenWords;
	private boolean enabled = true;

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
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(String w : words) {
			if(!enabled) {
				lectorWindow.hideDisplay();
				return;
			}
			if(!w.equals("")) {
				oneWord.setWord(w);
				try {
					float pause = 0;
					if(w.charAt(w.length()-1) == ',') pause = timeBetweenWords*0.1f;
					if(w.charAt(w.length()-1) == ':') pause = timeBetweenWords*0.1f;
					if(w.charAt(w.length()-1) == '.') pause = timeBetweenWords*0.2f;
					Thread.sleep((long) (timeBetweenWords + pause));
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
	
	public void setEnabled(boolean _enabled) {
		enabled = _enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	void work() {
		synchronized(cbLis) {
			while(true) {
				try {
					cbLis.wait();
					if(enabled) displayText(cbLis.getContent());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
