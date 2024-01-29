package by.seabattle.service;

import by.seabattle.entity.Match;
import by.seabattle.entity.Player;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MatchService {
	public static Match getConfiguredMatch(Player main, Player friend) {
		Match match = new Match();
		
		match.getPlayers()[0] = main;
		match.getPlayers()[1] = friend;
		
		match.getFields()[0] = FieldService.getRandomField();
		match.getFields()[1] = FieldService.getRandomField();
		
		return match;
	}
}
