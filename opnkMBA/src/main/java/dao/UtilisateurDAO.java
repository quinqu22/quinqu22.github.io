
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aero.Avion;
import openbd.Matiere;
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
	private UtilisateurDAO(){
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
		Utilisateur utilisateur = null;
		try {			
			String requete = "SELECT "+CLE_PRIMAIRE+", "+Nom+", "+Prénom+", "+motDePasse+" FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
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
			utilisateur = new Utilisateur(rs.getInt(CLE_PRIMAIRE), rs.getString(Nom), rs.getString(Prénom), rs.getString(motDePasse));	 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
    public List<Utilisateur> readTable() {
        List<Utilisateur> rep = new ArrayList<Utilisateur>();
        Utilisateur av = null;
        try{
            String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
            ResultSet res = Connexion.executeQuery(requete) ;
            while(res.next()){
                int id = res.getInt(1);
                av = UtilisateurDAO.getInstance().read(id);
                rep.add(av);
            }
        }
        catch(SQLException e){
            System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
        }
        return rep;
    }

}
