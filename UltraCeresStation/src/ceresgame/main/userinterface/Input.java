package ceresgame.main.userinterface;

import ceresgame.enumeration.Direction;
import ceresgame.map.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;


public class Input extends Thread{
    
        public boolean running = true;
        private Player player;
        
        public Input(Player player){
            this.player = player;
        }
    
        @Override
        public void run(){
            while(running){
                keyPressed();
                
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

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
