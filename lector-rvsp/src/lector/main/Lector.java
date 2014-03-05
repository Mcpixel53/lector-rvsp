package lector.main;

public class Lector {

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

	public static void main(String[] args) {
		Lector lector = new Lector();
		lector.setWordsPerMinute(400);
		lector.work();
	}

}
