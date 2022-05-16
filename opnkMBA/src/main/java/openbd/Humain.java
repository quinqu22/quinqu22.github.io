package openbd;

public abstract class Humain {
	
	private int id;
	private String nom;
	private String prenom;
	private String login;
	private String motdepasse;
	
	public Humain(int id, String nom, String prenom, String login, String motdepasse) {
		//Initialisation de l'atrribut avec l'argument
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.motdepasse = motdepasse;
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
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotdepasse() {
		return this.motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

}
