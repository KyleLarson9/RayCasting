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
		g2d.fillOval((int) (x - radius), (int) (y - radius), (int) radius*2, (int) radius*2);
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getRadius() {
		return radius;
	}
	
}
