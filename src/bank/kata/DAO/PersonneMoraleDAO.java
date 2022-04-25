package bank.kata.DAO;

import bank.Acount.kata.PersonneMorale;
import bank.Acount.kata.PersonnePhysique;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonneMoraleDAO extends DAO<PersonneMorale> {
    /**
     * Creation et enregistrement d'un client (entreprise) dans la base de donnée.
     * @param personneMorale
     */
    @Override
    public void creer(PersonneMorale personneMorale)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into personnemorale values('"+personneMorale.GetraisonSocial()+"','"+personneMorale.getSiret()+"','"+
                    personneMorale.GetCodeNaf()+"','"+personneMorale.GetAdresse()+"','"+personneMorale.GetNumerotelephone()+"'";

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
     * Recherche un client  dans la base de son à partir de son numero Siret
     * @param siret
     * @return PersonneMorale le client (entreprise) trouvé dans la base de donnée
     */
    @Override
    public PersonneMorale rechercher(int siret)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        PersonneMorale personneMorale= new PersonneMorale();

        try {
            String sql = "SELECT * FROM personnemorale WHERE siret = '"+siret+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucun client trouve");
                return null;
            } else {
                personneMorale.setraisonSociale(resultSet.getString("raisonSocial"));
                personneMorale.setCodeNaf(resultSet.getString("codeNaf"));
                personneMorale.setAdresse(resultSet.getString("adresse"));
                personneMorale.setNumeroTelephone(resultSet.getString("numeroTel"));
                System.out.println("recherche client reussit");
                return personneMorale;
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de client :"+e.getMessage());
            return  null;
        }
    }
}
