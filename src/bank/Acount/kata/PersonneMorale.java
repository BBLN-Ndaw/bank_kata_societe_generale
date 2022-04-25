package bank.Acount.kata;

public final class PersonneMorale extends  Clients{
    private String d_raisonSocial;
    private int d_siret;
    private String d_codeNaf;

    public PersonneMorale()
    {
        super();
    }
    public String GetraisonSocial()
    {
        return d_raisonSocial;
    }
    public int getSiret()
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
    public void setSiret(int siret)
    {
        d_siret=siret;
    }
    public void setCodeNaf(String codeNaf)
    {
        d_codeNaf=codeNaf;
    }

@Override
    public  void AfficherClient()
    {

    }

}
