package geometries;

import primitives.*;
import primitives.Vector;

public class Tube extends RadialGeometry {
protected Ray axisRay;
public Tube(Ray r,double radius)
{
	super(radius);
	axisRay=r;
}
	public Ray getAxisRay() {
		return axisRay;
	}
	@Override
	public Vector getNormal(Point p) {
		return null;
	}

}
