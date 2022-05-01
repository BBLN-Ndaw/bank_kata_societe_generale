package bank.Acount.kata;

import bank.Acount.kata.DAO.ParticulierDAO;
import bank.Acount.kata.DAO.entrepriseDAO;

/**
 * Classe entreprise
 * @author yayandaw95@gmail.com
 */
public final class entreprise extends Client {
    private int d_siret;
    private String d_raisonSocial;
    private String d_codeNaf;

    /**
     * Constructeur par defaut
     */
    public entreprise()
    {
        super();
        this.d_siret=0;
        this.d_raisonSocial="";
        this.d_codeNaf="";
    }

    /**
     * Construit un client(Entreprise) à partir de l'adresse, le numero de telephone, le numero de siret, la raison sociale et le code NAF
     * @param adresse
     * @param numTel
     * @param siret
     * @param raisonSocial
     * @param codeNaf
     */
    public entreprise(String adresse, String numTel, int siret,String raisonSocial,String codeNaf)
    {
        super(adresse,numTel);
        this.d_siret=siret;
        this.d_raisonSocial=raisonSocial;
        this.d_codeNaf=codeNaf;
        enregistrerClient();
    }

    /**
     * Permet d'obtenir le nom de l'entreprise client.
     * @return d_raisonSocial
     */
    public String GetraisonSocial()
    {
        return d_raisonSocial;
    }

    /**
     * Enregistre un client dans la base de donnée
     */
    @Override
    public void enregistrerClient() {
        //Enregistrement du client dans la base de donnée.
        new entrepriseDAO().creer(this);
    }

    /**
     * Permet d'obtenir l'identifiant du client
     * @return d_siret
     */
    @Override
    public int GetIdClient()
    {
        return d_siret;
    }

    /**
     * Permet d'obtenir le code NAF du client
     * @return d_codeNaf
     */
    public String GetCodeNaf()
    {
        return d_codeNaf;
    }

    /**
     * Change le nom de l'entreprise
     * @param raisonSociale
     */
    public void setraisonSociale(String raisonSociale)
    {
        d_raisonSocial=raisonSociale;
    }

    /**
     * Change le code NAF de l'entreprise
     * @param codeNaf
     */
    public void setCodeNaf(String codeNaf)
    {
        d_codeNaf=codeNaf;
    }

    /**
     * Change l'identifiant du client
     * @param siret
     */
    @Override
    public void setIdClient(int siret)
    {
        d_siret=siret;
    }

    /**
     * Affiche l'entreprise dans la console
     */
    @Override
    public  void AfficherClient()
    {
        System.out.println("------------Affichage du client (Entreprise)------------------");
        System.out.println("Numero Siret de l'entreprise : "+this.GetIdClient()+"------------------");
        System.out.println("Raison social de l'entreprise : "+this.GetraisonSocial()+"------------------");
        System.out.println("Code NAF de l'entreprise : "+this.GetCodeNaf()+"------------------");
        System.out.println("Adresse de l'entreprise : "+this.GetAdresse()+"------------------");
        System.out.println("Numero telephone de l'entreprise : "+this.GetNumerotelephone()+"------------------");


    }

}
