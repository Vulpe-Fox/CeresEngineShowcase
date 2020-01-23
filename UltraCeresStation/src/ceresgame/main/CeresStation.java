package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.graphics.Renderer;
import ceresgame.graphics.gui.Camera;
import ceresgame.helpers.VectorMath;
import ceresgame.main.userinterface.Input;
import ceresgame.map.GraphicalComponent;
import ceresgame.map.Player;
import ceresgame.map.Snowflake;
import ceresgame.models.RawModel;
import ceresgame.models.TexturedModel;
import ceresgame.shaders.StaticShader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
*The main class containing the runner object which contains all the relevant objects, so they can all be connected together (avoiding static issues)
*
*/
public class CeresStation{
	
    //initialize graphical components
    private Player player;
    private GraphicalComponent background;
    private GraphicalComponent foreground;
    private GraphicalComponent[] snowflake = new GraphicalComponent[10];
    
    //initialize components for second world
    private GraphicalComponent background2;
    private GraphicalComponent foreground2;
    private GraphicalComponent god;
    private GraphicalComponent godChat;
    
    //initialize threads
    private Input inputThread;
    private AudioLoop audioThread;
    private Camera camera;
	
    private final float[] imageUVVerticies = {
	//Top left point
    	0,0,
	//Bottom left point
	0,1,
	//Bottom right point
	1,1,
	//Top right point
	1,0
    };

    private final int[] indiciesForRendering = {
	//Top left triangle
    	0,1,3,
	//Bottom right triangle
    	3,1,2
    };
    
    private final ArrayList<Integer> vaos = new ArrayList<>();
    private final ArrayList<Integer> vbos = new ArrayList<>();
    private final ArrayList<Integer> textures = new ArrayList<>();
    
    private final ArrayList<GraphicalComponent> components = new ArrayList<>();
    private final ArrayList<Snowflake> snowflakes = new ArrayList<>();
    private final ArrayList<GraphicalComponent> components2 = new ArrayList<>();
    private StaticShader shader;
    private Renderer renderer;
    
    private Vector3f snowflakePosition = new Vector3f(0f, 0f, -0.7f);
    private ceresgame.textures.ObjectTexture texture = new ceresgame.textures.ObjectTexture(loadTexture("resources/images/Snowflake.png"));
    private float[] snowflakeVerticies = VectorMath.genVertices(VectorMath.genVector(snowflakePosition.getX(), snowflakePosition.getY(), snowflakePosition.getZ(), 0.2f, 0.2f), 0.2f, 0.2f);
    private RawModel snowflakeRawModel = generateRawModel(snowflakeVerticies, imageUVVerticies, indiciesForRendering);
    private TexturedModel snowflakeModel = new TexturedModel(snowflakeRawModel, texture);
    private boolean area = false;
    
    //private Camera camera = new Camera(this); //Please feed in a CeresStation object so you can reference the player
	
    /**
    *The constructor of the main game object
    *
    */
    public CeresStation() {
    	start();
    }
    
    /**
    *The method which starts all relevant processes on startup.
    *
    */
    public void start() {
        
        //create textures for graphical components
        //ceresgame.textures.ObjectTexture godTexture = new ceresgame.textures.ObjectTexture(loadTexture("resources/images/God.png"));
        
        //create the objects out of the graphical components
    	player = genPlayer(new Vector3f(0, -0.2f, -1f), 0.2f, 0.2f, "resources/images/Ariff.png");
        background = genGraphicalComponent(new Vector3f(1.1f,-0.4f,-1.5f), 8f, 4f, "resources/images/Background.png");
        foreground = genGraphicalComponent(new Vector3f(0.26f, -0.05f,-0.5f), 2.2f, 2f, "resources/images/snowforeground.png");
        for (int i = 0; i < 10; i++) {
            snowflake[i] = genGraphicalComponent(new Vector3f((float) (Math.random() * 0.44) - 0.22f, (float) (Math.random() * 0.30) - 0.15f,-0.4f), 0.02f, 0.02f, "resources/images/Snowflake.png");
        }
        
        //create objects out of graphical components for world2
        background2 = genGraphicalComponent(new Vector3f(1.1f,-0.4f,-1.5f), 8f, 4f, "resources/images/firebackground.png");
        foreground2 = genGraphicalComponent(new Vector3f(0.26f, -0.05f,-0.5f), 2.2f, 2f, "resources/images/fireforeground.png");
        god = genGraphicalComponent(new Vector3f(0f, -0.1f, -1.2f), 2.5f, 2.5f, "resources/images/God.png");
        godChat = genGraphicalComponent(new Vector3f(-0.12f, 0.2f, -1.1f), 2f, 2f, "resources/images/godhasjoinedthechat.png");
        
        //List components from back to front for alpha blending to work
        components.add(background);
        components.add(player);
        components.add(foreground);
        for (int i = 0; i < 10; i++) {
            components.add(snowflake[i]);
        }
        
        //List components from second world
        components2.add(background2);
        components2.add(god);
        components2.add(godChat);
        components2.add(player);
        components2.add(foreground2);
        
    	inputThread = new Input(this, player);
    	audioThread = new AudioLoop(this);
    	
    	inputThread.start();
        audioThread.start();
    }
    
