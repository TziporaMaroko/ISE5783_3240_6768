package geometries;
import primitives.*;
//A triangle is a three-sided polygon
public class Triangle extends Polygon {
 // Constructor using three points
 public Triangle(Point p0, Point p1, Point p2) {
     super(p0, p1, p2);
 }

 // Overrides the getNormal method from the Polygon class
 @Override
 public Vector getNormal(Point point) {
     // The normal of a point on the surface of a triangle is the normal of the plane containing the triangle
     return null; // Not yet implemented
 }
}
