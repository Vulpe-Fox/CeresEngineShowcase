/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.models.TexturedModel;

/**
 * The graphics for the background of the map
 * @author pintt3963
 */
public class Background extends GraphicalComponent {
    
    /**
     * The constructor for the background class
     * @param xPos The x position of the background
     * @param yPos The y position of the background
     * @param zPos The z position of the background
     * @param width The width of the background
     * @param height The height of the background
     * @param imgPath The image path of the background
     * @param model The model of the background
     */
    public Background(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        zPos = 1;
    }
}
