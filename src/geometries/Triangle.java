package geometries;

import primitives.*;

/**
 * 
 * A triangle is a three sided polygon
 */
public class Triangle extends Polygon {
	/*
	 * 
	 * Constructor using three points
	 * 
	 * @param p0 the first vertex of the triangle
	 * 
	 * @param p1 the second vertex of the triangle
	 * 
	 * @param p2 the third vertex of the triangle
	 */
	public Triangle(Point p0, Point p1, Point p2) {
		super(p0, p1, p2);
	}

	/**
	 * 
	 * Overrides the getNormal method from the Polygon class
	 * 
	 * @param point a point on the surface of the triangle
	 * @return the normal vector of the triangle at the specified point
	 */
	@Override
	public Vector getNormal(Point point) {
		// The normal of a point on the surface of a triangle is the normal of the plane containing the triangle
		return super.getNormal(point);
	}
}
