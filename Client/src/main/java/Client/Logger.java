package Client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Logger {

	
	private static List<String> log = new ArrayList<String>();
	private static List<String> err = new ArrayList<String>();
	
	public static void info(String...infos) {
		for(String s : infos) {
			s = getFormated(s);
			System.out.println(s);
			log.add(s);
		}
	}
	
	public static void err(Exception e) {
		e.printStackTrace();
		err.add(getFormated(e.getMessage()));
	}
	
	
	private static String getFormated(String in) {
		return "Client"+getTime()+" :"+in;
	}
	
	private static String getTime() {
		 SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");  
		 Date date = new Date();
		 return formatter.format(date);
	}
}
