package fr.treeptik.annuaire.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.treeptik.annuaire.dao.PersonneDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Numero;
import fr.treeptik.annuaire.model.Personne;
import fr.treeptik.annuaire.utils.DateUtils;

public class PersonneFILEDAO implements PersonneDAO {

	private File annuaire = new File("src/main/resources/annuaire.csv");
	List<Personne> personnes = null;

	@Override
	public void save(Personne personne) throws DAOException, ServiceException {
		// CREATION DE FICHIER : test si le fichier doit être créé dans le
		// /home/stagiaire
		try {
			if (annuaire.createNewFile())
				System.out.println("fichier créé");

			// //FILE WRITER
			FileWriter writer = new FileWriter(annuaire, true);// true permet de
																// rajouter du
																// texte à la
																// suite
			writer.write(personne.getNom() + ";" + personne.getPrenom() + ";"
					+ DateUtils.dateToString(personne.getDateDeNaissance())
					+ ";");
			for (Numero n : personne.getNumero()) {
				writer.write(n.getTel() + ";" + n.getType() + ";");
			}
			writer.write("\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Personne> findAllPersonne() throws DAOException {
		// //BUFFERED FILE READER
		FileReader reader = null;
		BufferedReader bufReader = null;
		personnes = new ArrayList<Personne>();
		Personne personne = null;
		
		// annuaire = openFile(annuaire);

		try {
			reader = new FileReader(annuaire);
			bufReader = new BufferedReader(reader);
			String line = "";

			while (bufReader.ready()) {
				line = bufReader.readLine();
				String[] fiche = line.split(";");

				personne = new Personne();
				personne.setNom(fiche[0]);
				personne.setPrenom(fiche[1]);
				Date date = DateUtils.stringToDate(fiche[2]);
				personne.setDateDeNaissance(date);

				for (int i = 3; i < fiche.length; i += 2) {
					personne.setNumero(fiche[i], fiche[i + 1]);
				}
				personnes.add(personne);
			}

			// Méthode 2
			// Permet de lire un groupe de mots ou mot tant que le délimiteur
			// n'a pas été atteint.
			// Scanner line = new Scanner(annuaire);
			// Scanner scanner = new Scanner(annuaire);

			// while(scanner.hasNextLine()){
			// while (scanner.hasNext()) {
			// scanner.useDelimiter(";");
			// personne = new Personne();
			// personne.setNom(scanner.next());
			// personne.setPrenom(scanner.next());
			// // System.out.println(scanner.next());
			// Date date = DateUtils.stringToDate(scanner.next());
			// personne.setDateDeNaissance(date);
			//
			// while(scanner.()){
			// String tel = scanner.next();
			// String type = scanner.next();
			// personne.setNumero(tel, type);
			// }
			// personnes.add(personne);
			// }
			// }
			//
			// scanner.close();

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personnes;
	}

	@Override
	public List<Personne> findAllPersonneByAlphabeticalOrder()
			throws DAOException {
		personnes = new ArrayList<Personne>();
		personnes = this.findAllPersonne();
		Collections.sort(personnes);

		return personnes;
	}

	@Override
	public List<Personne> findAllPersonneByDateOfBirth() throws DAOException {
	
		personnes = new ArrayList<Personne>();
		personnes = this.findAllPersonne();

		Collections.sort(personnes, new Comparator<Personne>(){
			public int compare(Personne o1, Personne o2){
				return o2.getDateDeNaissance().compareTo(o1.getDateDeNaissance());
			}
		});
		return personnes;
	}

	@Override
	public Long countRow() throws DAOException {
		
		personnes = new ArrayList<Personne>();
		personnes = this.findAllPersonne();
		long count = (long)personnes.size();
		
		return count;
	}

}
