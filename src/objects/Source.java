package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import main.Simulation;
import utils.MathUtils;

public class Source {

	private Simulation sim;
	private MathUtils mathUtils;
	
	private double x, y;
	private double radius;
	
	private double maxRayLength = 1000;
	private double currentRayLength = 0 + 10000;
	private double growthSpeed = 1;
	
	private double totalRays = 100;
	
	public Source(double x, double y, double radius, Simulation sim) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.sim = sim;
		
		mathUtils = new MathUtils();
	}
	
	public void update() {
		
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillOval((int) (x - radius), (int) (y - radius), (int) radius*2, (int) radius*2);
		
		rays(g2d);
		
	}
	
	// fix mine
	public void rays(Graphics2D g2d) {
		
		int totalAngle = 360;
		
		double startAngle = Math.toRadians(0);
		double endAngle = Math.toRadians(360);
		double angularSeperation = Math.toRadians(totalAngle/totalRays);
		
		for(double angle = startAngle; angle < endAngle; angle+=angularSeperation) {
			
			g2d.setColor(Color.white);
			
			Point2D.Double closestPoint = null;
			double closestDistance = Double.MAX_VALUE;

			double rayEndX = x + Math.cos(angle) * currentRayLength;
			double rayEndY = y + Math.sin(angle) * currentRayLength;
						
			// Loop through each wall and calculate its intersection point
			for(Wall wall : sim.getWalls()) {
				Point2D.Double point = mathUtils.calculateIntersection(
						x, y, 
						rayEndX, rayEndY,
						wall.getX1(), wall.getY1(), 
						wall.getX2(), wall.getY2());
				
				if(point != null) {
					double distance = point.distance(x, y);
					if(distance < closestDistance) {
						closestDistance = distance;
						closestPoint = point;
					}
				} 
				
			}
			
			if(closestPoint == null) { // if there was no intersection with any walls, draw max ray length
				closestPoint = new Point2D.Double(rayEndX, rayEndY);
			}
			
			g2d.drawLine((int) x, (int) y, (int) closestPoint.x, (int) closestPoint.y);
		}
		
	}
	
	
	// fixed by chatgpt -- understand why mine didn't work next
//	public void rays(Graphics2D g2d) {
//        int totalAngle = 360;
//        double angularSeparation = Math.toRadians(totalAngle / totalRays);
//
//        for (double angle = 0; angle < Math.toRadians(totalAngle); angle += angularSeparation) {
//            g2d.setColor(Color.white);
//
//            Point2D.Double closest = null;
//            double minDist = Double.MAX_VALUE;
//
//            double rayEndX = x + Math.cos(angle) * currentRayLength;
//            double rayEndY = y + Math.sin(angle) * currentRayLength;
//
//            for (Wall wall : sim.getWalls()) {
//                Point2D.Double point = mathUtils.calculateIntersection(
//                        x, y, rayEndX, rayEndY,
//                        wall.getX1(), wall.getY1(),
//                        wall.getX2(), wall.getY2()
//                );
//
//                if (point != null) {
//                    double dist = point.distance(x, y);
//                    if (dist < minDist) {
//                        minDist = dist;
//                        closest = point;
//                    }
//                }
//            }
//
//            if (closest == null) {
//                closest = new Point2D.Double(rayEndX, rayEndY);
//            }
//
//            g2d.drawLine((int) x, (int) y, (int) closest.x, (int) closest.y);
//        }
//	}
	
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
