package fr.treeptik.annuaire.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.treeptik.annuaire.model.Personne;

public class CompareDateOfBirth {
	
	public void dateOfBirthSort(List<Personne> personnes){
		Collections.sort(personnes, new Comparator <Personne>(){
			public int compare(Personne o1, Personne o2){
				return o2.getDateDeNaissanceXML().compare(o1.getDateDeNaissanceXML());
			}
		});
	}
}
