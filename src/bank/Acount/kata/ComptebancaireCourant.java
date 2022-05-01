package bank.Acount.kata;

import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;
import bank.Acount.kata.DAO.CompteCourantDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe de gestion d'un compte bancaires courant
 * @author yayandaw95@gmail.com
 */
public final class  ComptebancaireCourant extends  CompteBancaire{
    private  double d_tauxAgios;
    private double d_totalDecouvertAutoriser;
   private CompteCourantDAO d_compteCourantDAO= new CompteCourantDAO();
    private  static double d_debitMax=15000.99;

    /**
     * Compte bancaire courant
     */
    public ComptebancaireCourant()
    {
        super();
        this.d_totalDecouvertAutoriser=0;
        this.d_tauxAgios=0;
    }

    /**
     * Initialise un compte bancaire courant
     * @param idcompte
     * @param solde
     * @param dateCreation
     * @param client
     * @param agios
     * @param decouverte
     */
    public ComptebancaireCourant(int idcompte,double solde, String dateCreation, Client client,double agios, double decouverte)
    {
        super(idcompte,solde,dateCreation,client);
        this.d_totalDecouvertAutoriser=decouverte;
        this.d_tauxAgios=agios;


    }

    /**
     * Permet de savoir le montant total de decouverte autorisé
     * @return d_totalDecouvertAutoriser
     */
    public double GetTotalDecouvertAutoriser()
    {
        return d_totalDecouvertAutoriser;
    }

    /**
     * Permet d'obtenir le taux d'agios appliqués sur le compte
     * @return d_tauxAgios
     */
    public double GetAgios()
    {
        return d_tauxAgios;
    }

    /**
     * Permet d'obtenir le montant total de debit autorisé
     * @return d_debitMax
     */
    public double GetDebitMax()
    {
        return d_debitMax;
    }


    /**
     * Change le nombre total de découverte autorisé
     * @param totalDecouvertAutoriser
     */
    public  void setTotalDecouvertAutoriser(double totalDecouvertAutoriser)
    {
        this.d_totalDecouvertAutoriser=totalDecouvertAutoriser;
    }

    /**
     * Ajoute le taux d'agios
     * @param tauxAgios
     */
    public void setTauxAgios(double tauxAgios)
    {
        this.d_tauxAgios=tauxAgios;
    }

    /**
     * Affiche sur la console le compte bancaire courant
     */
    @Override
    public void afficherCOmpte() {
        System.out.println("--------------------- Affichage du compte courant numero : "+super.GetIdCompte()+" -------------------");
        System.out.println("Solde "+super.GetSolde());
        System.out.println("Date de creation "+super.GetDateCreation());
        System.out.println("Montant decouverte autorisé "+this.GetTotalDecouvertAutoriser());
        System.out.println("Taux  Agios "+this.GetAgios());

    }

    /**
     * Enregistre le compte dans la base de données
     */
    @Override
    public void enregistrerCompte() {
        new CompteCourantDAO().creer(this);
    }

    /**
     * Vérifie si le retrait est possible
     * @param montant
     * @return true si le retrait est possible et false sinon
     */
    @Override
    public Boolean RetraitPossible(double montant) {
            double retraitSolde=super.GetSolde()-montant;
            if(retraitSolde<=GetTotalDecouvertAutoriser()&&GetDebitMax()>=montant)
                return true;
            return false;

    }

    /**
     * Permet de retirer un montant sur le compte bancaire courant.
     * @param montant
     * @throws MontantNegatifException
     * @throws RetraitImpossibleException
     */
    @Override
    public void debiter(double montant)throws MontantNegatifException, RetraitImpossibleException
    {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
       if(RetraitPossible( montant)==false)
           throw new RetraitImpossibleException("Vous ne pouvez pas faire de retrait");
        setSolde(GetSolde()-montant);
        //mis à jours du solde dans la base de donnée
        new CompteCourantDAO().updateSolde(GetSolde()-montant);
       ///enregistrer l'operation
        enregistrerTransaction(montant,GetSolde(), "debit");
    }

    /**
     * Permet de rajouter de l'argent sur le compte bancaire courant
     * @param montant
     * @throws MontantNegatifException
     */
    @Override
    public void crediter(double montant) throws MontantNegatifException {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
       double soldeCourant=super.GetSolde();
       soldeCourant+=montant;
        super.setSolde(soldeCourant);
        //mis à jours du solde dans la base de donnée
        new CompteCourantDAO().updateSolde(soldeCourant);
        //enregistrerOperation
        enregistrerTransaction(montant, GetSolde(), "Credit");
    }

    /**
     *  Permet d'obtenir l'historique de toutes les opérations bancaires réalisées sur le compte
     *   @return List<OperationBancaire> la liste de toutes les operations bancaires sur le compte
     */
    @Override
    public List<OperationBancaire> getHistorique() {
        if(d_compteCourantDAO!=null)
        {
            try {

                return d_compteCourantDAO.GetHistorique(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            return null;
        return null;//exception lancé
    }

    /**
     * Affiche sur la console la liste de toutes les opérations.
     */
    @Override
    public void AfficheHistorique() {
        for (OperationBancaire operationBancaire:this.getHistorique())
        {
            operationBancaire.Afficher();
        }
    }

}
