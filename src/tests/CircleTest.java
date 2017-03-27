package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.junit.Before;
import org.junit.Test;

import game.Circle;
/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class CircleTest {

	public Circle c, c1, c2, c3, c4;
	public double radius, radius4;
	public Point2D.Double center, center4;
	
	@Before
	public void init(){
		radius = 3.0;
		center = new Point2D.Double(0.0 , 0.0);
		
		radius4 = -3.0;
		center4 = new Point2D.Double(4,4);
		
		c = new Circle(radius,center);
		c1 = new Circle(3.0,new Point2D.Double(4,0));
		c2 = new Circle(3.0,new Point2D.Double(0,6));
		c3 = new Circle(1.0,new Point2D.Double(0,5));
		c4 = new Circle(radius4,center4);
		
		c.setRadius(radius);
		c4.setRadius(radius4);
	}
	
	@Test
	public void test() {
		
		assertEquals(0, c4.getRadius(),0.0);
		assertEquals(3.0, c.getRadius(),0.0);
		
		assertEquals(center4, c4.getCenter());
		
		assertEquals(0, c4.getRadiusSq(), 0.0);
		assertEquals(9.0, c.getRadiusSq(), 0.0);
	}
	
	@Test
	public void intersectCircle(){
		
		assertEquals(true, c.intersects(c1));
		assertEquals(false, c.intersects(c2));
		assertEquals(false, c.intersects(c3));
		assertEquals(false, c.intersects(c4));
	}
	
	@Test
	public void intersectRect(){
		Rectangle2D.Double rect = new Rectangle2D.Double(2,4,6,6);
		
		assertEquals(false, c.intersects(rect));
		assertEquals(false, c1.intersects(rect));
		
		assertEquals(center, center.clone());
		assertNotSame(c, c.clone());
	}

}
