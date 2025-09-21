package objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Source {

	private double x, y;
	private double radius;
	
	public Source(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillOval((int) x, (int) y, (int) radius, (int) radius);
	}
	
}
