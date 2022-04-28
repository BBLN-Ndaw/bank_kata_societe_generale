package bank.Acount.kata.DAO;

import bank.Acount.kata.Client;
import bank.Acount.kata.CompteBancaire;
import bank.Acount.kata.OperationBancaire;
import bank.Acount.kata.connexion.DbBankAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperationBancaireDAO implements OperationBancaireDAOInterface {
    private static Connection d_Operationconnexion;

    public OperationBancaireDAO()
    {
        d_Operationconnexion=DbBankAccount.GetDbBankAccountInstance().GetConnexion();
    }

    @Override
    public OperationBancaire rechercher(Client client, CompteBancaire compteBancaire) throws SQLException
    {
        Statement Statement = null;
        ResultSet resultSet = null;
        OperationBancaire operationBancaire =new OperationBancaire(client,compteBancaire);

        try {
            String sql = "SELECT * FROM operation WHERE idClient = '"+client.GetIdClient()+"' and idCompte = '"+compteBancaire.GetIdCompte()+"'";
            Statement = d_Operationconnexion.createStatement();
            resultSet = Statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("aucune operation trouvée");
                return null;
            } else {
                operationBancaire.setClient(client);
                operationBancaire.setCompte(compteBancaire);
                operationBancaire.setDateOperation(resultSet.getString("date"));
                operationBancaire.setTypeOperation(resultSet.getString("typeOperation"));
                operationBancaire.setMontantantOperation(resultSet.getInt("montant"));

                System.out.println("recherche operation bancaire reussit");
                return operationBancaire;
            }

        } catch (Exception e) {
            System.out.println("erreur dans la recherche de l'operation bancaire :"+e.getMessage());
            return  null;
        }

    }

    @Override
    public void creer(OperationBancaire operationBancaire) throws SQLException {
        Statement Statement = null;
        try {
            Statement = d_Operationconnexion.createStatement();
            String sql = "insert into operation values('"+operationBancaire.getClient().GetIdClient()+"','"+operationBancaire.getCompte().GetIdCompte()+"','"+
                    operationBancaire.getDateOperation()+"','"+operationBancaire.getTypeOperation()+"','"+operationBancaire.getMontantantOperation()+"')";
            Statement.execute(sql);

            System.out.println("Operation reussit ");

        } catch (SQLException e) {
            System.out.println("Erreur dans la creation de l'operation : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur dans la creation de l'operation : ");
            e.getMessage();
        }

    }

}
