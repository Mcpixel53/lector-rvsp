package lector.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Lector {

	private final JFrame window;
	private final OneWord oneWord;
	private final ClipboardListener cbLis;
	private int timeBetweenWords = 240;
	
	private Lector() {
		window = new JFrame();
		DraggerListener dragger = new DraggerListener(window);
		window.addMouseListener(dragger);
		window.addMouseMotionListener(dragger);
		oneWord = new OneWord();
		window.add(oneWord);
		window.setSize(new Dimension(400, 60));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		window.setVisible(false);
		
		cbLis = new ClipboardListener();
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
		window.setVisible(true);
		window.repaint();
		try {
			Thread.sleep(240);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		for(String w : words) {
			oneWord.setWord(w);
			window.repaint();
			try {
				Thread.sleep(timeBetweenWords);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(240);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		window.setVisible(false);
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
		lector.setWordsPerMinute(500);
		lector.work();
	}

}
