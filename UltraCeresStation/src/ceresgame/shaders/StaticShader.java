/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.shaders;

import org.lwjgl.util.vector.Matrix4f;

import ceresgame.graphics.gui.Camera;
import ceresgame.helpers.VectorMath;

/**
 * 
 * @author carmc9538
 */
public class StaticShader extends ShaderType {

  private final static String VERTEX_SHADER_FILE = "src/ceresgame/shaders/vertexShader.txt";
  private final static String FRAGMENT_SHADER_FILE = "src/ceresgame/shaders/fragmentShader.txt";
  
  private int location_transformationMatrix;
  private int location_projectionMatrix;
  private int location_viewMatrix;

  public StaticShader() {
      super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
  }

  @Override
  protected void bindAttributes() {
      super.bindAttribute(0, "position");
      super.bindAttribute(1, "textureCoordinates");
  }

  @Override
  protected void getAllUniformLocations() {
      location_transformationMatrix = super.getUniformLocation("transformationMatrix");
      location_projectionMatrix = super.getUniformLocation("projectionMatrix");
      location_viewMatrix = super.getUniformLocation("viewMatrix");
       
  }
   
  public void loadTransformationMatrix(Matrix4f matrix){
      super.loadMatrix(location_transformationMatrix, matrix);
  }
   
  public void loadViewMatrix(Camera camera){
      Matrix4f viewMatrix = VectorMath.createViewMatrix(camera);
      super.loadMatrix(location_viewMatrix, viewMatrix);
  }
   
  public void loadProjectionMatrix(Matrix4f projection){
      super.loadMatrix(location_projectionMatrix, projection);
  }


}
