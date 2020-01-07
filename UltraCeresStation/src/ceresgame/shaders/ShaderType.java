/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceresgame.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

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
    
    private static int loadShader(String filePath, int type){
        //Gets the shader file using the path to it
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        //Creates a useable shader using openGL glCreateShader method
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        //If the shader can't be compiled, output that it can't
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
        }
        //Return the integer shader ID
        return shaderID;
    }
    
    public ShaderType(String vertexFile,String fragmentFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        typeID = GL20.glCreateProgram();
        GL20.glAttachShader(typeID, vertexShaderID);
        GL20.glAttachShader(typeID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(typeID);
        GL20.glValidateProgram(typeID);
    }
    
    public void start(){
        GL20.glUseProgram(typeID);
    }
    
    public void stop(){
        GL20.glUseProgram(0);
    }
     
    public void cleanUp(){
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
    
}
