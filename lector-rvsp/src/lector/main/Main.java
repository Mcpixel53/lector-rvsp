package lector.main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

	// private static final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	public static void main(String[] args) {
		JFrame window = new JFrame("Title");
		OneWord ow = new OneWord();
		ow.setWord("Supercalifragilisticoespialidoso");
		window.add(ow);
		window.setSize(new Dimension(400, 60));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setResizable(false);
		window.setVisible(true);
	}

}
