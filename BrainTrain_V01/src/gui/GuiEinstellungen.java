package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import berechnungen.AufgabenGenerator;
import berechnungen.AufgabenPruefer;
import berechnungen.AufgabenTimer;
import berechnungen.AufgabenZaehler;

//Hier werden die GUI-Elemente der Einstellungen-Seite definiert.
public class GuiEinstellungen implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane f�r die Rechner-Seite wird definiert.
	static JLayeredPane layersEinstellungen = new JLayeredPane();
	
	 // Das JPanel f�r die Einstellungen-Seite wird definiert.
	//static JPanel panelEinstellungen = new JPanel();
	
	// Die Buttons f�r die Einstellungen-Seite werden definiert.
	static JButton buttonGoClassic = new JButton("Start");
	static JButton buttonGoTimeAttack = new JButton("Start");
	static JButton buttonBack = new JButton("Zur�ck");
	
	// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
	static JLabel overlayClassicJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiEinstellungenClassic.png")));
	static JLabel overlayTimeAttackJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiEinstellungenTimeAttack.png")));
	
	// JTextField "AnzahlFragen" wird mit dem Standardwert "7" definiert.
	static JTextField textFieldAnzahlFragen = new JTextField("7");
	
	// JTextField "Dauer" wird mit dem Standardwert "1.5" definiert.
	static JTextField textFieldDauer = new JTextField("1.5");
	
	// Auswahlelemente f�r die JCombobox wird erstellt.
	static String comboBoxListe[] = {" x", " xx", " xxx", " xxxx", " xxxxx"};
	static String comboBoxListeSpez[] = {" x", " xx", " xxx"};

	// Die JCheckBoxen werden definiert.
	static JCheckBox checkBoxPlus = new JCheckBox("Plus");
	static JCheckBox checkBoxMinus = new JCheckBox("Minus");
	static JCheckBox checkBoxMal = new JCheckBox("Mal");
	static JCheckBox checkBoxGeteilt = new JCheckBox("Geteilt");
	
	// Die JComboboxen werden definiert.
	static JComboBox<?> comboBoxPlusErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxPlusZweiteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMinusErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMinusZweiteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMalErsteStelle = new JComboBox<Object>(comboBoxListeSpez);
	static JComboBox<?> comboBoxMalZweiteStelle = new JComboBox<Object>(comboBoxListeSpez);
	static JComboBox<?> comboBoxGeteiltErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxGeteiltZweiteStelle = new JComboBox<Object>(comboBoxListeSpez);
	
	// Boolean zum Erkennen des aktuellen Modus.
	static boolean classicMode;
	static boolean overlayOn = true;
	
	// Array um die Einstellungen der Stellen f�r Auswerungszwecke aufzunehmen
	static String[][] anzahlStellenArray;
	
	// Neue Farben werden definiert
	static Color consoleFrontGreen = new Color(30,105,52);
	static Color consoleBackGreen = new Color(37,194,86);
	static Color checkBoxFrontGrey = new Color(106,106,106);
	static Color checkBoxBackGrey = new Color(224,224,224);
	
	public GuiEinstellungen()
	{
		// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu k�nnen.
		layersEinstellungen.setLayout(null);
		
		// Die Position und Gr�sse Panel-Objekte werden gesetzt (x, y, breite, h�he)
		overlayClassicJLabel.setBounds(0, 0, 594, 471);
		overlayTimeAttackJLabel.setBounds(0, 0, 594, 471);
		
		checkBoxPlus.setBounds(60, 116, 120, 30);
		checkBoxMinus.setBounds(60, 186, 120, 30);
		checkBoxMal.setBounds(60, 256, 120, 30);
		checkBoxGeteilt.setBounds(60, 326, 120, 30);
		
		comboBoxPlusErsteStelle.setBounds(202, 111, 140, 40);
		comboBoxPlusZweiteStelle.setBounds(394, 111, 140, 40);
		comboBoxMinusErsteStelle.setBounds(202, 181, 140, 40);
		comboBoxMinusZweiteStelle.setBounds(394, 181, 140, 40);
		comboBoxMalErsteStelle.setBounds(202, 251, 140, 40);
		comboBoxMalZweiteStelle.setBounds(394, 251, 140, 40);
		comboBoxGeteiltErsteStelle.setBounds(202, 321, 140, 40);
		comboBoxGeteiltZweiteStelle.setBounds(394, 321, 140, 40);
		
		buttonGoClassic.setBounds(489, 415, 90, 40);
		buttonGoTimeAttack.setBounds(489, 415, 90, 40);
		buttonBack.setBounds(15, 415, 90, 40);
		
		textFieldAnzahlFragen.setBounds(360, 410, 70, 60);
		textFieldDauer.setBounds(312, 410, 120, 60);
		
		// Schriftarten werden bestummen	
		textFieldAnzahlFragen.setFont(new Font("Courier New", Font.BOLD, 30));
		textFieldAnzahlFragen.setForeground(consoleFrontGreen);
		textFieldAnzahlFragen.setBackground(consoleBackGreen);
		textFieldDauer.setFont(new Font("Courier New", Font.BOLD, 30));
		textFieldDauer.setForeground(consoleFrontGreen);
		textFieldDauer.setBackground(consoleBackGreen);
		
		checkBoxPlus.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxPlus.setForeground(checkBoxFrontGrey);
		checkBoxPlus.setBackground(checkBoxBackGrey);
		checkBoxMinus.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxMinus.setForeground(checkBoxFrontGrey);
		checkBoxMinus.setBackground(checkBoxBackGrey);
		checkBoxMal.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxMal.setForeground(checkBoxFrontGrey);
		checkBoxMal.setBackground(checkBoxBackGrey);
		checkBoxGeteilt.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxGeteilt.setForeground(checkBoxFrontGrey);
		checkBoxGeteilt.setBackground(checkBoxBackGrey);

		comboBoxPlusErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxPlusErsteStelle.setForeground(consoleFrontGreen);
		comboBoxPlusErsteStelle.setBackground(consoleBackGreen);
		comboBoxPlusZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxPlusZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxPlusZweiteStelle.setBackground(consoleBackGreen);
		comboBoxMinusErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMinusErsteStelle.setForeground(consoleFrontGreen);
		comboBoxMinusErsteStelle.setBackground(consoleBackGreen);
		comboBoxMinusZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMinusZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxMinusZweiteStelle.setBackground(consoleBackGreen);
		comboBoxMalErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMalErsteStelle.setForeground(consoleFrontGreen);
		comboBoxMalErsteStelle.setBackground(consoleBackGreen);
		comboBoxMalZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMalZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxMalZweiteStelle.setBackground(consoleBackGreen);
		comboBoxGeteiltErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxGeteiltErsteStelle.setForeground(consoleFrontGreen);
		comboBoxGeteiltErsteStelle.setBackground(consoleBackGreen);
		comboBoxGeteiltZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxGeteiltZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxGeteiltZweiteStelle.setBackground(consoleBackGreen);
		
		// Der Inhalt im JLabel wird immer Zentriert angezeigt.
		textFieldAnzahlFragen.setHorizontalAlignment(JLabel.CENTER);
		textFieldDauer.setHorizontalAlignment(JLabel.CENTER);
		
		// JCheckboxen werden standardm�ssig auf "selected" gesetzt.
		checkBoxPlus.setSelected(true);
		checkBoxMinus.setSelected(true);
		checkBoxMal.setSelected(true);
		checkBoxGeteilt.setSelected(true);
		
		// StandardEinstellungen der ComboBoxen werden vorgenommen.
		comboBoxPlusErsteStelle.setSelectedIndex(1);
		comboBoxPlusZweiteStelle.setSelectedIndex(1);
		comboBoxMinusErsteStelle.setSelectedIndex(1);
		comboBoxMinusZweiteStelle.setSelectedIndex(0);
		comboBoxMalErsteStelle.setSelectedIndex(1);
		comboBoxMalZweiteStelle.setSelectedIndex(0);
		comboBoxGeteiltErsteStelle.setSelectedIndex(1);
		comboBoxGeteiltZweiteStelle.setSelectedIndex(0);
		
		// Den Buttons und dem TextField werden dem ActionListener hinzugef�gt. Sie erkennen nun User-Imputs.
		buttonGoClassic.addActionListener(this);
		buttonGoTimeAttack.addActionListener(this);
		buttonBack.addActionListener(this);
		textFieldAnzahlFragen.addActionListener(this);
		textFieldDauer.addActionListener(this);
		
		// Die Layers werden bestummen (je h�her die Zahl desto weiter oben das Objekt).
		layersEinstellungen.setLayer(buttonBack, 2);
		layersEinstellungen.setLayer(buttonGoClassic, 2);
		layersEinstellungen.setLayer(buttonGoTimeAttack, 2);
		layersEinstellungen.setLayer(overlayClassicJLabel, 1);
		layersEinstellungen.setLayer(overlayTimeAttackJLabel, 1);
		
		layersEinstellungen.setLayer(checkBoxPlus, 2);
		layersEinstellungen.setLayer(checkBoxMinus, 2);
		layersEinstellungen.setLayer(checkBoxMal, 2);
		layersEinstellungen.setLayer(checkBoxGeteilt, 2);
		
		layersEinstellungen.setLayer(comboBoxPlusErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxPlusZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMinusErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMinusZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMalErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMalZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxGeteiltErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxGeteiltZweiteStelle, 0);
		
		// Die Panel-Objekte werden dem JPanel panelClassic hinzugef�gt.
		layersEinstellungen.add(buttonBack);
		
		layersEinstellungen.add(checkBoxPlus);
		layersEinstellungen.add(checkBoxMinus);
		layersEinstellungen.add(checkBoxMal);
		layersEinstellungen.add(checkBoxGeteilt);
		
		layersEinstellungen.add(comboBoxPlusErsteStelle);
		layersEinstellungen.add(comboBoxPlusZweiteStelle);
		layersEinstellungen.add(comboBoxMinusErsteStelle);
		layersEinstellungen.add(comboBoxMinusZweiteStelle);
		layersEinstellungen.add(comboBoxMalErsteStelle);
		layersEinstellungen.add(comboBoxMalZweiteStelle);
		layersEinstellungen.add(comboBoxGeteiltErsteStelle);
		layersEinstellungen.add(comboBoxGeteiltZweiteStelle);
		
	}
	
	
	
	// Diese Methode wird aufgerufen wenn der ActionListener einen User-Imput (ActionEvent) erh�lt.
	// Er speichert den Imput in der Variable event.
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// Der Imput wird durch eine If Else Schleife gepr�ft.
		// Wird der Imput von einer Abfrage erkannt, wird der darauffolgende Code ausgef�hrt.
		if (event.getSource() == buttonGoClassic || event.getSource() == textFieldAnzahlFragen)
		{
			// (Button "Start" geklickt oder Enter im Textfeld) der Imput startet diese Schleife, um mit den RechenAufgaben zu starten.
			
			// Die eingegebenen Werte der "geteilt" und "minus" Stellenanzahlen werden f�r sp�ter gespeichert.
			String geteiltStellenZahlEinsString = comboBoxGeteiltErsteStelle.getSelectedItem().toString();
			String geteiltStellenZahlZweiString = comboBoxGeteiltZweiteStelle.getSelectedItem().toString();
			String minusStellenZahlEinsString = comboBoxMinusErsteStelle.getSelectedItem().toString();
			String minusStellenZahlZweiString = comboBoxMinusZweiteStelle.getSelectedItem().toString();
					
			// Hier werden die Benutzer Eingaben auf Ihre Richtigkeit �berpr�ft.
			try
			{
				// Kann die Eingabe im Textfeld AnzahlFragen in einen Integer Wert umgewandelt werden, wird der folgende Code ausgef�hrt.
				Integer integerAnzahlFragen = Integer.parseInt(textFieldAnzahlFragen.getText());
				
				if (integerAnzahlFragen < 1)
				{
					// Bei ung�ltiger Eingabe (kleiner als 1) wird ein Fehler ausgegeben und das Eingabefeld zur�ckgesetzt.
					JOptionPane.showMessageDialog(null, "Wenn Sie keine Aufgabe l�sen wollen zwinge ich Sie auch nicht.  ; - )\nAnsonsten geben Sie einen Wert �ber 0 im Feld \"Anzahl Fragen\" ein");
					textFieldAnzahlFragen.setText("1");
				}
				else if (integerAnzahlFragen > 99)
				{
					// Bei ung�ltiger Eingabe (gr�sser als 99) wird ein Fehler ausgegeben und das Eingabefeld zur�ckgesetzt.
					JOptionPane.showMessageDialog(null, "Sie haben heute bestimmt noch was anderes vor.  ; - )\nGeben Sie bitte einen Wert unter 100 im Feld \"Anzahl Fragen\" ein.");
					textFieldAnzahlFragen.setText("99");
				}
				else if (checkBoxPlus.isSelected() == false && checkBoxMinus.isSelected() == false && checkBoxMal.isSelected() == false && checkBoxGeteilt.isSelected() == false)
				{
					// Bei ung�ltiger Eingabe (keine CheckBox aktiviert) wird ein Fehler ausgegeben.
					JOptionPane.showMessageDialog(null, "Sie sollten mindestens einen Operator ausw�hlen.");
				}
				else if (geteiltStellenZahlEinsString.length() < geteiltStellenZahlZweiString.length())
				{
					// Dann wird noch �berpr�ft, dass die Einstellungen bei "geteilt", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Geteilt\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else if (minusStellenZahlEinsString.length() < minusStellenZahlZweiString.length())
				{
					// Dann wird noch �berpr�ft, dass die Einstellungen bei "minus", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Minus\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else
				{
					// Bei g�ltiger Eingabe im Textfeld AnzahlFragen wird folgender Code ausgef�hrt:
					
					// Die gew�hlte Anzahl der Fragen wird dem AufgabenZaehler �bergeben.
					AufgabenZaehler.setZaehlerMax(integerAnzahlFragen);
					
					// Der Aufgabengenerator liest die gew�hlten "Einstellungen" aus und speichert sie ab.
					AufgabenGenerator.setGenerator();
					
					// Der Z�hler f�r die Aufgaben wird im AufgabenZaehler zur�ckgesetzt.
					AufgabenZaehler.resetZaehler();	
					
					// Die Rechner Seite wird dem Benutzer angezeigt.
					GuiContainer.changePanel("GUI_RECHNER");
					
					// Das Textfeld im Rechner wird fokussiert.
					GuiRechner.setFocus();
				}
			}
			catch (NumberFormatException ex)
			{
				// Wenn die Eingabe nicht in einen Integer Wert umgewandelt werden konnte, wird eine Meldung ausgegeben und das Eingabefeld wird zur�ckgesetzt.
				JOptionPane.showMessageDialog(null, "Die Tastatur zu treffen muss schwierig sein.  ; - )\nGeben Sie bitte einen g�ltigen Wert im Feld \"Anzahl Fragen\" ein");
				textFieldAnzahlFragen.setText("");
			}
		}
		
		else if (event.getSource() == buttonGoTimeAttack || event.getSource() == textFieldDauer)
		{
			// (Button "Start" geklickt oder Enter im Textfeld) der Imput startet diese Schleife, um mit den RechenAufgaben zu starten.
			
			// Die eingegebenen Werte der "geteilt" und "minus" Stellenanzahlen werden f�r sp�ter gespeichert.
			String geteiltStellenZahlEinsString = comboBoxGeteiltErsteStelle.getSelectedItem().toString();
			String geteiltStellenZahlZweiString = comboBoxGeteiltZweiteStelle.getSelectedItem().toString();
			String minusStellenZahlEinsString = comboBoxMinusErsteStelle.getSelectedItem().toString();
			String minusStellenZahlZweiString = comboBoxMinusZweiteStelle.getSelectedItem().toString();
						
			// Hier werden die Benutzer Eingaben auf Ihre Richtigkeit �berpr�ft.
			try
			{
				// Kann die Eingabe im Textfeld AnzahlFragen in einen double Wert umgewandelt werden, wird der folgende Code ausgef�hrt.
				Double doubleDauerMinuten = Double.parseDouble(textFieldDauer.getText());
			
				if (doubleDauerMinuten < 0.1)
				{
					// Bei ung�ltiger Eingabe (kleiner als 1) wird ein Fehler ausgegeben und das Eingabefeld zur�ckgesetzt.
					JOptionPane.showMessageDialog(null, "Das ist aber seeehr wenig Zeit...  \nGeben Sie einen Wert ab 0.1 im Feld \"Dauer\" ein.");
					textFieldDauer.setText("0.1");
				}
				else if (doubleDauerMinuten >= 100)
				{
					// Bei ung�ltiger Eingabe (100 oder gr�sser) wird ein Fehler ausgegeben und das Eingabefeld zur�ckgesetzt.
					JOptionPane.showMessageDialog(null, "Sie haben heute bestimmt noch was anderes vor.  ; - )\nGeben Sie bitte einen Wert unter 100 im Feld \"Dauer\" ein.");
					textFieldDauer.setText("99.99");
				}
				else if (checkBoxPlus.isSelected() == false && checkBoxMinus.isSelected() == false && checkBoxMal.isSelected() == false && checkBoxGeteilt.isSelected() == false)
				{
					// Bei ung�ltiger Eingabe (keine CheckBox aktiviert) wird ein Fehler ausgegeben.
					JOptionPane.showMessageDialog(null, "Sie sollten mindestens einen Operator ausw�hlen.");
				}
				else if (geteiltStellenZahlEinsString.length() < geteiltStellenZahlZweiString.length())
				{
					// Dann wird noch �berpr�ft, dass die Einstellungen bei "geteilt", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Geteilt\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else if (minusStellenZahlEinsString.length() < minusStellenZahlZweiString.length())
				{
					// Dann wird noch �berpr�ft, dass die Einstellungen bei "minus", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Minus\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else
				{
					// Bei g�ltiger Eingabe im Textfeld AnzahlFragen wird folgender Code ausgef�hrt:
					
					// Mit folgendem Code wird der "doubleDauerMinuten" auf zwei Nachkommastellen gek�rzt
					int temp = (int)((double) (doubleDauerMinuten)*100.0);
					doubleDauerMinuten = ((double)temp)/100.0;
					
					// Der gek�rzte "doubleDauerMinuten" wird in das Eingabefeld geschrieben.
					textFieldDauer.setText(Double.toString(doubleDauerMinuten));
					
					// Die gew�hlte Dauer wird dem AufgabenTimer �bergeben.
					AufgabenTimer.setTimer(doubleDauerMinuten);
			
					// Die im TimeAttack h�chstm�gliche Anzahl von Fragen werden dem Aufgabenz�hler �bergeben.
					AufgabenZaehler.setZaehlerMax(300);
					
					// Der Aufgabengenerator liest die gew�hlten "Einstellungen" aus und speichert sie ab.
					AufgabenGenerator.setGenerator();
					
					// Der Z�hler f�r die Aufgaben wird im AufgabenZaehler zur�ckgesetzt.
					AufgabenZaehler.resetZaehler();	

					// Der Timer wird gestartet.
					AufgabenTimer.startTimer();
					
					// Die Rechner Seite wird dem Benutzer angezeigt.
					GuiContainer.changePanel("GUI_RECHNER");
					
					// Das Textfeld im Rechner wird fokussiert.
					GuiRechner.setFocus();
				}					
			}
			catch (NumberFormatException ex)
			{
				// Wenn die Eingabe nicht in einen Double Wert umgewandelt werden konnte, wird eine Meldung ausgegeben und das Eingabefeld wird zur�ckgesetzt.
				JOptionPane.showMessageDialog(null, "Die Tastatur zu treffen muss schwierig sein.  ; - )\nGeben Sie bitte einen g�ltigen Wert im Feld \"Dauer\" ein.");
				textFieldDauer.setText("");
			}
		}
		
		else if (event.getSource() == buttonBack)
		{
			// Der Imput war vom JButton "Back".
			
			// Die Start Seite wird dem Benutzer angezeigt.
			GuiContainer.changePanel("GUI_START");
		
			// Die Anzeige der Einstellungen-Seite wird zur�ckgesetzt.
			layersEinstellungen.remove(buttonGoClassic);
			layersEinstellungen.remove(buttonGoTimeAttack);
			layersEinstellungen.remove(textFieldAnzahlFragen);
			layersEinstellungen.remove(textFieldDauer);	
			layersEinstellungen.remove(overlayClassicJLabel);
			layersEinstellungen.remove(overlayTimeAttackJLabel);

		}
	}

	
	
	// Durch diese Methode holt sich der AufgabenZaehler die Werte der Checkboxen.
	public static boolean[] getAuswahlCheckboxenArray()
	{
		boolean AuswahlCheckboxenArray[] = {checkBoxPlus.isSelected(), checkBoxMinus.isSelected(), checkBoxMal.isSelected(), checkBoxGeteilt.isSelected()};
					
		return AuswahlCheckboxenArray;
	}
	
	
	
	// Durch diese Methode holt sich der AufgabenZaehler die Werte der Stellen Auswahl.
	public static String[][] getAnzahlStellenArray()
	{
		// Die verschiedenen Auswahlen werden in ein 2 Dimensionales Array �bertragen.
		anzahlStellenArray = new String[][]
		{
				{comboBoxPlusErsteStelle.getSelectedItem().toString(), comboBoxPlusZweiteStelle.getSelectedItem().toString()},
				{comboBoxMinusErsteStelle.getSelectedItem().toString(), comboBoxMinusZweiteStelle.getSelectedItem().toString()},
				{comboBoxMalErsteStelle.getSelectedItem().toString(), comboBoxMalZweiteStelle.getSelectedItem().toString()},
				{comboBoxGeteiltErsteStelle.getSelectedItem().toString(), comboBoxGeteiltZweiteStelle.getSelectedItem().toString()},
		};
		return anzahlStellenArray;
	}

	
	
	// Mit dieser Methode wird das GuiEinstellungen f�r den Classic Modus voreingestellt.
	public static void setClassic()
	{
		layersEinstellungen.add(buttonGoClassic);
		layersEinstellungen.add(textFieldAnzahlFragen);
		if (overlayOn)
		{
			layersEinstellungen.add(overlayClassicJLabel);
		}
		classicMode = true;
	}

	
	
	// Mit dieser Methode wird das GuiEinstellungen f�r den TimeAttack Modus voreingestellt.
	public static void setTimeAttack()
	{
		layersEinstellungen.add(buttonGoTimeAttack);
		layersEinstellungen.add(textFieldDauer);
		if (overlayOn)
		{
			layersEinstellungen.add(overlayTimeAttackJLabel);
		}
		classicMode = false;
	}

	
	
	// Mit dieser Methode holt sich das GuiAuswertung die Benutzereinstellungen als html Code.
	public static String getEinstellungen()
	{
		// Die Start-Tags werden eingef�gt.
		String einstellungenString = "<html><font color=#1E6934 bgcolor=#25C256>";
		// Je nach Einstellungen m�ssen am Schluss des html Codes noch zus�tzliche leere Zeilen hinzugef�gt werden. Wie viele das sind regelt der zusatzZeilenInteger.
		Integer zusatzZeilenInteger = 0;
		
		// Je nach Modus wird der Anfang des html Codes angepasst.
		if (classicMode)
		{
			einstellungenString += ("**Anzahl Fragen: " + textFieldAnzahlFragen.getText() + "****************************<br>**********************************************<br>").replace("*", "&#160;");
		}
		else
		{
			einstellungenString += 
			(
				String.format
					("%1$-23s", ("**Anzahl Fragen:*" + (AufgabenPruefer.getAnzahlAufgaben()))).replace(" ", "*")
					
				+ "***Zeit: "
				+ textFieldDauer.getText() + "*Minuten**"
				+ "<br>**********************************************<br>"
					
			).replace("*", "&#160;");    
		}
		
		einstellungenString += "<br><br><br><br><br><br><br><br><br><br>";
			
		// Um die Anzeige der Stellen zu generieren, wird zuerst gepr�ft, ob der jeweilige Operator aktiv war.
		if (checkBoxPlus.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[0][0]))).replace(" ", "&#160;") + " &#43; " + anzahlStellenArray[0][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			// Ansonsten wird der zusatzZeilenInteger um 1 erh�ht.
			zusatzZeilenInteger++;
		}
		if (checkBoxMinus.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[1][0]))).replace(" ", "&#160;") + " &#8722; " + anzahlStellenArray[1][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		if (checkBoxMal.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[2][0]))).replace(" ", "&#160;") + " &#215; " + anzahlStellenArray[2][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		if (checkBoxGeteilt.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[3][0]))).replace(" ", "&#160;") + " &#247; " + anzahlStellenArray[3][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		
		// Mit dieser for Schleife werden die berechneten Zusatzzeilen erstellt.
		for (int i = 0; i < zusatzZeilenInteger; i++)
		{
			einstellungenString += ("*******************************<br>").replace("*", "&#160;");
		}
		
		// Der html Code wird mit den Schluss-Tags erg�nzt.
		einstellungenString += "</font></html>";
		
		return einstellungenString;		
	}


	// Methode f�r Testzwecke (Ausblenden des Overlay)
	public static void overlaySwitch(boolean on)
	{
		if (on)
		{
			overlayOn = true;
		}
		else
		{
			overlayOn = false;
		}
	}
}
