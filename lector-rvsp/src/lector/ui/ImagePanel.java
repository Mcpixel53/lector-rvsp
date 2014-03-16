package lector.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public final class ImagePanel extends JPanel {

	private static final long serialVersionUID = -5526340149401796784L;
	
	private BufferedImage image;

	protected ImagePanel() {
		try {
			image = ImageIO.read(ImagePanel.class.getResource("/lector/resources/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setOpaque(false);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return image == null? new Dimension(300, 300): new Dimension(image.getWidth(), image.getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g2d.drawImage(image, x, y, this);
            g2d.dispose();
        }
	}
}
