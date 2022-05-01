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
 * classe de gestion d'un compte bancaire
 * @author  yayandaw95@gmail.com
 */
public abstract class CompteBancaire {
    private int d_idCompte;
    private double d_solde;
    private String d_dateCreaationCompte;
    private  static double d_debitMax=15000.99;
    private Client d_client;

    /**
     * Constructeur
     */
    public CompteBancaire()
    {
        this.d_solde=0;
        this.d_dateCreaationCompte="";
        this.d_client=null;
    }

    /**
     * Initialise le compte bancaire à partir d'un identifiant, solde, la date de création et le proprietaire du client
     * @param idcompte
     * @param solde
     * @param dateCreation
     * @param client
     */
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

    /**
     * Permet d'obtenir le proprietaire du compte
     * @return d_client
     */
    public Client GetClient()
    {
        return d_client;
    }

    /**
     * Permet d'obtenir le montant maximum autorisé pour un retrait
     * @return d_debitMax
     */
    public double GetDebitMax()
    {
        return d_debitMax;
    }

    /**
     * Ajoute le solde du compte
     * @param solde
     */
    public void setSolde(double solde)
    {
        this.d_solde=solde;
    }

    /**
     * Ajoute la date de creation du compte
     * @param dateCreaationCompte
     */
    public void setDateCreaationCompte(String dateCreaationCompte)
    {
        this.d_dateCreaationCompte=dateCreaationCompte;
    }

    /**
     * rattache un client au ncompte
     * @param client
     */
    public void setClient(Client client)
    {
        d_client=client;
    }

    /**
     * Permet d'obtenir l'identifiant du compte
     * @return d_idCompte
     */
    public  int GetIdCompte()
    {
        return d_idCompte;
    }

    /**
     * Ajoute un identifiant de compte
     * @param id
     */
    public  void setIdCompte(int id)
    {
        d_idCompte=id;
    }

    /**
     * Affiche le proprietaire du compte
     */
    public void AfficheClient()
    {
        this.d_client.AfficherClient();
    }

    /**
     * Enregistre une operation bancaire realisé sur le compte
     * @param montant
     * @param soldeCourant
     * @param typeTransaction
     */
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

    /**
     * Enregistre un compte
     */
    public abstract void enregistrerCompte();

    /**
     * Affiche un compte
     */
    public abstract void afficherCOmpte();

    /**
     * Verifie la possibilité de faire un retrait
     * @param montant
     * @return true si le retrait et possible et false sinon
     */
    public  abstract Boolean RetraitPossible(double montant);

    /**
     * Permet de faire un retrait d'argent sur le compte
     * @param montant
     * @throws MontantNegatifException
     * @throws RetraitImpossibleException
     */
    public abstract void debiter(double montant) throws MontantNegatifException, RetraitImpossibleException;

    /**
     * Permet de rajouter de l'argent dans le compte
     * @param montant
     * @throws MontantNegatifException
     * @throws CrediterCompteImpossibleException
     */
    public abstract void crediter(double montant) throws MontantNegatifException, CrediterCompteImpossibleException;

    /**
     * Permet d'obtenir la liste de toutes les operations réalisées sur le compte
     * @return
     */
    public abstract List<OperationBancaire> getHistorique();

    /**
     * Affiche dands la console toutes les operation banacaires réalisées sur le compte bancaire.
     */
    public abstract void AfficheHistorique();








}
