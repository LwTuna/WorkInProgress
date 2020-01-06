# Client-Dokumentation

## JSON Packets send by Server

Every Packet is in JSON Format and contains a key:String attribut which is an identifier for the Packet type.

- {key:"loginResponse", succes:boolean}


## Saving World

Path : 

- /res
	- /saves
		- /maps
			-/mapName"                                 The Map name as String
				-/0-0"                                 Chunk Coords representet by "x-y" exmpl 0-0
					-/layers
						-/0.txt                        layerid.txt in format of a 2-D Array of id:meta tokens

## Next TODO

- Add Game class and Player handling
- Create login database
- Complete Tile.getTile method and add Tiles
- Add Player and Entity Managment
- Add Player Saving
	
