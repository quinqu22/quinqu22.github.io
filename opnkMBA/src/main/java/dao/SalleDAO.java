package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.LinkedList;

import openbd.Salle;


public class SalleDAO extends DAO<Salle> {

	private static final String TABLE = "Salle";
	private static final String CLE_PRIMAIRE = "id";//nom de la collone id de la tables salle
	private static final String DENOMINATION = "denomination";
	private static final String EQUIPEMENTVIRTUEL = "equipementVirtuel";
	private static final String NBPLACES = "nbPlaces";
	private static SalleDAO instance=null;

	public static SalleDAO getInstance() {
		if (instance == null) {
			instance = new SalleDAO();
		}
		return instance;
	}
	private SalleDAO() {
		super();
	}

	@Override
	public boolean create(Salle salle) {
		boolean succes = true;// booléan qui sert à dire si la création c'est bien passée ou non 
		try {//dans le try si il y a une exeption de type SQLException alors le try s'arrete et on rentre dans le catch
			String requete = "INSERT INTO "+TABLE+" ("+DENOMINATION+", "+EQUIPEMENTVIRTUEL+","+NBPLACES+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, salle.getDenomination());
			pst.setBoolean(2, salle.isEquipementVirtuel());
			pst.setInt(3, salle.getNbPlaces());			
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();// récupere l'ID de la salle qui a été genere automatiquement 
			if (rs.next()) { //si rs.next est false alors l'id n'as pas été generé comme il le faut 
				salle.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();//printStackTrace() affiche l'exeption dans la console 
		}
		return succes;
	}

	@Override
	public boolean delete(Salle salle) {
		boolean succes = true;
		try {
			int id = salle.getId();
			String requete = "DELETE FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();// on execute la requete qui consiste a supprimer un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean update(Salle salle) {
		boolean succes = true;
		try {			
			String requete = "UPDATE "+TABLE+" ("+DENOMINATION+", "+EQUIPEMENTVIRTUEL+","+NBPLACES+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, salle.getDenomination());
			pst.setBoolean(2, salle.isEquipementVirtuel());
			pst.setInt(3, salle.getNbPlaces());	
			pst.setInt(4, salle.getId());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	@Override
	public Salle read(int id) {
		Salle salle = null;
		try {			
			String requete = "SELECT "+CLE_PRIMAIRE+", "+DENOMINATION+", "+EQUIPEMENTVIRTUEL+", "+NBPLACES+"+ FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
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
			salle = new Salle(rs.getInt(CLE_PRIMAIRE), rs.getString(DENOMINATION), rs.getBoolean(EQUIPEMENTVIRTUEL), rs.getInt(NBPLACES));	 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}

}
