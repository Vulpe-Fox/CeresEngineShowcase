package ceresgame.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;


/**
 * This is the class which handles all audio endeavours sourced by other files
 * @author ivary45
 */
public class AudioHandler {
	
	private static ALCdevice device;
	private static ALCcontext context;

	private static ArrayList<Integer> buffers = new ArrayList<>();
	private static FileInputStream fileName;
	private static BufferedInputStream file;
	
	/**
	 * Initializes the audiosystem
	 */
	public static void initialization() {
                try {
                    AL.create();
                } catch (LWJGLException ex) {
                    Logger.getLogger(AudioHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            
		//Opens the audio device
		device = ALC10.alcOpenDevice(null);
		//Creates the audio context
		context = ALC10.alcCreateContext(device, (IntBuffer) null);
		//Sets the audio context to be the one in use
		ALC10.alcMakeContextCurrent(context);
	}
	
	/**
	 * Sets the information to do with the listener
	 */
	public static void setListenerData(){
		//Sets position of the listener to be at (0,0,0)
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
	}
	
	/**
	 * Loads a sound and generates buffers using a wav file path
	 * @param path The path of the file being loaded
	 * @return The buffer containing the pertinant wave file info
	 */
	public static int loadSound(String path) {
		//Gets file by finding file at filepath
		try{
			fileName = new FileInputStream(path);
			file = new BufferedInputStream(fileName);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//Generates buffers and adds them to a current buffer ArrayList
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		//Creates a functional waveFileFormat for the wav file
		WaveData waveFile = WaveData.create(file);
		//Assigns specific buffer to be storing data from the created waveFileFormat
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		//Disposes of the waveFileFormat now that it is stored in the buffer
		waveFile.dispose();
		//Returns the buffer for future use, just in case (so it's not stuck here)
		return buffer;
	}
	
	/**
	 * Deletes all buffers and destroys context
	 */
	public static void delete() {
		buffers.forEach((buffer) -> {AL10.alDeleteBuffers(buffer);});
		ALC10.alcDestroyContext(context);
	}	
	
}
