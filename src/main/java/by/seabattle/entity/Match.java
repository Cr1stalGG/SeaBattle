package by.seabattle.entity;

import java.io.Serializable;
import java.util.Date;

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
public class Match implements Serializable{
	@Builder.Default
	private Field[] fields = new Field[2];
	@Builder.Default
	private Player[] players = new Player[2];
	@Builder.Default
	private Date dateOfStart = new Date();
}
