package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Matiere;



public class MatiereDAO extends DAO<Matiere> {

	private static final String TABLE = "Matiere";
	private static final String CLE_PRIMAIRE = "id";
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
			String requete = "INSERT INTO "+TABLE+" ("+NOM+") VALUES (?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, matiere.getNom());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				matiere.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	/*
	 * 
	 * 
	 * 
	 */
	@Override
	public boolean delete(Matiere matiere) {
		boolean succes = true;
		try {
			int id = matiere.getId();
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
	public boolean update(Matiere matiere) {
		boolean succes = true;
		try {			
			String requete = "UPDATE "+TABLE+" ("+NOM+") VALUES (?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, matiere.getNom());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	

	public Matiere read(int id) {
		Matiere matiere = null;
		try {			
			String requete = "SELECT "+CLE_PRIMAIRE+", "+NOM+" FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();// on execute la requete qui consiste a selectionner la bonne salle
			int rowCount = 0;
			if (rs.last()) {//make cursor to point to the last row in the ResultSet object
				rowCount = rs.getRow();
				rs.first(); //make cursor to point to the front of the ResultSet object, donc au premier enregistrement 
			}
			if (rowCount != 1) {
				rs.close();
				return null;
			}
			matiere = new Matiere(rs.getInt(CLE_PRIMAIRE), rs.getString(NOM));	 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matiere;
	}
}
