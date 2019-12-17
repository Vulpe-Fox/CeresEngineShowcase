/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.graphics;

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
    
    final static int WIDTH = 1080;
    final static int HEIGHT = 720;
    private static final int FPS = 60;
	
	public static void createDisplay() {
		
		ContextAttribs cA = new ContextAttribs(3,2);
		cA.withForwardCompatible(true);
		cA.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), cA);
		} catch (LWJGLException e) {
                    // TODO Auto-generated catch block

		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GL11.glClearColor(0.6F, 1.0F, 0.0F, 1.0F);
		
		
	}
	
	public static void updateDisplay() {
		

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Display.sync(FPS);
		Display.update();
		
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
    
    
}
