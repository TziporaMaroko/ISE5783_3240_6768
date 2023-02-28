package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
	private double height;
	public Cylinder(double h, Ray r, double rad)
	{
		super(r,rad);
		height=h;
	}
	public double getHeight() {
		return height;
	}
	@Override
	public Vector getNormal(Point p) {
		return null;
	}

}
