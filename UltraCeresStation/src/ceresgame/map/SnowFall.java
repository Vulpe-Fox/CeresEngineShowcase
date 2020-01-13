/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import org.lwjgl.opengl.Display;

import ceresgame.models.TexturedModel;
import java.awt.Color;

/**
 *
 * @author pintt3963
 */
public class SnowFall extends GraphicalComponent {
    
    private int amp;
    private int xPos;
    private int yPos;
    long time;
    
    public SnowFall(int xPos, int yPos, int zPos, int width, int height, Color c) {
        super(xPos, yPos, zPos, width, height, c);
        amp = (int) (Math.random() * 100) + 10;
        xPos = (int) Math.random() * Display.getWidth();
        yPos = 0;
        zPos = -1;
        this.xPos = xPos;
        this.yPos = yPos;
        this.time = System.currentTimeMillis();
    }
    
    private void fall() {
        long currentTime = System.currentTimeMillis();
        yPos += (int) (currentTime - time);
        xPos = (int) (amp * Math.sin(currentTime - time));
    }
}
