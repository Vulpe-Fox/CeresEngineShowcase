/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import ceresgame.graphics.DisplayUpdater;
import ceresgame.models.TexturedModel;

/**
 * The graphics and behaviour of the player character
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private final float MOVE_SPEED = 0.00001f;
    
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
    }
    
    /**
     * Handles the movement of the player
     * @param dir The direction the user is holding
     */
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            this.position.setY((this.position.getY() + MOVE_SPEED * DisplayUpdater.getDelta()));
        }
        if (dir.equals(Direction.DOWN)) {
            
        }
        if (dir.equals(Direction.LEFT)) {
            this.position.setX((this.position.getX() - MOVE_SPEED * DisplayUpdater.getDelta()));
        }
        if (dir.equals(Direction.RIGHT)) {
            this.position.setX((this.position.getX() + MOVE_SPEED * DisplayUpdater.getDelta()));
        }
    }
    
    public void getHit() {
        
    }
}
