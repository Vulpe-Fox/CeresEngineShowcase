/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import org.lwjgl.opengl.Display;

import ceresgame.models.TexturedModel;

/**
 *
 * @author pintt3963
 */
public class SnowFall extends GraphicalComponent {
    
    private int amp;
    
    public SnowFall(int xPos, int yPos, int zPos, int width, int height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        xPos = (int) Math.random() * Display.getWidth();
        amp = (int) Math.random() * 10;
        zPos = -1;
    }
    
    private void fall(int xPos, int yPos) {
        
    }
}
