package ceresgame.helpers;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import ceresgame.graphics.gui.Camera;

/**
*A helper class which can do the vectoral math to create specific 4f matrices
*[x1 y1 z1 | d1]
*[x2 y2 z2 | d2]
*[x3 y3 z3 | d3]
*[x4 y4 z4 | d4]
*@author ivary45
*/
public class VectorMath {
	
     /**
     *Creates a transformation matrix for any entity whose model is being reused
     *@param translation The vector (x,y,z) where the object is
     *@param rx The rotation of the object in the x direction, in degrees (set to 0)
     *@param ry The rotation of the object in the y direction, in degrees (set to 0)
     *@param rz The rotation of the object in the z direction, in degrees (set to 0)
     *@param scale The scalar multiplier of the size of an object (set to 1)
     *@return the transformation matrix created using the object's parameters
     */
     public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
        return matrix;
    }
    
    /**
    *Creates a view matrix for the view coming from a camera
    *@param camera The camera which's view is being used
    *@return The view matrix created using the camera
    */
    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
}
