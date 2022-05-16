package openbd;

public class Apprenant extends Humain {
	private int id;
	
	public Apprenant(int idHumain, String nom, String prenom, String login, String motdepasse, int id) {
		super(idHumain, nom, prenom, login, motdepasse);
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
