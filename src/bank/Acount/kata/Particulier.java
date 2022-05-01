package bank.Acount.kata;

import bank.Acount.kata.DAO.ParticulierDAO;

/**
 * Gestion d'un client particulier
 * @author yayandaw95@gmail.com
 */
    public final class Particulier extends Client {
        private int d_CNI;
        private String d_nom;
        private String d_prenom;
        private String d_dateNaissance;

    /**
     * Constructeur par defaut
      */
    public Particulier()
    {
        super();
        this.d_CNI=0;
        this.d_nom="";
        this.d_prenom="";
        this.d_dateNaissance="";
    }

    /**
     * Initialise un client (Particulier) à partir de son adresse, numero telephone, CNI, nom, prenom et date de naissance
     * @param adresse
     * @param numTel
     * @param cni
     * @param nom
     * @param prenom
     * @param dateNaissance
     */
    public Particulier(String adresse, String numTel, int cni, String nom, String prenom, String dateNaissance)
    {
        super(adresse,numTel);
        this.d_CNI=cni;
        this.d_nom=nom;
        this.d_prenom=prenom;
        this.d_dateNaissance=dateNaissance;
        enregistrerClient();
    }

    /**
     * Permet d'obtenir la date du client
     * @return
     */
    public String GetNom()
    {
        return d_nom;
    }

    /**
     *
     * @return Prenom du client
     */
    public String GetPrenom()
    {
        return d_prenom;
    }

    /**
     * Date de naissance du client
     * @return
     */
    public String GetDateNaissance()
    {
        return d_dateNaissance;
    }

    /**
     * Change le nom du client
     * @param nom
     */
    public void setNom(String nom)
    {
        d_nom=nom;
    }

    /**
     * Change le prenom du client
     * @param prenom
     */
    public void setPrenom(String prenom)
    {
        d_prenom=prenom;
    }

    /**
     * Change la date de naisssance du client
     * @param dateNaissance
     */
    public void setdateNaissance(String dateNaissance)
    {
        d_dateNaissance=dateNaissance;
    }

    /**
     * Enregistre un client (Particulier) dans la base de donnée
     */
    @Override
    public void enregistrerClient() {
        //Enregistrement du client dans la base de donnée.
        new ParticulierDAO().creer(this);
    }

    /**
     * Change le nomp du client
     * @param CNI
     */
    @Override
    public  void setIdClient(int CNI)
    {
        d_CNI=CNI;
    }

    /**
     * ermet d'obtenir le nom du client
     * @return
     */
    @Override
    public int GetIdClient()
    {
        return d_CNI;
    }

    /**
     * Affiche un client dans la console
     */
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
