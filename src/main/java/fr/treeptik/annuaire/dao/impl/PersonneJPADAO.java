package fr.treeptik.annuaire.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.annuaire.dao.PersonneDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.model.Personne;

public class PersonneJPADAO implements PersonneDAO {

	private static EntityManager em = Persistence
			.createEntityManagerFactory("ANNUAIREPU").createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	@Override
	public void save(Personne personne) throws DAOException {
		transaction.begin();
		em.persist(personne);
		transaction.commit();
	}

	@Override
	public List<Personne> findAllPersonne() throws DAOException {
		TypedQuery<Personne> query = em.createNamedQuery(
				"findAllPersonne", Personne.class);
		return query.getResultList();
	}

	@Override
	public List<Personne> findAllPersonneByAlphabeticalOrder()
			throws DAOException {
		TypedQuery<Personne> query = em.createNamedQuery(
				"findAllPersonneByAlphabeticalOrder", Personne.class);
		return query.getResultList();
	}

	@Override
	public List<Personne> findAllPersonneByDateOfBirth() throws DAOException {
		TypedQuery<Personne> query = em.createNamedQuery(
				"findAllPersonneByDateOfBirth", Personne.class);
		return query.getResultList();
	}

	@Override
	public Long countRow() throws DAOException {
		TypedQuery<Long> query = em.createNamedQuery(
				"countRow", Long.class);
		return query.getSingleResult();
	}
	
	

	
	// Personne create(Personne personne) throws DAOException;
	// void remove(Personne personne);
	// Personne find(Integer id);
	// List<Personne> findAll() throws DAOException;
	// Personne update(Personne personne);

}
