package bank.Acount.kata;

import bank.Acount.kata.DAO.ParticulierDAO;

/**
 * Gestion d'un client particulier
 */
    public final class Particulier extends Client {
        private int d_CNI;
        private String d_nom;
        private String d_prenom;
        private String d_dateNaissance;

    public Particulier()
    {
        super();
        this.d_CNI=0;
        this.d_nom="";
        this.d_prenom="";
        this.d_dateNaissance="";
    }


    public Particulier(String adresse, String numTel, int cni, String nom, String prenom, String dateNaissance)
    {
        super(adresse,numTel);
        this.d_CNI=cni;
        this.d_nom=nom;
        this.d_prenom=prenom;
        this.d_dateNaissance=dateNaissance;
        enregistrerClient();
    }
    public String GetNom()
    {
        return d_nom;
    }
    public String GetPrenom()
    {
        return d_prenom;
    }
    public String GetDateNaissance()
    {
        return d_dateNaissance;
    }
    public void setNom(String nom)
    {
        d_nom=nom;
    }
    public void setPrenom(String prenom)
    {
        d_prenom=prenom;
    }
    public void setdateNaissance(String dateNaissance)
    {
        d_dateNaissance=dateNaissance;
    }

    @Override
    public void enregistrerClient() {
        //Enregistrement du client dans la base de donnée.
        new ParticulierDAO().creer(this);
    }

    @Override
    public  void setIdClient(int CNI)
    {
        d_CNI=CNI;
    }
    @Override
    public int GetIdClient()
    {
        return d_CNI;
    }
    @Override
    public  void AfficherClient()
    {
        System.out.println("------------Affichage du client (Particulier)------------------");
        System.out.println("------------Numero carte d'identité du client : "+this.GetIdClient()+"------------------");
        System.out.println("------------Nom du client : "+this.GetNom()+"------------------");
        System.out.println("------------Prenom du client : "+this.GetPrenom()+"------------------");
        System.out.println("------------Adresse du client : "+this.GetAdresse()+"------------------");
        System.out.println("------------Numero telephone du client : "+this.GetNumerotelephone()+"------------------");

    }
}
