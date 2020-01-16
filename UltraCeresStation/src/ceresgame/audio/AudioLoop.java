package ceresgame.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import ceresgame.main.CeresStation;
import org.lwjgl.openal.ALC10;

/**
 * This is the file which loops the main audio system, as well as allows other
 * classes to access the audio system
 *
 * @author camer, bartm6529
 *
 */
public class AudioLoop extends Thread {

    private boolean gameRunning = true;
    private boolean mainMusicPlaying = false;
    private boolean soundEffectPlaying = false;
    private boolean audioSystemInitialized = false;
    private AudioSource soundEffectSource;
    private AudioSource mainSource;

    private CeresStation game;

    private String path = new String();

    /**
     * Creating an instanced AudioLoop object
     * @param game The instanced game
     */
    public AudioLoop(CeresStation game) {
        this.game = game;
    }

    /**
     * Runs the looping main audio sound system, by first creating the audio
     * system itself
     */
    @Override
    public void run() {
        //Initializes the audio system
        System.out.println("Initializing the audio system...");
        AudioHandler.initialization();
        AudioHandler.setListenerData();
        System.out.println("... audio system has been initialized successfully!");
	    
        soundEffectSource = new AudioSource();
	mainSource = new AudioSource();
	    
        audioSystemInitialized = true;

        while(gameRunning) {
            //Path of wav file for main game audio
            path = "resources/audio/soundtrack.wav";
            //Buffer of the sound collected from the AudioHandler
            int buffer = AudioHandler.loadSound(path);
            //The main source of the audio to be played to the listener
            //Plays the buffer from the source
            mainSource.play(buffer);
            System.out.println("Now Playing: " + path);
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
    public void playSoundEffect(String path) {
	if (soundEffectPlaying) {
            //If sound effect has stopped, delete the source and buffers
            if (AL10.alGetSourcei(soundEffectSource.getSourceId(), AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED) {
                soundEffectSource.delete();
                soundEffectPlaying = false;
            }
	} else {
            //Creates new audio source 
            soundEffectSource = new AudioSource();
            //Buffer of the sound collected from the AudioHandler
            int buffer = AudioHandler.loadSound(path);
            System.out.println("Now Playing: " + path);
            //The main source of the audio to be played to the listener
            soundEffectSource = new AudioSource();
            //Plays the buffer from the source
            soundEffectSource.play(buffer);
            soundEffectPlaying = true;
            //Keep waiting for the sound effect has finished
        }
    }

    /**
     * Deletes the audio system and sources
     */
    public void delete() {
	soundEffectSource.delete();
	mainSource.delete();
        AL.destroy();
    }

    /**
     * Checks if the music system has been initialized (for external classes)
     *
     * @return The Audio System initialization state
     */
    public boolean getInitializationState() {
        return audioSystemInitialized;
    }

}
