/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import org.lwjgl.util.Color;

/**
 * This is a graphical component which will be able to appear on screen
 * @author ivary45
 */
public class GraphicalComponent {
    
    private int xPos;
    private int yPos;
    private int zPos;
    
    private final int width;
    private final int height;
    
    private String imgPath;
    private Color c;
        
    /**
     * A constructor for a graphical component using an image
     * @param xPos The x position of the component
     * @param yPos The y position of the component
     * @param zPos The z position of the component
     * @param width The width of the component
     * @param height The height of the component
     * @param imgPath The path of the image file
     */
    public GraphicalComponent(int xPos, int yPos, int zPos, int width, int height, String imgPath){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        
        this.width = width;
        this.height = height;
        
        this.imgPath = imgPath;
    }
    
    /**
     * A constructor for a graphical component using a colour
     * @param xPos The x position of the component
     * @param yPos The y position of the component
     * @param zPos The z position of the component
     * @param width The width of the component
     * @param height The height of the component
     * @param c The colour of the component
     */
    public GraphicalComponent(int xPos, int yPos, int zPos, int width, int height, Color c){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        
        this.width = width;
        this.height = height;
        
        this.c = c;
    }

    /**
     * Gets x position
     * @return x position
     */
    public int getxPos() {
        return xPos;
    }
    
    /**
     * Gets y position
     * @return y position
     */
    public int getyPos() {
        return yPos;
    }
    
    /**
     * Gets z position
     * @return z position
     */
    public int getzPos() {
        return zPos;
    }

    /**
     * Sets x position
     * @param xPos The x position to set to
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets y position
     * @param yPos The y position to set to
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Sets z position
     * @param zPos The z position to set to
     */
    public void setzPos(int zPos) {
        this.zPos = zPos;
    }

    /**
     * Gets the width of the component
     * @return The width of the component
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the component
     * @return The width of the component
     */
    public int getHeight() {
        return height;
    }
    

    public String getImgPath() {
        return imgPath;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }
    
}
