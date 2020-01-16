/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import ceresgame.models.TexturedModel;

/**
 * The graphics and behaviour of the player character
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private float xPos;
    private float yPos;
    
    /**
     * The constructor for the player class
     * @param xPos The x position of the player
     * @param yPos The y position of the player
     * @param zPos The z position of the player
     * @param width The width of the player
     * @param height The height of the player
     * @param imgPath The image path of the player
     * @param model The model of the player
     */
    public Player(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        this.xPos = xPos;
        this.yPos = yPos;
        zPos = 0;
    }
    
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            yPos -= 1f;
        }
        if (dir.equals(Direction.DOWN)) {
            yPos += 1f;
        }
        if (dir.equals(Direction.LEFT)) {
            xPos -= 1f;
        }
        if (dir.equals(Direction.RIGHT)) {
            xPos += 1f;
        }
    }
    
    public void getHit() {
        
    }
    
    public float getXPos() {
        return xPos;
    }
    
    public float getYPos() {
        return yPos;
    }
}
