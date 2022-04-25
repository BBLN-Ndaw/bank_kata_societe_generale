package bank.Acount.kata;

/**
 * Gestion d'un client particulier
 */
public final class PersonnePhysique extends Clients{
    private int d_CNI;
    private String d_nom;
    private String d_prenom;
    private String d_dateNaissance;

public PersonnePhysique()
{
    super();

}
    public int GetCNI()
    {
        return d_CNI;
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

    public  void setCNI(int CNI)
    {
        d_CNI=CNI;
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
    public  void AfficherClient()
    {

    }
}
