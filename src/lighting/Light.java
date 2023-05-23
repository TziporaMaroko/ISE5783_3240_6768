/**
 * 
 */
package lighting;

import primitives.Color;

/**
 * @author tzipora and ester
 *
 */
abstract class Light {
	private Color intensity;

	/**
	 * Constructs a Light object with the specified intensity.
	 * 
	 * @param intens The intensity of the light.
	 */
	protected Light(Color intens) {
		this.intensity = intens;
	}

	/**
	 * Returns the intensity of the light.
	 * 
	 * @return The intensity of the light.
	 */
	public Color getIntensity() {
		return intensity;
	}
}
