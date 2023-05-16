/**
 * 
 */
package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * @author tzipora and ester:)
 *
 */
/**
 * 
 * This class represents a scene in a computer graphics environment.
 * 
 * It contains methods to set and retrieve the scene's properties.
 */
public class Scene {
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = AmbientLight.NONE;
	public Geometries geometries = new Geometries();

	/**
	 * 
	 * Constructs a Scene object with the specified name.
	 * 
	 * @param name The name of the scene.
	 */
	public Scene(String name) {
		this.name = name;
	}

	/**
	 * 
	 * Sets the background color of the scene.
	 * 
	 * @param background The background color to be set.
	 * @return The Scene object itself for method chaining.
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * 
	 * Sets the ambient light of the scene.
	 * 
	 * @param ambient The ambient light to be set.
	 * @return The Scene object itself for method chaining.
	 */
	public Scene setAmbientLight(AmbientLight ambient) {
		this.ambientLight = ambient;
		return this;
	}

	/**
	 * 
	 * Sets the geometries of the scene.
	 * 
	 * @param geo The geometries to be set.
	 * @return The Scene object itself for method chaining.
	 */
	public Scene setGeometries(Geometries geo) {
		this.geometries = geo;
		return this;
	}
}