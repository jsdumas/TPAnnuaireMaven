package fr.treeptik.annuaire.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fr.treeptik.annuaire.dao.PersonneDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Annuaire;
import fr.treeptik.annuaire.model.ObjectFactory;
import fr.treeptik.annuaire.model.Personne;
import fr.treeptik.annuaire.service.PersonneService;
import fr.treeptik.annuaire.utils.Count;



public class PersonneXMLDAO implements PersonneDAO{
	
	File file = new File("src/main/resources/annuaire.xml");
	private Integer count = null;
	private PersonneService personneService = null;
	private List <Personne> personnes = null;
	
	@Override
	public void save(Personne personne) throws DAOException, ServiceException {
		this.personneService = new PersonneService();
		this.personnes = personneService.findAllPersonne();
		Annuaire annuaire = null;
		count = count == null ? Count.initCount(personnes, this) : count;
		personne.setId(++count);
		
		try {
			JAXBContext context = JAXBContext
					.newInstance("fr.treeptik.annuaire.model");
			List<Personne> personnes = this.findAllPersonne();
			personnes.add(personne);
			
			Marshaller marshaller = context.createMarshaller();
			ObjectFactory factory = new ObjectFactory();
			annuaire = factory.createAnnuaire();
			annuaire.getPersonne().addAll(personnes);
			marshaller.marshal(annuaire, file);

		} catch (JAXBException e) {
			throw new DAOException("erreur create", e);
		}
	}

	@Override
	public List<Personne> findAllPersonne() throws DAOException {
		
		Annuaire annuaire = null;

		try {
			JAXBContext context = JAXBContext
					.newInstance("fr.treeptik.annuaire.model");
			
			if (!file.exists()) {
				file.createNewFile();
				Marshaller marshaller = context.createMarshaller();
				ObjectFactory factory = new ObjectFactory();
				annuaire = factory.createAnnuaire();
				marshaller.marshal(annuaire, file);
				return new ArrayList<Personne>();
			}

			Unmarshaller unmarshaller = context.createUnmarshaller();
			annuaire = (Annuaire) unmarshaller.unmarshal(file);

		} catch (JAXBException | IOException e) {
			throw new DAOException("Erreur create", e);
		}
		return annuaire.getPersonne();		
	}

	@Override
	public List<Personne> findAllPersonneByAlphabeticalOrder()
			throws DAOException {

		personnes=this.findAllPersonne();	
		Collections.sort(personnes);
		
		return personnes;		
	}

	@Override
	public List<Personne> findAllPersonneByDateOfBirth() throws DAOException {
		
		personnes=this.findAllPersonne();
		
		Collections.sort(personnes, new Comparator<Personne>(){
			public int compare(Personne o1, Personne o2){
				return o2.getDateDeNaissanceXML().compare(o1.getDateDeNaissanceXML());
			}
		});

		return personnes;
	}

	@Override
	public Long countRow() throws DAOException {
		personnes=this.findAllPersonne();
		return (long) personnes.size();
	}
	
	

}
