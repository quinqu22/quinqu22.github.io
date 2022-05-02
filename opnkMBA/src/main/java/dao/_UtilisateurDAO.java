
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Utilisateur;


public class _UtilisateurDAO extends DAO<Utilisateur> {

	private static final String TABLE = "Utilisateur";
	private static final String CLE_PRIMAIRE = "ID";
	private static final String Nom = "nom";
	private static final String Prénom = "prénom";
	private static final String motDePasse = "mot de passe";
	private static _UtilisateurDAO instance=null;
	
	
	
	public static _UtilisateurDAO getInstance(){
		if (instance==null){
			instance = new _UtilisateurDAO();
		}
		return instance;
	}
	private _UtilisateurDAO(){
		super();
	}

	@Override
	public boolean create(Utilisateur utilisateur) {
		boolean succes=true;// boéan qui sert à dire si la création c'est bien passée ou non 
		try {//dans le try si il y a une exeption de type SQLException alors le try s'arrete et on rentre dans le catch
			String requete = "INSERT INTO "+TABLE+" ("+Nom+", "+Prénom+","+motDePasse+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, utilisateur.getNom());
			pst.setString(2, utilisateur.getPrenom());
			pst.setString(3, utilisateur.getmotDePaase());
			
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();// récupere l'ID de l'utilisateur qui a été genere automatiquement 
			if (rs.next()) { //si rs.next est false alors l'id n'as pas été generé comme il le faut 
				utilisateur.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();//printStackTrace() affiche l'exeption dans la console 
		}
		return succes;
	}

	@Override
	public boolean delete(Utilisateur utilisateur) {
		boolean succes = true;
		try {
			int id = utilisateur.getID();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();// on execute la requete qui consiste a supprimer un enregistrement de la table 
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	
	@Override
	public boolean update(Utilisateur utilisateur) {
		boolean succes = true;
		try {
			int id = utilisateur.getID();
			String requete = "UPDATE "+TABLE+" ("+Nom+", "+Prénom+","+motDePasse+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, utilisateur.getNom());
			pst.setString(2, utilisateur.getPrenom());
			pst.setString(3, utilisateur.getmotDePaase());
			pst.setInt(4, id );
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
		
	}
	@Override
	public Utilisateur read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
