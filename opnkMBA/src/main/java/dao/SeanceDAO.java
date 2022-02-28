package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Seance;

public class SeanceDAO extends DAO<Seance> {

	private static final String TABLE = "Seance";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String DENOMINATION = "denomination";
	private static final String DIPLOME = "diplôme";

	private static SeanceDAO instance=null;
	public static SeanceDAO getInstance(){
		if (instance==null){
			instance = new SeanceDAO();
		}
		return instance;
	}
	private SeanceDAO() {
		super();
	}
	@Override
	public boolean create(Seance seance) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DENOMINATION+", "+DIPLOME+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, seance.getDenomination());
			pst.setString(2, seance.getDiplome());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				seance.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Seance seance) {
		boolean succes = true;
		try {
			int id = seance.getID();
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
	public boolean update(Seance obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Seance read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
