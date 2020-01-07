package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.main.userinterface.Input;
import ceresgame.map.Player;

public class CeresStation{
	
        private static Player player = new Player(0, 0, 0, 50, 50, "resources\\images\\God.png");
        private static Input inputThread = new Input(player);
        private static AudioLoop audioThread = new AudioLoop();
	
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
                inputThread.start();
                audioThread.start();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			DisplayUpdater.updateDisplay();
		}
		
                inputThread.stop();
                audioThread.stop();
		//TODO: Add all delete methods here^^
		DisplayUpdater.closeDisplay();
	}
	
}
