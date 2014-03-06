package lector.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LectorTrayMenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Close")) {
			System.exit(0);
		}
	}

}
