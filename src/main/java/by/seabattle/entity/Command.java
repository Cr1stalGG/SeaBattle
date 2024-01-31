package by.seabattle.entity;

import by.seabattle.entity.enums.CommandType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Command {
	private CommandType commandType;
	@Builder.Default
	private int[] position = new int[2];
}
