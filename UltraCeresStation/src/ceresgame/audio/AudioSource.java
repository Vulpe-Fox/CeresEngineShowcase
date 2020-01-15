package ceresgame.audio;

import org.lwjgl.openal.AL10;
/**
 * The source which plays to a listener from the audio system
 * @author camer
 *
 */
public class AudioSource {

	/**
	 * Identification of this audiosource
	 */
	private final int sourceId;
	
	/**
	 * Constructor of an audio source object
	 */
	public AudioSource() {
		//Generates sourceid as a valid source
		sourceId = AL10.alGenSources();
		//Sets gain to be a default value of 1
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1F);
		//Sets pitch to be a default value of 1
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1F);
		//Sets position of source to be a default position at position (0,0,0)
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}
	
	/**
	 * Plays a sound from a given source using the sourceid
	 * @param buffer the buffer storing the sound being played
	 */
	public void play(int buffer) {
		AL10.alSourcef(sourceId, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceId);
	}
	
	/**
	 * Stops a sound playing from a source
	 */
	public void stop() {
		AL10.alSourceStop(sourceId);
	}
	
	/**
	 * Deletes a source
	 */
	public void delete() {
		AL10.alDeleteSources(sourceId);
	}
	
	/**
	 * Get sourceid of a source
         * @return this sourceid
	 */
	public int getSourceId() {
		return this.sourceId;
	}
}
