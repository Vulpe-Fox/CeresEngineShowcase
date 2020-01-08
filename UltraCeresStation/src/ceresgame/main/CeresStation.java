package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.main.userinterface.Input;
import ceresgame.map.Player;
import ceresgame.models.RawModel;
import ceresgame.models.TexturedModel;

public class CeresStation{
	
    private Player player;
    private Input inputThread;
    private AudioLoop audioThread;
	//private Camera camera = new Camera(this); //Please feed in a CeresStation object so you can reference the player
	
    public CeresStation() {
    	start();
    }
    
    public void start() {
    	player = new Player(0, 0, 0, 50, 50, "resources/images/God.png", null);
    	inputThread = new Input(this);
    	audioThread = new AudioLoop(this);
    	
    	inputThread.start();
        audioThread.start();
    }
    
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
	
	public Player getPlayer(){
		return this.player;	
	}
	
	public Input getInput() {
		return this.inputThread;
	}
	
	public AudioLoop getAudioLoop() {
		return this.audioThread;
	}
	
}
