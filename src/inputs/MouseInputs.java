package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objects.Source;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private Source source;
	
	private boolean dragging = false;
	
	private double offsetX, offsetY;
	
	public MouseInputs(Source source) {
		this.source = source;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		double dx = e.getX() - source.getX();
		double dy = e.getY() - source.getY();
		double distance = Math.sqrt(dx*dx + dy*dy);
		
		if(distance <= source.getRadius()) {
			dragging = true;
			offsetX = e.getX() - source.getX();
			offsetY = e.getY() - source.getY();
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(dragging) {
			
			source.setX(e.getX() - offsetX); 
			source.setY(e.getY() - offsetY);
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	
	
}
