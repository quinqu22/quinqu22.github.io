package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import openbd.Cours;
import openbd.Matiere;
// j'ai supprimer l'import de salle car pas utiliser 




public class CoursDAO extends DAO<Cours> {
	
	private static final String TABLE = "Cours";
	private static final String CLE_PRIMAIRE = "Id";
	private static final String DATEDEBUT= "dateDeb";
	private static final String DATEFIN = "dateFin";
	private static final String IDMATIERE = "idMatiere";
	private static CoursDAO instance=null;


	public static CoursDAO getInstance(){
		if (instance==null){
			instance = new CoursDAO();
		}
		return instance;
	}
	private CoursDAO() {
		super();
	}
	@Override
	public boolean create(Cours cours) {
		boolean succes=true;// booléan qui sert à dire si la création c'est bien passée ou non
		try {//dans le try si il y a une exeption de type SQLException alors le try s'arrete et on rentre dans le catch
			String requete = "INSERT INTO "+TABLE+" ("+DATEDEBUT+", "+DATEFIN+", "+IDMATIERE+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, cours.getDateDeb());
			pst.setDate(2, cours.getDateFin());
			pst.setInt(3, cours.getMatiere().getId());
			pst.executeUpdate();
			
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			
			ResultSet rs = pst.getGeneratedKeys();// récupere l'ID du cours qui a été genere automatiquement
			if (rs.next()) {//si rs.next est false alors l'id n'as pas été generé comme il le faut
				cours.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();//printStackTrace() affiche l'exeption dans la console 
		}
		return succes;
	}


	@Override
	public boolean delete(Cours cours) {
		boolean succes = true;
		try {
			int id = cours.getId();
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

	public boolean update(Cours cours) {
		boolean succes = true;
		try {           
			String requete = "UPDATE"+TABLE+" ("+DATEDEBUT+", "+DATEFIN+", "+IDMATIERE+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setDate(1, cours.getDateDeb());
			pst.setDate(2, cours.getDateFin());
			pst.setInt(3, cours.getMatiere().getId());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}


	@Override
	public Cours read(int id) {
		Cours cours = null;
		try {           
			String requete = "SELECT "+CLE_PRIMAIRE+", "+DATEDEBUT+", "+DATEFIN+" + FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
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
			
			/*
			 * pour savoir sir deux signe est compatible
			 * public boolean 
			 * return this.couleur==sign.couleur;
			 */
			
			
			Matiere matiere = MatiereDAO.getInstance().read(rs.getInt(IDMATIERE));
			cours = new Cours(rs.getInt(CLE_PRIMAIRE), rs.getDate(DATEDEBUT), rs.getDate(DATEFIN), matiere);    
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cours;
	}
}
