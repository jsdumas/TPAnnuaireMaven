package fr.treeptik.annuaire.dao;

import java.util.List;

import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Personne;



public interface PersonneDAO {
	
	void save(Personne personne) throws DAOException, ServiceException;
	List<Personne> findAllPersonne() throws DAOException;
	List<Personne> findAllPersonneByAlphabeticalOrder() throws DAOException;
	List<Personne> findAllPersonneByDateOfBirth() throws DAOException;
	Long countRow() throws DAOException;

}
