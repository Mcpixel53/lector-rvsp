package lector.ui.settings;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import lector.main.Lector;
import lector.ui.tray.LectorTrayIcon;

public class LectorSettingsWindow extends JFrame {

	public static final Image titlebarIcon = Toolkit.getDefaultToolkit().getImage(LectorTrayIcon.class.getResource("/lector/resources/icon-enabled-16x16.png"));

	private static final long serialVersionUID = 2743590712073448443L;
	private static Point location;
	private static LectorSettingsWindow window;
	
	private LectorSettingsWindow() {
		super("Settings" + " - " + Lector.APP_NAME);
		setIconImage(titlebarIcon);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(400, 140));
		
		if(location == null) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			location = new Point((int) ((width/2.)-getWidth()/2.), (int) ((height/2.)-getHeight()));
		}
		setLocation(location);

		// Remember location
		addWindowListener(new WindowListener() {		
			@Override
			public void windowClosing(WindowEvent arg0) {
				location = getLocation();
			}

			// **** you, Java.
			@Override public void windowOpened(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowClosed(WindowEvent arg0) {
				window = null;
			}
			@Override public void windowActivated(WindowEvent arg0) {}
		});
		
		add(new SettingsPanel(this));

		setVisible(true);
		toFront();
	}
	
	public static void displayConfigWindow() {
		if(window == null) window = new LectorSettingsWindow();
	}
}
