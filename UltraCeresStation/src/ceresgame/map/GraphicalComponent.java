/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

/**
 *
 * @author carmc9538
 */
public class GraphicalComponent {
    
    private int xPos;
    private int yPos;
    private int zPos;
    
    private final int width;
    private final int height;
    
    public GraphicalComponent(int xPos, int yPos, int zPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        this.width = width;
        this.height = height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getzPos() {
        return zPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setzPos(int zPos) {
        this.zPos = zPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
