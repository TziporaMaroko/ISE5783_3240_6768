package primitives;
import java.math.*;
public class Point {
Double3 point;
public Point(double x,double y, double z)
{
	point= new Double3(x,y,z);
}
Point(Double3 p)
{
	point=p;
}
@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj instanceof Point other)
		return this.point.equals(other.point);
	return false;
}
@Override
public String toString() { return point.toString(); }

public Vector subtract(Point other)
{
	return new Vector(this.point.subtract(other.point));
}
public Point add(Vector v)
{
	return new Point(this.point.add(v.point));
}
public double distanceSquared(Point p)
{
	return (this.point.d1-p.point.d1)*(this.point.d1-p.point.d1)
			+(this.point.d2-p.point.d2)*(this.point.d2-p.point.d2)
			+(this.point.d3-p.point.d3)*(this.point.d3-p.point.d3);
}
public double distance(Point p)
{
	return Math.sqrt(distanceSquared(p));
}
}
