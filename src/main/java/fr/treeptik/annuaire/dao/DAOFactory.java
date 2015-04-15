package fr.treeptik.annuaire.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

import fr.treeptik.annuaire.dao.impl.PersonneFILEDAO;
import fr.treeptik.annuaire.dao.impl.PersonneJDBCDAO;
import fr.treeptik.annuaire.dao.impl.PersonneJPADAO;
import fr.treeptik.annuaire.dao.impl.PersonneXMLDAO;

public abstract class DAOFactory {

	private static PersonneDAO personneDAO;

	private static File file = new File("src/main/resources/conf.properties");
	private static InputStream input = null;
	//Properties : classe pour charger les fichiers
	private static Properties properties = new Properties();
	private static String choix = "";
	private Path path = FileSystems.getDefault().getPath(System.getProperty("conf.properties"));

	public DAOFactory() throws IOException {
//		WatchService watchService = FileSystems.getDefault().newWatchService();
//		WatchKey watchkey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
	}

	public static PersonneDAO getPersonneDAO() throws IOException {

		if (personneDAO == null) {
			try {
				input = new FileInputStream(file);
				properties.load(input);
				choix = properties.getProperty("conf.dao");
				System.out.println(properties.getProperty("conf.dao"));
				
				switch (choix) {
				case "jpa":
					personneDAO = new PersonneJPADAO();
					break;
				case "xml":
					personneDAO = new PersonneXMLDAO();
					break;
				case "jdbc":
					personneDAO = new PersonneJDBCDAO();
					break;
				case "file":
					personneDAO = new PersonneFILEDAO();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				input.close();
			}
		}
		return personneDAO;
	}
	
	public static String getChoix(){
		return choix;
	}

}
