/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.enumeration.Direction;
import ceresgame.models.TexturedModel;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    public Player(int xPos, int yPos, int zPos, int width, int height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        zPos = 0;
    }
    
    public void movement(Direction dir) {
        if (dir.equals(Direction.UP)) {
            
        }
        if (dir.equals(Direction.DOWN)) {
            
        }
        if (dir.equals(Direction.LEFT)) {
            
        }
        if (dir.equals(Direction.RIGHT)) {
            
        }
    }
}
