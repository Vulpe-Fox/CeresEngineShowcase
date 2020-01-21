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
    
    private int programID;
    
    private int vertexShaderID;
    private int fragmentShaderID;
    
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    
    /**
     *
     * @param vertexFile
     * @param fragmentFile
     */
    public ShaderType(String vertexFile, String fragmentFile){
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }
     
    /**
     *
     */
    protected abstract void getAllUniformLocations();
     
    /**
     *Used by the shader to get the uniform location of a uniform variable (e.g. projection, view, and transformation matrices)
     *@param uniformName The name of the uniform variable
     *@return The location of the uniform variable in stored memory
     */
    protected int getUniformLocation(String uniformName){
        return GL20.glGetUniformLocation(programID,uniformName);
    }
     
    /**
    *Tells opengl to start using this shader
    */
    public void start(){
        GL20.glUseProgram(programID);
    }
     
    /**
    *Tells opengl to stop using shaders in general
    */
    public void stop(){
        GL20.glUseProgram(0);
    }
     
    /**
    *Deletes all shader files from system memory
    */
    public void delete(){
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }
     
    /**
    *Binds an attribute name to an attribute id location (to reference vbo inputs)
    */
    protected abstract void bindAttributes();
     
    /**
     *Binds an attribute name to an attribute id location (to reference vbo inputs)
     *@param attribute The attribute array object in a buffer
     *@param variableName The name of the attribute
     */
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }
     
    /**
     *Used by the shader to load a float value into shader program
     *@param location The location of the float
     *@param value The value associated of the float
     */
    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }
     
    /**
     *Used by the shader to load a vector into shader program
     * @param location The location of the vector
     * @param vector The vector from which to load coordinates from
     */
    protected void loadVector(int location, Vector3f vector){
        GL20.glUniform3f(location, vector.getX(), vector.getY(), vector.getZ());
    }
     
    /**
     *Used by the shader to load a boolean value into shader program
     * @param location The location of the boolean
     * @param value The boolean true or false to be loaded into binary
     */
    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }
     
    /**
     *Used by the shader to load a matrix into shader program
     * @param location The location of the matrix
     * @param matrix The matrix, by which the buffer matrix value will be transformed by
     */
    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }
    
    /**
     * Used in order to load a shader txt file into a workable glsl shader
     * @param file The file location of the shader text file
     * @param type The type of shader this is (vertex/fragment)
     * @return 
     */
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!= null){
                shaderSource.append(line).append("\n");
            }
            System.out.println(shaderSource);
            reader.close();
        }catch(IOException e){
            System.out.println("Error reading file: " + file);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS ) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!!!!!!");
        }
        return shaderID;
    }
    
}
