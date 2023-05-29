package geometries;

import java.util.List;

import primitives.*;

/**
 * A sphere is a three-dimensional shape that is perfectly round.
 */
public class Sphere extends RadialGeometry {
	private final Point center;

	/**
	 * 
	 * Constructor
	 * 
	 * @param p the center point of the sphere
	 * @param r the radius of the sphere
	 */
	public Sphere(Point p, double r) {
		super(r);
		center = p;
	}

	/**
	 * 
	 * Getter for center
	 * 
	 * @return the center point of the sphere
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * 
	 * Overrides the getNormal method from the RadialGeometry class
	 * 
	 * @param p the point on the surface of the sphere
	 * @return the normal vector of the sphere at point p
	 */
	@Override
	public Vector getNormal(Point p) {
		// The normal of a point on the surface of a sphere is a vector from the center of the sphere to that point
		return (p.subtract(center)).normalize();	
	}
	
	/**Finds the intersection-geoPoints between a ray and the sphere represented by this object.
	@param myRay The ray to intersect with the sphere.
	@return A list of GeoPoints representing the intersection-geoPoints between the ray and the plsphereane**/
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
		if (ray.getP0().equals(center)) // if the begin of the ray in the center, the point, is on the radius
			return List.of(new GeoPoint(this,ray.getPoint(radius)));
		Vector u = center.subtract(ray.getP0());
		double tm = Util.alignZero(ray.getDir().dotProduct(u));
		double d = Util.alignZero(Math.sqrt(u.lengthSquared()- tm * tm));
		double th = Util.alignZero(Math.sqrt(radius*radius - d*d));
		double t1 = Util.alignZero(tm+th);
		double t2 = Util.alignZero(tm-th);
		 
		
		if (d > radius)//the ray is out of the sphere
			return null; // there are no instructions

		
		if (t1 <=0 && t2<=0)//the ray begins after the sphere and goes to the opposite side
			return null;
		
		
		if (t1 > 0 && t2 >0)//2 intersections
		{
			//if(Util.alignZero(t1-maxDis)<=0&&Util.alignZero(t2-maxDis)<=0)
			return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
		}
		
		if (t1 > 0)//p0 is in the sphere
		{
			return List.of(new GeoPoint(this,ray.getPoint(t1)));
		}

		else//p0 is in the sphere
			return List.of(new GeoPoint(this,ray.getPoint(t2)));
    }
}