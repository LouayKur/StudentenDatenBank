package studentenDaten;

import javax.swing.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 * Die Klasse StudentenGUI erbt von der Klasse Jframe und dient zur erstellung der grafische oberfläsche
 * alle Komponenten sind in dieser Klasse als Eigenschaften difiniert und 
 * ein Objekt der Klasse StudentenDaten zur bearbeitung der Daten.
 * zusätzlich ist auch eine list "filterIndex" als Eigenschaft difiniert und dient zur behandlung der Daten , wenn
 * der Filter verwendet wird.
 * @author LOUAY
 *
 */
public class StudentenGUI extends JFrame  {
  private static final long serialVersionUID = 1687092896773968182L;
  private   JLabel MatrikelNummer;
  private	JLabel Name;
  private	JLabel Vorname;
  private	JLabel Studiengang;
  private	JLabel Geburtsjahr;
  private	JButton Hinzufuegen;
  private	JButton Loeschen;
  private	JButton filter;
  private	JTextField text_1;
  private	JTextField text_2;
  private	JTextField text_3;
  private	JTextField text_4;
  private	JTextField text_5;
  private	JComboBox comboBox;
  private	JComboBox combo_2;
  private	JTable table;
  private   DefaultTableModel model;
  private   JScrollPane tableScroller ;
  private   JMenuBar menuBar;
  private   JMenu sort;
  private   JMenu datei;
  private   JMenuItem speichern ;
  private   JMenuItem beenden;
  private   JMenuItem martikel_sort;
  private   JMenuItem name_sort;
  private   JMenuItem geburts_sort;
  private   JMenuItem studiengang_sort;
  private   JPanel panel1;
  private   JPanel panel2;
  private   JPanel panel3;
  private   JPanel panel4;
  private   ArrayList<Student> studenten; //dient zur vereinfachung der bearbeitung der Daten.
  private   StudentenDaten alleStudenten;
  private final String[] Spalten= {"MatrikelNummer","Name","Vorname","Studiengang","Geburtsjahr"}; //sind spalten_Kopfer der Tabelle
  private List<Integer> filterIndex;
  
    private StudentenGUI() {} //wird nicht benötigt.
    
    /**
     * Der konstruktor dieser Klasse dient zur erstellung der Kompenenten und die Daten hinzufügen.
     * @param data sind die hinzufügende Daten
     */
	public StudentenGUI(StudentenDaten data) {
		alleStudenten=data;
		studenten=alleStudenten.getStudenten();
		MatrikelNummer=new JLabel("MatrikelNr.:");
		Name=new JLabel("Name:");
		Vorname=new JLabel("Vorname:");
		Studiengang=new JLabel("Studiengang:");
		Geburtsjahr=new JLabel("Geburtsjahr:");
		Hinzufuegen=new JButton("Hinzufügen");
		Loeschen=new JButton("Löschen");
		filter=new JButton("Filtern");
		text_1=new JTextField();
		text_2=new JTextField();
		text_3=new JTextField();
		text_4=new JTextField();
		text_5=new JTextField();
		String[] jahre=new String[90];
		int jahr = Calendar.getInstance().get(Calendar.YEAR); //das aktuelle Jahr aus kalender bringen.
		for(int i=89;i>=0;i--) {jahre[i]=String.valueOf(jahr-11-i) ;}
		comboBox=new JComboBox(jahre);
		comboBox.setSelectedIndex(-1);
		comboBox.setToolTipText("Jahr auswaehlen");
		combo_2=new JComboBox(new String[] {"Matrikelnummer","Name","Studiengang","Geburtsjahr"});
		combo_2.setSelectedIndex(-1);
		combo_2.setToolTipText("Filtern nach");
		model = new DefaultTableModel();
		table_spalten();
		tableScroller= new JScrollPane(table);
		menuBar = new JMenuBar();
		sort = new JMenu("Sortieren nach");
		speichern = new JMenuItem("Speichern");
		martikel_sort = new JMenuItem("Matrikelnummer");
		name_sort = new JMenuItem("Name");
		studiengang_sort = new JMenuItem("Studiengang");
		geburts_sort = new JMenuItem("Geburtsjahr");
		beenden =new JMenuItem("Beenden");
		datei = new JMenu("Option");
		panel1 = new JPanel(null);
		panel1.setBorder(BorderFactory.createTitledBorder("Student Hinzufügen"));
		panel2 = new JPanel(null);
		panel2.setBorder(BorderFactory.createTitledBorder("Studentendaten"));
		panel3 = new JPanel(null);
		panel3.setBorder(BorderFactory.createTitledBorder("Datensatzlöschen"));
		panel4 = new JPanel(null);
		panel4.setBorder(BorderFactory.createTitledBorder("Filtern"));
		filterIndex=new ArrayList<>();
		Bounds();
		data_table();
		action_init();
	}
	
