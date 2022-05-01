package bank.Acount.kata;

/**
 * Classe Operation bancaire faisant le lien entre un client et un compte
 * @author  yayandaw95@gmail.com
 */
public class OperationBancaire {
    private Client d_client;
    private CompteBancaire d_compte;
    private String d_dateOperation;
    private double d_montantantOperation;
    private String d_typeOperation;

    /**
     * constructeur par defaut
     */
    public OperationBancaire()
    {
        d_client=null;
        d_compte=null;
        d_dateOperation="";
        d_montantantOperation=0.;
        d_typeOperation="";
    }
    /**
     * Constructeur à partir du client qui effectue l'operation bancaire et le compte sur lequel est effectué l'opération
     * @param client
     * @param compteBancaire
     */
    public OperationBancaire(Client client, CompteBancaire compteBancaire)
    {
        this.d_client=client;
        this.d_compte=compteBancaire;
    }

    /**
     * Construit une operation bancaire à partir d'un client, un compte, date de l'operation, le mpontant et le type d'operation.
     * @param client
     * @param compteBancaire
     * @param dateOperation
     * @param montantOperation
     * @param typeOperation
     */
    public OperationBancaire(Client client, CompteBancaire compteBancaire, String dateOperation, double montantOperation, String typeOperation)
    {
        this.d_client=client;
        this.d_compte=compteBancaire;
        this.d_dateOperation=dateOperation;
        this.d_montantantOperation=montantOperation;
        this.d_typeOperation=typeOperation;
    }

    /**
     * Donne le client qui effectue l'operation bancaire
     * @return d_client
     */
    public Client getClient() {
        return d_client;
    }

    /**
     * Ajoute ou change le client
     * @param d_client
     */
    public void setClient(Client d_client) {
        this.d_client = d_client;
    }

    /**
     * Donne le compte sur lequel on effectue l'operation bancaire
     * @return d_compte
     */
    public CompteBancaire getCompte() {
        return d_compte;
    }

    /**
     * Ajoute ou change le compte sur lequel on effectue l'operation bancaire
     * @param d_compte
     */
    public void setCompte(CompteBancaire d_compte) {
        this.d_compte = d_compte;
    }

    /**
     * Donne la date de l'operation bancaire
     * @return d_dateOperation
     */
    public String getDateOperation() {
        return d_dateOperation;
    }

    /**
     * Ajoute ou change la date de l'operation bancaire
     * @param d_dateOperation
     */
    public void setDateOperation(String d_dateOperation) {
        this.d_dateOperation = d_dateOperation;
    }

    /**
     * Donne le montant de la transaction bancaire
     * @return d_montantantOperation
     */
    public double getMontantantOperation() {
        return d_montantantOperation;
    }

    /**
     * Ajoute ou change le montant de l'operation bancaire.
     * @param d_montantantOperation
     */
    public void setMontantantOperation(double d_montantantOperation) {
        this.d_montantantOperation = d_montantantOperation;
    }

    /**
     * Donne le type d'operation (Debit ou credit)
     * @return d_typeOperation
     */
    public String getTypeOperation() {
        return d_typeOperation;
    }

    /**
     * Ajoute ou change le type d'operation bancaire
     * @param d_typeOperation
     */
    public void setTypeOperation(String d_typeOperation) {
        this.d_typeOperation = d_typeOperation;
    }

    /**
     * Affiche l'operation bancaire dans la console
     */
    public void Afficher() {
        System.out.println("Operation effectué le : "+this.d_dateOperation);
        System.out.println("Operation de type : "+this.d_typeOperation);
        System.out.println("Montant : "+this.d_montantantOperation);

    }

}
