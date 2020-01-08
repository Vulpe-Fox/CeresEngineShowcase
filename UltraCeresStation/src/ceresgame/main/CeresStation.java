package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.main.userinterface.Input;
import ceresgame.map.Player;
import ceresgame.models.RawModel;
import ceresgame.models.TexturedModel;

/**
*The main class containing the runner object which contains all the relevant objects, so they can all be connected together (avoiding static issues)
*
*/
public class CeresStation{
	
    private Player player;
    private Input inputThread;
    private AudioLoop audioThread;
    //private Camera camera = new Camera(this); //Please feed in a CeresStation object so you can reference the player
	
    /**
    *The constructor of the main game object
    *
    */
    public CeresStation() {
    	start();
    }
    
    /**
    *The method which starts all relevant processes on startup.
    *
    */
    public void start() {
    	player = new Player(0, 0, 0, 50, 50, "resources/images/God.png", null);
    	inputThread = new Input(this);
    	audioThread = new AudioLoop(this);
    	
    	inputThread.start();
        audioThread.start();
    }
    
    /**
    *The method which closes all relevant processes on program close.
    *
    */
    public void close() {
    	inputThread.stop();
    	audioThread.delete();
        audioThread.stop();
        
      //TODO: Add all delete methods here^^
    }
        
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
        CeresStation game = new CeresStation();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			DisplayUpdater.updateDisplay();
		}
		
		game.close();
		
		
		DisplayUpdater.closeDisplay();
	}
	
	/**
	*The method which gets the player object
	*@return The player object
	*/
	public Player getPlayer(){
		return this.player;	
	}
	
	/**
	*The method which gets the input thread object
	*@return The input thread object
	*/
	public Input getInput() {
		return this.inputThread;
	}
	
	/**
	*The method which gets the audio thread object
	*@return The audio thread object
	*/
	public AudioLoop getAudioLoop() {
		return this.audioThread;
	}
	
}
