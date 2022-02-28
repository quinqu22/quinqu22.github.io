package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Cours;

public class CoursDAO extends DAO<Cours> {

	private static final String TABLE = "Cours";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String DATE_DEBUT= "dateDeb";
	private static final String DATE_FIN = "dateFin";

	private static CoursDAO instance=null;
	public static CoursDAO getInstance(){
		if (instance==null){
			instance = new CoursDAO();
		}
		return instance;
	}
	private CoursDAO() {
		super();
	}
	@Override
	public boolean create(Cours cours) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DATE_DEBUT+", "+DATE_FIN+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cours.getDateDeb());
			pst.setString(2, cours.getDateFin());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cours.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Cours cours) {
		boolean succes = true;
		try {
			int id = cours.getID();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();;
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	@Override
	public boolean update(Cours obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Cours read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
