/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

/**
 *
 * @author pintt3963
 */
public class Foreground extends GraphicalComponent {
    
    public Foreground(int xPos, int yPos, int zPos, int width, int height, String imgPath) {
        super(xPos, yPos, zPos, width, height, imgPath);
        zPos = 0;
    }
}
