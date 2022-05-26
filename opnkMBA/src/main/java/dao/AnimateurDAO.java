package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.LinkedList;

import openbd.Animateur;


public class AnimateurDAO extends DAO<Animateur> {

	private static final String TABLE = "Animateur";
	private static final String CLE_PRIMAIRE = "id";//nom de la collone id de la tables animateur
	private static final String NOM = "nom";
	private static AnimateurDAO instance=null;

	public static AnimateurDAO getInstance() {
		if (instance == null) {
			instance = new AnimateurDAO();
		}
		return instance;
	}
	private AnimateurDAO() {
		super();
	}

	@Override
	public boolean create(Animateur animateur) {
		boolean succes = true;// booléan qui sert à dire si la création c'est bien passée ou non 
		try {//dans le try si il y a une exeption de type SQLException alors le try s'arrete et on rentre dans le catch
			String requete = "INSERT INTO "+TABLE+" ("+NOM+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, animateur.getNom());	
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();// récupere l'ID de la animateur qui a été genere automatiquement 
			if (rs.next()) { //si rs.next est false alors l'id n'as pas été generé comme il le faut 
				animateur.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();//printStackTrace() affiche l'exeption dans la console 
		}
		return succes;
	}

	@Override
	public boolean delete(Animateur animateur) {
		boolean succes = true;
		try {
			int id = animateur.getId();
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
	public boolean update(Animateur animateur) {
		boolean succes = true;
		try {			
			String requete = "UPDATE "+TABLE+" ("+NOM+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, animateur.getNom());
			pst.setInt(2, animateur.getId());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	@Override
	public Animateur read(int id) {
		Animateur animateur = null;
		try {			
			String requete = "SELECT "+CLE_PRIMAIRE+", "+NOM+" FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();// on execute la requete qui consiste a selectionner la bonne animateur
			int rowCount = 0;
			if (rs.last()) {//make cursor to point to the last row in the ResultSet object
				rowCount = rs.getRow();
				rs.first(); //make cursor to point to the front of the ResultSet object, donc au premier enregistrement 
			}
			if (rowCount != 1) {
				rs.close();
				return null;
			}
			// a Vérifier 
			animateur = new Animateur(rs.getInt(CLE_PRIMAIRE), rs.getString(NOM));	 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animateur;
	}

}
