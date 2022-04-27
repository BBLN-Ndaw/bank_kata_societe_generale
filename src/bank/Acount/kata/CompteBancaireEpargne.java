package bank.Acount.kata;

import bank.Acount.kata.Exceptions.CrediterCompteImpossibleException;
import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;
import bank.Acount.kata.DAO.CompteBancaireEpargneDAO;

import java.sql.SQLException;
import java.util.List;

public final class CompteBancaireEpargne extends  CompteBancaire{
    private static double d_tauxInteret;
    private static double d_plafondMaxDepotAutorise;
    private CompteBancaireEpargneDAO d_compteEpargnetDAO=new CompteBancaireEpargneDAO() ;

    public CompteBancaireEpargne()
    {
        super();
        this.d_tauxInteret=0;
        this.d_plafondMaxDepotAutorise=0;
    }
    public CompteBancaireEpargne(int idcompte,double solde, String dateCreation, Client client,double Tauxinteret, double plafond)
    {
        super(idcompte,solde,dateCreation,client);
        this.d_tauxInteret=Tauxinteret;
        this.d_plafondMaxDepotAutorise=plafond;
    }

    public double GetTauxInteret()
    {
        return d_tauxInteret;
    }
    public double GetPlafond()
    {
        return d_plafondMaxDepotAutorise;
    }
    public void setTauxInteret(double tauxInteret)
    {
        d_tauxInteret=tauxInteret;
    }
    public void setPlafond(double plafond)
    {
        d_plafondMaxDepotAutorise=plafond;
    }

    @Override
    public Boolean RetraitPossible(double montant) {
       // if(montant<=super.GetSolde()&&montant <=super.GetDebitMax())
            return true;
       // return false;
    }
    @Override
    public void debiter(double montant) throws MontantNegatifException, RetraitImpossibleException {

    }
    @Override
    public void crediter(double montant) throws MontantNegatifException,CrediterCompteImpossibleException {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
        double soldeCourant=super.GetSolde();
        soldeCourant+=montant;
        if(soldeCourant>this.GetPlafond())
            throw  new CrediterCompteImpossibleException("Le plafond est atteint");
        super.setSolde(soldeCourant);
        //enregistrer Operation
        enregistrerTransaction(montant,"Credit");

    }

    @Override
    public List<OperationBancaire> getHistorique() {
        if(d_compteEpargnetDAO!=null)
        {
            try {

                return d_compteEpargnetDAO.GetHistorique(this);
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

    @Override
    public void afficherCOmpte() {
        System.out.println("--------------------- Affichage du compte epargne numero : "+super.GetIdCompte()+" -------------------");
        System.out.println("Solde "+super.GetSolde());
        System.out.println("Date de creation "+super.GetDateCreation());
        System.out.println("Plafond "+this.GetPlafond());
        System.out.println("Taux d'interêt "+this.GetTauxInteret());

    }



}
