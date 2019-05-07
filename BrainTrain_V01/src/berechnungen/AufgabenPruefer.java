package berechnungen;

import gui.GuiAuswertung;

// In dieser Klasse wird die das Array mit all den gespeicherten Aufgaben ausgewertet, und der html text dazu zur Darstellung generiert.
public class AufgabenPruefer
{
	// Integer f�r die Berechnung der Aufgaben Anzahl
	static Double anzahlAufgabenDouble;

	// In dieser Methode wird die das Array mit all den gespeicherten Aufgaben ausgewertet, und der html text dazu zur Darstellung generiert.
	public static void pruefeAufgabenListe(String[][] aufgabeAntwortUndLoesungString)
	{
		// Strings		
		String pruefListeString;
		String zeileString;
		String prozentAngabeString;
		
		// Integer
		Double anzahlRichtigeAntwortenDouble;
		Integer antwortInteger;
		Integer loesungInteger;
		
		// Anzahl Aufgaben und Anzahl richtige Antworten wird auf 0 zur�ckgesetzt.
		anzahlAufgabenDouble = 0d;
		anzahlRichtigeAntwortenDouble = 0d;
		
		// Hier wird die Anzahl der gespeicherten Aufgaben im Array berechnet. (Das Array hat insgesamt Platz f�r 300 Aufgaben).
		for (int i = 0; i <= 299; i++)
		{
			if (aufgabeAntwortUndLoesungString[i][1] == null)	// > Sobald der Arraplatz leer ist, wird mit i = 300 die for Schleife abgebrochen.
			{
				i = 300;
			}
			else
			{								
				anzahlAufgabenDouble++;	// > Ist der Arrayplatz belegt, wird die Aufgaben Anzahl um 1 erh�ht.
			}		
		}
		
		// In die pruefListe wird der gesamte html code gespeichert. Die start-Tags werden hier direkt schon eingef�gt.
		pruefListeString = "<html><font bgcolor=#25C256>" + ("*******************************************<br></font>").replace("*", "&#160;");
				
		// F�r jede gespeicherte Aufgabe wird folgende Prozedur durchgef�hrt.
		for (int j = 0; j < (anzahlAufgabenDouble); j++)
		{
			// Antwort und L�sung werden in eine Integer Variable gespeichert.
			antwortInteger = Integer.parseInt(aufgabeAntwortUndLoesungString[j][1]);
			loesungInteger = Integer.parseInt(aufgabeAntwortUndLoesungString[j][2]);
			
			// Im zeileString wird die Aufgabe und die Benutzereingabe in einen html code umgewandelt (z.Bsp. 24 + 8 = 32). die Operatoren werden zus�tzlich durch optisch sch�nere html Operatoren ersetzt.
			zeileString = String.format("%1$-30s", ("  " + aufgabeAntwortUndLoesungString[j][0] + " = " + aufgabeAntwortUndLoesungString[j][1])).replace(" ", "&#160;").replace("+", "&#43;").replace("-", "&#8722;").replace("*", "&#215;").replace(":", "&#247;");
			
			// Antwort und L�sung wird verglichen.
			if (antwortInteger - loesungInteger == 0)
			{
				// Wurde die Aufgabe richtig beantwortet, erh�llt die Zeile eine hellere Hintergrundfarbe und ein "H�ckchen".
				pruefListeString += "<font color=#1E6934 bgcolor=#25C256>" + zeileString + "&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</font><br>";
				// Zus�tzlich wird die Anzahl der Richtigen Antworten um eins erh�ht.
				anzahlRichtigeAntwortenDouble++;
			}
			else
			{
				// Wurde die Aufgabe falsch beantwortet, erh�llt die Zeile eine dunklere Hintergrundfarbe und ein "x". Zus�tzlich wird die L�sung hinten angesetzt.
				pruefListeString += "<font color=#25C256 bgcolor=#1E6934>" + zeileString + "&#215;" + String.format("%1$-1s %2$8s", "", (aufgabeAntwortUndLoesungString[j][2])).replace(" ", "&#160;") + "&#160;&#160;</font><br>";
			}
		}
		
		// Dann werden aus Darstellungsgr�nden noch 8 Lehrzeilen hinzugef�gt.
		for (int x = 0; x < 8; x++)
		{
			pruefListeString += ("<font bgcolor=#25C256>*******************************************<br></font>").replace("*", "&#160;");
		}
		
		// Schlussendlich wird der html Code mit den abschliessenden Tags erg�nzt.
		pruefListeString += "</html>";
		
		// Mit dieser if Schleife wird im Falle von keiner gel�sten Aufgabe (in TimeAttack die Zeit ablaufen lassen) eine Division durch 0 verhindert.
		if (anzahlAufgabenDouble == 0)
		{
			prozentAngabeString = ("<html><font color=#1E6934 bgcolor=#25C256>****************************************<br>**0 % der Fragen richtig beantwortet****<br>****************************************</font></html>").replace("*", "&#160;");
		}
		else
		{
			// Hier wird die Prozentuale Angabe der richtig gel�sten Aufgaben berechnet und als html Code gespeichert.
			prozentAngabeString = ("<html><font color=#1E6934 bgcolor=#25C256>****************************************<br>**" + Integer.toString((int)(anzahlRichtigeAntwortenDouble / anzahlAufgabenDouble * 100)) +  " % der Fragen richtig beantwortet****<br>****************************************</font></html>").replace("*", "&#160;");
		}
			
		// Die pruefListe und die prozentAngabe werden dem GuiAuswertung �bergeben.
		GuiAuswertung.setAuswertung(pruefListeString, prozentAngabeString);
		
		// Da die pruefListe erstellt ist, wird das Aufgaben Array im AufgabenGenerator zur�ckgesetzt.
		AufgabenGenerator.resetAufgabeAntwortUndLoesungString();
	}
	
	// Diese Methode gibt die Anzahl der Aufgaben zur�ck.
	public static String getAnzahlAufgaben()
	{
		return Integer.toString(anzahlAufgabenDouble.intValue());
	}

}
