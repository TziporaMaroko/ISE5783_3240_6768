/**
 * 
 */
package primitives;

public class Vector extends Point
{
public Vector(double x,double y, double z)
{
	super(x,y,z);
	if(xyz.equals(Double3.ZERO))
		throw new IllegalArgumentException("zero vector");
	
}
Vector(Double3 p)
{
	super(p);
	if(xyz.equals(Double3.ZERO))
		throw new IllegalArgumentException("zero vector");
}
@Override
public boolean equals(Object obj) 
{
	return super.equals(obj);
}
@Override
public String toString() { return super.toString(); }
public Vector add(Vector v)
{
	return new Vector(xyz.add(v.xyz));
}
public Vector scale(double a) {
	return new Vector(xyz.scale(a));
}
public Double dotProduct(Vector v) {
	return xyz.d1*v.xyz.d1+xyz.d2*v.xyz.d2+xyz.d3*v.xyz.d3;
}
public Vector crossProduct(Vector v) {
	return new Vector(xyz.d2*v.xyz.d3-xyz.d3*v.xyz.d2,
			xyz.d3*v.xyz.d1-xyz.d1*v.xyz.d3,
			xyz.d1*v.xyz.d2-xyz.d2*v.xyz.d1);
}
public double lengthSquared()
{
	return dotProduct(this);
}
public double length()
{
	return Math.sqrt(lengthSquared());
}
public Vector normalize()
{
	return new Vector(xyz.reduce(length()));
}
}
