package ihm;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import openbd.Utilisateur;

public class AuthControl {
	private TableView<Utilisateur> UtilisateurTable;
	// pour laquelle on n'affiche que quatre informations (id, nom, prénom et motDePasse)
	@FXML
	private TableColumn<Utilisateur, String> idColonne;
	@FXML
	private TableColumn<Utilisateur, String> nomColonne;
	@FXML
	private TableColumn<Utilisateur, String> PrenomColonne;
	@FXML
	private TableColumn<Utilisateur, String> MotDePasseColonne;

	// Les champs de la partie gauche de la fen�tre qui affichent les d�tails 
	// de l'Utilisateurs actif
	@FXML
	private Label identifiant;
	@FXML
	private Label motDePasse;
	
	
	// un champs qui permet de montrer l'action du bouton
		@FXML
		private Label Connexion;
	
	

	// Les r�f�rences crois�es vers l'application principale
	private AuthGUI mainApp;

	// La ligne s�lectionn�e dans la liste (par d�faut la premi�re)
	private int ligneActive=0;

	/**
	 * Le constructeur est appel� juste avant la m�thode d'initialisation
	 */
	public AuthControl() {  	
	}

	/**
	 * Initializes the controller class.
	 * Cette m�thode est appel�e juste apr�s que la fen�tre ait �t� charg�e
	 */
	@FXML
	private void initialize() {
		// La notation s'appuie sur l'�criture fonctionnelle (lambda) que nous ne 
		// verrons pas en d�tail.
		// Pour faire court : les m�thodes sont appliqu�es pour chaque ligne avec chaque �l�ment de la liste
		// On passe par des SimpleStringProperty pour conserver nos classes m�tier sans javaFX
		idColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()+""));
		nomColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		PrenomColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
		MotDePasseColonne.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getmotDePaase()));
	}

	/**
	 * Cette m�thode permet de relier le contr�leur principal (reli� au m�tier)
	 * au sous-contr�leur
	 * @param mainApp : l'application principale 
	 */
	public void setMainApp(AuthGUI mainApp) {
		//System.out.println("setMainApp called");
		this.mainApp = mainApp;
		UtilisateurTable.setItems(this.mainApp.getUtilisateurDATA());
		afficheUtilisateurSelection();  
	}

	/**
	 * Mets � jour la partie gauche de la fen�tre avec les d�tails de l'Utilisateur actif 
	 */
	private void afficheUtilisateurSelection() {
		Utilisateur UtilisateurCourant = UtilisateurTable.getItems().get(ligneActive);
		identifiant.setText(UtilisateurCourant.getID()+"");

		motDePasse.setText(UtilisateurCourant.getmotDePaase());
	
	}

	/**
	 * Pour chaque bouton on peut d�finir une m�thode propre associ�e � ce bouton
	 * Dans Scene Builder, � gauche, on clique sur hierarchy, Button
	 * � droite, a �t� indiqu� le nom de la m�thode dans On Action
	 * @param event
	 */
	@FXML
	private void handleButtonAction(ActionEvent event) {
		Connexion.setText("clic (" + ligneActive+")");
	}

	
	/**
	 * � v�rifier
	 * Il serait possible de g�n�rer des petites fen�tres de dialogue
	 * mais actuellement la classe Alert est inconnue (n�cessite javaFX 8u40)
	 * @param event
	 *
	@FXML
	private void handleButtonActionMessage(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("I have a great message for you!");

		alert.showAndWait();
	
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			texteTest.setText("option ok");
		} else {
			texteTest.setText("option cancel");
		}	
	}*/
	
	
	
	/**
	 * Pour le MenuButton, on peut d�finir une m�thode propre � chaque ligne associ�e � cette liste
	 * Dans Scene Builder, � gauche, on clique sur hierarchy, Button
	 * � droite, a �t� indiqu� le nom de la m�thode dans On Action
	 * @param event
	 */
	
	/**
	 * Pour chaque partie de la fen�tre qui peut �tre manipul�e par la souris,
	 * on peut faire une m�thode.
	 * Celle-ci est indiqu�e dans la partie "code" de TableView :
	 * Dans Scene Builder, � gauche, on clique sur hierarchy, TableView
	 * � droite, a �t� indiqu� le nom de la m�thode dans On Mouse Clicked
	 *   
	 * @param event
	 */
	@FXML
	private void handleMouseTableViewAction(MouseEvent event) {
		// permet de r�cup�rer le num�ro de la ligne s�lectionn�es
		this.setLigneActive(UtilisateurTable.getSelectionModel().getSelectedIndex());
		this.afficheUtilisateurSelection();
	}

	/**
	 * Modifie la ligne active
	 * @param ligneSelectionnee
	 */
	public void setLigneActive(int ligneSelectionnee) {
		this.ligneActive=ligneSelectionnee;
	}

}



