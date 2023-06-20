package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.LightSource;
import lighting.PointLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

public class FinalImageTest {
    private Scene scene = new Scene("Test scene");

    @Test
    public void createInterestingMice() {
        Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0));
        camera.setVPDistance(1000).setVPSize(200, 200).setNumOfRays(1024)//
		.setMultithreading(4).setDebugPrint(0.1).setAdaptiveSuperSamplingFlag(true);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)))
                .setBackground(new Color(200, 230, 255));

        // Minnie Mouse
        // Head (big black sphere)
        Sphere minnieHead = (Sphere) new Sphere(new Point(50, 0, 0), 30)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(minnieHead);

        // Ears (smaller spheres)
        Sphere minnieLeftEar = (Sphere) new Sphere(new Point(20, -30, 50), 15)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(minnieLeftEar);

        Sphere minnieRightEar = (Sphere) new Sphere(new Point(85, -30, 50), 15)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(minnieRightEar);

        // Ribbon (red hourglass shape)
        Triangle minnieRibbonTop1 = (Triangle) new Triangle(
                new Point(75, -20, 5),
                new Point(75, -50, 0),
                new Point(50, -30, 40)
        ).setEmission(new Color(java.awt.Color.RED))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(minnieRibbonTop1);

        Triangle minnieRibbonTop2 = (Triangle) new Triangle(
                new Point(25, -20, 5),
                new Point(25, -50, 0),
                new Point(50, -30, 40)
        ).setEmission(new Color(java.awt.Color.RED))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(minnieRibbonTop2);

        // Mickey Mouse
        // Head (big black sphere)
        Sphere mickeyHead = (Sphere) new Sphere(new Point(-50, 0, 0), 30)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(mickeyHead);

        // Ears (smaller spheres)
        Sphere mickeyLeftEar = (Sphere) new Sphere(new Point(-85, -30, 50), 15)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(mickeyLeftEar);

        Sphere mickeyRightEar = (Sphere) new Sphere(new Point(-20, -30, 50), 15)
                .setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        
     // Sunglasses (brown polygons)
        Polygon mickeyGlasses1 = (Polygon) new Polygon(
                new Point(-50, -20, -40),
                new Point(-20, -20, -40),
                new Point(-20, 0, -40),
                new Point(-40, 0, -40)
        ).setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.3).setShininess(200).setKt(0.2).setKr(0.3));
        scene.geometries.add(mickeyGlasses1);

        Polygon mickeyGlasses2 = (Polygon) new Polygon(
                new Point(-50, -20, -40),
                new Point(-80, -20, -40),
                new Point(-80, 0, -40),
                new Point(-60, 0, -40)
        ).setEmission(new Color(java.awt.Color.BLACK))
                .setMaterial(new Material().setkD(0.5).setkS(0.3).setShininess(200).setKt(0.2).setKr(0.3));
        scene.geometries.add(mickeyGlasses2);
        scene.geometries.add(mickeyRightEar);

        // Props in the background
        // Tree
        Polygon treeTrunk = (Polygon) new Polygon(
                new Point(-95, -50, 100),
                new Point(-105, -50, 100),
                new Point(-110, 200, 100),
                new Point(-90, 200, 100)
        ).setEmission(new Color(92, 64, 51))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(10));
        scene.geometries.add(treeTrunk);

        Sphere treeLeaves = (Sphere) new Sphere(new Point(-100, -20, 100), 50)
                .setEmission(new Color(34, 139, 34))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(treeLeaves);

        // Cloud
        Sphere cloud1 = (Sphere) new Sphere(new Point(-30, -120, 0), 50)
                .setEmission(new Color(255, 255, 255))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(cloud1);

        Sphere cloud2 = (Sphere) new Sphere(new Point(20, -140, 10), 60)
                .setEmission(new Color(255, 255, 255))
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(40));
        scene.geometries.add(cloud2);

        // Lighting
        scene.setAmbientLight( new AmbientLight(new Color(50, 50, 50), new Double3(0.1)));
        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(-200, 200, -200), new Vector(1, -1, 1))
                .setKC(1).setKL(0.0004).setKQ(0.00002));
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(100, 100, -200))
                .setKC(1).setKL(0.0004).setKQ(0.00002));

        camera.setImageWriter(new ImageWriter("interestingMice", 600, 600))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.writeToImage();
    }
}
