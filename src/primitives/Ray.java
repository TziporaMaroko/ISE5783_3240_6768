package primitives;

/**
 * Represents a ray in 3D space, consisting of a starting point and a direction vector.
 */
public class Ray {
    private final Point p0; // the starting point of the ray
    private final Vector dir; // the direction vector of the ray, normalized

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
        if (this == obj) return true;
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
}
