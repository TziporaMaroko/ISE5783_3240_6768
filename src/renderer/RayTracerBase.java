/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author Tzipora and Ester
 *
 */
public abstract class RayTracerBase {
	protected Scene scene;
	
	public RayTracerBase(Scene scene) {
		this.scene= scene;
	}
	
	public abstract Color traceRay(Ray ray);
	
}
