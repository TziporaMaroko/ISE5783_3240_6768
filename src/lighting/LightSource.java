/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author ester & tzipora 🙃
 *
 */
public interface LightSource {
	public Color getIntensity(Point p);
	public Vector getL(Point p);


}
