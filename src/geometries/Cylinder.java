package geometries;

import primitives.*;

/**
 * The Cylinder class represents a cylinder in 3D Cartesian coordinate system.
 * A cylinder is defined by its axisRay, height and radius.
 */
public class Cylinder extends Tube {
    private double height;

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
        return null;
    }
}
