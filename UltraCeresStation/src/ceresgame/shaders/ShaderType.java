/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * An abstract class which helps create new shaders of varying types for
 * different situations.
 * In this program, we really will only need to use one shader, but it leaves
 * precedent to make it infinitely polymorphable to the needs of the engine.
 * This file loads vertex and fragment shaders and uses them as a pipeline to
 * drawing and rendering objects in the camera view.
 * @author carmc9538
 */
public abstract class ShaderType {
    
    private int typeID;
    
    private int vertexShaderID;
    private int fragmentShaderID;
    
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    
    public ShaderType(String vertexFile,String fragmentFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        typeID = GL20.glCreateProgram();
        GL20.glAttachShader(typeID, vertexShaderID);
        GL20.glAttachShader(typeID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(typeID);
        GL20.glValidateProgram(typeID);
        getAllUniformLocations();
    }
     
    protected abstract void getAllUniformLocations();
     
    protected int getUniformLocation(String uniformName){
        return GL20.glGetUniformLocation(typeID,uniformName);
    }
     
    public void start(){
        GL20.glUseProgram(typeID);
    }
     
    public void stop(){
        GL20.glUseProgram(0);
    }
     
    public void delete(){
        stop();
        GL20.glDetachShader(typeID, vertexShaderID);
        GL20.glDetachShader(typeID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(typeID);
    }
     
    protected abstract void bindAttributes();
     
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(typeID, attribute, variableName);
    }
     
    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }
     
    protected void loadVector(int location, Vector3f vector){
        GL20.glUniform3f(location,vector.x,vector.y,vector.z);
    }
     
    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }
     
    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }
     
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!!!!!!");
        }
        return shaderID;
    }
    
}
