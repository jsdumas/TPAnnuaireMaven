//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.04.08 à 02:06:40 PM CEST 
//

package fr.treeptik.annuaire.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import fr.treeptik.annuaire.utils.DateUtils;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllPersonne", query = "SELECT p FROM Personne p"),
		@NamedQuery(name = "findAllPersonneByAlphabeticalOrder", query = "SELECT p FROM Personne p ORDER BY p.nom, p.prenom"),
		@NamedQuery(name = "findAllPersonneByDateOfBirth", query = "SELECT p FROM Personne p ORDER BY p.dateDeNaissance"),
		@NamedQuery(name = "countRow", query = "SELECT COUNT(p) AS nb_enregistrement FROM Personne p") })
/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateDeNaissanceXML" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element ref="{}numero" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "nom", "prenom", "dateDeNaissanceXML",
		"numero" })
@XmlRootElement(name = "personne")
public class Personne implements Comparable<Personne> {

	@Id
	@GeneratedValue
	protected int id;
	@XmlElement(required = true)
	protected String nom;
	@XmlElement(required = true)
	protected String prenom;
	@Transient
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar dateDeNaissanceXML;
	@XmlTransient
	protected Date dateDeNaissance;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@XmlElement(required = true)
	protected List<Numero> numero;

	public Personne() {
	}

	public Personne(int id, String nom, String prenom, Date dateDeNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateDeNaissance=" + this.getDateDeNaissance() + ", dateDeNaissanceXML=" + dateDeNaissanceXML +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * Obtient la valeur de la propriété id.
	 * 
	 */
	public int getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 * 
	 */
	public void setId(int value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété nom.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit la valeur de la propriété nom.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNom(String value) {
		this.nom = value;
	}

	/**
	 * Obtient la valeur de la propriété prenom.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Définit la valeur de la propriété prenom.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPrenom(String value) {
		this.prenom = value;
	}

	/**
	 * Obtient la valeur de la propriété dateNaissance.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDateDeNaissanceXML() {

		
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(this.dateDeNaissance);

			try {
				dateDeNaissanceXML = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(calendar);
			} catch (DatatypeConfigurationException e) {

		}
		return dateDeNaissanceXML;
	}

	/**
	 * Définit la valeur de la propriété dateNaissance.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDateDeNaissanceXML(Date dateDeNaissance)
			throws DatatypeConfigurationException {

		// this.dateDeNaissance=dateDeNaissance;

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(dateDeNaissance);
		this.dateDeNaissanceXML = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(calendar);
	}

	/**
	 * Gets the value of the dateDeNaissance property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public Date getDateDeNaissance() {
		if (this.dateDeNaissance == null && this.dateDeNaissanceXML != null) {
			this.dateDeNaissance = this.dateDeNaissanceXML.toGregorianCalendar().getTime();
		}
		return dateDeNaissance;
	}

	/**
	 * Sets the value of the dateDeNaissance property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDateDeNaissance(Date value) {
		
		try {
			this.setDateDeNaissanceXML(value);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		this.dateDeNaissance = value;
	}

	/**
	 * Gets the value of the numero property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the numero property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNumero().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Numero }
	 * 
	 * 
	 */
	public List<Numero> getNumero() {
		if (numero == null) {
			numero = new ArrayList<Numero>();
		}
		return this.numero;
	}

	public void setNumero(String num, String type) {
		if (this.numero == null) {
			this.numero = new ArrayList<Numero>();
		}
		Numero n = new Numero(num, type);
		this.numero.add(n);
	}

	@Override
	public int compareTo(Personne p) {
		// final int BEFORE = -1;
		// final int EQUAL = 0;
		// final int AFTER = 1;

		int FirstnameDiff = this.nom.compareToIgnoreCase(p.nom);
		int LastNameDiff = this.prenom.compareToIgnoreCase(p.prenom);

		if (FirstnameDiff != 0) {
			return FirstnameDiff;
		} else if (LastNameDiff != 0)
			return LastNameDiff;

		return 0;
	}

}
