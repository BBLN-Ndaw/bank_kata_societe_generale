package bank.Acount.kata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Gestion de la clientele
 * @author  yayandaw95@gmail.com
 */
public abstract class Client {
    private  String d_adresse;
    private  String d_numeroTelephone;
    private Set<CompteBancaire>d_listeCompte=new HashSet<CompteBancaire>();

    public Client()
    {
        this.d_adresse="";
        this.d_numeroTelephone="";
    }
    public Client(String adresse, String numTel)
    {
        this.d_adresse=adresse;
        this.d_numeroTelephone=numTel;
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
    public Set<CompteBancaire> getListeCompte() {
        return d_listeCompte;
    }
    public void setListeCompte(Set<CompteBancaire> listeCompte) {
        this.d_listeCompte = listeCompte;
    }
    public void ajouterCompter(CompteBancaire compteBancaire)
    {
        if(!this.d_listeCompte.contains(compteBancaire))
            this.d_listeCompte.add(compteBancaire);
    }

    public void supprimerCompte(CompteBancaire compteBancaire)
    {
        this.d_listeCompte.remove(compteBancaire);
    }

    public int GetNombreCompte()
    {
        return getListeCompte().size();
    }

    public void AfficherTousLesComptes()
    {
        for(CompteBancaire compteBancaire:getListeCompte())
        {
            compteBancaire.afficherCOmpte();
        }
    }

    public abstract void enregistrerClient();
    public abstract int GetIdClient();
    public abstract void setIdClient(int id);
    public abstract void AfficherClient();



}
