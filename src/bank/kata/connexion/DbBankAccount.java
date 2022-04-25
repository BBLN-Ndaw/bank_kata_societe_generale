package bank.kata.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Implémentation  d'un singleton  pour la connexion à la base de donnée
 * L'instance est créée à l'initialisation.
 * @author yayandaw95@gmail.com
 */
public final class DbBankAccount {

    private static String d_URL ="jdbc:mysql://localhost:3306/bankkata";//"jdbc:mysql://localhost:3306/bankkatadb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static String d_LOGIN = "root";
    private static String d_PASSWORD = "root";
    private static Connection d_connexion=null;
    /** Instance unique pré-initialisée */
    private static DbBankAccount DbBankAccountInstance =new  DbBankAccount();

    private  DbBankAccount()
    {
        try
        {
           Class.forName("com.mysql.cj.jdbc.Driver");
            d_connexion =  DriverManager.getConnection(d_URL, d_LOGIN, d_PASSWORD);
            System.out.println("connected ");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Impossible de charger le pilote de la Base de donnée! ne pas oublier d'importer le fichier .jar : " + e.getMessage());
        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la base de donnée : ");
            e.printStackTrace();
        }
    }

    public static DbBankAccount GetDbBankAccountInstance()
    {
        return DbBankAccountInstance;
    }
    public Connection GetConnexion(){return d_connexion;}


    /**
     * exécute une requête sql
     * @param sqlStatement chaine contenant la requete à exécuter
     */
   /* public void executeStatement(String sqlStatement) {
        try {
            stmt.execute(sqlStatement);
        } catch (Exception e) {
            System.out.println("Probleme d'exécution de la requête : " + e.getMessage());
        }
    }
    */

}
