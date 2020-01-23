# final-project-ceres-station-masters
final-project-ceres-station-masters created by GitHub Classroom


This is an example product for a 3D game engine. It contains three types of objects: a player (an object which can be influenced by the input class), a graphical component (and object which can be influenced by events caused by the player or over time), and a camera (non-rendered object which is the viewport of the game). Additionally, all movements are meant to be made smoother by delta time, which is updated in the displayupdater class.

All input events are handled by the input class**

The player can move left and right with the 'a' and 'd' keys; and rotate about the y axis with the 'w' and 's' keys. There are currently no constraints on where the player can move, but most features just need logic to be added for the program to function. However, the graphics are defined by the shape of object from the vectormath function which finds the top left point of the object. The delta also makes the movement smoother using the player movement speed as a rate.

The snow falls normally based on the delta time, so it should fall at a smooth rate. however, it adds horizontal movement based on the sine of the delta, so that's why sometimes it doesn't move left much and a lot other times.

The camera view can be moved with the arrow keys and the zoom in and out uses the 'numpad 8' and 'numpad 2' keys. The view of the camera, however, is based on the projection matrix defined in the renderer class, which makes closer objects appear bigger, even if they're the same size as background objects. The view matrix is also adjusted by the inverse movement of the camera such that moving the camera left will move the objects right. Lastly, the objects are rendered by transforming them based on a transformation matrix using the position vector3f. As a result, the objects are then rendered by being added to an array list.

Lastly, I'm not sure why alpha blending doesn't work properly by showing the next layer of view. Although, the code which defines that is in the renderer prepare method. However, without what I did do, the blending would be nonexistent whatsoever. But one last thing, the depth test is the one opengl thing I know will always work b/c it's what determines which textures are displayed when other textures conceal parts of objects.

Also, while holding 'space', the scene changes to scene 2, which is a fire filled hellscape, which god (Ron T-Posing) watching close behind -- the text indicative of the power of the event. But along with that, different sounds should play when the player moves left or right while standing on the ground. Be wary, though, the fire sound lasts 4 seconds; unlike the snow sound.

Some next steps in the future would be to use a hashmap to render all of the same entity and so save time loading each same object over and over and over. Also, I would add multiplayer compatibility by stating each player has a specific id which is tied to a user. And finally, I would add the ability to input obj files for the coordinate system of a 3D model made in a program like blender.
