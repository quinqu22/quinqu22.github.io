package openbd;

public class Salle {
	int id;
	String denomination;
	boolean equipementVirtuel;
	int nbPlaces;
	
	Salle(int id, String denomination, boolean equipementVirtuel, int nbPlaces){
		this.id = id;
		this.denomination = denomination;
		this.equipementVirtuel = equipementVirtuel;
		this.nbPlaces = nbPlaces;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public boolean isEquipementVirtuel() {
		return equipementVirtuel;
	}

	public void setEquipementVirtuel(boolean equipementVirtuel) {
		this.equipementVirtuel = equipementVirtuel;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
}

