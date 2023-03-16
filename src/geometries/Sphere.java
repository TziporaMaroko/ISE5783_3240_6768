package geometries;
import primitives.*;
//A sphere is a three-dimensional shape that is perfectly round.
public class Sphere extends RadialGeometry {
 private Point center;

 // Constructor
 public Sphere(Point p, double r) {
     super(r);
     center = p;
 }

 // Getter for center
 public Point getCenter() {
     return center;
 }

 // Overrides the getNormal method from the RadialGeometry class
 @Override
 public Vector getNormal(Point p) {
     // The normal of a point on the surface of a sphere is a vector from the center of the sphere to that point
     return null; // Not yet implemented
 }
}
