package utils;

import java.awt.geom.Point2D;

public class MathUtils {

	public MathUtils() {
		
	}
	
	public static Point2D.Double calculateIntersection(double x1, double y1, double x2, double y2,
												double x3, double y3, double x4, double y4) {
				
		double delX1 = x2-x1;
		double delX2 = x4-x3;
		double delY1 = y2-y1;
		double delY2 = y4-y2;
		
		double denominator = delX2*delY1 - delX1*delY2;
		
		if(denominator == 0) { // lines are parallel
			return null;
		}
		
		double t = -delY2*(x3-x1) + delX2*(y3-y1);
		double u = -delY1*(x3-x1) + delX1*(y3-y1);
		
		double intersectionX = x1 + t*(x2-x1);
		double intersectionY = y1 + u*(y2-y1);
		
		return new Point2D.Double(intersectionX, intersectionY);
	}
	
}
