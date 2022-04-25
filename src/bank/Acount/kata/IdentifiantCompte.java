package bank.Acount.kata;

/**
 *  gestion de l'identification d'un compte bancaire
 * @author yayandaw95@gmail.com 22/04/2022
 */
public final class IdentifiantCompte {
    private  int d_identifiantBanque;
    private   int d_identifiantAgence;
    private  int d_numeroCompte;
    private   int d_cleRib;
    public IdentifiantCompte(int identifiantBanque, int identifiantAgence, int numeroCompte, int  cleRib )
    {
        this.d_identifiantBanque=identifiantBanque;
        this.d_identifiantAgence=identifiantAgence;
        this.d_numeroCompte=numeroCompte;
        this.d_cleRib=cleRib;
    }
    public IdentifiantCompte()
    {
        this.d_identifiantBanque=0;
        this.d_identifiantAgence=0;
        this.d_numeroCompte=0;
        this.d_cleRib=0;
    }

    public IdentifiantCompte GetIdentifiantCompte()
    {
        return new IdentifiantCompte(d_identifiantBanque,d_identifiantAgence,d_numeroCompte,d_cleRib);
    }

    public  int GetIdentifiantbanque(){return d_identifiantBanque;}
    public  int GetIdentifiantAgence(){return d_identifiantAgence;}
    public  int GetNumeroCompte(){return d_numeroCompte;}
    public  int GetCleRib(){return d_cleRib;}

    public void setIidentifiantBanque(int identifiantBanque)
    {
        this.d_identifiantBanque=identifiantBanque;
    }
    public void setidentifiantAgence(int identifiantAgence)
    {
        this.d_identifiantAgence=identifiantAgence;
    }
    public  void setNumeroCompte(int numeroCompte)
    {
        this.d_numeroCompte=numeroCompte;
    }
    public  void setcleRib(int rib)
    {
        this.d_cleRib=rib;
    }
}

