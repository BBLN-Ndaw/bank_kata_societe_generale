package bank.Acount.kata;

import java.math.BigDecimal;

public final class CompteBancaireEpargne extends  CompteBancaire{
    private static double d_tauxInteret;
    private static double d_plafondMaxDepotAutorise;
    public double GetTauxInteret()
    {
        return d_tauxInteret;
    }
    public double GetPlafond()
    {
        return d_plafondMaxDepotAutorise;
    }
    public void setTauxInteret(double tauxInteret)
    {
        d_tauxInteret=tauxInteret;
    }
    public void setPlafond(double plafond)
    {
        d_plafondMaxDepotAutorise=plafond;
    }




}
