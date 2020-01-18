package ceresgame.graphics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import ceresgame.helpers.VectorMath;
import ceresgame.map.GraphicalComponent;
import ceresgame.models.RawModel;
import ceresgame.models.TexturedModel;
import ceresgame.shaders.StaticShader;

/**
 *The renderer class which renders objects into the opengl system so when the display updates, they appear
 * @author ivary45
 */
public class Renderer {
    
    private static final float FOV = 60;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
     
    private Matrix4f projectionMatrix;
    
    /**
     * Creates a renderer object and creates the projection matrix, because it only needs to be done once
     * @param shader The shader to load the projection matrix to
     */
    public Renderer(StaticShader shader){
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    
    /**
     * Prepares the rendering by:
     * - Enabling depth test which renders objects on top of one another depending on the z position
     * - Enabling alpha blending such that the empty parts of textures are ignored
     * = Setting the clear colour and clearing the screen to a blue colour
     */
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
    }
    
    /**
     * Renders an input entity on the screen
     * @param entity The entity to render
     * @param shader The shader used to render said object: loads the vertex and fragment shaders
     */
    public void render(GraphicalComponent entity, StaticShader shader) {
        //Sets the models using the input entity
        TexturedModel model = entity.getModel();
        RawModel rawModel = model.getRawModel();
        
        //Binds the vaos assigned to position and uv verticies of each part of the textures
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        
        //Loads a transformation matrix which shifts objects depending on where they are
        Matrix4f transformationMatrix = VectorMath.createTransformationMatrix(entity.getPosition());
        shader.loadTransformationMatrix(transformationMatrix);
        
        //Loads the texture and assigns the texture to the uniform sampler2D (As seen in fragment shader)
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        
        //Unassigns the vaos assigned to this object and resets data for next object to be rendered
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
    
    /**
     * Creates a projection matrix which scales up the size of objects depending on how far it is from the camera
     */
    private void createProjectionMatrix(){
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
        
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
