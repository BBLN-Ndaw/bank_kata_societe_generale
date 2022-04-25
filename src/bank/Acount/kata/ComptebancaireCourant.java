package bank.Acount.kata;

import java.math.BigDecimal;

public final class  ComptebancaireCourant extends  CompteBancaire{
    private static double d_tauxAgios;
    private double d_totalDecouvertAutoriser;

    public double GettotalDecouvertAutoriser(){return d_totalDecouvertAutoriser;}
    public double GetTauxAgios(){return d_tauxAgios;}

    public  void setTotalDecouvertOutoriser(double totalDecouvertAutoriser)
    {
        this.d_totalDecouvertAutoriser=totalDecouvertAutoriser;
    }
    public void setTauxAgios(double tauxAgios)
    {
        this.d_tauxAgios=tauxAgios;
    }

}
