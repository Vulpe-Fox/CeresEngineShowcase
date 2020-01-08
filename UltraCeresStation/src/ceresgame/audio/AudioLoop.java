package ceresgame.audio;

import org.lwjgl.openal.AL10;

/**
 * This is the file which loops the main audio system, as well as allows other classes to access the audio system
 * @author camer
 *
 */
public class AudioLoop extends Thread {
	private boolean gameRunning = true;
	private boolean mainMusicPlaying = false;
	private boolean soundEffectPlaying = false;
	private static boolean audioSystemInitialized = false;
	
	private static String path = new String();
	
        /**
         * Creating an instanced AudioLoop object
         */
        public AudioLoop(){
            
        }
        
	/**
	 * Runs the looping main audio sound system, by first creating the audio system itself
	 */
	@Override
	public void run() {
		//Initializes the audio system
		System.out.println("Initializing the audio system...");
		AudioHandler.initialization();
		AudioHandler.setListenerData();
		System.out.println("... audio system has been initialized successfully!");
		
		audioSystemInitialized = true;
		
		while(gameRunning) {
			//Path of wav file for main game audio
			path = "";
			//Buffer of the sound collected from the AudioHandler
			int buffer = AudioHandler.loadSound(path);
			//The main source of the audio to be played to the listener
			AudioSource mainSource = new AudioSource();
			//Plays the buffer from the source
			mainSource.play(buffer);
			mainMusicPlaying = true;
			//Keep waiting for the music loop to finish
			while(mainMusicPlaying) {
				//If music has stopped, delete the source and buffers
				if(AL10.alGetSourcei(mainSource.getSourceId(), AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED){
					mainSource.delete();
					AudioHandler.delete();
					mainMusicPlaying = false;
				}
			}
		}
	}
	
	/**
	 * A method to run sound effects from the actionlistener
	 * @param path The path of the sound effect to be played
	 */
	public void playSoundEffect(String path){
		//Buffer of the sound collected from the AudioHandler
		int buffer = AudioHandler.loadSound(path);
		//The main source of the audio to be played to the listener
		AudioSource soundEffectSource = new AudioSource();
		//Plays the buffer from the source
		soundEffectSource.play(buffer);
		soundEffectPlaying = true;
		//Keep waiting for the sound effect has finished
		while(soundEffectPlaying) {
			//If sound effect has stopped, delete the source and buffers
			if(AL10.alGetSourcei(soundEffectSource.getSourceId(), AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED){
				soundEffectSource.delete();
				AudioHandler.delete();
				soundEffectPlaying = false;
			}
		}
	}
	
	/**
	 * Checks if the music system has been initialized (for external classes)
	 * @return The Audio System initialization state
	 */
	public static boolean getInitializationState() {
		return audioSystemInitialized;
	}

}
