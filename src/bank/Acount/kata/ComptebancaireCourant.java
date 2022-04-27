package bank.Acount.kata;

import bank.Acount.kata.DAO.OperationBancaireDAO;
import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;
import bank.Acount.kata.DAO.CompteCourantDAO;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public final class  ComptebancaireCourant extends  CompteBancaire{
    private  double d_tauxAgios;
    private double d_totalDecouvertAutoriser;
   private CompteCourantDAO d_compteCourantDAO= new CompteCourantDAO();
    private  static double d_debitMax=15000.99;

    public ComptebancaireCourant()
    {
        super();
        this.d_totalDecouvertAutoriser=0;
        this.d_tauxAgios=0;
    }




    public ComptebancaireCourant(int idcompte,double solde, String dateCreation, Client client,double agios, double decouverte)
    {
        super(idcompte,solde,dateCreation,client);
        this.d_totalDecouvertAutoriser=decouverte;
        this.d_tauxAgios=agios;


    }

    public double GetTotalDecouvertAutoriser()
    {
        return d_totalDecouvertAutoriser;
    }
    public double GetAgios()
    {
        return d_tauxAgios;
    }
    public double GetDebitMax()
    {
        return d_debitMax;
    }

    public  void setTotalDecouvertAutoriser(double totalDecouvertAutoriser)
    {
        this.d_totalDecouvertAutoriser=totalDecouvertAutoriser;
    }
    public void setTauxAgios(double tauxAgios)
    {
        this.d_tauxAgios=tauxAgios;
    }

    @Override
    public void afficherCOmpte() {
        System.out.println("--------------------- Affichage du compte courant numero : "+super.GetIdCompte()+" -------------------");
        System.out.println("Solde "+super.GetSolde());
        System.out.println("Date de creation "+super.GetDateCreation());
        System.out.println("Montant decouverte autorisé "+this.GetTotalDecouvertAutoriser());
        System.out.println("Taux  Agios "+this.GetAgios());

    }


    @Override
    public Boolean RetraitPossible(double montant) {
        if(this.d_totalDecouvertAutoriser>=montant ||(montant<=super.GetSolde()&&montant <=GetDebitMax()&&this.d_totalDecouvertAutoriser>=montant))
             return true;
        return false;
    }

    @Override
    public void debiter(double montant)throws MontantNegatifException, RetraitImpossibleException
    {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
       if(RetraitPossible( montant)==false)
           throw new RetraitImpossibleException("le Vous ne pouvez pas faire de retrait");
        double soldecourant=super.GetSolde();
       if(this.d_totalDecouvertAutoriser<=montant)
       {
           //jje veux retirer 100, je suis à zero de solde et j'ai un decouvert de 100
           //ya le sodle courant qui entre en jeux aussi
           //if(solde - montant >= 0) {
           //solde = solde - montant;
           //}

       }

       soldecourant-=montant;
       super.setSolde(soldecourant);
       ///enregistrer l'operation
    }

    @Override
    public void crediter(double montant) throws MontantNegatifException {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
       double soldeCourant=super.GetSolde();
       soldeCourant+=montant;
        super.setSolde(soldeCourant);
        //enregistrerOperation
        enregistrerTransaction(montant,"Credit");
    }

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

    @Override
    public void AfficheHistorique() {
        for (OperationBancaire operationBancaire:this.getHistorique())
        {
            operationBancaire.Afficher();
        }

    }


}
