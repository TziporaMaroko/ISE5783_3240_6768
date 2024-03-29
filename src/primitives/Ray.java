package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import geometries.Intersectable;

/**
 * Represents a ray in 3D space, consisting of a starting point and a direction
 * vector.
 */
public class Ray {
	private final Point p0; // the starting point of the ray
	private final Vector dir; // the direction vector of the ray, normalized
	private static final double DELTA = 0.1;

	/**
	 * Constructs a new ray from a starting point and a direction vector.
	 *
	 * @param p the starting point of the ray
	 * @param v the direction vector of the ray
	 */
	public Ray(Point p, Vector v) {
		p0 = p;
		dir = v.normalize();
	}

	/**
	 * Constructs a new Ray object.
	 *
	 * @param p      The starting point of the ray.
	 * @param v      The direction vector of the ray.
	 * @param normal The normal vector.
	 */
	public Ray(Point p, Vector v, Vector normal) {
		dir = v.normalize(); // Normalize the direction vector to ensure it has a unit length

		// Calculate the delta vector based on the dot product of the normal and v
		// vectors
		// If normal and v are in the same direction, scale normal by DELTA, otherwise
		// scale by -DELTA
		Vector delta = normal.scale(normal.dotProduct(v) > 0 ? DELTA : -DELTA);

		p0 = p.add(delta); // Move the starting point p by delta to get the updated starting point p0
	}

	/**
	 * Returns the starting point of the ray.
	 *
	 * @return the starting point of the ray
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * Returns the direction vector of the ray, normalized.
	 *
	 * @return the direction vector of the ray
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * a function that returns a vector that scale
	 * 
	 * @param t
	 * @return vector
	 */
	public Point getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	/**
	 * Checks whether this ray is equal to the specified object.
	 *
	 * @param obj the object to compare to
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Ray other)
			return this.p0.equals(other.p0) && this.dir.equals(other.dir);
		return false;
	}

	/**
	 * Returns a string representation of this ray.
	 *
	 * @return a string representation of this ray
	 */
	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

	/**
	 * 
	 * Finds the closest point from a given list of points to a reference point.
	 * 
	 * @param points The list of points to search from.
	 * 
	 * @return The closest point to the reference point, or null if the list is
	 *         empty or null.
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * 
	 * Finds the closest GeoPoint from a given list of GeoPoints.
	 * 
	 * @param points A list of GeoPoints to find the closest point from.
	 * 
	 * @return The closest GeoPoint from the list, or null if the list is null or
	 *         empty.
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
		// Check if the list is null or empty
		if (points == null || points.isEmpty())
			return null;

		// Initialize the minimum point and minimum distance with the first point
		GeoPoint minPoint = points.get(0);
		double minDis = p0.distance(minPoint.point);

		double tmp;

		// Iterate through the points and find the closest one
		for (int i = 1; i < points.size(); i++) {
			tmp = p0.distance(points.get(i).point);
			if (tmp < minDis) {
				minDis = tmp;
				minPoint = points.get(i);
			}
		}
		return minPoint;
	}
}
