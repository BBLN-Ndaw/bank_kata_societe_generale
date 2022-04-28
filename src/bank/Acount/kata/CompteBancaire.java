package bank.Acount.kata;

import bank.Acount.kata.DAO.OperationBancaireDAO;
import bank.Acount.kata.Exceptions.CrediterCompteImpossibleException;
import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Implementation de la classe de gestion d'un compte bancaire
 * @author  yayandaw95@gmail.com
 */
public abstract class CompteBancaire {
    private int d_idCompte;
    private double d_solde;
    private String d_dateCreaationCompte;
    private  static double d_debitMax=15000.99;
    private Client d_client;


    public CompteBancaire()
    {
        this.d_solde=0;
        this.d_dateCreaationCompte="";
        this.d_client=null;
    }

    public CompteBancaire(int idcompte,double solde, String dateCreation, Client client)
    {
        this.d_idCompte=idcompte;
        this.d_solde=solde;
        this.d_dateCreaationCompte=dateCreation;
        this.d_client=client;
    }
    /**
     * Permet d'obtenir le solde du compte
     * @return d_solde le solde du compte
     */
    public double GetSolde()
    {
        return d_solde;
    }

    /**
     * Permet d'obtenir la date de creation du compte
     * @return d_dateCreaationCompte la date de creation du compyte
     */
    public String GetDateCreation()
    {
        return  d_dateCreaationCompte;
    }
    public Client GetClient()
    {
        return d_client;
    }


    public double GetDebitMax()
    {
        return d_debitMax;
    }
    public void setSolde(double solde)
    {
        this.d_solde=solde;
    }
    public void setDateCreaationCompte(String dateCreaationCompte)
    {
        this.d_dateCreaationCompte=dateCreaationCompte;
    }
    public void setClient(Client client)
    {
        d_client=client;
    }

    public  int GetIdCompte()
    {
        return d_idCompte;
    }
    public  void setIdCompte(int id)
    {
        d_idCompte=id;
    }

    public void AfficheClient()
    {
        this.d_client.AfficherClient();
    }

    public  void enregistrerTransaction(double montant, double soldeCourant, String typeTransaction)
    {
        Date date = new Date();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        String dateOperation = format.format(date.toInstant());
        OperationBancaire operationBancaire =new OperationBancaire(this.GetClient(),this,dateOperation,montant,typeTransaction);
        try {
            new OperationBancaireDAO().creer(operationBancaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void enregistrerCompte();
    public abstract void afficherCOmpte();

    public  abstract Boolean RetraitPossible(double montant);
    public abstract void debiter(double montant) throws MontantNegatifException, RetraitImpossibleException;
    public abstract void crediter(double montant) throws MontantNegatifException, CrediterCompteImpossibleException;
    public abstract List<OperationBancaire> getHistorique();
    public abstract void AfficheHistorique();








}
