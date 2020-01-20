/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.shaders;

import ceresgame.graphics.gui.Camera;
import org.lwjgl.util.vector.Matrix4f;

import ceresgame.helpers.VectorMath;

/**
 * This is an example of a shader type which can use a different shader file from another shader type; this is the one used in the program
 * @author carmc9538
 */
public class StaticShader extends ShaderType {

    private final static String VERTEX_SHADER_FILE = "src/ceresgame/shaders/vertexShader.txt";
    private final static String FRAGMENT_SHADER_FILE = "src/ceresgame/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    /**
     *A static shader created using the shader type file
     */
    public StaticShader() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    /**
     *Binds the attributes of an object to array 0 being position and array 1 being texture uv coordinates
     */
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }

    /**
     *Gets the uniform locations of each matrix (referenced by the vertex shader)
     */
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

    }

    /**
     * Loads the transformation matrix of an object
     * @param matrix The object's transformation matrix
     */
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    /**
     * Loads the view matrix of a camera
     * @param camera The camera
     */
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = VectorMath.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    /**
     * Loads the projection matrix created in the renderer
     * @param projection The projection matrix
     */
    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

}
