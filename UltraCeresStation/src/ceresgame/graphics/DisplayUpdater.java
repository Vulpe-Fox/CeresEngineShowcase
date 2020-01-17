/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * Creates and handles the screen display for the game
 * @author carmc9538
 */
public class DisplayUpdater {
    
    private final static int WIDTH = 1080;
    private final static int HEIGHT = 720;
    private static final int FPS = 60;
    
    private static long lastTime;
    private static float delta;
	
    /**
    *Creates the display and generates the opengl version context for version 3.2
    */
    public static void createDisplay() {

        //Sets opengl version context to use version 3.2, which is forward compatible and a core profile
        ContextAttribs cA = new ContextAttribs(3,2);
        cA.withForwardCompatible(true);
        cA.withProfileCore(true);
        
        //Program tries to create a display with a specific width and height as specified above
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), cA);
            Display.setTitle("Game Engine Thingy");
        } catch (LWJGLException e) {
            System.out.println("Something went wrong with the display lol");
        }

        //Sets the viewport to be at 0,0 and the same size as the width and height of the screen
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        lastTime = getCurrentTime();
    }
	
    /**
    *Updates the display by erasing old information and loading new information at the rate of fps
    */
    public static void updateDisplay() {
        //Updates the screen in accordance to the rate of fps
        Display.sync(FPS);
        Display.update();
        long currentTime = getCurrentTime();
        delta = currentTime - lastTime;
        lastTime = currentTime;
    }

    /**
     * Deletes the display, thus closing all opengl elements
     */
    public static void closeDisplay() {
        
        Display.destroy();
        
    }
    
    private static long getCurrentTime(){
        return 1000*Sys.getTime()/Sys.getTimerResolution();
    }
    
    public static float getDelta(){
        return delta/1000f;
    }
    
    
}
