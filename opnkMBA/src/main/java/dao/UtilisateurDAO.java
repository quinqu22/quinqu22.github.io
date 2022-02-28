package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Utilisateur;


public class UtilisateurDAO extends DAO<Utilisateur> {

	private static final String TABLE = "Utilisateur";
	private static final String CLE_PRIMAIRE = "ID";

	private static final String Nom = "nom";
	private static final String Prénom = "prénom";
	private static final String motDePasse = "mot de passe";

	private static UtilisateurDAO instance=null;
	public static UtilisateurDAO getInstance(){
		if (instance==null){
			instance = new UtilisateurDAO();
		}
		return instance;
	}
	private UtilisateurDAO() {
		super();
	}
	@Override
	public boolean create(Utilisateur utilisateur) {
		boolean succes=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+Nom+", "+Prénom+","+motDePasse+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, utilisateur.getNom());
			pst.setString(2, utilisateur.getPrenom());
			pst.setString(2, utilisateur.getmotDePaase());
			
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setID(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
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
			pst.executeUpdate();;
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	@Override
	public boolean update(Utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Utilisateur read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
