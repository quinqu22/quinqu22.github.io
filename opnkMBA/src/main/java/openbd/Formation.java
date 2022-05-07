package openbd;

public class Formation {
	private final int DIPLOME = 0;
	private final int CERTIFICATION = 1;//constante	
	
	private int id;
	private String denomination;
	private int certificationOuDiplome;
	
	public Formation(int id, String denomination, int certificationOuDiplome){
		this.id = id;
		this.denomination = denomination;
		this.certificationOuDiplome = certificationOuDiplome;
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

	public int getCertificationOuDiplome() {
		return this.certificationOuDiplome;
	}

	public void setCertificationOuDiplome(int certificationOuDiplome) {
		this.certificationOuDiplome = certificationOuDiplome;
	}
	
}
