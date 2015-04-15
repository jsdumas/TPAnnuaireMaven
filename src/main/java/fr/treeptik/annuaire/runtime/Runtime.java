package fr.treeptik.annuaire.runtime;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Numero;
import fr.treeptik.annuaire.model.Personne;
import fr.treeptik.annuaire.service.PersonneService;
import fr.treeptik.annuaire.utils.DateUtils;
import fr.treeptik.annuaire.utils.MenuUtil;

public class Runtime {

	public static void main(String[] args) throws ParseException, ServiceException, DAOException {

		PersonneService personneService = new PersonneService();
		
		Scanner scanner = new Scanner(System.in);
		String choix = "";
		List<String> choixDisponible = Arrays.asList("1", "2", "3", "4", "5", "6");

		do {
			// on affiche le menu
			MenuUtil.afficherMenu();
			choix = scanner.nextLine();
			// si le choix n'est pas disponible on réaffiche le menu
			if (!choixDisponible.contains(choix)) {
				continue;
			}
			// si le choix est égal à 5 on sort
			if (choix.equalsIgnoreCase("6"))
				break;

			switch (choix) {
			case "1":
				System.out.println("Création");
				Personne personne = new Personne();
				System.out.println("Entrez un nom : ");
				personne.setNom(scanner.nextLine());
				System.out.println("Entrez un prenom : ");
				personne.setPrenom(scanner.nextLine());
				System.out
						.println("Entrez une date de naissance (dd/MM/yyyy) : ");
				personne.setDateDeNaissance(DateUtils.stringToDate(scanner
						.nextLine()));
				
				do {
					
					System.out.println("Entrez un numéro de téléphone : ");
					String numero=scanner.nextLine();
					
					System.out.println("Entrez le type de téléphone : ");
					String type=scanner.nextLine();
					
					personne.setNumero(numero,type);
					
					// on affiche le menu
					MenuUtil.telephoneMenu();
					choix = scanner.nextLine();
					
					List<String> choixTelephone = Arrays.asList("c", "q");
					// si le choix n'est pas disponible on réaffiche le menu
					if (!choixTelephone.contains(choix)) {
						continue;
					}
					// si le choix est égal à 5 on sort
					if (choix.equalsIgnoreCase("q")){
						break;
					}

				} while (!choix.equalsIgnoreCase("q"));

				personneService.save(personne);
				System.out.println(personne + " correctement crée");
				break;
				
			case "2":
				System.out.println("Liste des personnes");
				List<Personne> personnes = personneService.findAllPersonne();
				for (Personne p : personnes) {
					System.out.println(p);
					System.out.println("\t\t\t Liste de numéros de téléphones :");
					List<Numero> numeros = p.getNumero();
					for (Numero n : numeros) {
						System.out.println("\t\t\t "+n);
					}
				}
				System.out.println("****************************************");
				break;
				
			case "3":
				System.out.println("Liste des personnes par ordre alphabétique :");
				List<Personne> personnesAlpha = personneService.findAllPersonneByAlphabeticalOrder();
				for (Personne p : personnesAlpha) {
					System.out.println(p);
					System.out.println("\t\t\t Liste de numéros de téléphones :");
					List<Numero> numeros = p.getNumero();
					for (Numero n : numeros) {
						System.out.println("\t\t\t "+n);
					}
				}
				System.out.println("****************************************");
				break;
				
			case "4":
				System.out.println("Liste des personnes par date de naissance :");
				List<Personne> personnesBirth = personneService.findAllPersonneByDateOfBirth();
				for (Personne p : personnesBirth) {
					System.out.println(p);
					System.out.println("\t\t\t Liste de numéros de téléphones :");
					List<Numero> numeros = p.getNumero();
					for (Numero n : numeros) {
						System.out.println("\t\t\t "+n);
					}
				}
				System.out.println("****************************************");
				break;
				
			case "5":
				System.out.println("Nombre d'enregistements dans l'annuaire : " + personneService.countRow());
				System.out.println("****************************************");
				break;
//			case "3":
//				System.out.println("Update de la personne");
//
//				System.out
//						.println("Entrez de l'id de la personne à mettre à jour : ");
//				personne = personneService.find(Integer.parseInt(scanner
//						.nextLine()));
//				System.out.println("Remplacer le nom " + personne.getNom()
//						+ ": ");
//				personne.setNom(scanner.nextLine());
//				System.out.println("Remplacer le prenom "
//						+ personne.getPrenom() + " : ");
//				personne.setPrenom(scanner.nextLine());
//				System.out
//						.println("Remplacer la date de naissance (dd/MM/yyyy) "
//								+ DateUtils.dateToString(personne
//										.getDateDeNaissance()) + " : ");
//				personne.setDateNaissance(DateUtils.stringToDate(scanner
//						.nextLine()));
//				System.out.println("Remplacer le nom de société "
//						+ personne.getNomOrganisation() + ": ");
//				personne.setNomOrganisation(scanner.nextLine());
//				System.out.println("Remplacer le numéro de téléphone"
//						+ personne.getNumeroDeTelephone() + " : ");
//				personne.setNumeroDeTelephone(scanner.nextLine());
//
//				personne = personneService.update(personne);
//
//				System.out.println(personne + " correctement mise à jour");
//				System.out.println("****************************************");
//				break;
				
//			case "4":
//				System.out.println("suppression de la personne");
//				System.out
//						.println("Entrez de l'id de la personne à supprimer : ");
//				personne = personneService.find(Integer.parseInt(scanner
//						.nextLine()));
//				personneService.remove(personne);
//				System.out.println(personne + " correctement supprimée");
//				personne = null;
//				break;
			}

		} while (!choix.equalsIgnoreCase("6"));

	}

}
