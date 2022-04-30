package openbd;

public class Salle {
	private int id;
	private String denomination;
	private boolean equipementVirtuel;
	private int nbPlaces;
	
	public Salle(int id, String denomination, boolean equipementVirtuel, int nbPlaces){
		this.id = id;
		this.denomination = denomination;
		this.equipementVirtuel = equipementVirtuel;
		this.nbPlaces = nbPlaces;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenomination() {
		return this.denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public boolean isEquipementVirtuel() {
		return this.equipementVirtuel;
	}

	public void setEquipementVirtuel(boolean equipementVirtuel) {
		this.equipementVirtuel = equipementVirtuel;
	}

	public int getNbPlaces() {
		return this.nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
}

