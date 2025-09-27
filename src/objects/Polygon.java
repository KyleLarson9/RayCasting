package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Polygon {

	private double x, y;
	private double radius;
	private int sides;
	
	private Line2D.Double[] lines;
	
	public Polygon(double x, double y, double radius, int sides) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.sides = sides;
		this.lines = new Line2D.Double[sides];
		
		createPolygon();

	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.red);

		for(Line2D line : lines) {
			g2d.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
		}
	}
	
	private void createPolygon() {
		
		double deltaAngle = Math.toRadians(360/sides); 
		
		// Calculte polygon points
		Point2D.Double[] points = new Point2D.Double[sides];
		
		for(int i = 0; i < sides; i++) {
			double angle = i * deltaAngle;
			
			double endX = x + Math.cos(angle + Math.toRadians(45)) * radius;
			double endY = y + Math.sin(angle + Math.toRadians(45)) * radius;
			
			points[i] = new Point2D.Double(endX, endY);
		}
		
		// Build polygon lines
		for(int i = 0; i < points.length; i++) {
			Point2D.Double point1 = points[i];
			Point2D.Double point2 = points[(i + 1) % sides]; // wrap to first point at the end
			
		
			lines[i] = new Line2D.Double(point1.x, point1.y, point2.x, point2.y);
		}
		
	}
	
	public void addLineComponentsToWalls(ArrayList<Wall> walls) {
		
		for(Line2D.Double line : lines) {
			walls.add(new Wall(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
		}
		
	}
	
	public Line2D.Double[] getLineComponents() {
		   
	    return lines;
	}
	
}
