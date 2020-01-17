/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import ceresgame.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

/**
 * The graphics and behaviour of the player character
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private Vector3f position;
    
    /**
     * The constructor for the player class
     * @param xPos The x position of the player
     * @param yPos The y position of the player
     * @param zPos The z position of the player
     * @param width The width of the player
     * @param height The height of the player
     * @param model The model of the player
     */
    public Player(float xPos, float yPos, float zPos, float width, float height, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, model);
        this.position.x = xPos;
        this.position.y = yPos;
        this.position.z = zPos;
    }
    
    /**
     * Handles the movement of the player
     * @param dir The direction the user is holding
     */
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            this.position.y -= 1;
        }
        if (dir.equals(Direction.DOWN)) {
            this.position.y += 1;
        }
        if (dir.equals(Direction.LEFT)) {
            this.position.x -= 1;
        }
        if (dir.equals(Direction.RIGHT)) {
            this.position.x += 1;
        }
    }
    
    public void getHit() {
        
    }
    
    public float getXPos() {
        return this.position.x;
    }
    
    public float getYPos() {
        return this.position.y;
    }
}
