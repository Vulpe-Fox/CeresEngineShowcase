/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.graphics;

import java.awt.DisplayMode;

/**
 *
 * @author carmc9538
 */
public class DisplayUpdater {
    
    final static int WIDTH = 1080;
    final static int HEIGHT = 720;
    
    public void createDisplay(){
        
        Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
        
    }
    
    public void updateDisplay(){
        
    }
    
    public void destroyDisplay(){
        
    }
    
    
}
