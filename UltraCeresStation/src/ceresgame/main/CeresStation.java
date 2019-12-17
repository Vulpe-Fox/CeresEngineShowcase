package ceresgame.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.main.userinterface.Input;

public class CeresStation{
	
        public static Input inputThread = new Input();
	
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
                inputThread.start();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			DisplayUpdater.updateDisplay();
		}
		
                inputThread.stop();
		DisplayUpdater.closeDisplay();
	}
	
}