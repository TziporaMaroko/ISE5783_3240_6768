/**
 * 
 */
package geometries;

import java.util.List;
import java.util.Objects;

import primitives.*;

/**
 * @author ester
 *
 */
public abstract class Intersectable {
	/**
	 * The GeoPoint class represents a geometry object and its corresponding
	 * intersection point. It is a nested class within the Intersectable class.
	 * 
	 * This class overrides the equals() and toString() methods for proper
	 * comparison and display.
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point point;

		/**
		 * Constructs a GeoPoint object with the specified geometry and intersection
		 * point.
		 * 
		 * @param geometry The geometry object.
		 * @param point    The intersection point.
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			return Objects.equals(geometry, other.geometry) && Objects.equals(point, other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
	}

	/**
	 * Finds the intersection points of the geometry object with the given ray.
	 * 
	 * @param ray The ray to intersect with the geometry object.
	 * @return A list of intersection points between the geometry object and the
	 *         ray.
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * Finds the intersection-geoPoints of the geometry object with the given ray.
	 * 
	 * @param ray The ray to intersect with the geometry object.
	 * @return A list of GeoPoints representing the intersection-geoPoints between
	 *         the geometry object and the ray.
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray) {// NVI-non virtual interface
		return findGeoIntersectionsHelper(ray);
	}

	/**
	 * Abstract function to find the intersection-geoPoints with a given ray.
	 * 
	 * @param ray A ray.
	 * @return A list of GeoPoints representing the intersection-geoPoints between
	 *         the geometry object and the ray.
	 */

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray)// not private because of java defects(needs to be private cause of NVI
	;
}
