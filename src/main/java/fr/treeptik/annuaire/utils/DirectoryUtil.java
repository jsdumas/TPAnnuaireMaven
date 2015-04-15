package fr.treeptik.annuaire.utils;

import java.io.File;

public class DirectoryUtil {
		
	// Cette méthode traverse tous les fichiers et dossiers depuis le chemin de "file".
		public static void goIntoDirectory(File directory) {

			for (File file : directory.listFiles()) {
				System.out.println(file.getAbsolutePath());
				if (file.isDirectory()) {
					goIntoDirectory(file);
				}
			}
		}

}
