/**
 * 
 */
package renderer;

import java.util.List;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author Tzipora and Ester
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;
 
	/**
	 * Constructs a RayTracerBase object with the specified scene.
	 * 
	 * @param scene The scene to be rendered.
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Traces a ray and determines the color of the intersection point with the
	 * scene.
	 * 
	 * @param ray The ray to be traced.
	 * @return The color of the intersection point.
	 */
	public abstract Color traceRay(Ray ray);
	public abstract Color traceRay(List<Ray> rays);


}
