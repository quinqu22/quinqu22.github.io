package openbd;

import java.util.Date;

public class Promotion {
	private int id;
	private Date dateDeb;
	private Date dateFin;
	
	private Formation formation;
	
	public Promotion(int id, Date dateDeb, Date dateFin, Formation formation){
		this.id = id;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.formation = formation;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDeb() {
		return this.dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Formation getFormation() {
		return this.formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}	
	
}
