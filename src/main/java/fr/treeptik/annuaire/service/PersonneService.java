package fr.treeptik.annuaire.service;

import java.util.List;

import fr.treeptik.annuaire.dao.DAOFactory;
import fr.treeptik.annuaire.dao.PersonneDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Personne;

public class PersonneService {

	private PersonneDAO personneDAO = null;

	public PersonneService() {
		try {
			this.personneDAO = DAOFactory.getPersonneDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**********************IMPL**********************************/

	public void save(Personne personne) throws ServiceException {

			try {
				personneDAO.save(personne);
			} catch (DAOException e) {
				throw new ServiceException("erreur service save personne", e);
			}
	}

	public List<Personne> findAllPersonne() throws ServiceException {
		try {
			return personneDAO.findAllPersonne();
		} catch (DAOException e) {
			throw new ServiceException("erreur service findAllPersonne", e);
		}
	}

	public List<Personne> findAllPersonneByAlphabeticalOrder()
			throws ServiceException {
		try {
			return personneDAO.findAllPersonneByAlphabeticalOrder();
		} catch (DAOException e) {
			throw new ServiceException(
					"erreur service findAllPersonneByAlphabeticalOrder", e);
		}
	}

	public List<Personne> findAllPersonneByDateOfBirth()
			throws ServiceException {
		try {
			return personneDAO.findAllPersonneByDateOfBirth();
		} catch (DAOException e) {
			throw new ServiceException(
					"erreur service findAllPersonneByAlphabeticalOrder", e);
		}
	}

	public Long countRow() throws ServiceException {
		try {
			return personneDAO.countRow();
		} catch (DAOException e) {
			throw new ServiceException(
					"erreur service findAllPersonneByAlphabeticalOrder", e);
		}
	}
	

	//
	// public void remove(Personne personne){
	// personneDAO.remove(personne);
	// }
	//
	// public Personne find(Integer id){
	// return personneDAO.find(id);
	// }
	//
	// public Personne update(Personne personne){
	// return personneDAO.update(personne);
	// }

}
