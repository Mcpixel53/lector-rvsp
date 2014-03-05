package lector.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;

public class LectorWindow extends JFrame {

	private static final long serialVersionUID = -7078459164032750491L;
	private final Timer timer;
	private final OneWord oneWord;

	public LectorWindow() {
		DraggerListener dragger = new DraggerListener(this);
		this.addMouseListener(dragger);
		this.addMouseMotionListener(dragger);
		oneWord = new OneWord();
		timer = new Timer(20, oneWord);
		add(oneWord);
		setSize(new Dimension(400, 60));
		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(false);
	}

	public void showDisplay() {
		timer.start();
		setVisible(true);
	}
	
	public void hideDisplay() {
		setVisible(false);
		timer.stop();
	}
	
	public OneWord getOneWord() {
		return oneWord;
	}
}
