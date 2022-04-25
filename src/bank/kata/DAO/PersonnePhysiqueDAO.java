package bank.kata.DAO;

import bank.Acount.kata.ComptebancaireCourant;
import bank.Acount.kata.IdentifiantCompte;
import bank.Acount.kata.PersonnePhysique;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonnePhysiqueDAO extends DAO<PersonnePhysique> {
    /**
     * Creation et enregistrement d'un client dans la base de donnée.
     * @param personnePhysique
     */
    @Override
    public void creer(PersonnePhysique personnePhysique)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into personnephysiques values('"+personnePhysique.GetCNI()+"','"+personnePhysique.GetNom()+"','"+
                    personnePhysique.GetPrenom()+"','"+personnePhysique.GetDateNaissance()+"','"+personnePhysique.GetAdresse()+"','"+
                    personnePhysique.GetNumerotelephone()+"'";

            Statement.execute(sql);
            System.out.println("Client ajouté avec success");

        } catch (SQLException e) {
            System.out.println("Erreur dans la creation du client : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la creation du client : ");
            e.getMessage();
        }
    }

    /**
     * Recherche un client  dans la base de son à partir de son identifiant
     * @param CNI
     * @return PersonnePhysique le client  trouvé dans la base de donnée
     */
    @Override
    public PersonnePhysique rechercher(int CNI)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        PersonnePhysique personnePhysique=new PersonnePhysique();

        try {
            String sql = "SELECT * FROM personnephysiques WHERE CNI = '"+CNI+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucune ligne trouve");
                return null;
            } else {
                personnePhysique.setCNI(CNI);
                personnePhysique.setNom(resultSet.getString("Nom"));
                personnePhysique.setPrenom(resultSet.getString("prenom"));
                personnePhysique.setdateNaissance(resultSet.getString("DateNaissance"));
                personnePhysique.setAdresse(resultSet.getString("adresse"));
                personnePhysique.setNumeroTelephone(resultSet.getString("numeroTel"));

                System.out.println("recherche client reussit");
                return personnePhysique;
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de client :"+e.getMessage());
            return  null;
        }
    }
}
