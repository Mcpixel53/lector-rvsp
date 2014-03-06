package lector.ui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.SystemTray;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import lector.main.Lector;

public class LectorWindow extends JFrame {

	private static final long serialVersionUID = -7078459164032750491L;

	private final Timer timer;
	private final OneWord oneWord;
	
	public LectorWindow() {
		LectorDraggerListener dragger = new LectorDraggerListener(this);
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
		
		setUpSysTray();
	}
	
	private void setUpSysTray() {
		if(!SystemTray.isSupported()) {
			JOptionPane.showMessageDialog(this,
			    "Your OS doesn't support sytem tray.\n" +
			    "Lector will execute without a tray icon.",
			    Lector.APP_NAME,
			    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		SystemTray tray = SystemTray.getSystemTray();

		LectorTrayIcon trayIcon = new LectorTrayIcon();
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
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
