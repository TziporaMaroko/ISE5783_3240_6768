/**
 * 
 */
package renderer;

import primitives.*;

import scene.Scene;

import static primitives.Util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import geometries.Geometries;

/**
 * @author tzipora and ester:)
 * 
 *         This class represents a camera in a 3D space.
 * 
 *         The camera defines the view of the scene and is responsible for
 *         generating rays that pass through the pixels
 * 
 *         in the view plane and into the scene.
 */
public class Camera {
	private Point p0; // The location of the camera in 3D space
	private Vector vUp; // The up vector of the camera
	private Vector vRight; // The right vector of the camera
	private Vector vTo; // The view vector of the camera
	private double width; // The width of the view plane
	private double height; // The height of the view plane
	private double distance; // The distance between the camera and the view plane

	private ImageWriter writer;
	private RayTracerBase rayTracer;
	private int numOfRays=1024;
	
	/**
     * numOfRaysForSuperSampling - number of rays for super sampling
     */
    private int maxRaysForSuperSampling = 1050;
	
	/**
	 * 
	 * Constructs a new camera.
	 * 
	 * @param p0  the location of the camera in 3D space.
	 * @param vTo the view vector of the camera. *
	 * @param vUp the up vector of the camera. *
	 * @throws IllegalArgumentException if the up vector is not orthogonal to the
	 *                                  view vector.
	 */
	public Camera(Point p0, Vector vTo, Vector vUp) {
		if (!isZero(vTo.dotProduct(vUp))) {
			throw new IllegalArgumentException("vUp is not orthogonal to vTo");
		}

		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = (vTo.crossProduct(vUp)).normalize();

		this.p0 = p0;
	}

	/**
	 * 
	 * Sets the sizes of the view plane. *
	 * 
	 * @param width  the width of the view plane.
	 * @param height the height of the view plane.
	 * @return the camera object.
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * Sets the distance between the camera and the view plane.
	 * 
	 * @param distance the distance between the camera and the view plane.
	 * @return the camera object.
	 */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * 
	 * Sets the image writer for the camera.
	 * 
	 * @param writer The image writer to be set.
	 * @return The Camera object itself for method chaining.
	 */
	public Camera setImageWriter(ImageWriter writer) {
		this.writer = writer;
		return this;
	}

	/**
	 * 
	 * Sets the ray tracer for the camera.
	 * 
	 * @param rayTracerBase The ray tracer to be set.
	 * @return The Camera object itself for method chaining.
	 */
	public Camera setRayTracer(RayTracerBase rayTracerBase) {
		this.rayTracer = rayTracerBase;
		return this;
	}
	
	/**
	 * A setter function for parameter rayTracer
	 * this function return the object - this for builder pattern
	 * 
	 * @param rayTracer RayTracerBase value
	 * */
	public Camera setNumOfRays(int numOfRays){
		if(numOfRays == 0)
			this.numOfRays=1;
		else
		 this.numOfRays = numOfRays;
		return this;
	}
	

	/**
	 * 
	 * Renders the image using the configured image writer and ray tracer.
	 * 
	 * Throws an exception if the image writer or ray tracer is not set.
	 */
//	public Camera renderImage() {
//		// Check if the image writer is set
//		if (writer == null)
//			throw new MissingResourceException("This function must have values in all the fields", "ImageWriter",
//					"imageWriter");
//
//		// Check if the ray tracer is set
//		if (rayTracer == null)
//			throw new MissingResourceException("This function must have values in all the fields", "RayTracerBase",
//					"rayTracer");
//
//		int nx = writer.getNx();
//		int ny = writer.getNy();
//
//		// Iterate over each pixel and cast rays
//		for (int i = 0; i < nx; i++) {
//			for (int j = 0; j < ny; j++) {
//				Color rayColor = castRay(j, i);
//				writer.writePixel(j, i, rayColor);
//			}
//		}
//		return this;
//	}

	/**
	 * 
	 * Casts a ray from the camera's position to the specified pixel coordinates.
	 * 
	 * @param j The horizontal pixel coordinate.
	 * @param i The vertical pixel coordinate.
	 * @return The color of the ray.
	 */
	private Color castRay(int j, int i) {
		Ray ray = constructRay(writer.getNx(), writer.getNy(), j, i);
		return rayTracer.traceRay(ray);
	}

	/**
	 * A function that creates a grid of lines
	 * 
	 * @param interval int value
	 * @param color    Color value
	 */
	public void printGrid(int interval, Color color) {
		if (writer == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter",
					"imageWriter");

		for (int i = 0; i < writer.getNx(); i++) {
			for (int j = 0; j < writer.getNy(); j++) {
				if (i % interval == 0 || j % interval == 0)
					writer.writePixel(i, j, color);
			}
		}

	}

