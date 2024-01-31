package by.seabattle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
@AllArgsConstructor
public class Match implements Serializable{
	private Map<Player, Field[]> match;
	
	public Match() {
		this.match = new HashMap<Player, Field[]>();
	}
}
