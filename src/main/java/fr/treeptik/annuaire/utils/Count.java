package fr.treeptik.annuaire.utils;

import java.util.List;

import fr.treeptik.annuaire.dao.impl.PersonneXMLDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Personne;
import fr.treeptik.annuaire.service.PersonneService;


public class Count {
	
	public static int initCount(List<Personne> pl, PersonneXMLDAO ps) throws DAOException, ServiceException {
		try {
			List<Personne> personnes = pl;
			if (!personnes.isEmpty()) {
				return ps.findAllPersonne().stream().mapToInt(p -> p.getId()).max()
						.getAsInt();
			} else
				return 0;

		} catch (DAOException e) {
			throw new DAOException("erreur init count", e);
		}
	}

}
