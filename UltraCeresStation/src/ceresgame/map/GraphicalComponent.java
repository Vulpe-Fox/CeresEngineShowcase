/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.map;

import java.awt.Color;

import org.lwjgl.util.vector.Vector3f;

import ceresgame.models.TexturedModel;

/**
 * This is a graphical component which will be able to appear on screen
 * @author ivary45
 */
public class GraphicalComponent {
    
    private float xPos;
    private float yPos;
    private float zPos;
    
    private final float width;
    private final float height;
    
    private String imgPath;
    private Color c;
    
    private TexturedModel model;
        
    /**
     * A constructor for a graphical component using an image
     * @param xPos The x position of the component
     * @param yPos The y position of the component
     * @param zPos The z position of the component
     * @param width The width of the component
     * @param height The height of the component
     * @param imgPath The path of the image file
     * @param model The model of the component; if applicable
     */
    public GraphicalComponent(float xPos, float yPos, float zPos, float width, float height, String imgPath, TexturedModel model){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        
        this.width = width;
        this.height = height;
        
        this.imgPath = imgPath;
        
        this.model = model;
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
    public GraphicalComponent(float xPos, float yPos, float zPos, float width, float height, Color c){
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
    public float getxPos() {
        return xPos;
    }
    
    /**
     * Gets y position
     * @return y position
     */
    public float getyPos() {
        return yPos;
    }
    
    /**
     * Gets z position
     * @return z position
     */
    public float getzPos() {
        return zPos;
    }

    /**
     * Sets x position
     * @param xPos The x position to set to
     */
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets y position
     * @param yPos The y position to set to
     */
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     * Sets z position
     * @param zPos The z position to set to
     */
    public void setzPos(float zPos) {
        this.zPos = zPos;
    }

    /**
     * Gets the width of the component
     * @return The width of the component
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets the height of the component
     * @return The width of the component
     */
    public float getHeight() {
        return height;
    }
    
    /**
     * Gets path of image
     * @return The path of the image file
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Gets colour of component
     * @return The colour of the component
     */
    public Color getC() {
        return c;
    }

    /**
     * Sets colour of component
     * @param c The colour to shift to
     */
    public void setC(Color c) {
        this.c = c;
    }
    
    public Vector3f getPosition() {
    	Vector3f vector = new Vector3f(this.getxPos(), this.getyPos(), this.getzPos());
    	return vector;
    }

	public TexturedModel getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}
	
	public void setModel(TexturedModel model) {
		this.model = model;
	}
    
}
