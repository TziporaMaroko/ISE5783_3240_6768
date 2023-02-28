package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
private Point q0;
private Vector normal;
public Plane(Point p0,Point p1, Point p2)
{
	q0=p0;
	normal=null;
}
public Plane(Point p0,Vector v)
{
	q0=p0;
	normal=v.normalize();
}
public Vector getNormal() {
	return null;
}
	@Override
	public Vector getNormal(Point p) {
		return getNormal();
	}

}
