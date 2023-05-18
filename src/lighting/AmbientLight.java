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
public class AmbientLight extends Light{

	/**
	 * constructor that save the intensity=Ia*Ka
	 * 
	 * @param Ia Color value
	 * @param Ka double value
	 */
	public AmbientLight(Color iA, Double3 kA) {
		super(iA.scale(kA));
	}

	/**
	 * constructor that save the intensity=Ia*Ka
	 * 
	 * @param Ka double value
	 */
	public AmbientLight(Color iA,Double kA) {
		super(iA.scale(kA));
	}
	
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
}
