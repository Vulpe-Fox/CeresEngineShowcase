package ceresgame.models;

import ceresgame.textures.ObjectTexture;


/**
 * A model which can be drawn on by a texture
 * @author ivary45
 */
public class TexturedModel {
	
    private RawModel rawModel;
    private ObjectTexture objectTexture;
     
    /**
     * A textured model which is used to colour the shape of a raw model using a texture
     * @param model The raw model associated with the textured model
     * @param objectTexture The texture associated with the textured model
     */
    public TexturedModel(RawModel model, ObjectTexture objectTexture){
        this.rawModel = model;
        this.objectTexture = objectTexture;
    }
 
    /**
     * Gets the raw model associated with the textured model
     * @return The raw model
     */
    public RawModel getRawModel() {
        return rawModel;
    }
 
    /**
     * Gets the texture associated with the textured model
     * @return The texture
     */
    public ObjectTexture getTexture() {
        return objectTexture;
    }
    
}
