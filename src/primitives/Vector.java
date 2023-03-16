package primitives;

/** This class will represent a vector in 3D space */
public class Vector extends Point {
    /**
     * Creates a new vector with the specified x, y, and z components.
     * Throws an IllegalArgumentException if the vector is the zero vector.
     *
     * @param x The x-component of the vector
     * @param y The y-component of the vector
     * @param z The z-component of the vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) // check that the vector is not the zero vector
            throw new IllegalArgumentException("zero vector");
    }

    /**
     * Creates a new vector from a Double3 object.
     * Throws an IllegalArgumentException if the vector is the zero vector.
     *
     * @param p The Double3 object to create the vector from
     */
    Vector(Double3 p) {
        super(p);
        if (xyz.equals(Double3.ZERO)) // check that the vector is not the zero vector
            throw new IllegalArgumentException("zero vector");
    }

    /**
     * Returns true if this vector is equal to the specified object.
     *
     * @param obj The object to compare to this vector
     * @return True if the object is a vector with the same x, y, and z components as this vector
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns a string representation of this vector.
     *
     * @return A string representation of this vector in the form (x, y, z)
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns a new vector that is the sum of this vector and the specified vector.
     *
     * @param v The vector to add to this vector
     * @return A new vector that is the sum of this vector and the specified vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Returns a new vector that is the product of this vector and a scalar value.
     *
     * @param a The scalar value to multiply this vector by
     * @return A new vector that is the product of this vector and the scalar value
     */
    public Vector scale(double a) {
        return new Vector(xyz.scale(a));
    }

    /**
     * Returns the dot product of this vector and the specified vector.
     *
     * @param v The vector to dot product with this vector
     * @return The dot product of this vector and the specified vector
     */
    public Double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * Returns a new vector that is the cross product of this vector and the specified vector.
     *
     * @param v The vector to cross product with this vector
     * @return A new vector that is the cross product of this vector and the specified vector
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }
    /**
     * Returns the squared length of this vector.
     * This is a useful optimization when the actual length is not needed, 
     * as it avoids the expensive square root calculation.
     * 
     * @return The squared length of this vector.
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Returns the length of this vector.
     * 
     * @return The length of this vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns a new vector that is a normalized version of this vector.
     * This vector is not modified.
     * 
     * @return A new vector that is a normalized version of this vector.
     * @throws IllegalArgumentException if this vector has a length of zero.
     */
    public Vector normalize() {
        double len = length();
        if (len == 0)
            throw new IllegalArgumentException("Cannot normalize a zero-length vector.");
        return new Vector(xyz.reduce(len));
    }
}
