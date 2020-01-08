package ceresgame.main.userinterface;

import ceresgame.enumeration.Direction;
import ceresgame.main.CeresStation;
import ceresgame.map.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;

/**
*The input class which runs events based on whether different keys are down.
*@author ivary45, pintt3963
*/
public class Input extends Thread{
    
        public boolean running = true;
        private Player player;
        
        /**
        *The constructor of the input class which assigns the current player to be the actual player entity
        *@param game the runner class being input
        */
        public Input(CeresStation game){
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
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                player.movement(Direction.RIGHT);
            }
        }
}
