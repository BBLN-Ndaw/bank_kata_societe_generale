package bank.kata.DAO;

import bank.Acount.kata.ComptebancaireCourant;
import bank.Acount.kata.IdentifiantCompte;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteBancaireCourantDAO extends DAO<ComptebancaireCourant> {

    /**
     * créer et enregistrer un compte courant dans la base de donnée.
     * @param compteCourant
     */
    @Override
    public void creer(ComptebancaireCourant compteCourant)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into comptebancairecourant values('"+compteCourant.GetidentifiantCompte().GetIdentifiantbanque()+"','"+
                    compteCourant.GetidentifiantCompte().GetIdentifiantAgence()+"','"+compteCourant.GetidentifiantCompte().GetNumeroCompte()+"','"+
                    compteCourant.GetidentifiantCompte().GetCleRib()+"','"+compteCourant.GetDateCreation()+"','"+compteCourant.GetSolde()+"','"+
                    compteCourant.GettotalDecouvertAutoriser()+"','"+compteCourant.GetTauxAgios()+"')" ;
            Statement.execute(sql);
            System.out.println("compte ajouté avec success");

        } catch (SQLException e) {
            System.out.println("Erreur dans la creation de compte : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la creation de compte : ");
            e.getMessage();
        }
    }

    /**
     * Recherche un compte courant dans la base de son à partir de son identifiant
     * @param numeroCompte
     * @return ComptebancaireCourant le compte courant trouvé dans la base de donnée
     */
    @Override
    public ComptebancaireCourant rechercher(int numeroCompte)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        ComptebancaireCourant comptebancaireCourant=new ComptebancaireCourant();
        IdentifiantCompte identifiantCompte =new IdentifiantCompte();
        try {
            String sql = "SELECT * FROM comptebancairecourant WHERE #numerocompte = '"+numeroCompte+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucune ligne trouve");
                return null;
            } else {
                identifiantCompte.setIidentifiantBanque(resultSet.getInt("#identifiantBanque"));
                identifiantCompte.setidentifiantAgence(resultSet.getInt("#identifiantagence"));
                identifiantCompte.setNumeroCompte(resultSet.getInt("#numerocompte"));
                identifiantCompte.setcleRib(resultSet.getInt("#cleRib"));

                comptebancaireCourant.setidentifiantCompteBancaire(identifiantCompte);
                comptebancaireCourant.setDateCreaationCompte(resultSet.getString("dateCreation"));
                comptebancaireCourant.setSolde(resultSet.getDouble("solde"));
                comptebancaireCourant.setTotalDecouvertOutoriser(resultSet.getDouble("totalDecouvertAutorise"));
                comptebancaireCourant.setTauxAgios(resultSet.getDouble("tauxAgios"));

                System.out.println("recherche compte courant reussit");
                return comptebancaireCourant;
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de compte courant:"+e.getMessage());
            return  null;
        }
    }

}

