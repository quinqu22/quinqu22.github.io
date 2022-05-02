package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import openbd.Cours;
import openbd.Salle;
import openbd.Seance;


public class SeanceDAO extends DAO<Seance> {

	private static final String TABLE = "Seance";
	private static final String CLE_PRIMAIRE = "id"; //nom de la collone id de la tables salle
	private static final String DATEDEB = "dateDeb";
	private static final String DATEFIN = "dateFin";
	private static final String IDSALLE = "idSalle";
	private static final String IDCOURS = "idCours";
	private static SeanceDAO instance=null;

	public static SeanceDAO getInstance() {
		if (instance == null) {
			instance = new SeanceDAO();
		}
		return instance;
	}
	private SeanceDAO() {
		super();
	}

	@Override
	public boolean create(Seance seance) {
		boolean succes = true;// booléan qui sert à dire si la création c'est bien passée ou non 
		try {//dans le try si il y a une exeption de type SQLException alors le try s'arrete et on rentre dans le catch
			String requete = "INSERT INTO "+TABLE+" ("+DATEDEB+", "+DATEFIN+","+IDSALLE+","+IDCOURS+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, seance.getDateDeb());
			pst.setDate(2, seance.getDateFin());
			pst.setInt(3, seance.getSalle().getId());//va chercher l'id de la salle 
			pst.setInt(4, seance.getCours().getId());// va chercher l'id de cours
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();// récupere l'ID de la seance qui a été genere automatiquement 
			if (rs.next()) { //si rs.next est false alors l'id n'as pas été generé comme il le faut 
				seance.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();//printStackTrace() affiche l'exeption dans la console 
		}
		return succes;
	}

	@Override
	public boolean delete(Seance seance) {
		boolean succes = true;
		try {
			int id = seance.getId();
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
	//TODO a vérifier 
	@Override
	public boolean update(Seance seance) {
		boolean succes = true;
		try {			
			String requete = "UPDATE "+TABLE+" ("+DATEDEB+", "+DATEFIN+","+IDSALLE+","+IDCOURS+") VALUES (?, ?, ?) WHERE ("+CLE_PRIMAIRE+" = ?"+")";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setDate(1, seance.getDateDeb());
			pst.setDate(2, seance.getDateFin());
			pst.setInt(3, seance.getSalle().getId());
			pst.setInt(4, seance.getCours().getId());
			pst.executeUpdate();// on execute la requete qui consiste a mettre a jour un enregistrement de la table 
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	@Override
	public Seance read(int id) {
		Seance seance = null;
		try {			
			String requete = "SELECT "+CLE_PRIMAIRE+", "+DATEDEB+", "+DATEFIN+", "+IDSALLE+", "+IDCOURS+" + FROM "+TABLE+" WHERE ("+CLE_PRIMAIRE+" = ?"+")";
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
			
			// vas chercher l'adresse memoire des deux objets
			
			Salle salle = SalleDAO.getInstance().read(rs.getInt(IDSALLE));
			Cours cours = CoursDAO.getInstance().read(rs.getInt(IDCOURS));
			
			seance = new Seance(rs.getInt(CLE_PRIMAIRE), rs.getDate(DATEDEB), rs.getDate(DATEFIN), salle, cours);	 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seance;
	}

}
