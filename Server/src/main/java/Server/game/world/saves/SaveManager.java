package Server.game.world.saves;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class SaveManager {

	
	
	public static void save(String path,String value) throws IOException {
		File file = new File(path);
		File parentFolder = file.getParentFile();
		if(!parentFolder.exists()) parentFolder.mkdirs();
		if(!file.exists()) file.createNewFile();
		
		
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		br.write(value);
		br.close();
	}
	
	public static List<String> load(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		List<String> lines = new ArrayList<>();
		String line;
		while((line = reader.readLine())!= null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}
	
	public static JSONObject from(List<String> lines) {
		String ss = "";
		for(String s :lines) {
			ss+=s;
		}
		return new JSONObject(ss);
	}
}
