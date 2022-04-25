package bank.Acount.kata;

/**
 *  gestion de l'identification d'un compte bancaire
 * @author yayandaw95@gmail.com 22/04/2022
 */
public final class IdentifiantCompte {
    private final int d_identifiantBanque;
    private  final int d_identifiantAgence;
    private final int d_numeroCompte;
    private  final int d_cleRib;
    public IdentifiantCompte(int identifiantBanque, int identifiantAgence, int numeroCompte, int  cleRib )
    {
        this.d_identifiantBanque=identifiantBanque;
        this.d_identifiantAgence=identifiantAgence;
        this.d_numeroCompte=numeroCompte;
        this.d_cleRib=cleRib;
    }

    public IdentifiantCompte GetIdentifiantCompte()
    {
        return new IdentifiantCompte(d_identifiantBanque,d_identifiantAgence,d_numeroCompte,d_cleRib);
    }
}
