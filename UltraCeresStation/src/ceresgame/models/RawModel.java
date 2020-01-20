package ceresgame.models;

/**
 * A model which can be drawn on by colours
 * @author ivary45
 */
public class RawModel {
	
    private int vaoID;
    private int vertexCount;
     
    /**
     * A raw model object containing the id of its related vao and number of verticies
     * @param vaoID The id of the vertex array object
     * @param vertexCount The number of verticies of the model
     */
    public RawModel(int vaoID, int vertexCount){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }
 
    /**
     * Gets the id of the vertex array object
     * @return the id of the vao
     */
    public int getVaoID() {
        return vaoID;
    }
    /**
     * Gets the number of verticies of the model
     * @return the number of verticies of the model
     */
    public int getVertexCount() {
        return vertexCount;
    }
    
}
