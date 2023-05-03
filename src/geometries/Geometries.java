package geometries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import primitives.Point;
import primitives.Ray;

/**
 * @author ester & tzipora 😎
 *
 */
public class Geometries implements Intersectable {
	
	private List<Intersectable> geometries;
	
	/**
	 * 
	 * Constructs a composite of shapes
	 *
	 */
	public Geometries() {
		geometries = new LinkedList<Intersectable>();
	}
	
	/**
	 * Constructor that receives list of geometries and put them in new arrayList
	 * @param geometries
	 * */
	public Geometries(Intersectable... intersectables){
		geometries = new LinkedList<>();
        Collections.addAll(geometries, intersectables);
	}

	/**
	 * a function that adds a new geometry to the scene
	 * @param geometries
	 */
	public void add(Intersectable... intersectables){
		if (geometries != null)
		{
	        Collections.addAll(geometries,intersectables);
		}
	}
	
	@Override
	public List<Point> findIntersections(Ray ray) {
		
        List<Point> result = null;

        //iterate over the list of the geometries and find the intersections for each one
        //add the results to list "result"
        for (Intersectable item : geometries) {
            List<Point> intersection = item.findIntersections(ray);
            if (intersection != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
            	result.addAll(intersection);
            }
        }
        return result;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(geometries);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometries other = (Geometries) obj;
		return Objects.equals(geometries, other.geometries);
	}

}
