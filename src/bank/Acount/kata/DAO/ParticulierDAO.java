package bank.Acount.kata.DAO;

import bank.Acount.kata.CompteBancaireEpargne;
import bank.Acount.kata.ComptebancaireCourant;
import bank.Acount.kata.Particulier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ParticulierDAO extends DAO<Particulier>  {
    /**
     * Creation et enregistrement d'un client dans la base de donnée.
     * @param particulier
     */
    @Override
    public void creer(Particulier particulier)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into particuliers values('"+particulier.GetIdClient()+"','"+particulier.GetNom()+"','"+
                    particulier.GetPrenom()+"','"+particulier.GetDateNaissance()+"','"+particulier.GetAdresse()+"','"+
                    particulier.GetNumerotelephone()+"')";
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
    public Particulier rechercher(int CNI)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        Particulier particulier=new Particulier();

        try {
            String sql = "SELECT * FROM particuliers WHERE CNI = '"+CNI+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = Statement.executeQuery(sql);
              if (resultSet.next())
              {
                particulier.setIdClient(resultSet.getInt("cni"));
                particulier.setNom(resultSet.getString("Nom"));
                particulier.setPrenom(resultSet.getString("prenom"));
                particulier.setdateNaissance(resultSet.getString("DateNaissance"));
                particulier.setAdresse(resultSet.getString("adresse"));
                particulier.setNumeroTelephone(resultSet.getString("tel"));
                System.out.println("recherche client reussit");
                //Recherche compte courant du client
                String requeterechercheCompteCourant="SELECT idcomptecourant, solde, dateCreation,totalDecouverte,tauxAgios " +
                        "FROM comptecourants cr INNER JOIN particuliers p ON cr.idClient = p.CNI ";
                  resultSet = Statement.executeQuery(requeterechercheCompteCourant);
                  if (resultSet.next()) {
                      ComptebancaireCourant comptebancaireCourant = new ComptebancaireCourant();
                      comptebancaireCourant.setIdCompte(resultSet.getInt("idcomptecourant"));
                      comptebancaireCourant.setSolde(resultSet.getDouble("solde"));
                      comptebancaireCourant.setDateCreaationCompte(resultSet.getString("dateCreation"));
                      comptebancaireCourant.setTotalDecouvertAutoriser(resultSet.getDouble("totalDecouverte"));
                      comptebancaireCourant.setTauxAgios(resultSet.getDouble("tauxAgios"));
                      if (comptebancaireCourant != null) {
                          particulier.ajouterCompter(comptebancaireCourant);
                          System.out.println("compte courant ajouté");
                      }

                      //Recherche compte courant du client
                      String requeterechercheCompteEpargne="SELECT idcompteepargne, solde, dateCreation,tauxInteret,plafond " +
                              "FROM compteepargne cp INNER JOIN particuliers p ON cp.idClient = p.CNI ";
                      resultSet = Statement.executeQuery(requeterechercheCompteEpargne);
                      if (resultSet.next()) {
                          CompteBancaireEpargne compteBancaireEpargne = new CompteBancaireEpargne();
                          compteBancaireEpargne.setIdCompte(resultSet.getInt("idcompteepargne"));
                          compteBancaireEpargne.setSolde(resultSet.getDouble("solde"));
                          compteBancaireEpargne.setDateCreaationCompte(resultSet.getString("dateCreation"));
                          compteBancaireEpargne.setTauxInteret(resultSet.getDouble("tauxInteret"));
                          compteBancaireEpargne.setPlafond(resultSet.getDouble("plafond"));
                          if (compteBancaireEpargne != null) {
                              particulier.ajouterCompter(compteBancaireEpargne);
                              System.out.println("compte epargne ajouté");
                          }
                      }
                  }
              }
            return particulier;
        } catch (Exception e)
        {
            System.out.println("erreur dans la recherche de client :"+e.getMessage());
            return  null;
        }
    }

    /**
     * Obtenir la lise de tous les Client de la banque
     * @return
     */
    @Override
    public List<Particulier> GetListe() {
        return null;
    }

}
