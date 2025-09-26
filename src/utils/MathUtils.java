package utils;

import java.awt.geom.Point2D;

public class MathUtils {

	public MathUtils() {
		
	}
	
	// fix my version  -- needs to be fixed
	public Point2D.Double calculateIntersection(double x1, double y1, double x2, double y2,
												double x3, double y3, double x4, double y4) {
		
		double delX1 = x2 - x1;
		double delY1 = y2 - y1;
		double delX2 = x4 - x3;
		double delY2 = y4 - y3;
	
		double denominator = (delX2*delY1) - (delX1*delY2);
		
		if(denominator == 0) {
			return null;
		}
		
		double t = (delX2*(y3-y1) - delY2*(x3-x1))/denominator;
		double u = (delX1*(y3-y1) - delY1*(x3-x1))/denominator;
		
		if(t >= 0 && u >= 0 && u <= 1) {
			double intersectionX = x1 + t*delX1;
			double intersectionY = y1 + t*delY1;
			
			return new Point2D.Double(intersectionX, intersectionY);
		}
		
		return null;
		
	}
	

	
}
