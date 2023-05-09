package unittests.renderer;
import primitives.*;
import renderer.Camera;
import java.util.LinkedList;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;

/**
 * @author tzipora & ester
 *
 */
class CameraRayIntegration {
	
    /**
     * Test helper function to count the intersections and compare with expected value
     *
     * @author
     * @param expected amount of intersections
     * @param cam camera for the test
     * @param geo 3D body to test the integration of the camera with
     * @param nX how many pixels in line
     * @param nY how many pixels in column
     */
    static private void assertCountIntersections(int expected, Camera cam, Intersectable geo, int nX, int nY) {
        int count = 0;

        cam.setVPSize(3, 3);
        cam.setVPDistance(1);

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                count += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void cameraRaySphereIntegration() {
        int nX = 3, nY = 3;
        Camera cam1 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(1)
                .setVPSize(3, 3);
        // TC01: Small Sphere 2 points
        assertCountIntersections(2, cam1, new Sphere(new Point(0, 0, -2.5), 1.0), nX, nY);

        // TC02: Big Sphere 18 points
        assertCountIntersections(18, cam1, new Sphere( new Point(0, 0, -2.5),2.5), nX, nY);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(10, cam1, new Sphere(new Point(0, 0, -2), 2.0),nX, nY);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(9, cam1, new Sphere(new Point(0, 0, -1), 4.0),nX, nY);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(0, cam1, new Sphere(new Point(0, 0, 1), 0.5),nX, nY);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        int nX = 3, nY = 3;
        Camera cam = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(1)
                .setVPSize(3, 3);

        // TC01: Plane against camera 9 points
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -2), new Vector(0, 0, 1)), nX, nY);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -1.5), new Vector(0, -0.5, 1)), nX, nY);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(6, cam, new Plane(new Point(0, 0, -3), new Vector(0, -1, 1)),nX, nY);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(0, cam, new Plane(new Point(0, -5, 0), new Vector(-1, -9, 13)),nX, nY);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        int nX = 3, nY = 3;
        Camera cam = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(1)
                .setVPSize(3, 3);

        // TC01: Small triangle 1 point
        assertCountIntersections(1, cam, new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),nX, nY);

        // TC02: Medium triangle 2 points
        assertCountIntersections(2, cam, new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),nX, nY);
    }
}
