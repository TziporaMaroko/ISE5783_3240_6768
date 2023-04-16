package geometries;

import primitives.*;

/**
 * The Cylinder class represents a cylinder in 3D Cartesian coordinate system .
 * A cylinder is defined by its axisRay, height and radius.
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a new Cylinder object with given axisRay, radius and height.
     *
     * @param axisRay The axisRay of the Cylinder
     * @param radius The radius of the Cylinder
     * @param height The height of the Cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * Returns the height of the Cylinder.
     *
     * @return The height of the Cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector to the cylinder at the given point.
     * 
     * @param p A point on the cylinder
     * @return The normal vector to the cylinder at the given point
     */
    @Override
    public Vector getNormal(Point p) {
    	 // Calculate the projection of the point onto the cylinder's axis ray
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        Point o = axisRay.getP0().add(axisRay.getDir().scale(t));

        // Calculate the normal vector from the point to the projection onto the axis ray
        Vector normal = p.subtract(o);

        // If the point is on one of the caps, the normal vector should point outward from the caps
        double distFromCap = p.distance(axisRay.getP0());
        if (distFromCap <= radius || distFromCap >= radius + height) {
            normal = normal.scale(-1);
        }

        return normal.normalize();
    }
}
