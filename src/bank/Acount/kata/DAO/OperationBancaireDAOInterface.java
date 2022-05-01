package bank.Acount.kata.DAO;

import bank.Acount.kata.Client;
import bank.Acount.kata.CompteBancaire;
import bank.Acount.kata.OperationBancaire;

import java.sql.SQLException;

/**
 * Data Access Objet pour la gestions des operations bancaires.
 * @author  yayandaw95@gmail.com
 */
public interface OperationBancaireDAOInterface {
    /**
     * Recherche une transaction bancaire sur les comptes bancaires
     * @param client
     * @param compteBancaire
     * @return
     * @throws SQLException
     */
    public OperationBancaire rechercher(Client client, CompteBancaire compteBancaire) throws SQLException;

    /**
     * Enregistre une operation bancaire dans la base de données.
     * @param operationBancaire
     * @throws SQLException
     */
    public void creer(OperationBancaire operationBancaire)  throws SQLException;
}
