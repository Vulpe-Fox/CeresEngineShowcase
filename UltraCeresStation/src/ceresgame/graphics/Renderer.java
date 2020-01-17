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

public class Renderer {
    
    private static final float FOV = 60;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
     
    private Matrix4f projectionMatrix;
    
    public Renderer(StaticShader shader){
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(GraphicalComponent entity, StaticShader shader) {
        //System.out.println("Rendering: " + entity.getxPos());
        TexturedModel model = entity.getModel();
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix = VectorMath.createTransformationMatrix(entity.getPosition());
        System.out.println(transformationMatrix.m00 + " " + transformationMatrix.m01 + " " + transformationMatrix.m02 + " " + transformationMatrix.m03 + "\n" 
                + transformationMatrix.m10 + " " + transformationMatrix.m11 + " " + transformationMatrix.m12 + " " + transformationMatrix.m13 + "\n" 
                + transformationMatrix.m20 + " " + transformationMatrix.m21 + " " + transformationMatrix.m22 + " " + transformationMatrix.m23 + "\n" 
                + transformationMatrix.m30 + " " + transformationMatrix.m31 + " " + transformationMatrix.m32 + " " + transformationMatrix.m33 + "\n");
        shader.loadTransformationMatrix(transformationMatrix);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
    
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
