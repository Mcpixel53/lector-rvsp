package lector.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import lector.main.Lector;

public class LectorWindow extends JFrame {
	
	private static final long serialVersionUID = -7078459164032750491L;

	private final Timer timer;
	private final OneWord oneWord;
	
	public LectorWindow() {
		Dimension winSize = new Dimension(440, 80);
		LectorDraggerListener dragger = new LectorDraggerListener(this);
		addMouseListener(dragger);
		addMouseMotionListener(dragger);
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	ex.printStackTrace();
        }

		setUndecorated(true);
		setBackground(new Color(0f, 0f, 0f, 0f));

		setSize(winSize);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		setLocation(screenWidth/2 - getWidth()/2, 0);
		
		setLayout(new BorderLayout());
		
		JPanel background = new ImagePanel();
		background.setLayout(new BoxLayout(background, BoxLayout.X_AXIS));
		add(background);
		
		JPanel space = new JPanel();
		space.setOpaque(false);
		space.setBackground(new Color(0f, 0f, 0f, 0f));
		space.setMaximumSize(new Dimension(20, 80));
		space.setMinimumSize(new Dimension(20, 80));
		background.add(space);

		oneWord = new OneWord();
		timer = new Timer(20, oneWord);
		background.add(oneWord);
		
		setResizable(false);
		setAlwaysOnTop(true);
		
		setUpSysTray();
		
		setVisible(false);
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
