package openbd;

public class Matiere {
	int ID;
	String denomination;
	
	Matiere(int ID, String denomination){
		this.ID = ID;
		this.denomination = denomination;
		
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
	
}
