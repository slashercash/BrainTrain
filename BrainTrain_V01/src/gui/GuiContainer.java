package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Diese Klasse erstellt das HauptFenster und koordiniert durch Befehle, welche Seite aktuell angezeigt wird.
public class GuiContainer 
{
	// Hauptfenster wird erstellt.
	static JFrame frame = new JFrame("BrainTrain");
	
	// Die f�nf Workspaces werden erstellt.
	static GuiStart guiStart = new GuiStart();
	static GuiInfo guiInfo = new GuiInfo();
	static GuiEinstellungen guiEinstellungen = new GuiEinstellungen();
	static GuiRechner guiRechner = new GuiRechner();
	static GuiAuswertung guiAuswertung = new GuiAuswertung();
	
	// Ein CardLayout wird vordefiniert (Layout f�r das Anzeigen verschiedener Workspaces).	
	static CardLayout cl = new CardLayout();
	
	// Der Container wird als JPanel definiert.
	static JPanel panelContainer = new JPanel();
	
	
	// Diese main Methode wird am Anfang des Programmes aufgerufen.
	public static void main(String[] args)
	{
		// Das JPanel panelContainer wird durch das CardLayout zum Container.
		panelContainer.setLayout(cl);
		
		// Die JLayeredPane der 4 Workspaces werden dem panelContainer, zusammen mit je einem "Identifier", hinzugef�gt.
		panelContainer.add(GuiStart.layersStart, "GUI_START");
		panelContainer.add(GuiInfo.layersInfo, "GUI_INFO");
		panelContainer.add(GuiEinstellungen.layersEinstellungen, "GUI_EINSTELLUNGEN");
		panelContainer.add(GuiRechner.layersRechner, "GUI_RECHNER");
		panelContainer.add(GuiAuswertung.layersAuswertung, "GUI_AUSWERTUNG");

		// Dem Hauptfenster "frame" wird der panelContainer �bergeben.
		frame.add(panelContainer);
		// Die Gr�sse des Hauptfensters wird bestummen.
		frame.setSize(600, 500);
		// Beim Schliessen des Hauptfensters wird das JFrame auch im Code beendet.
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Das Hauptfenster wird in der Mitte des Bildschirmes ge�ffnet.
		frame.setLocationRelativeTo(null);
		// Das Hauptfenster wird auf Sichtbar gesetzt.
		frame.setVisible(true);	
		// Die gr�sse des Hauptfensters soll nicht ver�ndert werden.
		frame.setResizable(false);
		
		// Das GuiStart wird dem Benutzer angezeigt.
		changePanel("GUI_START");
	}
	
	
	
	// Diese Methode dient zum wechseln des aktiven JPanel.
	public static void changePanel(String panel)
	{
		// Die Methode erh�lt durch den Parameter den Identifier eines Workspaces (z.Bsp GUI_EINSTELLUNGEN).
		// Der Identifier wird in die show() Methode gesetzt und der dazugeh�rige Workspace wird somit Aktiv.
		cl.show(panelContainer, panel);
	}
}
