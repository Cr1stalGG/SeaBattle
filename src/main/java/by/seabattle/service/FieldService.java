package by.seabattle.service;

import java.util.Iterator;

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
		
		Ship ship;
		
		for(int i = 0; i < 6; ++i) 
			for(int ii = 0; ii < 6-i; ++ii) {
				ship = new Ship();
				
				ship.setCountOfCells(i+1);
				ship.setCellsState(new boolean[i+1]);
				ship.setPosition(Randomizer.getRandomShipPos());
				ship.setVertical(Randomizer.getRandomBoolean());
								
				field.getShips()[counter] = ship;
				counter++;
			}
		
		int c = 0;
		for(Ship ship2 : field.getShips()) {	
			while(!isCorrectPos(ship2, field.getField())) {
				ship2.setPosition(Randomizer.getRandomShipPos());
				ship2.setVertical(Randomizer.getRandomBoolean());
			}
				
			for(int i = 0; i < ship2.getCountOfCells(); ++i)
				if(ship2.getPosition()[1] + i <= 15 && ship2.getPosition()[0] + i <= 15)
					if(ship2.isVertical()) {
						field.getField()[ship2.getPosition()[0] + i][ship2.getPosition()[1]] = 2;
						
						if(i == 0 && ship2.getPosition()[0] != 0) {
							field.getField()[ship2.getPosition()[0] + i - 1][ship2.getPosition()[1]] = 1;
							
							if(ship2.getPosition()[1] + 1 <= 15)
								field.getField()[ship2.getPosition()[0] + i - 1][ship2.getPosition()[1] + 1] = 1;
							
							if(ship2.getPosition()[1] - 1 >= 0)
								field.getField()[ship2.getPosition()[0] + i - 1][ship2.getPosition()[1] - 1] = 1;
						}
						
						if(ship2.getPosition()[1] + 1 <= 15)
							field.getField()[ship2.getPosition()[0] + i][ship2.getPosition()[1] + 1] = 1;
						
						if(ship2.getPosition()[1] - 1 >= 0)
							field.getField()[ship2.getPosition()[0] + i][ship2.getPosition()[1] - 1] = 1;
						
						if(i == ship2.getCountOfCells() - 1 && ship2.getPosition()[0] + ship2.getCountOfCells()-1 <= 15) {
							field.getField()[ship2.getPosition()[0] + i + 1][ship2.getPosition()[1]] = 1;
							
							if(ship2.getPosition()[1] + 1 <= 15)
								field.getField()[ship2.getPosition()[0] + i + 1][ship2.getPosition()[1] + 1] = 1;
							
							if(ship2.getPosition()[1] - 1 >= 0)
								field.getField()[ship2.getPosition()[0] + i + 1][ship2.getPosition()[1] - 1] = 1;
						}
					}
					else {
						field.getField()[ship2.getPosition()[0]][ship2.getPosition()[1] + i] = 2;	
						
						if(i == 0 && ship2.getPosition()[1] != 0 ) {
							field.getField()[ship2.getPosition()[0]][ship2.getPosition()[1] + i - 1] = 1;
							
							if(ship2.getPosition()[0] + 1 <= 15)
								field.getField()[ship2.getPosition()[0] + 1][ship2.getPosition()[1] + i - 1] = 1;
								
							if(ship2.getPosition()[0] - 1 >= 0)
								field.getField()[ship2.getPosition()[0] - 1][ship2.getPosition()[1] + i - 1] = 1;
						}
						if(ship2.getPosition()[0] + 1 <= 15)
							field.getField()[ship2.getPosition()[0] + 1][ship2.getPosition()[1] + i] = 1;
						
						if(ship2.getPosition()[0] - 1 >= 0)
							field.getField()[ship2.getPosition()[0] - 1][ship2.getPosition()[1] + i] = 1;
						
						if(i == ship2.getCountOfCells() - 1 && ship2.getPosition()[1] + i <= 15) {
							field.getField()[ship2.getPosition()[0]][ship2.getPosition()[1] + i + 1] = 1;
							
							if(ship2.getPosition()[0] + 1 <= 15)
								field.getField()[ship2.getPosition()[0] + 1][ship2.getPosition()[1] + i + 1] = 1;
							
							if(ship2.getPosition()[0] - 1 >= 0)
								field.getField()[ship2.getPosition()[0] - 1][ship2.getPosition()[1] + i + 1] = 1;
						}
					}
		}
		
		return field;
	}
	
	private static boolean isCorrectPos(Ship ship, int[][] field) {
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
