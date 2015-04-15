package fr.treeptik.annuaire.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.treeptik.annuaire.dao.PersonneDAO;
import fr.treeptik.annuaire.exception.DAOException;
import fr.treeptik.annuaire.exception.ServiceException;
import fr.treeptik.annuaire.model.Numero;
import fr.treeptik.annuaire.model.Personne;
import fr.treeptik.annuaire.utils.JDBCUtil;

public class PersonneJDBCDAO implements PersonneDAO {
	
	private List <Personne> personnes = null;


	@Override
	public void save(Personne personne) throws DAOException, ServiceException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		String insertPersonSql = "INSERT INTO Personne (nom, prenom, dateDeNaissance) VALUES (?, ?, ?)";
		String insertTelSql = "INSERT INTO Numero (tel, type) VALUES (?, ?)";
		String insertSql = "INSERT INTO Personne_Numero (Personne_id, numero_id) VALUES (?,?)";

		try {

			connection = JDBCUtil.getConnection();

			pstmt = connection.prepareStatement(insertPersonSql,Statement.RETURN_GENERATED_KEYS);

			// Remplir le statement avec l'objet personne
			pstmt.setString(1, personne.getNom());
			pstmt.setString(2, personne.getPrenom());
			Timestamp date = new Timestamp(personne.getDateDeNaissance().getTime());
			pstmt.setTimestamp(3, date);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			int idPersonne = 0;
			if(rs.next()){
				idPersonne=rs.getInt(1);
			}
			
			for (Numero n : personne.getNumero()) {
				pstmt = connection.prepareStatement(insertTelSql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, n.getTel());
				pstmt.setString(2, n.getType());
				pstmt.executeUpdate();
				
				ResultSet rs2 = pstmt.getGeneratedKeys();
				int idNumero = 0;
				if(rs2.next()){
					idNumero=rs2.getInt(1);
				}
				
				pstmt = connection.prepareStatement(insertSql);
				pstmt.setInt(1, idPersonne);
				pstmt.setInt(2, idNumero);
				pstmt.executeUpdate();
			}
			// remplacer le timestamp en date dans le script SQL ou la base de
			// donnée tpannuaire
			// pstmt.setDate(3, new Date
			// (personne.getDateDeNaissance().getTime()));
			// exécuter l'update
			// ResultSet rs = pstmt.getGeneratedKeys();
			// while(rs.next()){
			// personne.setId(rs.getInt("id"));
			// }

			// Gérer les différents types d'exception dans un seul catch
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new DAOException("erreur create personne", e);
		}

	}

	@Override
	public List<Personne> findAllPersonne() throws DAOException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		String selectSql = "SELECT p.id, p.nom, p.prenom, p.dateDeNaissance, n.id as id_numero, n.tel, n.type "
				+ "FROM Personne p "
				+ "JOIN Personne_Numero pn ON pn.Personne_id=p.id "
				+ "JOIN Numero n ON n.id=pn.numero_id";

		personnes = new ArrayList<Personne>();
		Personne  p =null;
		int id = 0;

		try {

			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(selectSql);
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				if(id!=rs.getInt("id")){
					p = new Personne(rs.getInt("id"), rs.getString("nom"),
							rs.getString("prenom"), rs.getDate("dateDeNaissance"));
				}
				
				p.setNumero(rs.getString("tel"), rs.getString("type"));
				
				if(id!=rs.getInt("id")){
					personnes.add(p);
				}
	
				id = rs.getInt("id");

			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new DAOException("Erreur filedao findall personne", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personnes;
	}

	@Override
	public List<Personne> findAllPersonneByAlphabeticalOrder()
			throws DAOException {
		
		personnes = this.findAllPersonne();
		
		Collections.sort(personnes);
		
		return personnes;
	}

	@Override
	public List<Personne> findAllPersonneByDateOfBirth() throws DAOException {

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
		Connection connection = null;
		PreparedStatement pstmt = null;
		String selectSql = "SELECT COUNT(id) AS c FROM Personne";
		
		long count = 0;
		
		try {

			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement(selectSql);
			pstmt.execute();
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			
			count = rs.getInt("c");

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new DAOException("Erreur filedao findall personne", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

}
