package bank.Acount.kata;

public class OperationBancaire {
    private Client d_client;
    private CompteBancaire d_compte;
    private String d_dateOperation;
    private double d_montantantOperation;
    private String d_typeOperation;


    public OperationBancaire(Client client, CompteBancaire compteBancaire)
    {
        this.d_client=client;
        this.d_compte=compteBancaire;
    }

    public OperationBancaire()
    {
        d_client=null;
        d_compte=null;
        d_dateOperation="";
        d_montantantOperation=0.;
        d_typeOperation="";
    }


    public OperationBancaire(Client client, CompteBancaire compteBancaire, String dateOperation, double montantOperation, String typeOperation)
    {
        this.d_client=client;
        this.d_compte=compteBancaire;
        this.d_dateOperation=dateOperation;
        this.d_montantantOperation=montantOperation;
        this.d_typeOperation=typeOperation;
    }

    public Client getClient() {
        return d_client;
    }

    public void setClient(Client d_client) {
        this.d_client = d_client;
    }

    public CompteBancaire getCompte() {
        return d_compte;
    }

    public void setCompte(CompteBancaire d_compte) {
        this.d_compte = d_compte;
    }

    public String getDateOperation() {
        return d_dateOperation;
    }

    public void setDateOperation(String d_dateOperation) {
        this.d_dateOperation = d_dateOperation;
    }

    public double getMontantantOperation() {
        return d_montantantOperation;
    }

    public void setMontantantOperation(double d_montantantOperation) {
        this.d_montantantOperation = d_montantantOperation;
    }

    public String getTypeOperation() {
        return d_typeOperation;
    }

    public void setTypeOperation(String d_typeOperation) {
        this.d_typeOperation = d_typeOperation;
    }

    public void Afficher() {
        System.out.println("Operation effectué le : "+this.d_dateOperation);
        System.out.println("Operation de type : "+this.d_typeOperation);
        System.out.println("Montant : "+this.d_montantantOperation);

    }

}
