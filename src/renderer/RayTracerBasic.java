/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

/**
 * @author Tzipora and Ester
 *
 */
public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * Function that calculates the color for the nearest intersection point, if no
	 * intersection points are returned the color of the background
	 *
	 * @param ray Ray value
	 * @return Color
	 * @throws Exception
	 */
	public Color traceRay(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/**
	 * Function for calculating a point color
	 *
	 * @param point Point value
	 * @return Color
	 */
	private Color calcColor(GeoPoint point, Ray ray) {

		return scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray));
	}

	/**
	 * 
	 * Calculates the local effects (diffuse and specular) at a given intersection
	 * point with a ray.
	 * 
	 * @param gp  The intersection point and its associated geometry.
	 * @param ray The ray that intersected with the geometry.
	 * @return The color resulting from the local effects.
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Color color = gp.geometry.getEmission();
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return color;
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				Color iL = lightSource.getIntensity(gp.point);
				color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
			}
		}
		return color;
	}

	/**
	 * Calculate the diffuse light effect on the point
	 * 
	 * @param material       diffuse attenuation factor
	 * @param l              the direction of the light
	 * @param n              normal from the point
	 * @param lightIntensity the intensity of the light source at this point
	 * @return the color of the diffusive
	 */
	private Double3 calcDiffusive(Material material, double nl) {
		nl = Math.abs(nl);
		return material.kD.scale(nl); // Kd * |l * n| * Il
	}

	/**
	 * Calculate the specular factor and change the color by it, Light created by a
	 * special break of light.
	 * 
	 * @param material       specular attenuation factor
	 * @param l              the direction of the light
	 * @param n              normal from the point
	 * @param v              direction of the viewer
	 * @param lightIntensity the intensity of the light source at the point
	 * @return the color of the point
	 */
	private Double3 calcSpecular(Material material, Vector n, Vector l, Double nl, Vector v) {
		Vector r = l.subtract(n.scale(2 * nl)); // r=l-2*(l*n)*n
		double vr = Util.alignZero(v.dotProduct(r)); // vr=v*r
		double vrnsh = Math.pow(Math.max(0, -vr), material.nShininess); // vrnsh=max(0,-vr)^nshininess
		return material.kS.scale(vrnsh); // Ks * (max(0, - v * r) ^ Nsh) * Il
	}

}
