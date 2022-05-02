package openbd;

import java.sql.Date;

public class Cours {
	private int id;
	private Date dateDeb;
	private Date dateFin;
	
	private Matiere matiere;
	
	//Contructeur de la classe Cours
	public Cours(int id, Date dateDeb, Date dateFin, Matiere matiere){
		//Initialisation de l'atrribut avec l'argument
		this.id = id;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.matiere = matiere;
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

	public Matiere getMatiere() {
		return this.matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
}
