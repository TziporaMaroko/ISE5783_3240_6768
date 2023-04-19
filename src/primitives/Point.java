package primitives;

/**
 * Represents a point in 3D space using Cartesian coordinates (x, y, z).
 */
public class Point {
    protected final Double3 xyz;

    /**
     * Constructs a new point with the given Cartesian coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new point with the given 3D vector.
     * @param p The vector representing the point.
     */
    Point(Double3 p) {
        xyz = p;
    }

    /**
     * Checks whether this point is equal to another object.
     * @param obj The object to compare to.
     * @return true if the object is a point with the same coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    /**
     * Returns a string representation of this point in the form (x, y, z).
     * @return The string representation of this point.
     */
    @Override
    public String toString() {
        return xyz.toString();
    }
    
    public double getX()
    {
    	return xyz.d1;
    }
    
    public double getY()
    {
    	return xyz.d2;
    }
    
    public double getZ()
    {
    	return xyz.d3;
    }

    /**
     * Computes the vector from another point to this point.
     * @param other The other point.
     * @return The vector from the other point to this point.
     */
    public Vector subtract(Point other) {
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Computes the point resulting from adding a vector to this point.
     * @param v The vector to add.
     * @return The resulting point after adding the vector.
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Computes the squared distance from this point to another point.
     * @param p The other point.
     * @return The squared distance from this point to the other point.
     */
    public double distanceSquared(Point p) {
        return (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1)
                + (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2)
                + (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
    }

    /**
     * Computes the distance from this point to another point.
     * @param p The other point.
     * @return The distance from this point to the other point.
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
    
}