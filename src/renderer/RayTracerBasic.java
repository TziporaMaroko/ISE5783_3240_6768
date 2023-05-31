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
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final Double3 INITIAL_K =new Double3(1.0);
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
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * A function that calculates the refracted rays.
	 * 
	 * @param normal Vector value
	 * @param point  Point3D value
	 * @param ray    Ray value
	 * @return ray for refracted
	 */
	private Ray constructRefractedRay(Vector normal, Point point, Vector v) {
		return new Ray(point, v, normal);
	}

	/**
	 * A function that calculates the reflected rays.
	 * 
	 * @param normal Vector value
	 * @param point  Point3D value
	 * @param v    Vector value
	 * @return ray for reflected
	 */
	private Ray constructReflectedRay(Vector normal, Point point, Vector v) {
		// Vector v = ray.getDir();
		double nv = Util.alignZero(normal.dotProduct(v));
		if (Util.isZero(nv))
			return null;//no reflection ray as in presentation
		Vector r = v.subtract(normal.scale(nv * 2));
		return new Ray(point, r, normal);
	}

	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Recursive function for calculating the color of a point by considering local and global effects.
	 *
	 * @param intersection The intersection point on a geometry object.
	 * @param ray          The ray that intersected the geometry object.
	 * @param level        The current recursion level.
	 * @param k            The accumulated transparency coefficient.
	 * @return The color of the point.
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
		Color color = calcLocalEffects(intersection, ray, k);
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));

	}
	
	/**
	 * Calculates the global effects on a point by considering reflections and refractions.
	 *
	 * @param gp    The intersection point on a geometry object.
	 * @param ray   The ray that intersected the geometry object.
	 * @param level The current recursion level.
	 * @param k     The accumulated transparency coefficient.
	 * @return The color of the global effects on the point.
	 */
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		return calcColorGlobalEffect(constructReflectedRay(n, gp.point, v), level, k, material.kR)
				.add(calcColorGlobalEffect(constructRefractedRay(n,gp.point, v), level, k, material.kT));
	}

	/**
	 * Calculates the color contribution from a reflected or refracted ray at a specific recursion level.
	 *
	 * @param ray   The reflected or refracted ray.
	 * @param level The current recursion level.
	 * @param k     The accumulated transparency coefficient.
	 * @param kx    The material reflection or refraction coefficient.
	 * @return The color contribution from the ray.
	 */
	private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) 
	{
		Double3 kkx = k.product(kx);
		if (kkx.lowerThan(MIN_CALC_COLOR_K)) 
			return Color.BLACK;
		GeoPoint gp = findClosestIntersection(ray);
		if (gp == null) 
			return scene.background.scale(kx);
		return Util.isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx);
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
	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		Double3 ddd=new Double3(MIN_CALC_COLOR_K);
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
				
					Double3 ktr = transparency(gp,l,n,nv,lightSource);
				if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K)||ktr.product(k).equals(ddd))) {
					Color iL = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(iL.scale(calcDiffusive(material, nl)),
							iL.scale(calcSpecular(material, n, l, nl, v)));
				}
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
	private static Double3 calcDiffusive(Material material, double nl) {
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
	

//	private boolean unshaded(GeoPoint gp, Vector l, Vector n, double nv, LightSource light) {
//		Vector lightDirection = l.scale(-1); // from point to light source
//		Ray lightRay = new Ray(gp.point, lightDirection,n);
//		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
//		if (intersections == null)
//			return true;
//		double lightDistance = light.getDistance(gp.point);
//		for (GeoPoint gp1 : intersections) {
//			if (Util.alignZero(gp1.point.distance(gp.point) - lightDistance) <= 0 && gp1.geometry.getMaterial().kT==Double3.ZERO)
//				return false;
//		}
//		return true;
//	}

	/**
	 * Calculates the transparency coefficient of a point for a specific light source.
	 *
	 * @param gp     The intersection point on a geometry object.
	 * @param l      The direction from the point to the light source.
	 * @param n      The normal vector at the intersection point.
	 * @param nv     The dot product between the normal vector and the direction to the viewer.
	 * @param light  The light source.
	 * @return The transparency coefficient of the point for the light source.
	 */
	private Double3 transparency(GeoPoint gp, Vector l, Vector n, double nv, LightSource light) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection,n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return Double3.ONE;
		Double3 ktr=Double3.ONE;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint gp1 : intersections) {
			if (Util.alignZero(gp1.point.distance(gp.point) - lightDistance) <= 0)
			{
				ktr=ktr.product(gp1.geometry.getMaterial().kT);
				if(ktr.lowerThan(MIN_CALC_COLOR_K))
					return Double3.ZERO;
			}
			
		}
		return ktr;
	}
	
	
	/**
	 * A function that find the most closet point to the ray
	 * 
	 * @param ray Ray value
	 * @return the closet point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

}
