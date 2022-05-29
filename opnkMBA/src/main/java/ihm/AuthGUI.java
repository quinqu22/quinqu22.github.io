package ihm;

import java.io.IOException;
import java.util.List;

import dao.Connexion;
import dao.UtilisateurDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import openbd.Utilisateur;

public class AuthGUI extends Application {


	private static int compteur=0;
	private final int numero;
	
    private Stage fenetrePrincipale;
    private AnchorPane structureRacineDeLaFenetre;
    
    private ObservableList<Utilisateur> connexionData = FXCollections.observableArrayList();
    
    private AuthControl sousControleur=null;
    
    
    
    public AuthGUI() {
    	super();
    	this.connexionData = getConnexionDataConnexion();
    	this.numero=compteur++;
		System.out.println("cponstructeur " + this.numero);
    }
    public ObservableList<Utilisateur> getConnexionData() {
		return connexionData;
	}

 	@Override
 	public void start(Stage premierescene) throws Exception {
 	
 		
 		this.fenetrePrincipale = premierescene;
 		this.fenetrePrincipale.setTitle("Utilisateur");
 		
 		initStructureRacineDeLaFenetre();
 		
 		
 		
 	}
    public void initStructureRacineDeLaFenetre() {
        try {
            // Fait le lien avec la vue
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AuthGUI.class.getResource("authentification.fxml"));
            
            // Ici, nous choisissons de gérer par nous même le lien entre
            // le controleur et la vue. Cela évite d'avoir deux instances du contrôleur
            // à savoir celle lancée au début du programme et celle qui aurait été générée
            // maintenant
            loader.setController(this);
            structureRacineDeLaFenetre = (AnchorPane) loader.load();
            
            // Affiche la fenêtre principale
            Scene scene = new Scene(structureRacineDeLaFenetre);
            fenetrePrincipale.setScene(scene);
            fenetrePrincipale.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     /*public void montrerLesUtilisateurs() {
        try {
            // On associe à l'autre vue de la liste 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnexionGUI.class.getResource("./Utilisateur.fxml"));
            //System.out.println("loader"+UtilisateurGUI.class.getResource("./Utilisateur.fxml"));
            // Ici, nous laissons la vue déclarer sa propre instance de contrôleur,
            // unique, déclarée une seule fois : maintenant
            AnchorPane lesUtilisateurs = (AnchorPane) loader.load();
            
            
            // Place cette sous-fenêtre au milieu de la fenêtre principale
            structureRacineDeLaFenetre.setCenter(lesUtilisateurs);
                        
            // récupère le contrôleur de la sous-fenêtre 
            this.sousControleur = loader.getController();
            this.sousControleur.setMainApp(this);
            
    		System.out.println(this.numero+" -> fin de montrer les utilisateur / "+this.sousControleur);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public Stage getFenetrePrincipale() {
 		return fenetrePrincipale;
 	}
    
    public ObservableList<Utilisateur> getConnexionDataConnexion() {
		connexionData = FXCollections.observableArrayList();
		List<Utilisateur> lesUtilisateurs = UtilisateurDAO.getInstance().readTable();
		for (Utilisateur connexion : lesUtilisateurs) {
			connexionData.add(connexion);
		}
		return connexionData;
	}
    
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        fenetrePrincipale.getScene().setRoot(pane);
    }
    
    public static void main(String[] args) {
        launch(args);
        Connexion.fermer();
    }
	public AuthControl getSousControleur() {
		return sousControleur;
	}
	public void setSousControleur(AuthControl sousControleur) {
		this.sousControleur = sousControleur;
	}
}