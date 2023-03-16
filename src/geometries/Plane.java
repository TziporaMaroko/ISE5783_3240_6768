package geometries;

import primitives.Point;
import primitives.Vector;

//A plane is a flat surface that extends infinitely in all directions.
public class Plane implements Geometry {
 private Point q0; // A point on the plane
 private Vector normal; // The plane's normal vector

 // Constructor using three points on the plane
 public Plane(Point p0, Point p1, Point p2) {
     q0 = p0;
     normal = null; // Not yet initialized
 }

 // Constructor using a point on the plane and a normal vector
 public Plane(Point p0, Vector v) {
     q0 = p0;
     normal = v.normalize(); // Normalizes the vector to make it a unit vector
 }

 // Getter for the normal vector
 public Vector getNormal() {
     return null; // Not yet implemented
 }

 // Overrides the getNormal method from the Geometry interface
 @Override
 public Vector getNormal(Point p) {
     // The normal of a point on a plane is the same as the normal of the plane itself
     return getNormal();
 }
}

