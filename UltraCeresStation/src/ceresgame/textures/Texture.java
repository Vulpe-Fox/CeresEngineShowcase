package ceresgame.textures;

/**
*An object type which contains the identifier of a texture
*
*/
public class Texture {

	private int textureID;
	
	/**
	*The object of a texture which doesn't contain the actual textures, but instead contains the reference to the object
	*@param texture The id of the texture referenced elsewhere
	*/
	public Texture(int texture) {
		this.textureID = texture;
	}
	/**
	*Gets the id of the texture
	*@return The integer id of the texture
	*/
	public int getID() {
		return this.textureID;
	}
	
}
