/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import ceresgame.models.TexturedModel;

/**
 * The graphics and behaviour of God
 * @author pintt3963
 */
public class God extends GraphicalComponent {
    
    /**
     * The constructor for the god class
     * @param xPos The x position of god
     * @param yPos The y position of god
     * @param zPos The z position of god
     * @param width The width of god
     * @param height The height of god
     * @param imgPath The image path of god
     * @param model The model of god
     */
    public God(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model) {
        super(xPos, yPos, zPos, width, height, imgPath, model);
        zPos = 0;
    }
    
}
