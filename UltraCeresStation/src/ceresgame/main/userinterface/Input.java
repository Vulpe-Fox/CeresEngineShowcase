package ceresgame.main.userinterface;

import ceresgame.enumeration.Direction;
import ceresgame.main.CeresStation;
import ceresgame.map.Player;
import java.awt.event.ActionEvent;
import org.lwjgl.input.Keyboard;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
*The input class which runs events based on whether different keys are down.
*@author ivary45, pintt3963, bartm6529
*/
public class Input extends Thread implements KeyListener, ActionListener{
        private boolean running = true;
        private Player player;
        private CeresStation game;
        
        private int buttonStatus = 0;
        
        /**
        *The constructor of the input class which assigns the current player to be the actual player entity
        *@param game the runner class being input
        */
        public Input(CeresStation game){
            this.game = game;
            this.player = game.getPlayer();
        }
    
        /**
        *Runs the thread, which loops checking input and waiting for more
        *
        */
        @Override
        public void run(){
            while(running){
                keyPressed();
                //TODO change input scheme to reflect delta time changes instead of waiting
            }
        }

        /**
        *The method which checks which keys are down and moves the player using said inputs
        *
        */
        public void keyPressed() {
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                player.movement(Direction.UP);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                player.movement(Direction.DOWN);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                player.movement(Direction.LEFT);
                
                //If player is on the ground, play sound effect
                if(player.getyPos() == 0){
                    game.getAudioLoop().playSoundEffect("resources/audio/step.wav");
                }
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                player.movement(Direction.RIGHT);
                
                //If player is on the ground, play sound effect
                if(player.getyPos() == 0){
                    game.getAudioLoop().playSoundEffect("resources/audio/step.wav");
                }
            }
        }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}