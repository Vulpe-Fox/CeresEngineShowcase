/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author pintt3963
 */
public class Player extends GraphicalComponent {
    
    private int xPosRaw;
    private int yPosRaw;
    private int xPos;
    private int yPos;
    
    private final int zPos = 0;

    public Player(int xPos, int yPos, int zPos, int width, int height, String imgPath) {
        super(xPos, yPos, zPos, width, height, imgPath);
        
    }
    
    private void movement() {
        
    }
}
