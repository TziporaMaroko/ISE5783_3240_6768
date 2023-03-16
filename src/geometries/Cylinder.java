package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

//A cylinder is a tube with a fixed height.
public class Cylinder extends Tube {
 private double height;

 // Constructor
 public Cylinder(double h, Ray r, double rad) {
     super(r, rad);
     height = h;
 }

 // Getter for height
 public double getHeight() {
     return height;
 }

 // Overrides the getNormal method from the Tube class
 @Override
 public Vector getNormal(Point p) {
     // The normal of a point on the surface of a cylinder is parallel to the axis of the cylinder
     return null; // Not yet implemented
 }
}
