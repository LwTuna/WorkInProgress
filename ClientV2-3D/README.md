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

#### Vertex Shader
Calculates The Vertex Positions on Screen and the Values(like Colors) used in the fragment Shader of each vertex    
Get the VAO Model Data as an Input

#### Fragment Shader
Gets the Output of the Vertex Shader     
Outputs the pixel Color    
Executed for every Pixel    
Mostly Mixes The Color of the vertices of the triangle

## Textures
Just used PNG Imgs.
UV equal to XY at coordinate system
