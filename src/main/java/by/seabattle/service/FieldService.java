package by.seabattle.service;

import by.seabattle.entity.Field;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FieldService {
	private static final String HEADER = "          YOU                   ENEMY        \n";
	private static final String FOOTER = "\n   ABCDEFGHIJKLMNOP       ABCDEFGHIJKLMNOP";
	
	public static void showFields(Field myField, Field enemyField) {
		System.out.println(HEADER);
		
		for(int i = 1; i <= 16; ++i) {
			//MAIN
			if(i < 10)
				System.out.print(i + "  ");
			else
				System.out.print(i + " ");
			
			for(int ii = 1; ii <= 16; ++ii) 
				if(myField.getField()[i-1][ii-1] == 1)
					System.out.print("#");
				else
					System.out.print("O");
				
			
			//ENEMY
			if(i < 10)
				System.out.print("    " + i + "  ");
			else
				System.out.print("    " + i + " ");
			
			for(int ii = 1; ii <= 16; ++ii) 
				if(enemyField.getField()[i-1][ii-1] == 2)
					System.out.print("X");
				else if(enemyField.getField()[i-1][ii-1] == 1)
					System.out.print("/");
				else
					System.out.print("O");
			
			
			System.out.println();
		}
		
		System.out.print(FOOTER);
	}
}
