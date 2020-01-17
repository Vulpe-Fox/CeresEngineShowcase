package ceresgame.models;

import ceresgame.textures.ObjectTexture;

public class TexturedModel {
	
    private RawModel rawModel;
    private ObjectTexture objectTexture;
     
    public TexturedModel(RawModel model, ObjectTexture objectTexture){
        this.rawModel = model;
        this.objectTexture = objectTexture;
    }
 
    public RawModel getRawModel() {
        return rawModel;
    }
 
    public ObjectTexture getTexture() {
        return objectTexture;
    }
    
}
