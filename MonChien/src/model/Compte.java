package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compte {

	private Integer id;
	private String email;
	private String password;
	private String role;
	private boolean bloque;
	private boolean active;
	private int id_client;

}
