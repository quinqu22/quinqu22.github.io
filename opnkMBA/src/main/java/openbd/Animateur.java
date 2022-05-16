package openbd;

public class Animateur extends Humain {
	private int id;
	
	public Animateur(int idHumain, String nom, String prenom, String login, String motdepasse, int id) {
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
