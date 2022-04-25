package bank.kata.DAO;

import bank.Acount.kata.CompteBancaireEpargne;
import bank.Acount.kata.ComptebancaireCourant;
import bank.Acount.kata.IdentifiantCompte;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteBancaireEpargneDAO extends DAO<CompteBancaireEpargne> {

    @Override
    public void creer(CompteBancaireEpargne compteEpargne)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into comptebancaireepargne values('"+compteEpargne.GetidentifiantCompte().GetIdentifiantbanque()+"','"+
                    compteEpargne.GetidentifiantCompte().GetIdentifiantAgence()+"','"+compteEpargne.GetidentifiantCompte().GetNumeroCompte()+"','"+
                    compteEpargne.GetidentifiantCompte().GetCleRib()+"','"+compteEpargne.GetDateCreation()+"','"+compteEpargne.GetSolde()+"','"+
                    compteEpargne.GetPlafond()+"','"+compteEpargne.GetTauxInteret()+"')" ;

            Statement.execute(sql);
            System.out.println("Compte ajouté avec success");

        } catch (SQLException e) {
            System.out.println("Erreur dans la creation de compte Epargne : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la creation de compte Epargne : ");
            e.getMessage();
        }
    }

    /**
     * Recherche un compte epargne dans la base de son à partir de son identifiant
     * @param numeroCompteEpargne
     * @return ComptebancaireCourant le compte epargne trouvé dans la base de donnée
     */
    @Override
    public CompteBancaireEpargne rechercher(int numeroCompteEpargne)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        IdentifiantCompte  identifiantCompte=new IdentifiantCompte();
        CompteBancaireEpargne compteBancaireEpargne = new CompteBancaireEpargne();

        try {
            String sql = "SELECT * FROM comptebancairecourant WHERE #numerocompte = '"+numeroCompteEpargne+"'";

            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucune ligne trouvée");
                return null;
            } else {
                identifiantCompte.setIidentifiantBanque(resultSet.getInt("#identifiantBanque"));
                identifiantCompte.setidentifiantAgence(resultSet.getInt("#identifiantagence"));
                identifiantCompte.setNumeroCompte(resultSet.getInt("#numerocompte"));
                identifiantCompte.setcleRib(resultSet.getInt("#cleRib"));

                compteBancaireEpargne.setidentifiantCompteBancaire(identifiantCompte);
                compteBancaireEpargne.setDateCreaationCompte(resultSet.getString("dateCreation"));
                compteBancaireEpargne.setSolde(resultSet.getDouble("solde"));
                compteBancaireEpargne.setPlafond(resultSet.getDouble("plafond"));
                compteBancaireEpargne.setTauxInteret(resultSet.getDouble("tauxInteret"));

                System.out.println("recherche compte epargne reussit");
                return compteBancaireEpargne;
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de compte epargne:"+e.getMessage());
            return  null;
        }


    }

}