    /**
    *The method which closes all relevant processes on program close.
    *
    */
    public void close() {
    	inputThread.stop();
    	audioThread.delete();
        audioThread.stop();
        
        this.deleteVAOVBOTEXTURE();
        shader.delete();
        
        
      //TODO: Add all delete methods here^^
    }
    
    /**
     * The main class which runs the main game loop
     * @param args The command line arguments
     */    
    public static void main(String[] args) {
        //Creates display
        DisplayUpdater.createDisplay();

        //Creates game object, and assigns the game camera, shader, and renderer to be new objects
        CeresStation game = new CeresStation();
        game.camera = new Camera();
        game.shader = new StaticShader();
        game.renderer = new Renderer(game.shader);

        //Main game loop which renders objects so long as the f key or the x button aren't pressed
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
            //Generate a snowflake
            game.generateSnowflake();
            //Prepares rendering settings
            game.renderer.prepare();
            //Preps shader for use
            game.shader.start();
            //Loads the view matrix based on the camera's location
            game.shader.loadViewMatrix(game.camera);
            //Renders all objects
            game.render(game.renderer, game.shader);
            //Stops the memory use by the shader
            game.shader.stop();

            //Updates the display screen to show what has been rendered
            DisplayUpdater.updateDisplay();
        }

        //Stops all currently running threads and recovers memory from objects
        game.close();

