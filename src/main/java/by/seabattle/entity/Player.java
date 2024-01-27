package by.seabattle.entity;

import java.io.Serializable;

import by.seabattle.entity.enums.Role;
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
@NoArgsConstructor
public class Player implements Serializable{
	private String name;
	@Builder.Default
	private Role role = Role.ROLE_USER;
}
