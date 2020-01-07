/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.shaders;

/**
 * 
 * @author carmc9538
 */
public class StaticShader extends ShaderType {

  private final String VERTEX_SHADER_FILE = "src/ceresgame/shaders/vertexShader.txt";
  private final String FRAGMENT_SHADER_FILE = "src/ceresgame/shaders/fragmentShader.txt";
  
  public StaticShader(){
    super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
  }
  
  @Override
  protected void bindAttributes(){
    super.bindAttribute(0, "position");
    super.bindAttribute(1, "textureCoords");
  }


}
