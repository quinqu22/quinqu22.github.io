package openbd;

public class Formation {
	int ID;
	String denomination;
	String diplome;
	
	Formation(int ID, String denomination, String diplome){
		this.ID = ID;
		this.denomination = denomination;
		this.diplome = diplome;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String Diplome) {
		this.diplome = Diplome;
	}
	
}
