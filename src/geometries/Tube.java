
package geometries;

import primitives.Vector;

import java.util.List;

import geometries.RadialGeometry;
import primitives.Point;
import primitives.Ray;


/**
 * The Tube class represents a tube in 3D Cartesian coordinate system.
 * The tube is represented by its radius and a @link Ray that passes through its center axis.
 */
public class Tube extends RadialGeometry {
    protected final Ray axisRay;

    /**
     * Constructs a new tube with the specified @link Ray and radius.
     * @param axisRay the @link Ray that passes through the center axis of the tube.
     * @param radius the radius of the tube.
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Returns the @link Ray that passes through the center axis of the tube.
     * @return the @link Ray that passes through the center axis of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns null since this method is not implemented for {@link Tube}.
     * @param p the point on the tube for which to calculate the normal.
     * @return null.
     */
	@Override
	public Vector getNormal(Point p) {
		double t=(axisRay.getDir()).dotProduct(p.subtract(axisRay.getP0()));
		Vector v=(axisRay.getDir()).scale(t);
		Point o=(axisRay.getP0()).add(v);
	    return (p.subtract(o)).normalize();
	}
	
	public List<Point> findIntersections(Ray ray){
    	return null;
    }
}

