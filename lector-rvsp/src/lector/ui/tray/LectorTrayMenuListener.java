package lector.ui.tray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lector.ui.settings.LectorSettingsWindow;

public class LectorTrayMenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Close":
			System.exit(0);
		case "Configure...":
			LectorSettingsWindow.displayConfigWindow();
		default:
			break;
		}
	}
}
