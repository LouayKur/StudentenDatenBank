package studentenDaten;
/**
 * @author LOUAY
 *Die Klasse Student dient zur Speicherung der Daten eines students.
 *Attribute der Klasse:
 *MartikelNummer speichert die MartikelNummer eines students.
 *Name, Vorname, Studiengang und Geburtsjahr speichern jeweils laut der Attribut_name die Eigenschaft ab.
 */
	public class Student {
	protected int MatrikelNummer;
	protected String	Name;
	protected String	Vorname;
	protected String	Studiengang;
	protected int	Geburtsjahr;
	
	/**
	 *konstruktor der Klasse Student nimmt kein Parameter auf und dient zur erstellung eines Objektes dieser Klasse. 
	 */
	public Student() {}
	public int getMartikelNummer() {
		return MatrikelNummer;
	}
	
	/**
	 * Die Methode setMartikelNummer speichert die martikelNummer eines Objektes.
	 * @param martikelNummer dient zur Aktualiesierung die Eigenschaft martikelNummer eines Objektes. 
	 */
	public void setMartikelNummer(int martikelNummer) {
		MatrikelNummer = martikelNummer;
	}
	
	/**
	 * Die Methode getName gibt die Eigenschaft zurück.
	 * @return Name ist die zurückgegebene Eigenschaft.
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Die Methode setName dient zur Aktualiesierung die Eigenschaft Name eines Objektes.
	 * @param name ist die neue Wert.
	 */
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Die Methode getVorname gibt die Eigenschaft Vorame eines Objektes zurück.
	 * @return Die Eigenschaft Vorname
	 */
	public String getVorname() {
		return Vorname;
	}
	
	/**
	 * Die Methode setVorname dient zur Aktualiesierung die Eigenschaft Vorname eines Objektes.
	 * @param vorname ist die neue Wert.
	 */
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	
	/**
	 *  Die Methode getStudiengang gibt die Eigenschaft Studiengang eines Objektes zurück.
	 * @return Die Eigenschaft Studiengang.
	 */
	public String getStudiengang() {
		return Studiengang;
	}
	
	/**
	 *  Die Methode setStudiengang dient zur Aktualiesierung die Eigenschaft studiengang eines Objektes.
	 * @param studiengang ist die neue Wert.
	 */
	public void setStudiengang(String studiengang) {
		Studiengang = studiengang;
	}
	
	/**
	 *   Die Methode getGeburtsjahr gibt die Eigenschaft Geburtsjahr eines Objektes zurück.
	 * @return Die Eigenschaft Geburtsjahr.
	 */
	public int getGeburtsjahr() {
		return Geburtsjahr;
	}
	
	/**
	 * Die Methode setGeburtsjahr dient zur Aktualiesierung die Eigenschaft Geburtsjahr eines Objektes.
	 * @param geburtsjahr ist die neue Wert.
	 */
	public void setGeburtsjahr(int geburtsjahr) {
		Geburtsjahr = geburtsjahr;
	}	
}  