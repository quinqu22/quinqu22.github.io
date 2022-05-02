package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Matiere;

public class MatiereDAO extends DAO<Matiere> {

	private static final String TABLE = "matiere";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String NOM= "nom";
	

	private static MatiereDAO instance=null;
	public static MatiereDAO getInstance(){
		if (instance==null){
			instance = new MatiereDAO();
		}
		return instance;
	}
	private MatiereDAO() {
		super();
	}
	@Override
	public boolean create(Matiere matiere) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DENOMINATION+") VALUES (?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, matiere.getDenomination());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				matiere.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Matiere matiere) {
		boolean succes = true;
		try {
			int id = matiere.getID();
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
	public boolean update(Matiere obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Matiere read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
