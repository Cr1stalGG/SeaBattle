package by.seabattle.service;

import java.io.IOException;
import java.util.Scanner;

import by.seabattle.entity.Match;
import by.seabattle.entity.Player;
import by.seabattle.entity.enums.Role;
import by.seabattle.utils.Serializer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameService {
	private static final String MAIN_NICKNAME_UI = "$INPUT YOUR NICKNAME: ";
	private static final String FRIEND_NICKNAME_UI = "$INPUT NICKNAME OF SECOND PLAYER: ";
	private static final String GAME_MODE_UI = "$CHOOSE GAME MODE:\n1. SINGLEPLAYER\n2. MULTIPLAYER";
	
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void startGame() {
		Player player = new Player();
		player.setRole(Role.ROLE_USER);
		
		System.out.print(MAIN_NICKNAME_UI);
		player.setName(scanner.next());
		
		System.out.println(GAME_MODE_UI);
		
		
		int game_mode = -1;
		
		do {
			game_mode = scanner.nextInt();
			
			if(game_mode != 1 || game_mode != 2)
				System.out.println("$Incorrect input data!");
		} while(game_mode != 1 || game_mode != 2);
		
		switch (game_mode) {
			case 1: 
				singleplayer(player);
				break;
			
			case 2:
				multiplayer(player);
				break;
		
			default:
				throw new IllegalArgumentException("Unexpected value: " + game_mode);
		}
		
	}
	
	private void singleplayer(Player player) {
		Player bot = new Player("Bot", Role.ROLE_USER);
		
		Match match = MatchService.getConfiguredMatch(player, bot);
		
	}
	
	private void multiplayer(Player mainPlayer) {
		Player friendPlayer = new Player();
		friendPlayer.setRole(Role.ROLE_USER);
		
		System.out.print(FRIEND_NICKNAME_UI);
		friendPlayer.setName(scanner.next());
		
		Match match = MatchService.getConfiguredMatch(mainPlayer, friendPlayer);
		
		
		
		try {
			Serializer.serialize(match);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
