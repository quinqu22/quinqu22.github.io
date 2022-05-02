package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Formation;
import openbd.Promotion;


public class PromotionDAO extends DAO<Promotion> {
	/*
	 * les valeurs son en string car c'est du texte mis dans les requetes sql
	 */

	private static final String TABLE = "Promotion";
	private static final String CLE_PRIMAIRE = "id";
	private static final String DATEDEB = "dateDeb";
	private static final String DATEFIN = "dateFin";
	private static final String IDFORMATION = "idFormation";
	

	private static PromotionDAO instance=null;
	// CRÉE UNE INSTance si elle est null
	public static PromotionDAO getInstance(){
		if (instance == null){
			instance = new PromotionDAO();
		}
		return instance;
	}
	
	
	private PromotionDAO() {
		super();
	}
	
	
	@Override
	public boolean create(Promotion promotion) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DATEDEB+", "+DATEFIN+", "+IDFORMATION+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, promotion.getDateDeb());
			pst.setDate(2, promotion.getDateFin());
			pst.setInt(3, promotion.getFormation().getId());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				promotion.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Promotion promotion) {
		boolean succes = true;
		try {
			int id = promotion.getId();
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
	public boolean update(Promotion promotion) {
		boolean succes = true;
		try {           
			String requete = "UPDATE"+TABLE+" ("+DATEDEB+", "+DATEFIN+","+IDFORMATION+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setDate(1, promotion.getDateDeb());
			pst.setDate(2, promotion.getDateFin());
			pst.setInt(3, promotion.getFormation().getId());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	
	@Override
	public Promotion read(int id) {
		Promotion promotion = null;
		try {           
			String requete = "SELECT "+CLE_PRIMAIRE+", "+DATEDEB+", "+DATEFIN+", "+IDFORMATION+" + FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
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
			Formation formation = FormationDAO.getInstance().read(rs.getInt(IDFORMATION));
			promotion = new Promotion(rs.getInt(CLE_PRIMAIRE), rs.getDate(DATEDEB), rs.getDate(DATEFIN), formation);    
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return promotion;
	}
}
