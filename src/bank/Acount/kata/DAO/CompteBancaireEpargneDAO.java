package bank.Acount.kata.DAO;

import bank.Acount.kata.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object pour l'interaction entre un compte bancaire epargne et la base de données
 * @author yayandaw95@gmail.com
 */
public class CompteBancaireEpargneDAO extends DAO<CompteBancaireEpargne> {

    @Override
    public void creer(CompteBancaireEpargne compteEpargne)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into compteepargne values('"+compteEpargne.GetIdCompte()+"','"+
                    compteEpargne.GetSolde()+"','"+compteEpargne.GetDateCreation()+"','"+
                    compteEpargne.GetTauxInteret()+"','"+compteEpargne.GetPlafond()+"','"+compteEpargne.GetClient().GetIdClient()+"')";
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
        CompteBancaireEpargne compteBancaireEpargne = new CompteBancaireEpargne();

        try {
            String sql = "SELECT * FROM compteepargne WHERE idcompteepargne = '"+numeroCompteEpargne+"'";

            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next())
            {
                System.out.println("aucun compte epargne trouvée");
                return null;
            }
            else {
                compteBancaireEpargne.setIdCompte(resultSet.getInt("idcompteepargne"));
                compteBancaireEpargne.setSolde(resultSet.getDouble("solde"));
                compteBancaireEpargne.setDateCreaationCompte(resultSet.getString("dateCreation"));
                compteBancaireEpargne.setTauxInteret(resultSet.getDouble("tauxInteret"));
                compteBancaireEpargne.setPlafond(resultSet.getDouble("plafond"));
                System.out.println("recherche compte epargne reussit");
                //Rechercher propriétaire du compte
                String requeterechercheCLientParticulier="SELECT cni, nom, prenom,dateNaissance,adresse,tel " +
                        "FROM particuliers p INNER JOIN compteepargne ce ON p.CNI = ce.idclient ";
                resultSet = Statement.executeQuery(requeterechercheCLientParticulier);
                if (resultSet.next()) {
                    Particulier particulier = new Particulier();
                    particulier.setIdClient(resultSet.getInt("cni"));
                    particulier.setNom(resultSet.getString("nom"));
                    particulier.setPrenom(resultSet.getString("prenom"));
                    particulier.setdateNaissance(resultSet.getString("dateNaissance"));
                    particulier.setAdresse(resultSet.getString("adresse"));
                    particulier.setNumeroTelephone(resultSet.getString("tel"));
                    if (compteBancaireEpargne != null) {
                        compteBancaireEpargne.setClient(particulier);
                        System.out.println("le particullier propriétaire du compte retrouvé");
                    }
                    else {
                        //Recherche si le propriétaire est une entreprise
                        String requeterechercheCompteEpargne="SELECT siret, raisonSocial, codeNaf,adresse,tel " +
                                "FROM entreprises e INNER JOIN comptecourants cr ON e.siret = cr.idclient ";
                        resultSet = Statement.executeQuery(requeterechercheCompteEpargne);
                        if (resultSet.next()) {
                            entreprise  entreprise=new entreprise();
                            entreprise.setIdClient(resultSet.getInt("siret"));
                            entreprise.setraisonSociale(resultSet.getString("raisonSocial"));
                            entreprise.setCodeNaf(resultSet.getString("codeNaf"));
                            entreprise.setAdresse(resultSet.getString("adresse"));
                            entreprise.setNumeroTelephone(resultSet.getString("tel"));
                            if (compteBancaireEpargne != null &&entreprise!=null) {
                                compteBancaireEpargne.setClient(entreprise);
                                System.out.println("l'entreprise propriétaire  du compte retrouvé");
                            }
                        }

                    }


                }


            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de compte epargne : "+e.getMessage());
            return  null;
        }
        return compteBancaireEpargne;
    }

    /**
     * met à jour le solde du compte epargne suite à une transaction
     * @param solde
     */
    public void updateSolde(double solde)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;

        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql="update compteepargne set solde = '"+solde+"'";
            Statement.execute(sql);
            System.out.println("Solde mis a jour");

        } catch (SQLException e) {
            System.out.println("Erreur dans la mise à jour du solde : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la mise à jour du solde : ");
            e.getMessage();
        }
    }

    /**
     * permet d'otenir la liste de toutes les comptes epargnes.
     * @return List<CompteBancaireEpargne>
     */
    @Override
    public List<CompteBancaireEpargne> GetListe()
    {
        List<CompteBancaireEpargne> compteBancaireEpargneList = new ArrayList<CompteBancaireEpargne>();
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        try
        {
            String sql = "SELECT * FROM compteepargne";
            connexion = this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            while (resultSet.next())
            {
                CompteBancaireEpargne compteBancaireEpargne = new CompteBancaireEpargne();
                compteBancaireEpargne.setIdCompte(resultSet.getInt("idcompteepargne"));
                compteBancaireEpargne.setSolde(resultSet.getDouble("solde"));
                compteBancaireEpargne.setDateCreaationCompte(resultSet.getString("dateCreation"));
                compteBancaireEpargne.setTauxInteret(resultSet.getDouble("tauxInteret"));
                compteBancaireEpargne.setPlafond(resultSet.getDouble("plafond"));
                compteBancaireEpargneList.add(compteBancaireEpargne);
            }
        }
        catch (Exception e)
        {
            System.out.println("erreur dans la recherche des operations bancaires :" + e.getMessage());
            return null;
        }
        return compteBancaireEpargneList;

    }

    /**
     * Donne la liste de toutes les opérations bancaires realisées sur le compte epargne.
     * @param compteBancaireEpargne
     * @return
     * @throws SQLException
     */
    public List<OperationBancaire> GetHistorique(CompteBancaireEpargne compteBancaireEpargne) throws SQLException
    {
        List<OperationBancaire> operationBancaireList = new ArrayList<OperationBancaire>();

        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        try
        {
            String sql = "SELECT * FROM operation WHERE idCompte='" + compteBancaireEpargne.GetIdCompte() + "'";
            connexion = this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);

            while (resultSet.next())
            {
                OperationBancaire operationBancaire = new OperationBancaire();
                operationBancaire.setCompte(compteBancaireEpargne);
                operationBancaire.setDateOperation(resultSet.getString("date"));
                operationBancaire.setTypeOperation(resultSet.getString("typeOperation"));
                operationBancaire.setMontantantOperation(resultSet.getInt("montant"));
                operationBancaireList.add(operationBancaire);
            }
        }
        catch (Exception e)
        {
            System.out.println("erreur dans la recherche des operations bancaires :" + e.getMessage());
            return null;
        }
        return operationBancaireList;

    }

}
