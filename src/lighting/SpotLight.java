/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author ester & tzipora 😁
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;

	public SpotLight(Color iA, Point pos, Vector dir) {
		super(iA, pos);
		direction = dir;
	}
	
	@Override
	public Color getIntensity(Point p) {
		Vector l=super.getL(p);
		if(l==null)
			return super.getIntensity();
		double directionDotL=Util.alignZero(direction.dotProduct(l));
		if(directionDotL<=0)
			return Color.BLACK;
		
		return super.getIntensity(p).scale(directionDotL);//@#$#$%$&%$#$@#$%^&*^%%#@$%^&*^$#@!$%^&*^what about the mechane????????????????????
	}
}
