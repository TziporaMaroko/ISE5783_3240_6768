package geometries;

import java.util.List;

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
	
	/**Finds the intersection-geoPoints between a ray and the triangle represented by this object.
	@param myRay The ray to intersect with the triangle.
	@return A list of GeoPoints representing the intersection-geoPoints between the ray and the triangle**/
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> rayPoints = plane.findGeoIntersectionsHelper(ray);
		if (rayPoints == null)
			return null;
		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		rayPoints.get(0).geometry=this;
		//The point is inside if all  have the same sign (+/-)
		
		if (Util.alignZero(n1.dotProduct(ray.getDir())) > 0 && Util.alignZero(n2.dotProduct(ray.getDir())) > 0 && Util.alignZero(n3.dotProduct(ray.getDir())) > 0)
		{
			return rayPoints;
		}
		else if (Util.alignZero(n1.dotProduct(ray.getDir())) < 0 && Util.alignZero(n2.dotProduct(ray.getDir())) < 0 && Util.alignZero(n3.dotProduct(ray.getDir())) < 0)
		{
			return rayPoints;
		}
		if (Util.isZero(n1.dotProduct(ray.getDir())) || Util.isZero(n2.dotProduct(ray.getDir())) || Util.isZero(n3.dotProduct(ray.getDir())))
			return null; //there is no instruction point
		return null;//opposite signs
	}
}
