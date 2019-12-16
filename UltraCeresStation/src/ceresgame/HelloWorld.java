package ceresgame;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;

public class HelloWorld{
	
	
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			DisplayUpdater.updateDisplay();
		}
		
		DisplayUpdater.closeDisplay();
	}
	
}