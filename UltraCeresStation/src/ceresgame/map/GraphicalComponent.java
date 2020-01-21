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
    
    protected Vector3f position = new Vector3f();
    protected float rotationX = 0;
    protected float rotationY = 0;
    protected float rotationZ = 0;
    
    
    private final float width;
    private final float height;
    
    private Color c;
    
    private TexturedModel model;
        
    /**
     * A constructor for a graphical component using an image
     * @param xPos The x position of the component
     * @param yPos The y position of the component
     * @param zPos The z position of the component
     * @param width The width of the component
     * @param height The height of the component
     * @param model The model of the component; if applicable
     */
    public GraphicalComponent(float xPos, float yPos, float zPos, float width, float height, TexturedModel model){
        this.position.setX(xPos);
        this.position.setY(yPos);
        this.position.setZ(zPos);
        
        this.width = width;
        this.height = height;
        
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
        this.position.setX(xPos);
        this.position.setY(yPos);
        this.position.setZ(zPos);
        
        this.width = width;
        this.height = height;
        
        this.c = c;
    }

    /**
     * Gets x position
     * @return x position
     */
    public float getxPos() {
        return this.position.getX();
    }
    
    /**
     * Gets y position
     * @return y position
     */
    public float getyPos() {
        return this.position.getY();
    }
    
    /**
     * Gets z position
     * @return z position
     */
    public float getzPos() {
        return this.position.getZ();
    }

    /**
     * Sets x position
     * @param xPos The x position to set to
     */
    public void setxPos(float xPos) {
        this.position.setX(xPos);
    }

    /**
     * Sets y position
     * @param yPos The y position to set to
     */
    public void setyPos(float yPos) {
        this.position.setY(yPos);
    }

    /**
     * Sets z position
     * @param zPos The z position to set to
     */
    public void setzPos(float zPos) {
        this.position.setZ(zPos);
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

    /**
     * Gets rotation around X axis
     * @return Rotation
     */
    public float getRotationX() {
        return rotationX;
    }
    
    /**
     * Gets rotation around Y axis
     * @return Rotation
     */
    public float getRotationY() {
        return rotationY;
    }

    /**
     * Gets rotation around Z axis
     * @return Rotation
     */
    public float getRotationZ() {
        return rotationZ;
    }

    /**
     * Sets rotation around X axis
     * @param rotationX The rotation in degrees
     */
    public void setRotationX(float rotationX) {
        this.rotationX = rotationX;
    }

    /**
     * Sets rotation around Y axis
     * @param rotationY The rotation in degrees
     */
    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
    }

    /**
     * Sets rotation around Z axis
     * @param rotationZ The rotation in degrees
     */
    public void setRotationZ(float rotationZ) {
        this.rotationZ = rotationZ;
    }
    
    /**
     *Gets the vector position of the object
     *@return The vector
     */
    public Vector3f getPosition() {
    	return position;
    }

    /**
     *Gets the model of the object
     *@return The model
     */
    public TexturedModel getModel() {
            return this.model;
    }

    /**
     * Sets the model of the object
     * @param model The model to set it to
     */
    public void setModel(TexturedModel model) {
            this.model = model;
    }
    
}
