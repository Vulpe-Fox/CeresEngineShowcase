package ceresgame.models;

import ceresgame.textures.ObjectTexture;

public class TexturedModel {
	
    private RawModel rawModel;
    private ObjectTexture texture;
     
    public TexturedModel(RawModel model, ObjectTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }
 
    public RawModel getRawModel() {
        return rawModel;
    }
 
    public ObjectTexture getTexture() {
        return texture;
    }
    
}
