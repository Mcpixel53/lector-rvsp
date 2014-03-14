package lector.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import lector.main.Control;
import lector.main.Lector;

public class LectorTrayIcon extends TrayIcon {

	public static final Image iconImageEnabled = Toolkit.getDefaultToolkit().getImage(LectorTrayIcon.class.getResource("/lector/resources/icon-enabled-16x16.png"));
	public static final Image iconImageDisabled = Toolkit.getDefaultToolkit().getImage(LectorTrayIcon.class.getResource("/lector/resources/icon-disabled-16x16.png"));
	
	private final JMenuItem configJMenuItem = new JMenuItem("Configure...");
	private final JMenuItem closeJMenuItem = new JMenuItem("Close");
	private final JPopupMenu jpopup = new JPopupMenu();
	
	public LectorTrayIcon() {
		super(iconImageEnabled, Lector.APP_NAME);

		setUpPopup();
		
		addMouseListener(new MouseListener() {

			private void checkPopUpTrigger(MouseEvent e) {
				if(e.isPopupTrigger()) {
	                jpopup.setLocation(e.getX(), e.getY());
	                jpopup.setInvoker(jpopup);
	                jpopup.setVisible(true);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					Control control = Lector.getControl();
					boolean disabled = !control.isEnabled();
					control.setEnabled(disabled);
					setImage(disabled? iconImageEnabled : iconImageDisabled);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				checkPopUpTrigger(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				checkPopUpTrigger(e);
			}
			
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
		});
	}

	private void setUpPopup() {
		LectorTrayMenuListener l = new LectorTrayMenuListener();
		jpopup.add(configJMenuItem);
		configJMenuItem.addActionListener(l);
		jpopup.addSeparator();
		jpopup.add(closeJMenuItem);
		closeJMenuItem.addActionListener(l);
	}
}
