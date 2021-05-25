package model;

import java.sql.Date;

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
public class Adoption {

	private int id;
	private Date dateAdoption;
	private int idClient;
	private int idChien;
	private int idEtatAdoption;

}
