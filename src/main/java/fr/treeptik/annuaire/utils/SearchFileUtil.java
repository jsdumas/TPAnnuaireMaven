package fr.treeptik.annuaire.utils;

import java.io.File;

public class SearchFileUtil {

	// ////AFFICHAGE DE TOUS LES FICHIERS
	public static void searchFile(File file) {
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			System.out.println(f.getAbsolutePath());
			if (f.isDirectory()) {
				searchFile(f);
			}
		}
	}
}