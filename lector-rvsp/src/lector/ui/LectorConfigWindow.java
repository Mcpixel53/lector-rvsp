package lector.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LectorConfigWindow extends JFrame {

	private static final long serialVersionUID = 2743590712073448443L;
	private static Point location;
	private static LectorConfigWindow window;
	
	private LectorConfigWindow() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(300, 200));
		
		if(location == null) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			location = new Point((int) ((width/2.)-getWidth()/2.), (int) ((height/2.)-getHeight()));
		}
		setLocation(location);
		
		setVisible(true);
		toFront();
	}
	
	public static void displayConfigWindow() {
		if(window == null) window = new LectorConfigWindow();
	}
	

}
