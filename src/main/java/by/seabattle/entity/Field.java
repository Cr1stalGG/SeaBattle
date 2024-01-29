package by.seabattle.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Serializable{
	@Builder.Default
	private int[][] field = new int[16][16];
	@Builder.Default
	private Ship[] ships = new Ship[21];
}
