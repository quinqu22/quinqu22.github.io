package openbd;

public class Utilisateur {

	int ID;
	String Nom;
	String Prenom;
	String motDePasse;
	
	Utilisateur(int ID, String Nom, String Prenom, String motDePasse){
		this.ID = ID;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.motDePasse = motDePasse;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getNom() {
		return Nom;
	}
	
	public void setNom(String Nom) {
		this.Nom = Nom;
	}
	
	public String getPrenom() {
		return Prenom;
	}
	
	public void setPrenom(String Prenom) {
		this.Prenom = Prenom;
	}
	
	public String getmotDePaase() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
}