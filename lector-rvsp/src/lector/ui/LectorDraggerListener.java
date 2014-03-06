package lector.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class LectorDraggerListener implements MouseListener, MouseMotionListener
{
    private JFrame frame;
    private Point offset;

    public LectorDraggerListener(JFrame _frame) {
        frame = _frame;
    }
    
    @Override public void mousePressed(MouseEvent e) {
    	offset = e.getPoint();
    }
    
    @Override public void mouseDragged(MouseEvent e) {
    	Point mouse = e.getLocationOnScreen();
        frame.setLocation(mouse.x-offset.x, mouse.y-offset.y);
    }
    
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}