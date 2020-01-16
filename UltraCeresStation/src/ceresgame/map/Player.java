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
 *
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private Vector3f position = new Vector3f();
    
    public Player(float xPos, float yPos, float zPos, float width, float height, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, model);
        this.position.x = xPos;
        this.position.y = yPos;
        this.position.z = 0;
    }
    
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
    
    public float getXPos() {
        return this.position.x;
    }
    
    public float getYPos() {
        return this.position.y;
    }
}
