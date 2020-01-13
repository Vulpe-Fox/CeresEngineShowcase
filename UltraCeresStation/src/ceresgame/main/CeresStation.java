package ceresgame.main;

import ceresgame.audio.AudioLoop;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import ceresgame.graphics.DisplayUpdater;
import ceresgame.graphics.Renderer;
import ceresgame.helpers.VectorMath;
import ceresgame.main.userinterface.Input;
import ceresgame.map.GraphicalComponent;
import ceresgame.map.Player;
import ceresgame.models.RawModel;
import ceresgame.models.TexturedModel;
import ceresgame.shaders.StaticShader;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
*The main class containing the runner object which contains all the relevant objects, so they can all be connected together (avoiding static issues)
*
*/
public class CeresStation{
	
    private Player player;
    private Input inputThread;
    private AudioLoop audioThread;
    private float[] imageUVVerticies = {
	//Top left point
    	0,0,
	//Bottom left point
	0,1,
	//Bottom right point
	1,1,
	//Top right point
	1,0
    };
    
    private ArrayList<Integer> vaos = new ArrayList<>();
    private ArrayList<Integer> vbos = new ArrayList<>();
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
        float[] playerVerticies = VectorMath.genVertices(VectorMath.genVector(0, 0, 0, 5f, 5f), 5f, 5f);
        RawModel rawPlayerModel = generateRawModel(playerVerticies);
        //TexturedModel playerModel = new TexturedModel(rawPlayerModel, );
    	player = new Player(0, 0, 0, 5f, 5f, "resources/images/God.png", null); //Still need a model, so that's my next step
	    
	//Player component is at first position
	//addComponent(player);
		
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
        
        this.deleteVAOVBO();
        shader.delete();
        
        
      //TODO: Add all delete methods here^^
    }
        
	public static void main(String[] args) {
		DisplayUpdater.createDisplay();
		
                CeresStation game = new CeresStation();
		game.shader = new StaticShader();
                game.renderer = new Renderer(game.shader);
                
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
			//Update saved positions of graphicalComponents
			game.renderer.prepare();
			game.shader.start();
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
        
        private RawModel generateRawModel(float[] verticies) {
            int vaoID = createVAO();
            storeAttributeData(0, verticies);
            unbindVAO();
            return new RawModel(vaoID, verticies.length/3);
        }

        private int createVAO() {
            int vaoID = GL30.glGenVertexArrays();
            vaos.add(vaoID);
            GL30.glBindVertexArray(vaoID);
            return vaoID;
        }

        private void storeAttributeData(int attributeNumber, float[] verticies) {
            int vboID = GL15.glGenBuffers();
            vbos.add(vboID);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
            FloatBuffer buffer = storeVerticiesInFloatBuffer(verticies);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
            GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        }

        private void unbindVAO() {
            GL30.glBindVertexArray(0);
        }
        
        private FloatBuffer storeVerticiesInFloatBuffer(float[] verticies){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(verticies.length);
            buffer.put(verticies);
            buffer.flip();
            return buffer;
        }
        
        private void deleteVAOVBO(){
            vaos.forEach((vao) -> {
                GL30.glDeleteVertexArrays(vao);
        });
            vbos.forEach((vbo) -> {
                GL15.glDeleteBuffers(vbo);
        });
        }

    
}
