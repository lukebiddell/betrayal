package game;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle {

	private double radius;
	public Point2D.Double center;

	private double radiusSq;

	public void setRadius(double r) {
		double R = Math.max(r, 0);
		radius = R;
		radiusSq = R * R;
	}

	public double getRadius() {
		return radius;
	}

	public double getRadiusSq() {
		return radiusSq;
	}

	public Circle(double radius, Point2D.Double center) {
		setRadius(radius);
		this.center = center;
	}

	public boolean intersects(Circle c) {
		double dx = c.center.x - center.x;
		double dy = c.center.y - center.y;
		return (dx * dx + dy * dy < c.getRadiusSq() + getRadiusSq() + 2 * getRadius() * c.getRadius());
	}

	public boolean intersects(Rectangle2D.Double rect) {
		Rectangle2D.Double cToSquare = new Rectangle2D.Double(center.x - radius, center.y - radius, radius * 2,
				radius * 2);

		if (!cToSquare.intersects(rect))
			return false;

		Circle sToCircle = new Circle(rect.getWidth() / 2,
				new Point2D.Double(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2));

		return sToCircle.intersects(rect);
	}

	public boolean intersects(CircleSector s) {
		return s.intersects(this);
	}
}
