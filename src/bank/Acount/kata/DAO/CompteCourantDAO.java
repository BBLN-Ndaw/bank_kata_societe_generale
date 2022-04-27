package bank.Acount.kata.DAO;

import bank.Acount.kata.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompteCourantDAO extends DAO<ComptebancaireCourant> {

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
            String sql = "insert into comptecourants values('"+compteCourant.GetIdCompte()+"','"+
                    compteCourant.GetSolde()+"','"+compteCourant.GetDateCreation()+"','"+
                    compteCourant.GetTotalDecouvertAutoriser()+"','"+compteCourant.GetAgios()+"','"+compteCourant.GetClient().GetIdClient()+"')";
            Statement.execute(sql);
            System.out.println("compte courant ajouté avec success");

        } catch (SQLException e) {
            System.out.println("Erreur dans la creation du compte : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la creation du compte : ");
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

        try
        {
            String sql = "SELECT * FROM comptecourants WHERE idcomptecourant ='"+numeroCompte+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next())
            {
                System.out.println("aucune ligne trouve");
                return null;
            }
            else
            {
                comptebancaireCourant.setIdCompte(resultSet.getInt("idcomptecourant"));
                comptebancaireCourant.setSolde(resultSet.getDouble("solde"));
                comptebancaireCourant.setDateCreaationCompte(resultSet.getString("dateCreation"));
                comptebancaireCourant.setTotalDecouvertAutoriser(resultSet.getDouble("totalDecouverte"));
                comptebancaireCourant.setTauxAgios(resultSet.getDouble("tauxAgios"));
                System.out.println("recherche compte courant reussit");
                //Rechercher propriétaire du compte
                String requeterechercheCLientParticulier="SELECT cni, nom, prenom,dateNaissance,adresse,tel " +
                        "FROM particuliers p INNER JOIN comptecourants cr ON p.CNI = cr.idclient ";
                resultSet = Statement.executeQuery(requeterechercheCLientParticulier);
                if (resultSet.next()) {
                    Particulier particulier = new Particulier();
                    particulier.setIdClient(resultSet.getInt("cni"));
                    particulier.setNom(resultSet.getString("nom"));
                    particulier.setPrenom(resultSet.getString("prenom"));
                    particulier.setdateNaissance(resultSet.getString("dateNaissance"));
                    particulier.setAdresse(resultSet.getString("adresse"));
                    particulier.setNumeroTelephone(resultSet.getString("tel"));
                    if (comptebancaireCourant != null) {
                        comptebancaireCourant.setClient(particulier);
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
                            if (comptebancaireCourant!=null && entreprise != null) {
                                comptebancaireCourant.setClient(entreprise);
                                System.out.println("l'entreprise propriétaire  du compte retrouvé");
                            }
                        }

                    }


                }

            }
        }
        catch (Exception e)
        {
            System.out.println("erreur dans la recherche de compte courant: "+e.getMessage());
            return  null;
        }
        return comptebancaireCourant;
    }

    @Override
    public List<ComptebancaireCourant> GetListe() {
        List<ComptebancaireCourant> ComptebancaireCourantList = new ArrayList<ComptebancaireCourant>();
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        try
        {
            String sql = "SELECT * FROM comptecourants";
            connexion = this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);

            while (resultSet.next())
            {
                ComptebancaireCourant comptebancaireCourant = new ComptebancaireCourant();
                comptebancaireCourant.setIdCompte(resultSet.getInt("idcomptecourant"));
                comptebancaireCourant.setSolde(resultSet.getDouble("solde"));
                comptebancaireCourant.setDateCreaationCompte(resultSet.getString("dateCreation"));
                comptebancaireCourant.setTotalDecouvertAutoriser(resultSet.getDouble("totalDecouverte"));
                comptebancaireCourant.setTauxAgios(resultSet.getDouble("tauxAgios"));
                ComptebancaireCourantList.add(comptebancaireCourant);
            }
        } catch (Exception e)
        {
            System.out.println("erreur dans la recherche de l'operation bancaire :" + e.getMessage());
            return null;
        }
        return ComptebancaireCourantList;

    }

    public List<OperationBancaire> GetHistorique(ComptebancaireCourant compteBancaire) throws SQLException
    {
        List<OperationBancaire> operationBancaireList = new ArrayList<OperationBancaire>();

        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        try
        {
            String sql = "SELECT * FROM operation WHERE idCompte='" + compteBancaire.GetIdCompte() + "'";
            connexion = this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);

            while (resultSet.next())
            {
                OperationBancaire operationBancaire = new OperationBancaire();
                operationBancaire.setCompte(compteBancaire);
                operationBancaire.setDateOperation(resultSet.getString("date"));
                operationBancaire.setTypeOperation(resultSet.getString("typeOperation"));
                operationBancaire.setMontantantOperation(resultSet.getInt("montant"));
                operationBancaireList.add(operationBancaire);
            }
        } catch (Exception e)
        {
            System.out.println("erreur dans la recherche de l'operation bancaire :" + e.getMessage());
            return null;
        }
        return operationBancaireList;

    }

}

