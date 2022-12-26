package studentenDaten;

import java.nio.file.Paths;
/**
 * Die Klasse Studenten enthält main funktion.
 * @author LOUAY
 *
 */
public class Studenten {

	/**
	 * mein Funktion sucht der pfad , wo gerade gearbeitet wird und:
	 * 1. erstellt für die Datenstruktur ein objekt der Klasse StudentenDaten
	 * 2. erstellt für grafische oberfläsche ein Objekt der Klasse StudentenGUI
	 */
	public static void main(String[] args) {
		String pfad = Paths.get("").toAbsolutePath().toString(); //pfad entdecken , wo der Programm abgespielt wird.
        pfad+="\\src\\datei.txt";			 // die Datei , wo die Daten sind, auswählen. auf Linux soll "\" zu "/" geändert werden.
			try {
				StudentenDaten data = new StudentenDaten(pfad); //Objekt erstellen und die Daten auslesen.
				StudentenGUI view=new StudentenGUI(data); //die Daten in grafische oberfläsche übergeben.
				view.setVisible(true);  	// Die grafische oberflösche sichtbar machen.
			} catch (Exception e) {}
	}
}
