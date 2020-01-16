/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.models.TexturedModel;
import org.lwjgl.opengl.Display;

/**
 * The graphics and physics of the falling snow in the map
 * @author pintt3963
 */
public class Snowflake extends GraphicalComponent {
    
    private int amp;
    private float xPos;
    private float yPos;
    long time;
    long currentTime = System.currentTimeMillis();
    
    /**
     * The constructor for the snowflake class
     * @param xPos The x position of the snowflake
     * @param yPos The y position of the snowflake
     * @param zPos The z position of the snowflake
     * @param width The width of the snowflake
     * @param height The height of the snowflake
     * @param imgPath The image path of the snowflake
     * @param model The model of the snowflake
     */
    public Snowflake(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, "resources/images/Snowflake.png", model);
        amp = (int) (Math.random() * 100) + 10;
        xPos = (int) Math.random() * Display.getWidth();
        yPos = 0;
        zPos = -1;
        this.xPos = xPos;
        this.yPos = yPos;
        this.time = System.currentTimeMillis();
    }
    
    /**
     * Handles the falling physics of the snowflakes
     */
    private void fall() {
        yPos += (int) (currentTime - time);
        xPos = (int) (amp * Math.sin(currentTime - time));
    }
}
