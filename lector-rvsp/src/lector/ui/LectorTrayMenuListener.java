package lector.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LectorTrayMenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Close":
			System.exit(0);
		case "Configure...":
			LectorConfigWindow.displayConfigWindow();
		default:
			break;
		}
	}
}
