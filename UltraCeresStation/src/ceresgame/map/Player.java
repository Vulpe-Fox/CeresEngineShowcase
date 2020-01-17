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
    
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            this.position.y += 0.1f;
        }
        if (dir.equals(Direction.DOWN)) {
            
        }
        if (dir.equals(Direction.LEFT)) {
            this.position.setX(this.position.getX() - 0.0000001f);
        }
        if (dir.equals(Direction.RIGHT)) {
            this.position.setX(this.position.getX() + 0.0000001f);
        }
    }
    
    public void getHit() {
        
    }
}
