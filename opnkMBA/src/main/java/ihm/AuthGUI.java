package ihm;

import javafx.collections.ObservableList;
import openbd.Utilisateur;

public class AuthGUI {

	public ObservableList<Utilisateur> getUtilisateurDATA() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 private static int compteur=0;
	    private final int numero;
	    private Stage fenetrePrincipale;
	    private BorderPane structureRacineDeLaFenetre;    
	    // format d'objet particulier javaFX qui permet de manipuler facilement les donn�es affich�es ou
	    // s�lectionn�es dans l'ihm
	    private ObservableList<Avion> avionData = FXCollections.observableArrayList();
	    private AvionIhmControl sousControleur=null;
	    public AvionGUI() {
	        super();
	        // On transforme les avions de notre mod�le pour les ajouter � la structure Observable 
	        // mais on pourrait faire cela avec n'importe quel type de requ�te ou fonction
	        this.avionData = getAvionDataAvion();
	        this.numero=compteur++;
	        System.out.println("cponstructeur " + this.numero);
	    }
	    /**
	     * accesseur
	     * @return avionData
	     */
	    public ObservableList<Avion> getAvionData() {
	        return avionData;
	    }
	    /**
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
	    public void initStructureRacineDeLaFenetre() {
	        try {
	            // Fait le lien avec la vue
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AvionGUI.class.getResource("Aviation.fxml"));
	            // Ici, nous choisissons de g�rer par nous m�me le lien entre
	            // le controleur et la vue. Cela �vite d'avoir deux instances du contr�leur
	            // � savoir celle lanc�e au d�but du programme et celle qui aurait �t� g�n�r�e
	            // maintenant
	            loader.setController(this);
	            structureRacineDeLaFenetre = (BorderPane) loader.load();
	            // Affiche la fen�tre principale
	            Scene scene = new Scene(structureRacineDeLaFenetre);
	            fenetrePrincipale.setScene(scene);
	            fenetrePrincipale.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * La mise en place de la sous-fen�tre
	     * qui permettra d'afficher les avions dans la TableView
	     */
	    public void montrerLesAvions() {
	        try {
	            // On associe � l'autre vue de la liste d'avions
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(AvionGUI.class.getResource("./AvionIhm.fxml"));
	            // Ici, nous laissons la vue d�clarer sa propre instance de contr�leur,
	            // unique, d�clar�e une seule fois : maintenant
	            AnchorPane lesAvions = (AnchorPane) loader.load();
	            // Place cette sous-fen�tre au milieu de la fen�tre principale
	            structureRacineDeLaFenetre.setCenter(lesAvions);
	            // r�cup�re le contr�leur de la sous-fen�tre 
	            this.sousControleur = loader.getController();
	            this.sousControleur.setMainApp(this);
	            System.out.println(this.numero+" -> fin de montrer les avions / "+this.sousControleur);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * Retourne la fen�tre principale
	     * @return
	     */
	    public Stage getFenetrePrincipale() {
	        return fenetrePrincipale;
	    }
	    public ObservableList<Avion> getAvionDataEpave() {
	        avionData = FXCollections.observableArrayList();
	        for (int i = 0; i < 10; i++) {
	            Avion av = new Avion(88, "�pave", "garage", 200);
	            avionData.add(av);
	        }
	        return avionData;
	    }
	    public ObservableList<Avion> getAvionDataAvion() {
	        avionData = FXCollections.observableArrayList();
	        List<Avion> lesAvions = AvionDAO.getInstance().readTable();
	        for (Avion avion : lesAvions) {
	            avionData.add(avion);
	        }
	        return avionData;
	    }
	    /**
	     * Pour le Menu principal, on peut associer une action � chaque ligne
	     * Ici, on utilise une fonction de l'autre controleur
	     * Dans Scene Builder, � gauche, on clique sur hierarchy, Button
	     * � droite, a �t� indiqu� le nom de la m�thode dans On Action
	     * @param event
	     */
	    @FXML
	    private void handleButtonsListEpaves(ActionEvent event) {
	        System.out.println(this.numero+" -> on a cliqu� sur le grand menu �paves / "+this.sousControleur);
	        this.sousControleur.handleButtonsListEpaves(event);
	    }
	    @FXML
	    private void handleButtonsListRestore(ActionEvent event) {
	        System.out.println(this.numero+" -> on a cliqu� sur le grand menu restore / "+this.sousControleur);
	        this.sousControleur.handleButtonsListRestore(event);
	    }
	    /**
	     * Programme principal, utilise les arguments que nous avions pr�cis� dans
	     * Run Configurations
	     * @param args
	     */
	    public static void main(String[] args) {
	        launch(args);
	        Connexion.fermer();
	    }
	}

	
}
