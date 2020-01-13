/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import ceresgame.models.TexturedModel;

/**
 *
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private float xPos;
    private float yPos;
    
    public Player(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        this.xPos = xPos;
        this.yPos = yPos;
        zPos = 0;
    }
    
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            yPos -= 1;
        }
        if (dir.equals(Direction.DOWN)) {
            yPos += 1;
        }
        if (dir.equals(Direction.LEFT)) {
            xPos -= 1;
        }
        if (dir.equals(Direction.RIGHT)) {
            xPos += 1;
        }
    }
    
    public float getXPos() {
        return xPos;
    }
    
    public float getYPos() {
        return yPos;
    }
}
