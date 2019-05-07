package berechnungen;

import javax.swing.JOptionPane;

import gui.GuiContainer;
import gui.GuiRechner;

//Diese Klasse regelt das saubere Beenden des Aufgabenblockes und die korrekte Darstellung der AufgabenNr im Classic Modus.
public class AufgabenZaehler
{
	// Integer f�r Z�hlerberechnungen
	public static Integer integerZaehler;
	public static Integer integerZaehlerMax;
	
	// String f�r Z�hleranzeigen
	public static String stringZaehler;
	public static String stringZaehlerMax;
	public static String stringZaehlerAndZaehlerMax;
	
	// Boolean zum erkennen in welchem Modus der Z�hler sich befindet.
	public static Boolean ClassicModeBoolean;
	
	
	
	// Mit dieser Methode wird die gew�hlte Anzahl der Fragen gesetzt.
	public static void setZaehlerMax(Integer maxZahl)
	{
		// Das Argument wird als lokale Integer Variable gespeichert.
		integerZaehlerMax = maxZahl;
	}
	
	
	
	// Mit dieser Methode wird der Zaehler zur�ckgesetzt.
	public static void resetZaehler()
	{
		integerZaehler = 0;
		// Anschliessend wird direkt die Methode setNextQuestion() aufgerufen um den Z�hler auf 1 zu setzen 
		// und damit im GuiRechner die erste Anzeige (Aufgabe 1 von ?) angezeigt wird.
		setNextQuestion();
	}

	
	
	// Mit dieser Methode regelt den Befehl zum generieren neuer Aufgaben, und den Status im Classic Modus des GuiRechners.
	public static void setNextQuestion()
	{
		// Diese Schleife wird ausgef�hrt, solange die ausgew�hlte Anzahl der Fragen noch nicht erreicht wurde.
		if (integerZaehler < integerZaehlerMax)
		{
			// Der Z�hler wird um eins erh�ht.
			integerZaehler += 1;

			// Wenn der AufgabenZ�hler im Classic Modus befindet wird der Anzeige Status aktualisiert.
			if (ClassicModeBoolean)
			{				
				// Ein String mit der Nr der Aktuellen Frage und der Anzahl aller Fragen wird erstellt (z.Bsp Aufgabe 7 von 25).
				stringZaehlerAndZaehlerMax = "Aufgabe " + Integer.toString(integerZaehler) + " von " + Integer.toString(integerZaehlerMax);
				// Der String wird im GuiRechner als Status gesetzt.
				GuiRechner.setStatus(stringZaehlerAndZaehlerMax);
			}
			
			// Eine neue Aufgabe wird generiert.
			AufgabenGenerator.generateAufgabe(integerZaehler);			
		}
		
		// Wenn man im TimeAttack Modus 300 Fragen beantwortet, greift diese Schleife.
		else if (integerZaehler == 300)
		{
			// Der Timer wird gestoppt.
			AufgabenTimer.stopTimer();
			// Eine Meldung wird ausgegeben.
			JOptionPane.showMessageDialog(null, "Sie haben die H�chstzahl der Antworten erreicht.\nSie werden nun zur Auswertung weitergeleitet");
			// Der AufgabenGenerator sendet seine generierten Aufgaben weiter.
			AufgabenGenerator.sendAufgabenListe();
			// Dem Benutzer wird das GuiAuswertung angezeigt.
			GuiContainer.changePanel("GUI_AUSWERTUNG");
		}
		
		// Wenn die Max Anzahl der Fragen erreicht wurde, greift folgende Schleife
		else
		{
			// Der AufgabenGenerator sendet seine generierten Aufgaben weiter.
			AufgabenGenerator.sendAufgabenListe();						
			// Dem Benutzer wird das GuiAuswertung angezeigt.
			GuiContainer.changePanel("GUI_AUSWERTUNG");		
		}
	}

	
	
	// Diese Methode setzt den AugabenZaehler in den Classic Modus.
	public static void setClassic()
	{
		ClassicModeBoolean = true;
	}

	
	
	// Diese Methode setzt den AugabenZaehler in den TimeAttack Modus.
	public static void setTimeAttack()
	{
		ClassicModeBoolean = false;
	}
}
