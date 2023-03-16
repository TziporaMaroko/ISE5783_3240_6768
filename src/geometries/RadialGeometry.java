package geometries;

//RadialGeometry is an abstract base class for all geometries that have a radius
public abstract class RadialGeometry implements Geometry {
 protected double radius;

 // Constructor
 public RadialGeometry(double rad) {
     radius = rad;
 }
}
