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
    private Vector3f position = new Vector3f();

    /**
     * Assign starting camera values
     */
    public Camera(){
        this.position.setX(0);
        this.position.setY(0);
        this.position.setZ(0);
    }
    
    /**
     * Get new x, y, and x positions for vector3f
     * @return new vector3f
     */
    public Vector3f getPosition() {
        return this.position;
    }
    
    
    /**
     * Move up by adding to cameras y position
     */
    public void moveUp(){
        this.position.y = this.position.y + 0.0000002F;
    }
    
    /**
     * Move down by subtracting cameras y position
     */
    public void moveDown(){
        this.position.y = this.position.y - 0.0000002F;
    }
    
    /**
     * Move left by subtracting from cameras x position
     */
    public void moveLeft(){
        this.position.x = this.position.x - 0.0000002F;
    }
    
    /**
     * Move right by adding to cameras x position
     */
    public void moveRight(){
        this.position.x= this.position.x + 0.0000002F;
    }
    
    /**
     * Move frontwards by subtracting from cameras z position
     */
    public void moveFront(){
        this.position.z = this.position.z - 0.0000002F;
    }
    
    /**
     * Move backwards by adding to cameras z position
     */
    public void moveBack(){
        this.position.z = this.position.z + 0.0000002F;
    }
    
}
