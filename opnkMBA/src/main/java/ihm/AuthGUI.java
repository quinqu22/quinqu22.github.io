package ihm;

import aero.Avion;
import ihm.bd.AvionIhmControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import openbd.Utilisateur;

public class AuthGUI extends Application {

	private static int compteur=0;
	private final int numero;
	
    private Stage fenetrePrincipale;
    private BorderPane structureRacineDeLaFenetre;    
    
    // format d'objet particulier javaFX qui permet de manipuler facilement les donn�es affich�es ou
    // s�lectionn�es dans l'ihm
    private ObservableList<Avion> avionData = FXCollections.observableArrayList();
	
    private AvionIhmControl sousControleur=null;
    
    public AuthGUI() {
    	super();
	// On transforme les avions de notre mod�le pour les ajouter � la structure Observable 
    // mais on pourrait faire cela avec n'importe quel type de requ�te ou fonction
    	this.avionData = getAvionDataAvion();
    	this.numero=compteur++;
    	System.out.println("cponstructeur " + this.numero);
    }
 
    **
	 * La fonction lanc�e au d�but d'une application javafx
	 */
	@Override
   public void start(Stage premiereScene) {
       System.out.println("Start appel� !");
       this.fenetrePrincipale = premiereScene;
       this.fenetrePrincipale.setTitle("Aeroport");

       System.out.println("milieu Start 1");
       initStructureRacineDeLaFenetre();
       System.out.println("milieu Start 2 ");

       montrerLesAvions();
       System.out.println("fin Start");
   }

   /**
    * Initialisation de la structure de fen�tres (la vue principale)
    */
	
}
