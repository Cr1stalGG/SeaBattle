package by.seabattle.utils;

import java.util.regex.Pattern;

import by.seabattle.entity.Command;
import by.seabattle.entity.enums.CommandType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandSplitter {
	private static final String COMMAND_REGEX = "^[a-zA-Z]{1} [0-9]{1,2}$";// { A 12} 
	private static final Pattern CLEAR_PATTERN = Pattern.compile("[\\s]+");

	
	public static int[] getAttackPos(String query) { //A 12
		int[] pos = new int[2];
		
		pos[0] = LetterToIntConvertor.convertLetterToInt(query.trim().split(" ")[0].charAt(0));
		pos[1] = Integer.valueOf(query.trim().split(" ")[1]);
		
		return pos;
	}
	
	public static Command getCommandByQuery(String query) {
		Command command = new Command();
		
		CLEAR_PATTERN.matcher(query).replaceAll(" ").trim();
		
		if(!checkCommand(query))
			return null;
		else if(!(query.toUpperCase().split(" ")[0] == "VERTICAL" || query.toUpperCase().split(" ")[0] == "HORIZONTAL"))
			command.setCommandType(CommandType.ATTACK);
		else {
			switch (query.toUpperCase().split("")[0]){
				case "VERTICAL": 
					command.setCommandType(CommandType.VERTICAL_ARRANGE);
					break;
					
				case "HORIZONTAL":
					command.setCommandType(CommandType.HORIZONTAL_ARRANGE);
					break;
				
				default:
					return null;
			}
		}	
			command.getPosition()[0] = LetterToIntConvertor.convertLetterToInt(query.toUpperCase().split(" ")[0].charAt(0));
			command.getPosition()[1] = Integer.valueOf(query.split(" ")[1]);
			
			return command;
		
	}
	
	public static boolean checkCommand(String command) {
		CLEAR_PATTERN.matcher(command).replaceAll(" ").trim();
		return Pattern.matches(COMMAND_REGEX, command);
	}
	
}
