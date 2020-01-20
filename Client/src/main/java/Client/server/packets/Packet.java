package Client.server.packets;

import org.json.JSONObject;

public class Packet {

    private String key;

    public Packet(String key) {
	this.key = key;
    }
    
    public JSONObject toJSON() {
	JSONObject object = new JSONObject();
	object.put("key", key);
	return object;
    }
    
}
