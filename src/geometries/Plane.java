package geometries;

import java.util.List;

import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * 
 * A plane is a flat surface that extends infinitely in all directions.
 */
public class Plane extends Geometry {
	private final Point q0; // A point on the plane
	private final Vector normal; // The plane's normal vector

	/**
	 * 
	 * Constructs a plane using three points on the plane.
	 * 
	 * @param p0 the first point on the plane
	 * @param p1 the second point on the plane
	 * @param p2 the third point on the plane
	 */
	public Plane(Point p0, Point p1, Point p2)/*throws Exception*/ {
		q0 = p0;
        if(p0==p1 || p1==p2 || p2==p0)
            throw new IllegalArgumentException("Two points converge");    
        Vector v1=p0.subtract(p1);
        Vector v2=p0.subtract(p2);
        if((v1.crossProduct(v2)).equals(Double3.ZERO))
    		throw new IllegalArgumentException("Two points or more exist in one line");
        normal=v1.crossProduct(v2).normalize();
    }


	/**
	 * 
	 * Constructs a plane using a point on the plane and a normal vector.
	 * 
	 * @param p0 the point on the plane
	 * @param v  the normal vector of the plane
	 */
	public Plane(Point p0, Vector v) {
		q0 = p0;
		normal = v.normalize(); // Normalizes the vector to make it a unit vector
	}

	/**
	 * 
	 * Gets the normal vector of the plane.
	 * 
	 * @return the normal vector of the plane
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * 
	 * Gets the normal vector of the plane at the specified point.
	 * 
	 * @param p the point to get the normal vector at
	 * @return the normal vector of the plane
	 */
	@Override
	public Vector getNormal(Point p) {
		// The normal of a point on a plane is the same as the normal of the plane itself
		return getNormal();
	}
	
	/**Finds the intersection-geoPoints between a ray and the plane represented by this object.
	@param myRay The ray to intersect with the plane.
	@return A list of GeoPoints representing the intersection-geoPoints between the ray and the plane**/
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray myRay) {
		double nv = normal.dotProduct(myRay.getDir());
		//The plane is parallel to the ray
		if (Util.isZero(nv))
		{
			return null;
		}
		
		try 
		{
			Vector qSubtractP0 = q0.subtract(myRay.getP0());
			double t = Util.alignZero((normal.dotProduct(qSubtractP0))/nv);

			if(t <= 0)//no intersections-the ray goes to the opposite side
			{
				return null;
			}
			 
			return List.of(new GeoPoint(this,myRay.getPoint(t)));
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
}
