package by.seabattle.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import by.seabattle.entity.Match;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Serializer { //TODO test is path correct
	private static final String MATCHES_FILE_PATH = "../../resources/matches.txt";
	
	public static void serialize(Object object) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(MATCHES_FILE_PATH);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(object);
		
		objectOutputStream.close();
	
	}
	
	public static List<Match> deserializeMatches() throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(MATCHES_FILE_PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
        if(objectInputStream.readObject() != null)
        	return (List<Match>)objectInputStream.readObject();
        
        return new ArrayList<Match>();

	}
}
