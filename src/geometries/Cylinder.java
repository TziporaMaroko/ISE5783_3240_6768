package geometries;

import java.util.List;

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
    public Vector getNormal(Point p){
    	Point topCenter= axisRay.getP0().add(axisRay.getDir().scale(height));
    	//if the point is on the center of one of the caps, the normal vector should point outward from the caps
        if(p.equals(axisRay.getP0())||p.equals(topCenter)) {
        	return axisRay.getDir().normalize();
        }
    	// If the point is on one of the caps, the normal vector should point outward from the caps
        Vector checkBottom= p.subtract(axisRay.getP0());
        Vector checkTop= p.subtract(topCenter);
        if((checkBottom.length()<radius && checkBottom.dotProduct(axisRay.getDir())==0)
        		|| (checkTop.length()<radius && checkTop.dotProduct(axisRay.getDir())==0)) {
        	return axisRay.getDir().normalize();
        }
        //else- the point is on the sides
        return super.getNormal(p);
    }
    public List<Point> findIntersections(Ray ray){
    	return null;
    }
}
