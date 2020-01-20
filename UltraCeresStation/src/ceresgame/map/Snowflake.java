/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.models.TexturedModel;
import java.util.Random;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

/**
 * The graphics and physics of the falling snow in the map
 * @author pintt3963
 */
public class Snowflake extends GraphicalComponent {
    
    private int amp = (int) (Math.random() * 100) + 10;
    private Vector3f position;
    
    /**
     * The constructor for the snowflake class
     * @param xPos The x position of the snowflake
     * @param yPos The y position of the snowflake
     * @param zPos The z position of the snowflake
     * @param width The width of the snowflake
     * @param height The height of the snowflake
     * @param model The model of the snowflake
     */
    public Snowflake(float xPos, float yPos, float zPos, float width, float height, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, model);
        Random random = new Random();
        amp = (int) (Math.random() * 100) + 10;
        xPos = random.nextInt() * Display.getWidth();
        yPos = 0;
        zPos = -1;
    }
    
    /**
     * Handles the falling physics of the snowflakes
     */
    private void fall() {
        //this.position.y += (int) (CeresStation.getCurrentTime() - CeresStation.getStartTime());
        //this.position.x = (int) (amp * Math.sin(CeresStation.getCurrentTime() - CeresStation.getStartTime()));
    }
}