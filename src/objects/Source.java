package objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Source {

	private double x, y;
	private double radius;
	
	private double maxRayLength = 1000;
	private double currentRayLength = 0;
	private double growthSpeed = 1;
	
	private double totalRays = 100;
	
	public Source(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void update() {
		
		if(currentRayLength <= maxRayLength) {
			currentRayLength += growthSpeed;
		}
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillOval((int) (x - radius), (int) (y - radius), (int) radius*2, (int) radius*2);
		
		rays(g2d);
		
	}
	
	public void rays(Graphics2D g2d) {
		
		int totalAngle = 360;
		
		double startAngle = Math.toRadians(0);
		double endAngle = Math.toRadians(360);
		double angularSeperation = Math.toRadians(totalAngle/totalRays);
		
		for(double angle = startAngle; angle < endAngle; angle+=angularSeperation) {
			g2d.setColor(Color.red);
			
			double endX = x + Math.cos(angle) * currentRayLength;
			double endY = y + Math.sin(angle) * currentRayLength;
			
			g2d.drawLine((int) x, (int) y, (int) endX, (int) endY);
		}
		
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
