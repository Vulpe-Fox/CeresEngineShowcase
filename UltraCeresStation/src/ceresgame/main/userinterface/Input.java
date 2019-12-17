package ceresgame.main.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;


public class Input extends Thread{
    
        public boolean running = true;
    
        public Input(){
            
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
                if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                    
                }
        }
}
