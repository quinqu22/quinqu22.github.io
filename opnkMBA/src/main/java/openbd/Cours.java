package openbd;

public class Cours {
	int ID;
	String dateDeb;
	String dateFin;
	
	//Contructeur de la classe Cours
	Cours(int ID, String dateDeb, String dateFin){
		//Initialisation de l'atrribut avec l'argument
		this.ID = ID;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
}
