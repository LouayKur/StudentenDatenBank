package studentenDaten;

import java.nio.file.Paths;
/**
 * Die Klasse Studenten enth�lt main funktion.
 * @author LOUAY
 *
 */
public class Studenten {

	/**
	 * mein Funktion sucht der pfad , wo gerade gearbeitet wird und:
	 * 1. erstellt f�r die Datenstruktur ein objekt der Klasse StudentenDaten
	 * 2. erstellt f�r grafische oberfl�sche ein Objekt der Klasse StudentenGUI
	 */
	public static void main(String[] args) {
		String pfad = Paths.get("").toAbsolutePath().toString(); //pfad entdecken , wo der Programm abgespielt wird.
        pfad+="\\src\\datei.txt";			 // die Datei , wo die Daten sind, ausw�hlen. auf Linux soll "\" zu "/" ge�ndert werden.
			try {
				StudentenDaten data = new StudentenDaten(pfad); //Objekt erstellen und die Daten auslesen.
				StudentenGUI view=new StudentenGUI(data); //die Daten in grafische oberfl�sche �bergeben.
				view.setVisible(true);  	// Die grafische oberfl�sche sichtbar machen.
			} catch (Exception e) {}
	}
}
