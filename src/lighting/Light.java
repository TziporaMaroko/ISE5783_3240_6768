/**
 * 
 */
package lighting;

import primitives.Color;

/**
 * @author ester
 *
 */
abstract class Light {
	private Color intensity;

	protected Light(Color intens) {
		this.intensity = intens;
	}

	public Color getIntensity() {
		return intensity;
	}
}
