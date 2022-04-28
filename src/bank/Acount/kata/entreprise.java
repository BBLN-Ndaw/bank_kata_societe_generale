package bank.Acount.kata;

import bank.Acount.kata.DAO.ParticulierDAO;
import bank.Acount.kata.DAO.entrepriseDAO;

public final class entreprise extends Client {
    private int d_siret;
    private String d_raisonSocial;
    private String d_codeNaf;

    public entreprise()
    {
        super();
        this.d_siret=0;
        this.d_raisonSocial="";
        this.d_codeNaf="";
    }
    public entreprise(String adresse, String numTel, int siret,String raisonSocial,String codeNaf)
    {
        super(adresse,numTel);
        this.d_siret=siret;
        this.d_raisonSocial=raisonSocial;
        this.d_codeNaf=codeNaf;
        enregistrerClient();
    }
    public String GetraisonSocial()
    {
        return d_raisonSocial;
    }
    @Override
    public void enregistrerClient() {
        //Enregistrement du client dans la base de donnée.
        new entrepriseDAO().creer(this);
    }

    @Override
    public int GetIdClient()
    {
        return d_siret;
    }
    public String GetCodeNaf()
    {
        return d_codeNaf;
    }
    public void setraisonSociale(String raisonSociale)
    {
        d_raisonSocial=raisonSociale;
    }

    public void setCodeNaf(String codeNaf)
    {
        d_codeNaf=codeNaf;
    }

    @Override
    public void setIdClient(int siret)
    {
        d_siret=siret;
    }

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
