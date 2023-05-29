/**
 * 
 */
package primitives;

/**
 * @author ester and tzipora
 *
 */
public class Material {
	public Double3 kD = Double3.ZERO, kS = Double3.ZERO, kT=Double3.ZERO, kR=Double3.ZERO;
	public int nShininess = 0;

	/**
	 * Sets the diffuse reflection coefficient of the material.
	 * 
	 * @param kD The diffuse reflection coefficient as a Double3 representing the
	 *           RGB values.
	 * @return The updated Material object.
	 */
	public Material setkD(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a Double3 representing the
	 *           RGB values.
	 * @return The updated Material object.
	 */
	public Material setkS(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Sets the diffuse reflection coefficient of the material.
	 * 
	 * @param kD The diffuse reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setkD(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setkS(double kS) {
		this.kS = new Double3(kS);
		return this;
	}
	
	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setKt(double kT) {
		this.kT = new Double3(kT);
		return this;
	}
	
	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setKr(double kR) {
		this.kR = new Double3(kR);
		return this;
	}
	
	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setKt(Double3 kT) {
		this.kT = kT;
		return this;
	}
	
	/**
	 * Sets the specular reflection coefficient of the material.
	 * 
	 * @param kS The specular reflection coefficient as a double value for all RGB
	 *           channels.
	 * @return The updated Material object.
	 */
	public Material setKr(Double3 kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * Sets the shininess value of the material.
	 * 
	 * @param nShininess The shininess value representing the material's smoothness.
	 * @return The updated Material object.
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
