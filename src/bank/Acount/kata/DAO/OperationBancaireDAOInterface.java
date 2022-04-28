package bank.Acount.kata.DAO;

import bank.Acount.kata.Client;
import bank.Acount.kata.CompteBancaire;
import bank.Acount.kata.OperationBancaire;

import java.sql.SQLException;

public interface OperationBancaireDAOInterface {

    public OperationBancaire rechercher(Client client, CompteBancaire compteBancaire) throws SQLException;

    public void creer(OperationBancaire operationBancaire)  throws SQLException;




}
