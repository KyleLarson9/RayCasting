package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Polygon {

	private double x, y;
	private double width, height;
	
	public Polygon(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fillRect((int) x, (int) y, (int) width, (int) height);
	}
	
	public Line2D.Double[] getLineComponents() {
		
		Line2D.Double[] lines = new Line2D.Double[4];
		lines[0] = new Line2D.Double(x, y, x + width, y);
	    // Right
	    lines[1] = new Line2D.Double(x + width, y, x + width, y + height);
	    // Bottom
	    lines[2] = new Line2D.Double(x + width, y + height, x, y + height);
	    // Left
	    lines[3] = new Line2D.Double(x, y + height, x, y);
	    
	    return lines;
	}
	
}
