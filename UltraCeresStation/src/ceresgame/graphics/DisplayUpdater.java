/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.graphics;

import ceresgame.map.GraphicalComponent;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author carmc9538
 */
public class DisplayUpdater {
    
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 720;
    private static final int FPS = 60;
    private static ArrayList<GraphicalComponent> components = new ArrayList<>();
	
    public static void createDisplay() {

        //Sets opengl version context to use version 3.2, which is forward compatible and a core profile
        ContextAttribs cA = new ContextAttribs(3,2);
        cA.withForwardCompatible(true);
        cA.withProfileCore(true);
        
        //Program tries to create a display with a specific width and height as specified above
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), cA);
        } catch (LWJGLException e) {
            System.out.println("Something went wrong with the display lol");
        }

        //Sets the viewport to be at 0,0 and the same size as the width and height of the screen
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        //Sets the background unrendered colour to be a very nice green colour
        GL11.glClearColor(0.6F, 1.0F, 0.0F, 1.0F);
        //Enables the openGL depth test, so object behind other objects won't render (stops overlapping conflicts)
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public static void addComponent(GraphicalComponent gc) {
        components.add(gc);
    }

    public static void updateDisplay() {
        //Clears the screen to the unrendered colour
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        //Updates the screen in accordance to the rate of fps
        Display.sync(FPS);
        Display.update();
    }

    /**
     * Deletes the display, thus closing all opengl elements
     */
    public static void closeDisplay() {
        Display.destroy();
    }
    
    
}
