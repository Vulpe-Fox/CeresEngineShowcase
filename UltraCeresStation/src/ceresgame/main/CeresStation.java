package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.graphics.Renderer;
import ceresgame.graphics.gui.Camera;
import ceresgame.helpers.VectorMath;
import ceresgame.main.userinterface.Input;
import ceresgame.map.Background;
import ceresgame.map.GraphicalComponent;
import ceresgame.map.Player;
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
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
*The main class containing the runner object which contains all the relevant objects, so they can all be connected together (avoiding static issues)
*
*/
public class CeresStation{
	
    //initialize graphical components
    private Player player;
    //private Background background;
    
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
    
    private ArrayList<Integer> vaos = new ArrayList<>();
    private ArrayList<Integer> vbos = new ArrayList<>();
    private ArrayList<Integer> textures = new ArrayList<>();
    
    private ArrayList<GraphicalComponent> components = new ArrayList<>();
    private StaticShader shader;
    private Renderer renderer;
    
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
        float height = 2f;
        
        //create textures for graphical components
        ceresgame.textures.Texture playerTexture = new ceresgame.textures.Texture(loadTexture("resources/images/Ariff.png"));
        //ceresgame.textures.Texture backgroundTexture = new ceresgame.textures.Texture(loadTexture("resources/images/Background.png"));
        
        //create verticies for graphical components
        float[] playerVerticies = VectorMath.genVertices(VectorMath.genVector(0, 0, 0, height, height), height, height);
        //float[] backgroundVerticies = VectorMath.genVertices(VectorMath.genVector(0, 0, 2, 1080f, 720f), 1080f, 720f);
        
        //generate the models for each graphical components
        RawModel rawPlayerModel = generateRawModel(playerVerticies, imageUVVerticies, indiciesForRendering);
        TexturedModel playerModel = new TexturedModel(rawPlayerModel, playerTexture);
        //RawModel rawBackgroundModel = generateRawModel(backgroundVerticies, imageUVVerticies, indiciesForRendering);
        //TexturedModel backgroundModel = new TexturedModel(rawBackgroundModel, backgroundTexture);
        
        //create the objects out of the graphical components
    	player = new Player(0, 0, 0, height, height, "resources/images/Ariff.png", playerModel); 
        //background = new Background(0, 0, 2, 1080f, 720F, "resources/images/Background.png", backgroundModel);
	    	
        components.add(player);
        
    	inputThread = new Input(this);
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
        
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
		
                CeresStation game = new CeresStation();
                game.camera = new Camera();
		game.shader = new StaticShader(game);
                game.renderer = new Renderer(game.shader);
                
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			//Update saved positions of graphicalComponents
			game.renderer.prepare();
			game.shader.start();
                        game.shader.loadViewMatrix(game.camera);
			game.render(game.renderer, game.shader);
			game.shader.stop();
			DisplayUpdater.updateDisplay();
		}
		
		game.close();
		
		
		DisplayUpdater.closeDisplay();
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
	*Renders all graphicalComponents in the list
	*@param renderer The renderer used to render the graphical components
	*@param shader The shader used to position the graphical components onto the visual plane
	*/
	public void render(Renderer renderer, StaticShader shader){
            for(int i = 0; i < components.size(); i++){
                renderer.render(components.get(i), shader);
            }
	}
        
        private RawModel generateRawModel(float[] verticies, float[] uvVerticies, int[] indicies) {
            int vaoID = createVAO();
	    bindIndicesBuffer(indicies);
            storeAttributeData(0, 3, verticies);
            storeAttributeData(1, 2, uvVerticies);
            unbindVAO();
            return new RawModel(vaoID, indicies.length);
        }

        private int createVAO() {
            int vaoID = GL30.glGenVertexArrays();
            vaos.add(vaoID);
            GL30.glBindVertexArray(vaoID);
            return vaoID;
        }

        private void storeAttributeData(int attributeNumber, int coordinateSize, float[] verticies) {
            int vboID = GL15.glGenBuffers();
            vbos.add(vboID);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
            FloatBuffer buffer = storeVerticiesInFloatBuffer(verticies);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }

        private void unbindVAO() {
            GL30.glBindVertexArray(0);
        }
	
	private void bindIndicesBuffer(int[] indicies){
	    int vboID = GL15.glGenBuffers();
	    vbos.add(vboID);
	    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
	    IntBuffer buffer = storeVerticiesInIntBuffer(indicies);
	    GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeVerticiesInIntBuffer(int[] verticies){
		IntBuffer buffer = BufferUtils.createIntBuffer(verticies.length);
		buffer.put(verticies);
            	buffer.flip();
            	return buffer;
	}
        
        private FloatBuffer storeVerticiesInFloatBuffer(float[] verticies){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(verticies.length);
            buffer.put(verticies);
            buffer.flip();
            return buffer;
        }
        
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
        
        private int loadTexture(String path){
            Texture texture = null;
            try {
                texture = TextureLoader.getTexture("png", new FileInputStream(path));
            } catch (FileNotFoundException ex) {
                System.out.println("Image: " + path + " cannot be found!");
            } catch (IOException ex) {
                System.out.println("IO Error loading: " + path);
            }
            int textureID = texture.getTextureID();
            textures.add(textureID);
            return textureID;
        }
        

    
}
