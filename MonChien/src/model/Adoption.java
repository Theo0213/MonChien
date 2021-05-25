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
public class Adoption {

	private Integer id;
	private java.sql.Date dateAdoption;
	private Integer idClient;
	private Integer idChien;
	private Integer idEtatAdoption;

}
