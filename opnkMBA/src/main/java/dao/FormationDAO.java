package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import openbd.Formation;


public class FormationDAO extends DAO<Formation> {
	/*
	 * les valeurs son en string car c'est du texte mis dans les requetes sql
	 */

	private static final String TABLE = "Formation";
	private static final String CLE_PRIMAIRE = "id";
	private static final String DENOMINATION = "denomination";
	private static final String CERTIFICATIONOUDIPLOME = "certificationOuDiplome";

	private static FormationDAO instance=null;
	
	public static FormationDAO getInstance(){
		if (instance == null){
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
			String requete = "INSERT INTO "+TABLE+" ("+DENOMINATION+", "+CERTIFICATIONOUDIPLOME+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, formation.getDenomination());
			pst.setInt(2, formation.getCertificationOuDiplome());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				formation.setId(rs.getInt(1));
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
			int id = formation.getId();
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
	public boolean update(Formation formation) {
		boolean succes = true;
		try {           
			String requete = "UPDATE"+TABLE+" ("+DENOMINATION+", "+CERTIFICATIONOUDIPLOME+") VALUES (?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, formation.getDenomination());
			pst.setInt(2, formation.getCertificationOuDiplome());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public Formation read(int id) {
		Formation formation = null;
		try {           
			String requete = "SELECT "+CLE_PRIMAIRE+", "+DENOMINATION+", "+CERTIFICATIONOUDIPLOME+" + FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();// on execute la requete qui consiste a selectionner du bon cours
			int rowCount = 0;
			if (rs.last()) {//make cursor to point to the last row in the ResultSet object
				rowCount = rs.getRow();
				rs.first(); //make cursor to point to the front of the ResultSet object, donc au premier enregistrement 
			}
			if (rowCount != 1) {
				rs.close();
				return null;
			}
		
			formation = new Formation(rs.getInt(CLE_PRIMAIRE), rs.getString(DENOMINATION), rs.getInt(CERTIFICATIONOUDIPLOME));    
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return formation;
	}
}
