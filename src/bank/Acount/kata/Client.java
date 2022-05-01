package bank.Acount.kata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Gestion de la clientele Parent des classes Particulier et Entreprise
 * @author  yayandaw95@gmail.com
 */
public abstract class Client {
    private  String d_adresse;
    private  String d_numeroTelephone;
    private Set<CompteBancaire>d_listeCompte=new HashSet<CompteBancaire>();

    /**
     * Constructeur par defaut
     */
    public Client()
    {
        this.d_adresse="";
        this.d_numeroTelephone="";
    }

    /**
     * Initialise un client à partir de son adresse et de son numero de telemphone
     * @param adresse
     * @param numTel
     */
    public Client(String adresse, String numTel)
    {
        this.d_adresse=adresse;
        this.d_numeroTelephone=numTel;
    }

    /**
     * Donne l'adresse du client
     * @return
     */
    public String GetAdresse()
    {
        return d_adresse;
    }

    /**
     * Donne le numero de telephone du client
     * @return
     */
    public String GetNumerotelephone()
    {
        return d_numeroTelephone;
    }

    /**
     * Change l'adresse du client
     * @param adresse
     */
    public void setAdresse(String adresse)
    {
        d_adresse=adresse;
    }

    /**
     * Change le numero de telephone du client
     * @param numeroTelephone
     */
    public void setNumeroTelephone(String numeroTelephone)
    {
        d_numeroTelephone=numeroTelephone;
    }

    /**
     * Permmet d'obtenir l liste de tous les comptes d'un client
     * @return
     */
    public Set<CompteBancaire> getListeCompte() {
        return d_listeCompte;
    }

    /**
     * Permet d'ajouter une liste de compte au client
     * @param listeCompte
     */
    public void setListeCompte(Set<CompteBancaire> listeCompte) {
        this.d_listeCompte = listeCompte;
    }

    /**
     * Affecte un compte bancaire (Courant ou epargne ) au client
     * @param compteBancaire
     */
    public void ajouterCompter(CompteBancaire compteBancaire)
    {
        if(!this.d_listeCompte.contains(compteBancaire))
            this.d_listeCompte.add(compteBancaire);
    }

    /**
     * Supprime un compte" bancaire du client
     * @param compteBancaire
     */
    public void supprimerCompte(CompteBancaire compteBancaire)
    {
        this.d_listeCompte.remove(compteBancaire);
    }

    /**
     * Donne le nombre de compte ba,ncaire appartenant à un client
     * @return
     */
    public int GetNombreCompte()
    {
        return getListeCompte().size();
    }

    /**
     * Affiche sur la console la liste de tous les omptes bancaire du client
     */
    public void AfficherTousLesComptes()
    {
        for(CompteBancaire compteBancaire:getListeCompte())
        {
            compteBancaire.afficherCOmpte();
        }
    }

    /**
     * Enregistre un client dans la base de donnée
     */
    public abstract void enregistrerClient();

    /**
     * Permet d'obtenir l'identifiant du client
     * @return Id l'identifiant du client (Particulier ou Entreprise)
     */
    public abstract int GetIdClient();

    /**
     * Change l'identifiant du client
     * @param id
     */
    public abstract void setIdClient(int id);

    /**
     * Affiche un client dans le console
     */
    public abstract void AfficherClient();



}
