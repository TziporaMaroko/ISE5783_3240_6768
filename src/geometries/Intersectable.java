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
	
	public static class GeoPoint {
		 public Geometry geometry;
		 public Point point;
		 
		 public GeoPoint(Geometry geometry,Point point)
		 {
			 this.geometry=geometry;
			 this.point=point;
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
	
	public List<Point> findIntersections(Ray ray) {
		 var geoList = findGeoIntersections(ray);
		 return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
		}

	/**
	 * Finding intersection-geoPoints with a given ray
	 * @param ray A ray
	 * @return All the intersection-geoPoints of this geometry and the given ray
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray)//NVI-non virtual interfaceI
	{
	
	    	return findGeoIntersectionsHelper(ray);
	    }
	
	/**
	 * abstract function to Finds intersection-geoPoints with a given ray
	 * @param ray A ray
	 * @return All the intersection-geoPoints of this geometry and the given ray
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray)//not private because of java defects
	;
}
