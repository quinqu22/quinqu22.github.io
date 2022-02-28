package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Formation;

public class FormationDAO extends DAO<Formation> {

	private static final String TABLE = "Formation";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String DENOMINATION = "denomination";
	private static final String DIPLOME = "diplôme";

	private static FormationDAO instance=null;
	public static FormationDAO getInstance(){
		if (instance==null){
			instance = new FormationDAO();
		}
		return instance;
	}
	private FormationDAO() {
		super();
	}
	@Override
	public boolean create(Formation formation) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DENOMINATION+", "+DIPLOME+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, formation.getDenomination());
			pst.setString(2, formation.getDiplome());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				formation.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Formation formation) {
		boolean succes = true;
		try {
			int id = formation.getID();
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
	public boolean update(Formation obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Formation read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
