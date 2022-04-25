package bank.Acount.kata;

import java.math.BigDecimal;

/**
 * Implementation de la classe de gestion d'un compte bancaire
 * @author  yayandaw95@gmail.com
 */
public abstract class CompteBancaire {
    private IdentifiantCompte d_identifiantCompteBancaire;
    private BigDecimal d_solde;
    private String d_dateCreaationCompte;

    /**
     * Permet d'obtenir le solde du compte
     * @return d_solde le solde du compte
     */
    public BigDecimal GetSolde()
    {
        return d_solde;
    }

    /**
     * Permet d'obtenir la date de creation du compte
     * @return d_dateCreaationCompte la date de creation du compyte
     */
    public String GetDateCreation()
    {
        return  d_dateCreaationCompte;
    }

    /**
     *  permet d'obtenir l'identifiant du compte
     * @return d_identifiantCompteBancaire l'identifiant du compte bancaire
     */
    public  IdentifiantCompte GetidentifiantCompte()
    {
        return d_identifiantCompteBancaire;
    }

}
