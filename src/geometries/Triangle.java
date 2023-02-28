package geometries;
import primitives.*;
public class Triangle extends Polygon {
public Triangle(Point p0, Point p1, Point p2)
{
	super(p0,p1,p2);
}
@Override
public Vector getNormal(Point point) { return null; }
}