	/**
	 * A function that finally creates the image. This function delegates the
	 * function of a class imageWriter
	 */
	public void writeToImage() {
		if (writer == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter",
					"imageWriter");
		writer.writeToImage();// delegation
	}

	/**
	 * Constructs a ray that passes through a specific pixel in the view plane.
	 * 
	 * @param nX the number of pixels in the x direction.
	 * @param nY the number of pixels in the y direction.
	 * @param j  the index of the pixel in the x direction.
	 * @param i  the index of the pixel in the y direction.
	 * @return a Ray object that passes through the specified pixel.
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pC;
		if (isZero(distance))
			pC = p0;
		else
			pC = p0.add(vTo.scale(distance));

		Point pIJ = pC;
		double rY = height / nY; // height of every pixel
		double rX = width / nX; // width of every pixel
		double yI = -(i - (nY - 1) / 2d) * rY;
		double xJ = (j - (nX - 1) / 2d) * rX;

		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));

		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));

		if (pIJ.equals(p0))// if distance is zero and piont axactly in the middle...
			return new Ray(p0, new Vector(pIJ.getX(), pIJ.getY(), pIJ.getZ()));
		return new Ray(p0, pIJ.subtract(p0));
	}
	
	/**
	 * The function transfers beams from camera to pixel, tracks the beam
	 *  and receives the pixel color from the point of intersection
	 * */
	public Camera renderImage() {
		if (writer == null)
			throw new MissingResourceException("this function must have values in all the fields", "ImageWriter", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("this function must have values in all the fields", "RayTracerBase", "rayTracer");
		
		for (int i = 0; i < writer.getNx(); i++)
		{
			for (int j = 0; j < writer.getNy(); j++)	
			{
				if(numOfRays == 1)
				{
					Ray ray = constructRay(writer.getNx(), writer.getNy(), j, i);
					Color rayColor = rayTracer.traceRay(ray);
					writer.writePixel(j, i, rayColor); 
				}
				else
				{	
					List<Ray> rays = constructBeamThroughPixel(writer.getNx(), writer.getNy(), j, i,numOfRays);
					Color rayColor = rayTracer.traceRay(rays);
					writer.writePixel(j, i, rayColor); 
				}
				
			}
		}
		return this;
	}
	
	public List<Ray> constructBeamThroughPixel (int nX, int nY, int j, int i, int raysAmount){

		//The distance between the screen and the camera cannot be 0
        if (isZero(distance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        
		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //num of rays in each row or column
		
		if (numOfRays==1) //if (1-4) so there is 1 ray
			return List.of(constructRay(nX, nY, j, i));
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;
    
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray

        List<Ray> sample_rays = new LinkedList<>();

        for (int row = 0; row < numOfRays; ++row) {//foreach place in the pixel grid
            for (int column = 0; column < numOfRays; ++column) {
                sample_rays.add(constructRaysThroughPixel(PRy,PRx,Yi, Xj, row, column));//add the ray
            }
        }
        sample_rays.add(constructRay(nX, nY, j, i));//add the center screen ray
        return sample_rays;
	}
	
	 /**
     * In this function we treat each pixel like a little screen of its own and divide it to smaller "pixels".
     * Through each one we construct a ray. This function is similar to ConstructRayThroughPixel.
     * @param Ry height of each grid block we divided the pixel into
     * @param Rx width of each grid block we divided the pixel into
     * @param yi distance of original pixel from (0,0) on Y axis
     * @param xj distance of original pixel from (0,0) on X axis
     * @param j j coordinate of small "pixel"
     * @param i i coordinate of small "pixel"
     * @param distance distance of screen from camera
     * @return beam of rays through pixel
     */
    private Ray constructRaysThroughPixel(double Ry,double Rx, double yi, double xj, int j, int i){
        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

        double y_sample_i =  (i *Ry + Ry/2d); //The pixel middle point on the y axis
        double x_sample_j=   (j *Rx + Rx/2d); //The pixel middle point on the x axis

        Point Pij = Pc; //The point at the pixel through which a beam is fired
        //Moving the point through which a beam is fired on the x axis
        if (!isZero(x_sample_j + xj))
        {
            Pij = Pij.add(vRight.scale(x_sample_j + xj));
        }
        //Moving the point through which a beam is fired on the y axis
        if (!isZero(y_sample_i + yi))
        {
            Pij = Pij.add(vUp.scale(-y_sample_i -yi ));
        }
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0,Vij);//create the ray throw the point we calculate here
    }

	/**
	 * @return the p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
		
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

}
