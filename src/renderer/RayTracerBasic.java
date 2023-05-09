/**
 * 
 */
package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * @author Tzipora and Ester
 *
 */
public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * Function that calculates the color for the nearest intersection point, if no
	 * intersection points are returned the color of the background
	 *
	 * @param ray Ray value
	 * @return Color
	 * @throws Exception
	 */
	public Color traceRay(Ray ray) {
		List<Point> intersections = scene.geometries.findIntersections(ray);
		if (intersections == null)
			return scene.background;
		Point closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/**
	 * Function for calculating a point color
	 *
	 * @param point Point value
	 * @return Color
	 */
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}
}
