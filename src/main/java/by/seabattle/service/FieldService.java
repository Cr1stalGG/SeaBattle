package by.seabattle.service;

import by.seabattle.entity.Field;
import by.seabattle.entity.Ship;
import by.seabattle.utils.Randomizer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FieldService {
	private static final String HEADER = "          YOU                   ENEMY\n";
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
				if(myField.getField()[i-1][ii-1] == 2)
					System.out.print("#");
				else if(myField.getField()[i-1][ii-1] == 1)
					System.out.print("*");
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
	
	public static Field getRandomField() {
		Field field = new Field();
		
		int counter = 0;
		
		for(int i = 0; i < 6; ++i) 
			for(int ii = 0; ii < 6-i; ++ii) {
				field.getShips()[counter] = Ship.builder()
						.countOfCells(i+1)
						.cellsState(new boolean[i+1])
						.position(Randomizer.getRandomShipPos())
						.isVertical(Randomizer.getRandomBoolean())
						.build();
				
				counter++;
			}		
		
		for(Ship ship : field.getShips()) {	
			while(!isCorrectPos(ship, field.getField())) {
				ship.setPosition(Randomizer.getRandomShipPos());
				ship.setVertical(Randomizer.getRandomBoolean());
			}
				
			if(ship.isVertical()) 
				field.setField(verticalArrange(field.getField(), ship));
			
			else 
				field.setField(horizontalArrange(field.getField(), ship));
					
		}
		
		return field;
	}
	
	public static int[][] verticalArrange(int[][] field, Ship ship) {
		for(int i = 0; i < ship.getCountOfCells(); ++i) {
			field[ship.getPosition()[0] + i][ship.getPosition()[1]] = 2;
			
			if(i == 0 && ship.getPosition()[0] != 0) {
				field[ship.getPosition()[0] + i - 1][ship.getPosition()[1]] = 1;
				
				if(ship.getPosition()[1] + 1 <= 15)
					field[ship.getPosition()[0] + i - 1][ship.getPosition()[1] + 1] = 1;
				
				if(ship.getPosition()[1] - 1 >= 0)
					field[ship.getPosition()[0] + i - 1][ship.getPosition()[1] - 1] = 1;
			}
			
			if(ship.getPosition()[1] + 1 <= 15)
				field[ship.getPosition()[0] + i][ship.getPosition()[1] + 1] = 1;
			
			if(ship.getPosition()[1] - 1 >= 0)
				field[ship.getPosition()[0] + i][ship.getPosition()[1] - 1] = 1;
			
			if(i == ship.getCountOfCells() - 1 && ship.getPosition()[0] + ship.getCountOfCells()-1 <= 15) {
				field[ship.getPosition()[0] + i + 1][ship.getPosition()[1]] = 1;
				
				if(ship.getPosition()[1] + 1 <= 15)
					field[ship.getPosition()[0] + i + 1][ship.getPosition()[1] + 1] = 1;
				
				if(ship.getPosition()[1] - 1 >= 0)
					field[ship.getPosition()[0] + i + 1][ship.getPosition()[1] - 1] = 1;
			}
		}
		return field;
	}
	
	public static int[][] horizontalArrange(int[][] field, Ship ship) {
		for(int i = 0; i < ship.getCountOfCells(); ++i) {
			field[ship.getPosition()[0]][ship.getPosition()[1] + i] = 2;	
			
			if(i == 0 && ship.getPosition()[1] != 0 ) {
				field[ship.getPosition()[0]][ship.getPosition()[1] + i - 1] = 1;
				
				if(ship.getPosition()[0] + 1 <= 15)
					field[ship.getPosition()[0] + 1][ship.getPosition()[1] + i - 1] = 1;
					
				if(ship.getPosition()[0] - 1 >= 0)
					field[ship.getPosition()[0] - 1][ship.getPosition()[1] + i - 1] = 1;
			}
			
			if(ship.getPosition()[0] + 1 <= 15)
				field[ship.getPosition()[0] + 1][ship.getPosition()[1] + i] = 1;
			
			if(ship.getPosition()[0] - 1 >= 0)
				field[ship.getPosition()[0] - 1][ship.getPosition()[1] + i] = 1;
			
			if(i == ship.getCountOfCells() - 1 && ship.getPosition()[1] + i <= 15) {
				field[ship.getPosition()[0]][ship.getPosition()[1] + i + 1] = 1;
				
				if(ship.getPosition()[0] + 1 <= 15)
					field[ship.getPosition()[0] + 1][ship.getPosition()[1] + i + 1] = 1;
				
				if(ship.getPosition()[0] - 1 >= 0)
					field[ship.getPosition()[0] - 1][ship.getPosition()[1] + i + 1] = 1;
			}
			
		}
		
		return field;
	}
	
	public static boolean isCorrectPos(Ship ship, int[][] field) {
		boolean firstTry = ship.isVertical() && ship.getPosition()[0] + ship.getCountOfCells() <= 15 ||
				   	       !ship.isVertical() && ship.getPosition()[1] + ship.getCountOfCells() <= 15;
		
		if(!firstTry)
			return false;
		
		boolean secondTry = false;
			
		for(int i = 0; i < ship.getCountOfCells(); ++i)
			if(ship.isVertical()) {
				if(field[ship.getPosition()[0] + i][ship.getPosition()[1]] != 0)
					secondTry = false;
			} else {
				if(field[ship.getPosition()[0]][ship.getPosition()[1] + i] != 0)
					secondTry = false;
			}
		
		secondTry = true;
		
		return secondTry;	
	}
}
