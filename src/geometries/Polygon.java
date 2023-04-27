package geometries;

import static primitives.Util.isZero;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon implements Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   private final int           size;

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

   
   
   
   
   
//   
//   
//   
//   /**
//	@Override
//	public List<Point> findIntersections(Ray ray) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	*/
//	@Override
//	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
//		Vector[] p0pi= new Vector[vertices.size()];
//		for (int i=0; i<vertices.size(); i++) {
//			try {
//				p0pi[i]=vertices.get(i).subtract(ray.getP0());
//			}
//			//if got here - it means that the ray starts on one of the polygon's vertices, so there are no intersections:
//			catch(IllegalArgumentException iae) {
//				return null;
//			}
//		}
//		Vector[] normals= new Vector[vertices.size()];
//		for (int i=0; i<p0pi.length-1; i++) {
//			try {
//				normals[i]=p0pi[i].crossProduct(p0pi[i+1]);
//			}
//			//if got here - it means that the ray is on the polygon's plane (continuation of one of the edges),
//			//so there are no intersections:
//			catch(IllegalArgumentException iae) {
//				return null;
//			}
//		}
//		//get the normal for the last vertex:
//		try {
//			normals[p0pi.length-1]=p0pi[p0pi.length-1].crossProduct(p0pi[0]);
//		}
//		//if got here - it means that the ray is on the polygon's plane (continuation of the edge),
//		//so there are no intersections:
//		catch(IllegalArgumentException iae) {
//			return null;
//		}
//		
//		
//		int sign=1; //helping variable - 1 if positive, 0 if negative
//		double vni=ray.getDir().dotProduct(normals[0]);
//		if(isZero(vni))
//			return null;
//		if(alignZero(vni)<0)
//			sign=0;
//		for (int i=1; i<normals.length; i++) {
//			vni=ray.getDir().dotProduct(normals[i]);
//			if(isZero(vni)||(sign==1 && alignZero(vni)<0)||(sign==0 && alignZero(vni)>0))
//				return null;
//		}
//		
//		
//		List<GeoPoint> intersections=plane.findGeoIntersectionsHelper(ray);
//		if(intersections==null)
//			return null;
//		else
//			return List.of(new GeoPoint(this,intersections.get(0).point));
//
//	}
//	
//	
//	
//	@Override
//	public List<Point> findIntersections(Ray ray) {
//	    // Find the intersection point with the plane
//	    List<Point> planeIntersections = plane.findIntersections(ray);
//	    if (planeIntersections == null) {
//	        // The ray does not intersect the plane
//	        return null;
//	    }
//
//	    // Check if the intersection point is inside the polygon
//	    Point intersection = planeIntersections.get(0);
//	    Vector rayDirection = ray.getDir();
//	    Vector edge, normal;
//	    Point vertex;
//	    int count = 0;
//
//	    for (int i = 0; i < size; i++) {
//	        vertex = vertices.get(i);
//	        edge = vertex.subtract(ray.getP0());
//	        normal = edge.crossProduct(rayDirection);
//
//	        // Check if the ray is parallel to this edge
//	        if (normal.isZero()) {
//	            // The ray is parallel to this edge, so we cannot determine if there is an intersection
//	            if (edge.dotProduct(rayDirection) > 0) {
//	                // The ray is outside the polygon (because it is parallel to an edge pointing away from the polygon)
//	                return null;
//	            }
//	            // Otherwise, continue to the next edge
//	            continue;
//	        }
//
//	        // Check if the ray intersects this edge
//	        List<Point> intersections = new Vector(vertex, edge).findIntersections(ray);
//	        if (intersections != null) {
//	            Point intersectionPoint = intersections.get(0);
//	            // Check if the intersection point is between the ray start and intersection with the plane
//	            if (intersectionPoint.subtract(ray.getP0()).dotProduct(rayDirection) > 0 &&
//	                intersectionPoint.subtract(intersection).dotProduct(rayDirection) < 0) {
//	                count++;
//	            }
//	        }
//	    }
//
//	    if (count % 2 == 0) {
//	        // The intersection point is outside the polygon
//	        return null;
//	    }
//
//	    // The intersection point is inside the polygon
//	    return List.of(intersection);
//	}
 
	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> intersections = plane.findIntersections(ray);
		if (intersections == null)
			return null;
		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		
		Vector normal = v1.crossProduct(v2).normalize();

		double sign=normal.dotProduct(ray.getDir());
		boolean sameSign=sign>0;
		if(isZero(sign))
			return null;
		for(int i=2;i<vertices.size();i++)
		{
			v1=v2;
			v2=vertices.get(i).subtract(ray.getP0());
			normal = v1.crossProduct(v2).normalize();
			sign=normal.dotProduct(ray.getDir());
			if(isZero(sign))
				return null;
			if(sameSign!=(sign>0))
				return null;
			sameSign=sign>0;
		}
		v1=v2;
		v2=vertices.get(0).subtract(ray.getP0());
		normal = v1.crossProduct(v2).normalize();
		sign=normal.dotProduct(ray.getDir());
		if(isZero(sign))
			return null;
		if(sameSign!=(sign>0))
			return null;
		sameSign=sign>0;
	return intersections;
	}   
   
}
