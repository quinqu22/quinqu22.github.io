package openbd;

public class Seance {
	String dateDeb;
	String dateFin;
	
	Seance(String dateDeb, String dateFin){
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
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
