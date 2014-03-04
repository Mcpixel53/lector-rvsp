package lector.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;

import javax.swing.JPanel;

public class OneWord extends JPanel {

	private static final long serialVersionUID = 3568780586785687917L;
	private String word;
	private AttributedString coloredWord;
	private Color txtColor = Color.BLACK;
	private Color orpColor = Color.RED;
	private Font font = new Font("SansSerif", Font.PLAIN, 20);
	private float lineWidth = 2f;

	
	private int getORP(String str) {
		final int[] table = {0, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4};
		int length = str.length();
		
		if(length < 1) return 0;
		if(length > 13) return 5;
		return table[length];
	}
	
	public void setWord(String _word) {
		word = _word;
		int orp = getORP(_word);
		AttributedString attStr = new AttributedString(_word);
		attStr.addAttribute(TextAttribute.FONT, font);
		attStr.addAttribute(TextAttribute.FOREGROUND, txtColor);
		attStr.addAttribute(TextAttribute.FOREGROUND, orpColor, orp, orp+1);
		coloredWord = attStr;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(word != null && coloredWord != null) {
			
			g2.setRenderingHint(
			        RenderingHints.KEY_TEXT_ANTIALIASING,
			        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			g2.setFont(font);
			g2.setColor(txtColor);
			FontMetrics fm = g2.getFontMetrics();
			Rectangle2D rect = fm.getStringBounds(word, g2);
			int x = (int) (getWidth()/2 - rect.getWidth()/2);
			int y = (int) (getHeight()/2 + fm.getAscent()/2);
			g2.drawString(coloredWord.getIterator(), x, y);
		}

		float halfLine = lineWidth/2;
		Rectangle2D box = new Rectangle2D.Float(halfLine, halfLine, getWidth()-lineWidth, getHeight()-lineWidth);
		g2.setStroke(new BasicStroke(lineWidth));
		g2.draw(box);
		float markerPos = getWidth()*0.3f;
		Line2D marker = new Line2D.Float(0f, 0f, 0f, getHeight()*0.2f);
		g2.translate(markerPos, 0);
		g2.draw(marker);
		g2.translate(0, getHeight()*0.8f);
		g2.draw(marker);
	}
}
