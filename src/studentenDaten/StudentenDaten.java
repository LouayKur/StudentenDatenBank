package studentenDaten;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Die Klasse StudentenDaten erbt von der Klasse Student und dient zur Speicherung eine liste von Studentn und der Pfad der interne
 * Datei zum einlesen und zurückspeichern der Daten nach der bearbeitung.
 * @attribute :
 * pfad dient zur Speicherung der pfad der initial Datei.
 * studenten dient zur Speicherung eine Liste von Obiekten der type Student.
 * @author LOUAY
 *
 */

public class StudentenDaten extends Student {
	private String pfad;
	private ArrayList<Student> studenten;
	
	/**
	 * Der konstruktor der Klasse liest der Initial_datei ab und speichert der Daten in der Eigenschaft studenten
	 * @param pfad ist der pfad der initial Datei.
	 * @throws Exception Fehler beim einlesen, wird fehler geworfen.
	 */
	
	public StudentenDaten(String pfad) throws Exception{
		this.pfad=pfad;
		String zeile=null;
		studenten=new ArrayList<Student>();
		BufferedReader file = new BufferedReader(new FileReader(new File(pfad)));
		try {
		while((zeile = file.readLine()) != null) {	
			Student student=new Student();
			String[] teil = zeile.split(";"); // alle substring einer Zeile nach Simikolon aufteilen
			student.MatrikelNummer=Integer.parseInt(teil[0]);
			student.Name=teil[1];
			student.Vorname=teil[2];
			student.Studiengang=teil[3];
			student.Geburtsjahr=Integer.parseInt(teil[4]);
			studenten.add(student);
		  }
		} catch(FileNotFoundException e) {}
		catch(IOException e) {} 
		finally {
			if(file != null) {
				try {
					file.close();
				} catch(IOException e) {}
			}
		 }
	  }
	
	/**
	 * Die Methode gibt die Eigenschaft zurück.
	 * @return die Eigenschaft studenten.
	 */
	
	public ArrayList<Student> getStudenten() {
		return studenten;
	}

	/**
	 * Die Methode setStudenten dient zur Aktualisierung die Eigenschaft studenten.
	 * @param studenten ist die neue wert.
	 */
	
	public void setStudenten(ArrayList<Student> studenten) {
		this.studenten = studenten;
	}
	
	/**
	 * Die Methode speichern speichert die Obekte in der Liste studenten in der Datei ab.
	 * @throws Exception Fehler beim speichern, wird er geworfen.
	 */
	
	public void speichern() throws Exception {
	    FileWriter datei = new FileWriter(pfad);
	    BufferedWriter schreib = new BufferedWriter(datei);
	    for(Student s:studenten) {
	    	schreib.write(s.MatrikelNummer+";"+s.Name+";"+s.Vorname+";"+s.Studiengang+";"+s.Geburtsjahr);
	    	schreib.newLine();
	    }
	    schreib.close();
		}
	
	//für das Sortieren wurde das Teil vom unseren Buch "Java ist auch ein Insel" Abschnitt: 9.1 Vergleichen von Objekten benutzt.
	/**
	 * Die Methode MartikelSort überschreibt die Methode Compare aus Java.util.
	 * @return gibt die Eigenschaft studenten nach Mertikelnummer sortiert aus.
	 */
	
	public ArrayList<Student> MartikelSort() {
		Collections.sort(studenten, new Comparator<Student>() {
			@Override
		    public int compare(Student erste, Student zweite) {
		        return Integer.compare(erste.MatrikelNummer, zweite.MatrikelNummer);
		    }
		});
		return studenten;
	}

	/**
	 * Die Methode NameSort überschreibt die Methode Compare aus Java.util.
	 * @return gibt die Eigenschaft studenten nach Name sortiert aus.
	 */
	
	public ArrayList<Student> NameSort() {
		Collections.sort(studenten, new Comparator<Student>() {
			@Override
		    public int compare(Student erste, Student zweite) {
		        return erste.Name.compareTo(zweite.Name);
		    }
		});
		return studenten;
	}

	/**
	 * Die Methode StudiengangSort überschreibt die Methode Compare aus Java.util.
	 * @return gibt die Eigenschaft studenten nach studiengang sortiert aus.
	 */
	
	public ArrayList<Student> StudiengangSort() {
		Collections.sort(studenten, new Comparator<Student>() {
			@Override
		    public int compare(Student erste, Student zweite) {
		        return erste.Studiengang.compareTo(zweite.Studiengang);
		    }
		});
		return studenten;
	}
	
	 /**
	  * Die Methode GeburtsjahrSort überschreibt die Methode Compare aus Java.util.
	  * @return gibt die Eigenschaft studenten nach Geburtsjar sortiert aus.
	  */
	public ArrayList<Student> GeburtsjahrSort() {
		Collections.sort(studenten, new Comparator<Student>() {
			@Override
		    public int compare(Student erste, Student zweite) {
		        return  Integer.compare(erste.Geburtsjahr, zweite.Geburtsjahr);
		    }
		 });
		return studenten;
	}
	
	/**
	 //* für das Filtern habe ich die entsprechende Klasse der java bibliothek aus Oracle Webseite gelesen. *-
	 //* Quelle: https://docs.oracle.com/javase/8/docs/api/java/util/function/class-use/Predicate.html
	 */
	
	/**
	 * Die Methode martikelFilter vergleicht die Eigenschaft Mrtikelnummer eines objekts der type student mit bestimmte zahl.
	 * @param nummer ist der zuvergleichende.
	 * @return true, wenn die bedingung erfüllt ist .andersfalls @return false.
	 */
	
	public static Predicate<Student> martikelFilter(int nummer) {
	    return y -> y.MatrikelNummer ==nummer ;
	}
	
	/**
	 * Die Methode nameFilter vergleicht die Eigenschaft Name eines objekts der type student mit bestimmte zeichenkette.
	 * @param name ist der zuvergleichende.
	 * @return true, wenn die bedingung erfüllt ist .andersfalls @return false.
	 */
	
	public static Predicate<Student> nameFilter(String name) {
	    return y -> y.Name.equalsIgnoreCase(name);
	}
	
	/**
	 * Die Methode studieFilter vergleicht die Eigenschaft Studiengang eines objekts der type student mit bestimmte zeichenkette.
	 * @param Studiengang ist der zuvergleichende.
	 * @return true, wenn die bedingung erfüllt ist .andersfalls @return false.
	 */
	public static Predicate<Student> studieFilter(String studiegang) {
	    return y -> y.Studiengang.equalsIgnoreCase(studiegang);
	}

	/**
	 * Die Methode jahrFilter vergleicht die Eigenschaft Geburtsjahr eines objekts der type student mit bestimmte zahl.
	 * @param Geburtsjahr ist der zuvergleichende.
	 * @return true, wenn die bedingung erfüllt ist .andersfalls @return false.
	 */
	public static Predicate<Student> jahrFilter(int geburts) {
	    return y -> y.Geburtsjahr ==geburts;
	}

	/**
	 * Die methode datenFilter ruft bestimmte Predicate zum vergleich.
	 * @param studenten ist die liste zum filtern.
	 * @param kreterium ruft bestimmte funktion zum vergleich nach bestimmten Kretirien
	 * @return gibt die liste gefiltert zurück.
	 */
	public static List<Student> datenFilter (List<Student> studenten,Predicate<Student> kreterium) 
	{
	    return studenten.stream().filter( kreterium ).collect(Collectors.<Student>toList());
	}	
}