package bank.Acount.kata;

import bank.Acount.kata.DAO.CompteCourantDAO;
import bank.Acount.kata.Exceptions.CrediterCompteImpossibleException;
import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;
import bank.Acount.kata.DAO.CompteBancaireEpargneDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe gerant un compte epargne
 * @author  yayanda95@gmail.com
 */
public final class CompteBancaireEpargne extends  CompteBancaire{
    private static double d_tauxInteret;
    private static double d_plafondMaxDepotAutorise;
    private CompteBancaireEpargneDAO d_compteEpargnetDAO=new CompteBancaireEpargneDAO() ;

    /**
     * constructeur par defaut
     */
    public CompteBancaireEpargne()
    {
        super();
        this.d_tauxInteret=0;
        this.d_plafondMaxDepotAutorise=0;
    }

    /**
     * Initialise un compte epargne à partir de son identifiant, solde, date de creation, client, taux d"interet et le plafond de depôt autorise.
     * @param idcompte
     * @param solde
     * @param dateCreation
     * @param client
     * @param Tauxinteret
     * @param plafond
     */
    public CompteBancaireEpargne(int idcompte,double solde, String dateCreation, Client client,double Tauxinteret, double plafond)
    {
        super(idcompte,solde,dateCreation,client);
        this.d_tauxInteret=Tauxinteret;
        this.d_plafondMaxDepotAutorise=plafond;
        enregistrerCompte();
    }

    /**
     * Donne le taux d'interet appliqué sur le compte
     * @return d_tauxInteret
     */
    public double GetTauxInteret()
    {
        return d_tauxInteret;
    }

    /**
     * Donne le plafond
     * @return d_plafondMaxDepotAutorise.
     */
    public double GetPlafond()
    {
        return d_plafondMaxDepotAutorise;
    }

    /**
     * Ajout eou change le taux d'interet
     * @param tauxInteret
     */
    public void setTauxInteret(double tauxInteret)
    {
        d_tauxInteret=tauxInteret;
    }

    /**
     * Ajoute ou change le plafond
     * @param plafond
     */
    public void setPlafond(double plafond)
    {
        d_plafondMaxDepotAutorise=plafond;
    }

    /**
     * Enregistre le compte dans la base de données
     */
    @Override
    public void enregistrerCompte() {
        new CompteBancaireEpargneDAO().creer(this);
    }

    /**
     * Verifie la possibilité d'effectuer un retrait sur le compte
     * @param montant
     * @return true si vrai false sinon
     */
    @Override
    public Boolean RetraitPossible(double montant) {
        if(GetSolde()-montant>0&&GetDebitMax()>=montant&&montant>0)
            return true;
        return false;
    }

    /**
     * Retir une somme sur le compte epargne
     * @param montant
     * @throws MontantNegatifException
     * @throws RetraitImpossibleException
     */
    @Override
    public void debiter(double montant) throws MontantNegatifException, RetraitImpossibleException {
        if(montant<0)
            throw  new MontantNegatifException("Le montant ne peut pas être négatif");
        if(RetraitPossible(montant)==false)
            throw new RetraitImpossibleException("Vous ne pouvez pas faire de retrait");
        setSolde(GetSolde()-montant);
        //mis à jours du solde dans la base de donnée
        new CompteBancaireEpargneDAO().updateSolde(GetSolde()-montant);
        ///enregistrer l'operation
        enregistrerTransaction(montant,GetSolde(), "debit");
    }

    /**
     * Ajoute de l'argent dans le compte
     * @param montant
     * @throws MontantNegatifException
     * @throws CrediterCompteImpossibleException
     */
    @Override
    public void crediter(double montant) throws MontantNegatifException,CrediterCompteImpossibleException {
        if(montant<0)
            throw new MontantNegatifException("Le montant ne peut pas être negatif");
        double soldeCourant=super.GetSolde();
        soldeCourant+=montant;
        if(soldeCourant>this.GetPlafond())
            throw  new CrediterCompteImpossibleException("Le plafond est atteint");
        super.setSolde(soldeCourant);
        //Mis à jour du sole
        new CompteBancaireEpargneDAO().updateSolde(soldeCourant);
        //enregistrer Operation
        enregistrerTransaction(montant, GetSolde(), "Credit");

    }

    /**
     * Applique les interet sur le compte
     */
    public void ajouterInteret()
    {
        double interet=this.GetSolde()*this.GetTauxInteret();
        this.setSolde(this.GetSolde()+interet);
        //Mis à jour du solde dans la base de donnée
        new CompteBancaireEpargneDAO().updateSolde(this.GetSolde()+interet);
    }

    /**
     * Donne la liste de tous les opérations bancaires réalisées sur le compte
     * @return List<OperationBancaire>
     */
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

    /**
     * Affiche l'historique des operations effectuées sur le compte
     */
    @Override
    public void AfficheHistorique() {
        for (OperationBancaire operationBancaire:this.getHistorique())
        {
            operationBancaire.Afficher();
        }
    }

    /**
     * Affiche le compte bancaire dans la console
     */
    @Override
    public void afficherCOmpte() {
        System.out.println("--------------------- Affichage du compte epargne numero : "+super.GetIdCompte()+" -------------------");
        System.out.println("Solde "+super.GetSolde());
        System.out.println("Date de creation "+super.GetDateCreation());
        System.out.println("Plafond "+this.GetPlafond());
        System.out.println("Taux d'interêt "+this.GetTauxInteret());

    }



}
