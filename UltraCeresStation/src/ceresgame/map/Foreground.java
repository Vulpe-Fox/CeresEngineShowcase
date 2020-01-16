/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.models.TexturedModel;

/**
 * The graphics for the foreground of the map
 * @author pintt3963
 */
public class Foreground extends GraphicalComponent {
    
    /**
     * The constructor for the foreground class
     * @param xPos The x position of the foreground
     * @param yPos The y position of the foreground
     * @param zPos The z position of the foreground
     * @param width The width of the foreground
     * @param height The height of the foreground
     * @param imgPath The image path of the foreground
     * @param model The model of the foreground
     */
    public Foreground(int xPos, int yPos, int zPos, int width, int height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        zPos = 0;
    }
}
