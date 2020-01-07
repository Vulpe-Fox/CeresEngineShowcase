/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private int xPos;
    private int yPos;
    
    public Player(int xPos, int yPos, int zPos, int width, int height, String imgPath) {
        super(xPos, yPos, zPos, width, height, imgPath);
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
    
    public int getXPos() {
        return xPos;
    }
    
    public int getYPos() {
        return yPos;
    }
}
