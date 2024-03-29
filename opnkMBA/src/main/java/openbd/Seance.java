package openbd;

import java.sql.Date;

public class Seance {
	private int id;
	private Date dateDeb;
	private Date dateFin;
	
	private Salle salle;
	private Cours cours;
	
	public Seance(int id, Date dateDeb, Date dateFin, Salle salle, Cours cours){
		this.id = id;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.salle = salle;
		this.cours = cours;
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
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Salle getSalle() {
		return this.salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Cours getCours() {
		return this.cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
}
