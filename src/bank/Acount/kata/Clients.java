package bank.Acount.kata;

/**
 * Gestion de la clientele
 * @author  yayandaw95@gmail.com
 */
public abstract class Clients {
    private  String d_adresse;
    private  String d_numeroTelephone;


    public Clients()
    {

    }

    public String GetAdresse()
    {
        return d_adresse;
    }
    public String GetNumerotelephone()
    {
        return d_numeroTelephone;
    }
    public void setAdresse(String adresse)
    {
        d_adresse=adresse;
    }
    public void setNumeroTelephone(String numeroTelephone)
    {
        d_numeroTelephone=numeroTelephone;
    }
    public abstract void AfficherClient();

}
