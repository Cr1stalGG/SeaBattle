package by.seabattle.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LetterToIntConvertor {
	private static final String letters = "ABCDEFGHIJKLMNOP";
	
	public static int convertLetterToInt(char letter) {
		for (int i = 0; i < 16; ++i) 
			if(letters.charAt(i) == letter)
				return i;
		
		return -1;
	}
	
	public static char convertIntToLetter(int pos) {
		return letters.charAt(pos-1);
	}
}