        //Closes the display window
        DisplayUpdater.closeDisplay();
    }

    /**
     * Generates a player object
     * @param position The (x,y,z) position vector of a player
     * @param width The width of the player you want to display on the screen
     * @param height The height of the player you want to display on the screen
     * @param path The path to the player texture image file
     * @return The player created from the above parameters
     */
    private Player genPlayer(Vector3f position, float width, float height, String path){
        ceresgame.textures.ObjectTexture texture = new ceresgame.textures.ObjectTexture(loadTexture(path));
        float[] playerVerticies = VectorMath.genVertices(VectorMath.genVector(position.getX(), position.getY(), position.getZ(), width, height), width, height);
        RawModel rawModel = generateRawModel(playerVerticies, imageUVVerticies, indiciesForRendering);
        TexturedModel model = new TexturedModel(rawModel, texture);
        Player component = new Player(position.getX(), position.getY(), position.getZ(), width, height, model);
        return component;
    }
    
    /**
     * Generates a snowflake object
     * @param position The (x,y,z) position vector of a snowflake
     * @param width The width of the snowflake you want to display on the screen
     * @param height The height of the snowflake you want to display on the screen
     * @param model The model of the snowflake
     * @return The snowflake created from the above parameters
     */
    private Snowflake genSnowflake(Vector3f position, float width, float height, TexturedModel model){
        Snowflake component = new Snowflake(position.getX(), position.getY(), position.getZ(), width, height, model);
        return component;
    }

    /**
     * Generates a graphical component object
     * @param position The (x,y,z) position vector of a graphical component
     * @param width The width of the graphical component you want to display on the screen
     * @param height The height of the graphical component you want to display on the screen
     * @param path The path to the graphical component texture image file
     * @return The graphical component created from the above parameters
     */
    private GraphicalComponent genGraphicalComponent(Vector3f position, float width, float height, String path){
        ceresgame.textures.ObjectTexture texture = new ceresgame.textures.ObjectTexture(loadTexture(path));
        float[] objectVerticies = VectorMath.genVertices(VectorMath.genVector(position.getX(), position.getY(), position.getZ(), width, height), width, height);
        RawModel rawModel = generateRawModel(objectVerticies, imageUVVerticies, indiciesForRendering);
        TexturedModel model = new TexturedModel(rawModel, texture);
        GraphicalComponent component = new GraphicalComponent(position.getX(), position.getY(), position.getZ(), width, height, model);
        return component;
    }

    /**
    *The method which gets the player object
    *@return The player object
    */
    public Player getPlayer(){
        return this.player;	
    }

    /**
     * The method which gets the camera object
     * @return The camera object
     */
    public Camera getCamera(){
        return this.camera;
    }

    /**
    *The method which gets the input thread object
    *@return The input thread object
    */
    public Input getInput() {
            return this.inputThread;
    }

    /**
    *The method which gets the audio thread object
    *@return The audio thread object
    */
    public AudioLoop getAudioLoop() {
            return this.audioThread;
    }

    /**
    *Adds graphical components to the list of components being used
    *@param gc The graphical component being added
    */
    public void addComponent(GraphicalComponent gc) {
        components.add(gc);
    }
    
    /**
     * Adds graphical components to second list of components for world2
     * @param gc The graphical component being added
     */
    public void addComponent2(GraphicalComponent gc){
        components2.add(gc);
    }

    /**
    *Renders all graphicalComponents in the list
    *@param renderer The renderer used to render the graphical components
    *@param shader The shader used to position the graphical components onto the visual plane
    */
    public void render(Renderer renderer, StaticShader shader){
        if(this.area == false){
            for(int i = 0; i < components.size(); i++){
                renderer.render(components.get(i), shader);
            }
            for(int i = 0; i < snowflakes.size(); i++){
                if (snowflakes.get(i).getyPos() < -1.25f) {
                    snowflakes.remove(i);
                } else {
                    renderer.render(snowflakes.get(i), shader);
                }
            }
        }else{
            for(int i = 0; i < components2.size(); i++){
                renderer.render(components2.get(i), shader);
            }
        }
    }

    /**
     * Generates a raw model for an object by storing its verticies in a vao (array object), then binding the vaos into a vbo (buffer object) then disposes of the vaos
     * @param position The position vector of the object whose model is being created
     * @param textureCoords The texture coordinates being bound (found in variable definitions at the top of this file)
     * @param indicies The indicies which form the polys (triangles) where the textures will be rendered (found in variable definitions at the top of this file)
     * @return The raw model generated by this method
     */
    private RawModel generateRawModel(float[] position, float[] textureCoords, int[] indicies) {
        //Creates vertex array object
        int vaoID = createVAO();
        //Binds vao information into vertex buffer object
        bindIndicesBuffer(indicies);
        storeAttributeData(0, 3, position);
        storeAttributeData(1, 2, textureCoords);
        //Unbinds the vao
        unbindVAO();
        //Returns the raw model with stored vao locations and the number of indicies which need to be used to render the object (in most cases 6, 3 for each triangle of a 2D object)
        return new RawModel(vaoID, indicies.length);
    }

    /**
     * The method to create a vao which can store the verticies
     * @return The created vao
     */
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /**
     * Stores vao attributes into vbo coordinates such that the vbo can be referenced to find all the vao data
     * @param attributeNumber The number referencing the type of attribute which the vertex shader will read in
     * @param coordinateSize The size of each single coordinate (3 for positions, 2 for uv coordinates)
     * @param verticies The vertex array of the vao in question (triangles used for rendering)
     */
    private void storeAttributeData(int attributeNumber, int coordinateSize, float[] verticies) {
        System.out.println("Storing attribute array of size: " + coordinateSize);
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeVerticiesInFloatBuffer(verticies);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        System.out.println("Attrubute array stored in position: " + attributeNumber);
    }
    
    /**
     * Unbinds a vao from use, freeing up workable memory and prepping for next use
     */
    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    /**
     * Binds the indicies of the polys to the buffer array such that the buffer array is created and the type of buffer associated with it (verticies used to draw objects) is stored; also stores the verticies in a buffer so the program knows how each vertex associates with one another
     * @param indicies The list of poly verticies
     */
    private void bindIndicesBuffer(int[] indicies){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeVerticiesInIntBuffer(indicies);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Stores verticies in a buffer which can later be referenced to understand where each vertex connects to other verticies
     * @param verticies The list of poly verticies
     * @return The int buffer containing the list of poly verticies
     */
    private IntBuffer storeVerticiesInIntBuffer(int[] verticies){
            IntBuffer buffer = BufferUtils.createIntBuffer(verticies.length);
            buffer.put(verticies);
            buffer.flip();
            return buffer;
    }

    /**
     * Stores position and uv attributes to a the vbo as a float buffer
     * @param verticies The set of positions associated with a parameter
     * @return The buffer containing the identities of the verticies
     */
    private FloatBuffer storeVerticiesInFloatBuffer(float[] verticies){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(verticies.length);
        buffer.put(verticies);
        buffer.flip();
        return buffer;
    }

    /**
     * Deletes all leftover vaos, vbos, and textures at the texture locations
     */
    private void deleteVAOVBOTEXTURE(){
        vaos.forEach((vao) -> {
            GL30.glDeleteVertexArrays(vao);
        });
        vbos.forEach((vbo) -> {
            GL15.glDeleteBuffers(vbo);
        });
        textures.forEach((texture) -> {
            GL11.glDeleteTextures(texture);
        });
    }

    /**
     * Loads a texture from the path to a png file
     * @param path The path of the png file
     * @return Returns the id of the texture, to be stored
     */
    private int loadTexture(String path){
        Texture texture = null;
        try {
            texture = TextureLoader.getTexture("png", new FileInputStream(path));
        } catch (FileNotFoundException ex) {
            System.out.println("Image: " + path + " cannot be found!");
        } catch (IOException ex) {
            System.out.println("IO Error loading: " + path);
        }
        textures.add(texture.getTextureID());
        return texture.getTextureID();
    }

    private void generateSnowflake() {
        Snowflake snowflake = genSnowflake(snowflakePosition, 1f, 1f, snowflakeModel);
        snowflakes.add(snowflake);
    }
    
    private void deleteSnowflake(int i) {
        snowflakes.remove(i);
    }

    private void fallSnowflake() {
	
    }
        
        /**
         * 
         * @param area which scene will be displayed on the scene, area 1/2
         * @return True or false, true being area 2 and false being area 1
         */
        public boolean setArea(boolean area){
            this.area = area;
            return area;
        }
    
}
