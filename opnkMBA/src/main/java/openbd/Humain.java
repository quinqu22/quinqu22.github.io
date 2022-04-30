package openbd;

public abstract class Humain {
	private int id;
	private String nom;
	private String prenom;
	
	//Contructeur de la classe Cours
	
	public Humain(int id, String nom, String prenom) {
		//Initialisation de l'atrribut avec l'argument
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
