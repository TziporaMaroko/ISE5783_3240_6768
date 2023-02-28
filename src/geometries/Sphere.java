package geometries;
import primitives.*;
public class Sphere extends RadialGeometry{
private Point center;
public Sphere(Point p, double r)
{
	super(r);
	center=p;
}
public Point getCenter() {
	return center;
}
@Override
public Vector getNormal(Point p)
{
	return null;
}

}
