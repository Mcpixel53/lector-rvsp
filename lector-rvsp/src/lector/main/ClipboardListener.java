package lector.main;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/*
* Original code from Marc Weber
* http://www.coderanch.com/t/377833/java/java/listen-clipboard
*/
class ClipboardListener implements ClipboardOwner, Runnable {

	private Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

	public void run() {
		Transferable trans = sysClip.getContents(this);
		regainOwnership(trans);
		System.out.println("Listening to board...");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void lostOwnership(Clipboard c, Transferable t) {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		Transferable contents = sysClip.getContents(this);
		processContents(contents);
		regainOwnership(contents);
	}

	private void processContents(Transferable t) {
		if(t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String content = null;

			try {
				content = (String) t.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException e0) {
				e0.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("Processing: " + content);
		}

	}

	private void regainOwnership(Transferable t) {
		sysClip.setContents(t, this);
	}
}