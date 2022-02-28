package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Promotion;

public class PromotionDAO extends DAO<Promotion> {

	private static final String TABLE = "Promotion";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String DATE_DEBUT= "dateDeb";
	private static final String DATE_FIN = "dateFin";

	private static PromotionDAO instance=null;
	public static PromotionDAO getInstance(){
		if (instance==null){
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
			String requete = "INSERT INTO "+TABLE+" ("+DATE_DEBUT+", "+DATE_FIN+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, promotion.getDateDeb());
			pst.setString(2, promotion.getDateFin());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				promotion.setID(rs.getInt(1));
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
			int id = promotion.getID();
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
	public boolean update(Promotion obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Promotion read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
