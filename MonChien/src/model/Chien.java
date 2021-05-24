package model;

import java.util.Date;

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
public class Chien {

	private Integer id;
	private Integer numeroPuce;
	private String nom;
	private String couleur;
	private Date dateNaissance;
	private Integer idRace;


}
