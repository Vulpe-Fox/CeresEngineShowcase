/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.graphics.gui;

import org.lwjgl.util.vector.Vector3f;

/**
 * Main class which returns player positions in order and assigns corresponding camera positions
 * @author bartm6529
 */
public class Camera {
    private Float posX;
    private Float posY;
    private Float posZ;

    /**
     * Assign starting camera values
     */
    public Camera(){
        this.posX = 0F;
        this.posY = 0F;
        this.posZ = +1.0F;
    }
    
    /**
     * Get new x, y, and x positions for vector3f
     * @return new vector3f
     */
    public Vector3f getPosition() {
        return new Vector3f(this.posX, this.posY, this.posZ);
    }
    
    
    /**
     * Move up by adding to cameras x position
     */
    public void moveUp(){
        this.posX = posX + 1F;
    }
    
    /**
     * Move down by subtracting from cameras x position
     */
    public void moveDown(){
        this.posX = posX - 1F;
    }
    
    /**
     * Move left by adding to cameras y position
     */
    public void moveLeft(){
        this.posY = posY - 1F;
    }
    
    /**
     * Move right by subtracting from cameras y position
     */
    public void moveRight(){
        this.posY = posY - 1F;
    }
    
}
