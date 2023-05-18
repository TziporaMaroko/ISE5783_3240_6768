/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * @author ester
 *
 */
public class PointLight extends Light implements LightSource {
	private Point position;
	private double kC=1, kL=0, kQ=0;

	public PointLight(Color iA, Point pos) {
		super(iA);
		position = pos;
	}

	@Override
	public Color getIntensity(Point p) {

		double dis=Util.alignZero(p.distance(position));
		if (Util.isZero(dis))
			return super.getIntensity();
		
		double denominator=Util.alignZero(kC+kL*dis+kQ*dis*dis);
		return super.getIntensity().reduce(denominator);
	}

	@Override
	public Vector getL(Point p) {
		if (p.equals(position))
			return null;
		return p.subtract(position).normalize();
	}

	public PointLight setKC(double kC) {
		this.kC = kC;
		return this;
	}

	public PointLight setKL(double kL) {
		this.kL = kL;
		return this;
	}

	public PointLight setKQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

}
