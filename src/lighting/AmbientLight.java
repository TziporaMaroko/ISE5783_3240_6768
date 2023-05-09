/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;

/**
 * @author tzipora and ester:)
 *
 */
public class AmbientLight {
	private Color intensity;

	/**
	 * constructor that save the intensity=Ia*Ka
	 * 
	 * @param Ia Color value
	 * @param Ka double value
	 */
	public AmbientLight(Color iA, Double3 kA) {
		intensity=iA.scale(kA);
	}

	/**
	 * constructor that save the intensity=Ia*Ka
	 * 
	 * @param Ka double value
	 */
	public AmbientLight(Double kA) {
		intensity=intensity.scale(kA);
	}
	
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	/**
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
