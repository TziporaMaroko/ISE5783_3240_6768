/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   private Scene scene = new Scene("Test scene");
   
   
   @Test
	public void effectsTests() {
		Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0));
		camera.setVPDistance(1000).setVPSize(200,200);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
		scene.geometries.add( //
				new Triangle(new Point(-150, 150, 115), new Point(150, 150, 135), new Point(75, -75, 150))
				.setEmission(new Color(java.awt.Color.magenta)).setMaterial(new Material().setkD(0).setkS(0.8).setShininess(6)), //
				new Triangle(new Point(-150, 150, 115), new Point(-70, -70, 140), new Point(75, -75, 150))
				.setEmission(new Color(java.awt.Color.magenta)).setMaterial(new Material().setkD(0).setkS(0.8).setShininess(60)),
				new Sphere(new Point(0, 0, 115),10).setEmission(new Color(java.awt.Color.BLUE))
				.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30).setKt(0).setKr(0.2)), 
				new Sphere(new Point(50, 50, 115),10).setEmission(new Color(java.awt.Color.BLACK))
               .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)), 
				new Sphere(new Point(-50, -50, 115),10).setEmission(new Color(java.awt.Color.RED))
				.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
				new Sphere(new Point(50, -10, 115),30).setEmission(new Color(java.awt.Color.BLUE))
				.setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point(-20, 50, 100),10).setEmission(new Color(java.awt.Color.GREEN))
				.setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point(0, 15, 30),10).setEmission(new Color(java.awt.Color.ORANGE))
				.setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setKt(0.6).setKr(0)),
				new Sphere(new Point(-10, -20, 70),20).setEmission(new Color(java.awt.Color.BLUE))
				.setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setKt(0.6).setKr(0))
						
				);
		scene.lights.add(new SpotLight(new Color(700, 400, 400),new Point(40,-40,-160), new Vector(-1, 1, 4))
				.setKC(1).setKL(4E-4).setKQ(2E-5));
		scene.lights.add(new SpotLight(new Color(700, 400, 400),new Point(300, 30,0), new Vector(-2, 3, 3))
				.setKC(1).setKL(4E-4).setKQ(2E-5));
		camera.setImageWriter(new ImageWriter("pool", 600, 600)) 
		.setRayTracer(new RayTracerBasic(scene)); 
camera.renderImage(); //
camera.writeToImage();
		
	}
   
   
   @Test
   public void colorfulScene() {
	   Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
		         .setVPSize(150, 150).setVPDistance(1000);

		      scene.geometries.add( //
		                           new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
		                              .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setKt(0.3)),
		                           new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
		                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100)));
		      					   new Sphere(new Point(20, 0, -50), 100d).setEmission(new Color(GREEN)) //
		      					   .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100).setKr(0.7));
		      scene.lights.add( //
		                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
		                          .setKL(0.0004).setKQ(0.0000006));

		      camera.setImageWriter(new ImageWriter("refractionThreeSpheres", 500, 500)) //
		         .setRayTracer(new RayTracerBasic(scene)) //
		         .renderImage() //
		         .writeToImage();
   }



   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(150, 150).setVPDistance(1000);

      scene.geometries.add( //
                           new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.4).setkS(0.3).setShininess(100).setKt(0.3)),
                           new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100)));
      scene.lights.add( //
                       new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                          .setKL(0.0004).setKQ(0.0000006));

      camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(2500, 2500).setVPDistance(10000); //

      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

      scene.geometries.add( //
                           new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)
                                 .setKt(new Double3(0.5, 0, 0))),
                           new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                              .setMaterial(new Material().setkD(0.25).setkS(0.25).setShininess(20)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(670, 670, 3000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(1)),
                           new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                                        new Point(-1500, -1500, -2000)) //
                              .setEmission(new Color(20, 20, 20)) //
                              .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
         .setKL(0.00001).setKQ(0.000005));

      ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
         .setVPSize(200, 200).setVPDistance(1000);

      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.geometries.add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60)), //
                           new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(30).setKt(0.6)));

      scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
         .setKL(4E-5).setKQ(2E-7));

      ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
      camera.setImageWriter(imageWriter) //
         .setRayTracer(new RayTracerBasic(scene)) //
         .renderImage() //
         .writeToImage();
   }
}
