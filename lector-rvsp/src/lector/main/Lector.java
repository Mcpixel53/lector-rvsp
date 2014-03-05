package lector.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Lector {

	private JFrame window;
	private OneWord oneWord;
	
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
		window.setVisible(true);
		
		oneWord.setWord("Foquita");
	}
	
	private void work() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Lector lector = new Lector();
		ClipboardListener cbLis = new ClipboardListener();
		Thread cbLisThread = new Thread(cbLis);
		cbLisThread.start();
		lector.work();
	}

}
