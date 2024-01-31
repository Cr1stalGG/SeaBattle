package by.seabattle.service;

import java.util.Scanner;

import by.seabattle.entity.Field;
import by.seabattle.entity.Match;
import by.seabattle.entity.Player;
import by.seabattle.entity.enums.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MatchService {
	private static final String GENERATION_OF_FIELD = "%s, do you want to place ships automatically?\n1. Yes\n2. No";
	
	private static final Scanner scanner = new Scanner(System.in);
	
	public static Match getConfiguredMatch(Player main, Player enemy) {
		Match match = new Match();
		
		match.getMatch().put(main, new Field[2]);
		match.getMatch().put(enemy, new Field[2]);
		
		
		System.out.println(String.format(GENERATION_OF_FIELD, main.getName()));
		
		int choice = -1;
		
		while(!(choice == 1 || choice == 2)) {
			choice = scanner.nextInt();
			
			if(!(choice == 1 || choice == 2)) {
				System.out.println("$Incorrect input data!");
				System.out.println(String.format(GENERATION_OF_FIELD, enemy.getName()));
			}
		}
		
		switch (choice) {
			case 1: 
				match.getMatch().get(main)[0] = FieldService.getRandomField();
				match.getMatch().get(main)[1] = new Field();
				break;
				
			case 2:
				match.getMatch().get(main)[1] = new Field();
				
				match.getMatch().get(main)[0] = new Field();
				
				//TODO let user create field itself
				
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		if(enemy.getRole() != Role.ROLE_BOT) {
			System.out.println(String.format(GENERATION_OF_FIELD, enemy.getName()));
			
			while(!(choice == 1 || choice == 2)) {
				choice = scanner.nextInt();
				
				if(!(choice == 1 || choice == 2)) {
					System.out.println("$Incorrect input data!");
					System.out.println(String.format(GENERATION_OF_FIELD, enemy.getName()));
				}
			}
			
			switch (choice) {
				case 1: 
					match.getMatch().get(enemy)[0] = FieldService.getRandomField();
					match.getMatch().get(enemy)[1] = new Field();
					
					break;
					
				case 2:
					match.getMatch().get(enemy)[1] = new Field();
					
					match.getMatch().get(enemy)[0] = new Field();
					
					//TODO let user create field itself
					
					break;
					
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} else {
			match.getMatch().get(enemy)[0] = FieldService.getRandomField();
			match.getMatch().get(enemy)[1] = new Field(); 
		}
		return match;
	}
}
