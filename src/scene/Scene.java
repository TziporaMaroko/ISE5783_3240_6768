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
public class Scene {
	public String name;
	public Color background=Color.BLACK;
	public AmbientLight ambientLight=AmbientLight.NONE;
	public Geometries geometries = new Geometries();
	public Scene(String name)
	{
		this.name=name;
	}
	
	public Scene setBackground(Color background)
	{
		this.background=background;
		return this;
	}
	
	public Scene setAmbientLight(AmbientLight ambient)
	{
		this.ambientLight=ambient;
		return this;
	}
	
	public Scene setGeometries(Geometries geo)
	{
		this.geometries=geo;
		return this;
	}
}