	/**
	 * Die Methode table_spalten initialisert der model der tablle , sodass 1. und 5. spalte nicht bearbeitbar werden.
	 * Die Methode isCellEditable wird zu diesem zweck überschrieben.
	 */
	private void table_spalten() {
		table=new JTable(model){  //die spalten Matrikelnummer und Geburtsjahr dürfen nicht bearbeitet werden.
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column){
				int modelcol=table.getColumnModel().getColumn(column).getModelIndex();
		        return (modelcol!=0 && modelcol!=4);
		    };
		};
	}
	
	/**
	 * Die Methode Bounds setzt die Position alle Komponenten zur frame fest.
	 * ruft die Methode frame_init()
	 */
	private void Bounds() {
		panel1.setBounds(50, 130, 290, 300);
		panel2.setBounds(400, 30, 630, 500);
		panel3.setBounds(50, 430, 290, 100);
		panel4.setBounds(50, 30, 290, 100);
		MatrikelNummer.setBounds(30, 50, 100, 25);
		Name.setBounds(30, 90, 100, 25);
		Vorname.setBounds(30, 130, 100, 25);
		Studiengang.setBounds(30, 170, 100, 25);
		Geburtsjahr.setBounds(30, 210, 100, 25);
		text_1.setBounds(130, 50, 130, 25);
		text_2.setBounds(130, 90, 130, 25);
		text_3.setBounds(130, 130, 130, 25);
		text_4.setBounds(130, 170, 130, 25);
		text_5.setBounds(150, 20, 130, 25);
		comboBox.setBounds(130, 210, 130, 25);
		combo_2.setBounds(10, 20, 130, 25);
		Hinzufuegen.setBounds(130, 255, 130, 25);
		Loeschen.setBounds(130, 50, 130, 25);
		filter.setBounds(150, 60, 130, 25);
		tableScroller.setBounds(30, 20, 580, 460);
		frame_init();	
	}

	/**
	 * Die Methode frame_init fügt die Komponenten zur panels bzw. zur fram hinzu.
	 */
	private void frame_init() {
		menuBar.add(datei);
		datei.add(speichern);
		datei.add(sort);
		sort.add(martikel_sort);
		sort.add(name_sort);
		sort.add(studiengang_sort);
		sort.add(geburts_sort);
		datei.addSeparator();
		datei.add(beenden);
		panel1.add(Geburtsjahr);
		panel1.add(Hinzufuegen);
		panel1.add(Loeschen);
		panel1.add(Loeschen);
		panel1.add(MatrikelNummer);
		panel1.add(Name);
		panel1.add(Studiengang);
		panel1.add(Vorname);
		panel1.add(text_1);
		panel1.add(text_2);
		panel1.add(text_3);
		panel1.add(text_4);
		panel1.add(comboBox);
		panel3.add(Loeschen);
		panel4.add(combo_2);
		panel4.add(filter);
		panel4.add(text_5);
		this.setLocation(300, 300);
		this.setTitle("Studentendaten");
		this.setSize(1100, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.add(panel1);
		for(int i=0;i<Spalten.length;i++) {model.addColumn(Spalten[i]);}
		panel2.add(tableScroller);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Die Methode data_table fügt alle Datensätze aus der Eigenschaft studenten zur Tabelle hinzu.
	 */
	private void data_table() {
		for(Student s:studenten) {
			String[] Datensatz= {String.valueOf(s.getMartikelNummer()),s.getName(),s.getVorname(),s.getStudiengang(),String.valueOf(s.getGeburtsjahr())};
			model.addRow(Datensatz);
		}
	}

	/**
	 * Die Methode dient zur initialisierung der Ereignisse alle benötigte Komponenten dieser Klasse.
	 */
	private void action_init() {
		Hinzufuegen.addActionListener(e-> {
			try {
				Datensatz_Hinzufuegen();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "füllen Sie bitte alle Felder aus !");
			}
		});
		Loeschen.addActionListener(e->Datensatz_Loeschen());
		tabelle_geaendert();
		speichern.addActionListener(e->speichern());
		martikel_sort.addActionListener(e->MartikelSort());
		name_sort.addActionListener(e->NameSort());
		studiengang_sort.addActionListener(e->StudiengangSort());
		geburts_sort.addActionListener(e->GeburtsjahrSort());
		beenden.addActionListener(e->beenden());
		filter.addActionListener(e->filtereDaten());
		//wenn enter Taste gedrückt wird, wird Filter Modus eingeschaltet.
		text_5.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
		    		filtereDaten();
		    	  }
		});
		nichtMehrFiltern();
		//wenn auf schließen der frame gedrückt wurde wird nach speicherung der Daten abgefragt.
		 this.addWindowListener(new WindowAdapter() {
			 @Override
			 public void windowClosing(WindowEvent e) {
				 beenden();
			 }
		 });
	}
	
	/**
	 * Die Methode nichtMehrFiltern prüft, ob Filter Modus nicht mehr benötigt, um alle Daten der Studenten anzuzeigen.
	 * dazu werden die Methoden der Klasse DocumentListener überschrieben.
	 */
	private void nichtMehrFiltern() {
		text_5.getDocument().addDocumentListener(new DocumentListener() {
		       @Override
		       public void insertUpdate(DocumentEvent e) {}
		       @Override  //wenn Filter Modus schon verwendet wurde und Text feld leer ist -> alle Daten anzeigen. 
		       public void removeUpdate(DocumentEvent e) {
		    	   if(text_5.getText().length()==0 && filterIndex.size()!=0) {
	                	model.setRowCount(0);
	                	data_table();
		    	   }
		       }
		       @Override
		       public void changedUpdate(DocumentEvent e) {}
		});	
	}

	/**
	 * dieser Ereigniss wird ausgelöst, wenn eine zelle in der tabelle geändert wird.
	 * *var pruef dient zur erkennung, ob filter modus eingeschaltet ist.
	 */
	private void tabelle_geaendert() {
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn()!=-1) {
                	int col=e.getColumn();
                	int row=e.getFirstRow();
                	boolean pruef=false;
                	Student tmp=new Student(); //dient zur vereinfachung des umsetzen im Datenbank.
                	String mart_Tmp=(String) model.getValueAt(row, 0);
                	String wert=(String) model.getValueAt(row, col);
                	//dient zur unterscheidung, ob index der daten der ausgewählte zeile und index abgespeicherte Daten gleich sind.
                	if(Integer.parseUnsignedInt(mart_Tmp)==studenten.get(row).getMartikelNummer()) {   
                		tmp=studenten.get(row);
                		pruef=true;
                	}
                	else
                		tmp=studenten.get(filterIndex.get(row));
                	if(wert.length()>0) { // Die Zelle wurde zum leere Text geändert!
                	switch(col) {  //nur drei spalten sind änderbar.
                	case 1:
                		tmp.setName(wert);
                		break;
                	case 2:
                		tmp.setVorname(wert);
                		break;
                	case 3:
                		tmp.setStudiengang(wert);
                	default:
                		break;
                	}
                	if(pruef)
                		studenten.set(row, tmp);
                	else
                		studenten.set(filterIndex.get(row), tmp);
                	}else 
                			zelleRuecksetzen(pruef,row,col);
                }
            }
        });
	}

	/**
	 * Diese Methode dient zum rücksetzen einer Zelle wenn die aktualisierung der Zelle ein leeres Text lässt.
	 * @param pruef deint zur unterscheidung, ob Filter Modus eingeschaltet ist.
	 * @param row ist die Zeile nummer, wo die zelle geändert wurde.
	 * @param col ist die Spalter nummer, wo die Zelle geändert wurde.
	 */
	private void zelleRuecksetzen(boolean pruef, int row, int col) {
		JOptionPane.showMessageDialog(null,"Die Zelle darf nicht leer sein!");	
	switch(col) {
		case 1:
				if(pruef)
					table.getModel().setValueAt(studenten.get(row).getName(), row, col);  //Filter Modus ausgeschaltet.
				else
					table.getModel().setValueAt(studenten.get(filterIndex.get(row)).getName(), row, col);  //Richtige Daten aus dem datenbank holen.
				break;
		case 2:
				if(pruef)
					table.getModel().setValueAt(studenten.get(row).getVorname(), row, col);
				else
					table.getModel().setValueAt(studenten.get(filterIndex.get(row)).getVorname(), row, col);
				break;
		case 3:
				if(pruef)
					table.getModel().setValueAt(studenten.get(row).getStudiengang(), row, col);
				else
					table.getModel().setValueAt(studenten.get(filterIndex.get(row)).getStudiengang(), row, col);
				break;
			}
	}
	
	/**
	 * Die Methode filtereDaten nimmt den wert von text_5 auf und ruft die Methode datenfilter aus der Klasse 
	 * StudentenDaten zum filtern der Daten nach bestimmte Kretirien. 
	 * es wird auch die Methode getIndex aufgerufen.
	 */
	private void filtereDaten() {
		String wert=text_5.getText();
		int index=combo_2.getSelectedIndex();
		List<Student> gefiltert=new ArrayList<>();
		if(index==-1)
			JOptionPane.showMessageDialog(null,"Bitte wählen Sie ein kriterium aus !");
		else if(wert==null || wert.length()==0)
			JOptionPane.showMessageDialog(null,"Bitte Text eingeben !");
		else if(index==0 || index==3) {
			if(istZahl(wert)) {
				switch(index) {
				case 0:
					gefiltert=StudentenDaten.datenFilter(studenten,StudentenDaten.martikelFilter(Integer.parseInt(wert)));
					getIndex(gefiltert);
					break;
				case 3:
					gefiltert=StudentenDaten.datenFilter(studenten,StudentenDaten.jahrFilter(Integer.parseInt(wert)));
					getIndex(gefiltert);
					break;
				}	
			}else
				JOptionPane.showMessageDialog(null,"Bitte eine Zahl eingeben !");
		}else {
			switch(index) {
			case 1:
				gefiltert=StudentenDaten.datenFilter(studenten,StudentenDaten.nameFilter(wert));
				getIndex(gefiltert);
				break;
			case 2:
				gefiltert=StudentenDaten.datenFilter(studenten,StudentenDaten.studieFilter(wert));
				getIndex(gefiltert);
				break;
			}
		}
	}
	
	/**
	 * Die Methode getIndex speichert die wirkliche indizes der gefilterten Daten und zeigt sie in der Tabelle zur weitere
	 * bearbeitung.
	 * @param gefiltert sind eine liste der gefilterte Daten. 
	 */
	private void getIndex(List<Student> gefiltert) {
		filterIndex = new ArrayList<>();
		if(gefiltert.size()==0 || gefiltert==null)
			JOptionPane.showMessageDialog(null,"keine Daten vorhanden !");
		else {
		filterIndex.clear();
		model.setRowCount(0); //tabelle leeren.
		for(Student s:gefiltert) { //die gefilterte Daten in der Tabelle anzeigen.
			filterIndex.add(studenten.indexOf(s));
			String[] Datensatz= {String.valueOf(s.getMartikelNummer()),s.getName(),s.getVorname(),s.getStudiengang(),String.valueOf(s.getGeburtsjahr())};
			model.addRow(Datensatz);
			}
		}
	}
	
	/**
	 * Der Ereigniss wird ausgelöst ,wenn auf menuItem beenden angekickt wird.
	 */
	private void beenden() {
		int result = JOptionPane.showConfirmDialog( this,
                "möchten Sie die Änderungen speichern?",
                "Schließen",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE ); //es wird nach Speicherung der Daten nachgefragt.
		switch (result) {
		case JOptionPane.YES_OPTION:
			speichern();
			System.exit(0);
			break;
		case JOptionPane.NO_OPTION:
			System.exit(0);
			break;
		}
	}
	
	/**
	 * Die Methode GeburtsjahrSort ruft die Methode GeburtsjahrSort aus der Klasse StudentenDaten ab und
	 * bekommt die Daten nach Geburtsjahr sortiert zurück.
	 */
	private void GeburtsjahrSort() {
		if(studenten!=null) {
			studenten=alleStudenten.GeburtsjahrSort();
			model.setRowCount(0); //die tabelle leeren.
			data_table();  //die Daten sortiert ausgeben.
		}
	}
	
	/**
	 * Die Methode StudiengangSort ruft die Methode StudiengangSort aus der Klasse StudentenDaten ab und
	 * bekommt die Daten nach Studiengang sortiert zurück.
	 */
	private void StudiengangSort() {
		if(studenten!=null) {
			studenten=alleStudenten.StudiengangSort();
			model.setRowCount(0);
			data_table();
		}
	}

	/**
	 * Die Methode NameSort ruft die Methode NameSort aus der Klasse StudentenDaten ab und
	 * bekommt die Daten nach Name sortiert zurück.
	 */
	private void NameSort() {
		if(studenten!=null) {
			studenten=alleStudenten.NameSort();
			model.setRowCount(0);
			data_table();
		}
	}

	/**
	 * Die Methode MatrikelSort ruft die Methode MartikelSort aus der Klasse StudentenDaten ab und
	 * bekommt die Daten nach Matrikelnummer sortiert zurück.
	 */
	private void MartikelSort() {
		if(studenten!=null) {
			studenten=alleStudenten.MartikelSort();
			model.setRowCount(0);
			data_table();
		}
	}
	
	/**
	 * Die Methode speichern ruft die Methode speichern aus der Klasse StudentenDaten und
	 * dient zur speicherung der Daten in der Datei ab.
	 */
	private void speichern() {
		try {
			alleStudenten.setStudenten(studenten);
			alleStudenten.speichern();
			JOptionPane.showMessageDialog(null,"Die Daten wurden erfolgreich gespeichert!");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,"Die Daten wurden nicht gespeichert!");
		}
	}

	/**
	 * Die Methode Datensatz_Loeschen dient zur löschung eines Datensatz und 
	 * unterscheidet, ob Filter Modus eingeschaltet ist.
	 */
	private void Datensatz_Loeschen() {
        if( table.getSelectedRowCount() > 0 )
        {
            int[] Indices = table.getSelectedRows();
            for( int i=Indices.length-1; i>=0; i-- )
            {
            	
            	String tmp=(String) table.getValueAt(Indices[0], 0);
                if(Integer.parseUnsignedInt(tmp)==studenten.get(Indices[0]).getMartikelNummer())//Filter Modus Unterscheidung.
                	studenten.remove(Indices[i]);
                else
                	studenten.remove((int)filterIndex.get(Indices[i]));
                	model.removeRow(Indices[i]);
            }
        }else
        	JOptionPane.showMessageDialog(null,"Bitte wählen Sie eine Zeile aus !");
	}

	/**
	 * Die Methode Datensatz_Hinzufuegen dient zum hinzufügen ein neues Datensatz zur tabelle und 
	 * prüft , ob die Matrikelnummer eindeutig ist und ob die Mertikelnummer und Geburtsjahr zaheln sind.
	 * @throws Exception wenn ein text leer ist.
	 */
	private void Datensatz_Hinzufuegen() throws Exception {
		Student s=new Student();
		String[] Datensatz=new String[5];
		Datensatz[0]=text_1.getText();
		Datensatz[1]=text_2.getText();
		Datensatz[2]=text_3.getText();
		Datensatz[3]=text_4.getText();
		Datensatz[4]=(String) comboBox.getSelectedItem();
		for(int i=0;i<=4;i++) {
			if(Datensatz[i].length()==0) throw new Exception();}//wird Fehler geworfen, wenn ein Textfeld leer ist.
		if(istZahl(Datensatz[0])){
			if(istZahl(Datensatz[4])) {
			if(Martikel_eindeutigkeit(Integer.parseInt(Datensatz[0]))) {
				s.setMartikelNummer(Integer.parseInt(Datensatz[0]));
				s.setGeburtsjahr(Integer.parseInt(Datensatz[4]));
				s.setName(Datensatz[1]);
				s.setVorname(Datensatz[2]);
				s.setStudiengang(Datensatz[3]);
				model.addRow(Datensatz);
				studenten.add(s);
				filterIndex.add(studenten.size()-1);
				text_init();
			}else 
				JOptionPane.showMessageDialog(null, "Die Matrikelnummer ist schon vergeben!");
		}else 
			JOptionPane.showMessageDialog(null, "Bitte ein Geburtsjahr auswählen!");
		}else
			JOptionPane.showMessageDialog(null, "Die Matrikelnummer muss eine Zahl sein!");
	}

	/**
	 * Die Methode text_init leert die Text_felder nach erfolgreichem addieren ein neues Datensatz.
	 */
	private void text_init() {
		text_1.setText("");
		text_2.setText("");
		text_3.setText("");
		text_4.setText("");	
		comboBox.setSelectedIndex(-1);
	}
	
	/**
	 * Die Methode Martikel_eindeutigkeit prüft, ob die Matrikelnummer eindeutig ist.
	 * @param martikel ist die hinzufügende nummer und dient zur vergleich.
	 * @return gibt true zurück, wenn die nummer nicht gefunden wurde.
	 */
	private boolean Martikel_eindeutigkeit(int martikel) {
		for(Student s:studenten) {
			if(s.getMartikelNummer()==martikel)
				return false;
		}
		return true;
	}
	
	/**
	 * Die methode istZahl prüft, ob die Zeichenkette eine zahl ist.
	 * @param wert ist die Zeichenkette
	 * @return true, wenn die zeichenkette eine Zahl ist.
	 */
	private boolean istZahl(String wert) {
		if(wert==null || wert.length()==0)
			return false;
		for(int i=0;i<wert.length();i++) {
			char ziffer=wert.charAt(i);
			int x=(int)ziffer;
			if(x<48 || x>57 ) {
				return false;
			}
		}
		return true;
	}

}