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
public class Adresse {

	private Integer id;
	private String ligne1;
	private String ligne2;
	private String lieu;
	private int codePostal;
	private String ville;
	private String pays;

}
