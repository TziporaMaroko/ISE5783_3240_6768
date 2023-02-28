package geometries;

public abstract class RadialGeometry implements Geometry {
	protected double radius;
	public RadialGeometry(double rad)
	{
		radius=rad;
	}
}
