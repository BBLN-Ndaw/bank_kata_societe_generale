package bank.Acount.kata.DAO;

import bank.Acount.kata.CompteBancaireEpargne;
import bank.Acount.kata.ComptebancaireCourant;
import bank.Acount.kata.entreprise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Data acces objet d'un client(entreprise)
 * @author yayandaw95@gmail.com
 */
public class entrepriseDAO extends DAO<entreprise> {
    /**
     * Creation et enregistrement d'un client (entreprise) dans la base de donnée.
     * @param entreprise
     */
    @Override
    public void creer(entreprise entreprise)
    {
        Connection connexion = null;
        Statement Statement = null;
        try {
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            String sql = "insert into entreprises values('"+entreprise.GetIdClient()+"','"+entreprise.GetraisonSocial()+"','"+entreprise.GetCodeNaf()+"','"+
                    entreprise.GetAdresse()+"','"+entreprise.GetNumerotelephone()+"')";

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
     * Recherhce la liste des clients(entreprise) enregistrés dans la base de données.
     * @return
     */
    @Override
    public List<entreprise> GetListe() {
        return null;
    }

    /**
     * Recherche un client  dans la base de son à partir de son numero Siret
     * @param siret
     * @return PersonneMorale le client (entreprise) trouvé dans la base de donnée
     */
    @Override
    public entreprise rechercher(int siret)
    {
        Connection connexion = null;
        Statement Statement = null;
        ResultSet resultSet = null;
        entreprise entreprise= new entreprise();

        try {
            String sql = "SELECT * FROM entreprises WHERE siret = '"+siret+"'";
            connexion= this.d_connexionDAO;
            Statement = connexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucun client trouve");
                return null;
            } else {

                entreprise.setIdClient(resultSet.getInt("siret"));
                entreprise.setraisonSociale(resultSet.getString("raisonSocial"));
                entreprise.setCodeNaf(resultSet.getString("codeNaf"));
                entreprise.setAdresse(resultSet.getString("adresse"));
                entreprise.setNumeroTelephone(resultSet.getString("tel"));
                System.out.println("recherche client reussit");

                //Recherche compte courant de l'entreprise
                String requeterechercheCompteCourant="SELECT idcomptecourant, solde, dateCreation,totalDecouverte,tauxAgios " +
                        "FROM comptecourants cr INNER JOIN entreprises e ON cr.idClient = e.siret ";
                resultSet = Statement.executeQuery(requeterechercheCompteCourant);
                if (resultSet.next()) {
                    ComptebancaireCourant comptebancaireCourant = new ComptebancaireCourant();
                    comptebancaireCourant.setIdCompte(resultSet.getInt("idcomptecourant"));
                    comptebancaireCourant.setSolde(resultSet.getDouble("solde"));
                    comptebancaireCourant.setDateCreaationCompte(resultSet.getString("dateCreation"));
                    comptebancaireCourant.setTotalDecouvertAutoriser(resultSet.getDouble("totalDecouverte"));
                    comptebancaireCourant.setTauxAgios(resultSet.getDouble("tauxAgios"));
                    if (comptebancaireCourant != null) {
                        entreprise.ajouterCompter(comptebancaireCourant);
                        System.out.println("compte courant ajouté");
                    }

                    //Recherche compte courant du client
                    String requeterechercheCompteEpargne="SELECT idcompteepargne, solde, dateCreation,tauxInteret,plafond " +
                            "FROM compteepargne cp INNER JOIN entreprises e ON cp.idClient = e.siret ";
                    resultSet = Statement.executeQuery(requeterechercheCompteEpargne);
                    if (resultSet.next()) {
                        CompteBancaireEpargne compteBancaireEpargne = new CompteBancaireEpargne();
                        compteBancaireEpargne.setIdCompte(resultSet.getInt("idcompteepargne"));
                        compteBancaireEpargne.setSolde(resultSet.getDouble("solde"));
                        compteBancaireEpargne.setDateCreaationCompte(resultSet.getString("dateCreation"));
                        compteBancaireEpargne.setTauxInteret(resultSet.getDouble("tauxInteret"));
                        compteBancaireEpargne.setPlafond(resultSet.getDouble("plafond"));
                        if (compteBancaireEpargne != null) {
                            entreprise.ajouterCompter(compteBancaireEpargne);
                            System.out.println("compte epargne ajouté");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de client :"+e.getMessage());
            return  null;
        }
        return entreprise;
    }
}
