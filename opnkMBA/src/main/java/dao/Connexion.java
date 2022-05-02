package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Connexion {

	private static Connection connect = null;

	
	private static final String ID = "adminSIO";
	private static final String MDP = "toto";

	private static final int COLONNE_TEXTE = 10;
	private static final int COLONNE_ENTIER = 6;
	private static final int COLONNE_DATE = 11;

	/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static Connection getInstance() {
        if (connect==null) {
            // Si la connexion n'a pas encore été faite, on la fait.
            try {
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);
                connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/openknowledge", ID, MDP);
                System.out.println("connecté");
            }
            catch (SQLException | ClassNotFoundException e){
                System.out.println("Echec de la tentative de connexion : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connect;
    }
	
	private Connexion() {
		super();
	}

	public static ResultSet executeQuery(String requete){
		Statement st = null ;
		ResultSet rs = null;
		//System.out.println("requete = "+requete);
		try{
			st = getInstance().createStatement() ;
			rs = st.executeQuery(requete) ;
		}catch(SQLException e){
			System.out.println("Echec de la tentative d'ex�cution de requete : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return rs;
	}

	/**
	 * Une m�thode statique qui permet de faire une mise � jour d'une table (INSERT / UPDATE / DELETE)
	 * @param requete
	 * @return
	 */
	public static boolean executeUpdate(String requete){
		boolean succes = true;
		//System.out.println(requete);
		Statement st = null ;
		try{
			st = getInstance().createStatement() ;
			st.executeUpdate(requete) ;
		}catch(SQLException e){
			succes = false;
			System.out.println("Echec de la tentative d'ex�cution de Mise � Jour : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return succes;
	}

	/**
	 * Fermeture de la connexion au SGBD SQL ServerExpress
	 */
	public static void fermer()
	{
		try
		{
			getInstance().close();
			System.out.println("deconnexion ok");
		}
		catch (SQLException e)
		{
			connect=null;
			System.out.println("echec de la fermeture");
		}
	}

	/**
	 * Requ�te qui permet de voir le contenu d'une table
	 * Attention � ne pas perdre la premi�re ligne en testant la table vide
	 * @param table
	 */
	public static void afficheSelectEtoile(String table, String clauseWhere){
		try{
			String requete = "SELECT * FROM "+table;
			if (clauseWhere!=null) {
				requete += " WHERE "+clauseWhere;
			}
			ResultSet res = Connexion.executeQuery(requete) ;
			ResultSetMetaData rsmd = res.getMetaData();
			int taille = rsmd.getColumnCount();
			boolean hasNext =res.next(); 
			if (!hasNext) {System.out.println("table vide");}
			else {
				// Affichage du nom des colonnes
				System.out.print("|");
				for (int j = 1; j <= taille; j++) {
					String colonneNorme = extraireNomColonneNorme(rsmd, j);
					System.out.print(colonneNorme+"|");							
				} 
				System.out.println();

				// Affichage des donn�es
				while(hasNext){
					System.out.print("|");
					for (int j = 1; j <= taille; j++) {
						String valeurNormee = extraireValeurNormeeTypee(res, rsmd, j);
						System.out.print(valeurNormee+"|");							
					} 
					System.out.println();
					hasNext = res.next();
				}
			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
	}

	private static String extraireValeurNormeeTypee(ResultSet res, ResultSetMetaData rsmd, int j)
			throws SQLException {
		String valeurNormee = "";
		switch (rsmd.getColumnType(j)) {
		case Types.VARCHAR:
			valeurNormee = res.getString(j);
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_TEXTE, _Alignement.Droite);
			break;
		case Types.DATE:
			valeurNormee = res.getDate(j).toString();
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_DATE, _Alignement.Droite);
			break;
		case Types.TIMESTAMP:
			valeurNormee = res.getTimestamp(j).toString();
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_DATE, _Alignement.Droite);
			break;
		case Types.INTEGER:
			valeurNormee = res.getInt(j)+"";
			valeurNormee = Connexion.norme(valeurNormee, Connexion.COLONNE_ENTIER, _Alignement.Droite);
			break;
		default:
			break;
		}
		return valeurNormee;
	}

	private static String extraireNomColonneNorme(ResultSetMetaData rsmd, int j)
			throws SQLException {
		String nomColonneNorme = rsmd.getColumnName(j);
		switch (rsmd.getColumnType(j)) {
		case Types.VARCHAR:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_TEXTE, _Alignement.Droite);
			break;
		case Types.DATE:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_DATE, _Alignement.Droite);
			break;
		case Types.TIMESTAMP:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_DATE, _Alignement.Droite);
			break;
		case Types.INTEGER:
			nomColonneNorme = Connexion.norme(nomColonneNorme, Connexion.COLONNE_ENTIER, _Alignement.Droite);
			break;
		default:
			break;
		}
		return nomColonneNorme;
	}

	
	/** Le seul alignement pris en compte est � droite.
	 * 
	 * @param valeurNormee la chaine de texte � normaliser
	 * @param colonneTexte  la largeur maximale de la colonne
	 * @param aligne  gauche / droite / centr�
	 * @return la chaine de caract�re normalis� pour affichage de tableau.
	 */
	private static String norme(String valeurNormee, int colonneTexte, _Alignement aligne) {
		String rep = "";
		int tailleEffective =valeurNormee.length(); 
		if (tailleEffective>=colonneTexte) {
			rep = valeurNormee.substring(0, colonneTexte);
		}
		else {
			rep = valeurNormee;
			for (int i = tailleEffective; i < colonneTexte; i++) {
				rep += " ";
			}
		}
		return rep;
	}

	/**
	 * Requ�te qui permet de r�cup�rer le plus grand id de la table, a priori celui qui vient d'�tre affect�
	 * � une ligne cr��e via identity.
	 * @param cle
	 * @param table
	 * @return
	 */
	public static int getMaxId(String cle, String table) {
		String requete = "SELECT MAX("+cle+")as max FROM "+table;
		ResultSet rs = Connexion.executeQuery(requete);
		int id= -1;
		try {
			rs.next();
			id = rs.getInt("max");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static List<Integer> getLesIds(String attribut, String table, String clauseWhere) {
		String requete = "SELECT DISTINCT "+attribut+" FROM "+table;
		if (clauseWhere!=null) {
			requete += " WHERE "+clauseWhere;
		}		
		ResultSet rs = Connexion.executeQuery(requete);
		List<Integer> liste = new ArrayList<Integer>();
		try {
			while (rs.next()) {
			int id = rs.getInt(attribut);
			liste.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
		
	}

	
}
