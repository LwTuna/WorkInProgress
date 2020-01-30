# CLIENT V2 - 3D Engine 


## What is What ? 

### VAO
A VAO(Vertex Array Object) is The List of Information about a model like the Vertex Positions, texture Coords, indices, normal coords ....    
A List of VBOs.
Has an ID. "VAO-ID"

Represents a "Model" in Code
### VBO

A VBO(Vertex Buffer Object) is an Array of those Information used for the VAO. So all the Vertex Positions are Stored in a VBO and the Put in the VAO.
Alias: AttributeList

### Raw Model
A Raw Model contains the id of a Model VAO and the vertex count

### Shaders
Programs Executed on the GPU to determine Colors/Positions on Screen    

MODEL DATA in VAO => |VertexShader| => per Vertex variables => |FragmentShader| => pixel Color     
Uniform Variables => |Vertex/Fragment Shader|

#### Uniform Variables
Variables from Java Code, changed by any time to change Shader behaviour
Can be Loaded/Changed from java by their locations    
#### Vertex Shader
Calculates The Vertex Positions on Screen and the Values(like Colors) used in the fragment Shader of each vertex    
Get the VAO Model Data as an Input

#### Fragment Shader
Gets the Output of the Vertex Shader     
Outputs the pixel Color    
Executed for every Pixel    
Mostly Mixes The Color of the vertices of the triangle



## Models

### Wavefront .obj File format
I Build an OBJ File Loader in the Engine but it uses a specific .obj export when working with blender    

IMPORTANT SETTINGS : 
- Geometry
    - Write normals
    - Include Uvs
    - Triangulate Faces
- Transform
    - -Z Forward
    - Y Up
    - Path Auto

#### Documentation    
for each line   
v = vertex Positon   
vt = texture Coord   
vn = normals   
f = vertex1/texture1/normal1 v2/t2/n2 v3/t3/n3 is one face/triangle out of 3 Vertices textureCord and normal indexes in the file 


### Textures
PNG Format
Blender Tut :   
Select All Faces   
Create new Image in UV Editing Tab   
Also in Modeling => ctrl+T to triangulate faces & Upper menu edge=>edge split   
Right Click Model=> UV Unwrap => Smart?   
Go To Texture Paint Tab => Select Object in Scene and Change Texture Slot from Material to Single Image   
Paint ^^   
Save Image Under the tabs View/Brush/Image => Select Image save as .png ...   
And export after setting the UV coords   

## Math

### Transformation Matrix

Transforms Size, Position and Rotation of a given Model to the Screen

### Projection Matrix

Calculate the Objects Position with the :
- Field of View Angle
- Near Plane Distance
- Far Plane Distance    
Matrix :    
|(1/tan(fov/2))/a | 0 | 0 | 0 |    
| 0 | 1/tan(fov/2) | 0 | 0 |   
| 0 | 0 | -zp/zm | -(2*Zfar*Znear)/zm |   
| 0 | 0 | -1 | 0 |   
a = Ascpect Ratio   
fov = Field Of View   
Zfar = far Plane distance   
ZNear = near Plane distance   
zm = Zfar - Znear   
zp = Zfar + Znear   

[Explanation Here](http://www.songho.ca/opengl/gl_projectionmatrix.html)

### View Matrix

The Offset of the Camera to draw Objects in relation to the camera Position