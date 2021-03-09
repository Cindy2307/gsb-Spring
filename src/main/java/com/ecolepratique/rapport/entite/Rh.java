package com.ecolepratique.rapport.entite;

import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RH")
public class Rh extends Utilisateur{
	
	public Rh() {
		super();
	}

	public Rh(String login, String nom, String prenom, String adresse,
			int codePostal, String ville, LocalDate dateNaissance) {
		super(login, nom, prenom, adresse, codePostal, ville, dateNaissance);
	}

	@Override
	public String toString() {
		return "Rh []";
	}
}
