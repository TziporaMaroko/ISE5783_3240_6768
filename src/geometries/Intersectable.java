/**
 * 
 */
package geometries;
import java.util.List;

import primitives.*;

/**
 * @author ester
 *
 */
public interface Intersectable {
	public List<Point> findIntersections(Ray ray);
}
