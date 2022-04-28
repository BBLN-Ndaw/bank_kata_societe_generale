package bank.Acount.kata;

import bank.Acount.kata.DAO.*;
import bank.Acount.kata.Exceptions.CrediterCompteImpossibleException;
import bank.Acount.kata.Exceptions.MontantNegatifException;
import bank.Acount.kata.Exceptions.RetraitImpossibleException;
import bank.Acount.kata.connexion.DbBankAccount;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //Unique Instance de la base de donnée Mysql
        DbBankAccount DbBankAccountInstance = DbBankAccount.GetDbBankAccountInstance();


//--------------------Test Client (Particulier)-----------------------------------------------------------

        //Creation et ajout d'un client (Particulier) dans la base de donnée
         //Particulier particulier =new Particulier("adresse","0758",123456,"yaya","ndaw","10/11/1998");
        //On peut le faire ici comme ça aussi
       // DAO<Particulier> particulierDAO =new ParticulierDAO();
       // particulierDAO.creer(particulier);//sucess


        //Recherche d'un client (Particulier)
      // Particulier particulier2= particulierDAO.rechercher(particulier.GetIdClient());//succes
        //Affichage du client
        //particulier2.AfficherClient();

        //Affichage de la liste des comptes du client (particulier)
       // particulier2.AfficherTousLesComptes();

        //On peut le faire comme ça aussi :
       //Set<CompteBancaire> compteBancaireSet= particulier2.getListeCompte();
       // for(CompteBancaire compteBancaire:particulier2.getListeCompte())
       // {
        //    compteBancaire.afficherCOmpte();

 //--------------------------------------------------Fin test Client particulier--------------------------------------------




//-----------------------------------Test client(Entreprise)--------------------------------------------------------------

        //Creation d'un client (entreprise) et Ajout dans la base de donnée
        //entreprise entrepriseClient =new entreprise("adresseEntreprise","00337864",2804,"Societe general entreprise","code");
        //DAO<entreprise> entrepriseDAO=new entrepriseDAO();
       // entrepriseDAO.creer(entrepriseClient);



        //DAO<entreprise> entrepriseDAO=new entrepriseDAO();

        // Recherche d'un client (entreprise)
       //entreprise entrepriseClient2= entrepriseDAO.rechercher(entrepriseClient.GetIdClient());//succes

        //Affichage du client (entreprise)
        //entrepriseClient2.AfficherClient();

        //Affichage des comptes du client (Entreprise)
        //entrepriseClient2.AfficherTousLesComptes();

        //On peut le faire comme ça aussi :
       // Set<CompteBancaire> compteBancaireEntrepriseSet= entrepriseClient2.getListeCompte();
      // for(CompteBancaire compteBancaire:entrepriseClient2.getListeCompte())
      // {
        //    compteBancaire.afficherCOmpte();

       // }

//--------------------------------Fin test Client(entreprise)----------------------------------------------------------------



//----------------------------------------------------COMPTE EPARGNE----------------------------------------------------
        //Creation d'un compte epargne
        //CompteBancaireEpargne compteBancaireEpargne =new CompteBancaireEpargne(5,2000,"26/04/2022",particulier,0.75,15590);
        // compteBancaireEpargne.AfficheHistorique();
        //DAO<CompteBancaireEpargne> compteBancaireEpargneDAO= new CompteBancaireEpargneDAO();
        //compteBancaireEpargneDAO.creer(compteBancaireEpargne);

        //Affichage du compte epargne
        //compteBancaireEpargne.afficherCOmpte();

        //Affichage du propriétaire du compte epargne : Particulier ou entreprise.
        //compteBancaireEpargne.AfficheClient();

        //Recherche d'un compte epargne
       // CompteBancaireEpargne compteBancaireEpargne2 =  compteBancaireEpargneDAO.rechercher(compteBancaireEpargne.GetIdCompte());
        // if(compteBancaireEpargne2 != null)
        //  compteBancaireEpargne2.GetClient().AfficherClient();

        //Transaction sur le compte epargne (crediter le compte)
       /* try
        {
            compteBancaireEpargne2.crediter(196);
        }
        catch (MontantNegatifException e)
        {
            e.printStackTrace();
        }
        catch (CrediterCompteImpossibleException e)
        {
            e.printStackTrace();
        }*/


        //Affichage du solde mis à jour
        //System.out.println(compteBancaireEpargne2.GetSolde());

        //Transaction sur le compte epargne (Debiter le compte)
       /* try
        {
            compteBancaireEpargne2.debiter(15);
        }
        catch (MontantNegatifException e)
        {
            e.printStackTrace();
        }
        catch (RetraitImpossibleException e) {
            e.printStackTrace();
        }
        */

        //Affichage solde mis à jour
        // System.out.println(compteBancaireEpargne2.GetSolde());


        //Affichage de l'historique du compte epargne
        //compteBancaireEpargne2.AfficheHistorique();

        //Application de l'interêt sur le compte :
       // compteBancaireEpargne2.ajouterInteret();




//------------------------------------Fin test Compte Epargne------------------------------------



//----------------------------------Test compte banciare courant-----------------------------------
        //Recherche d'un compte bancaire courant
       //DAO<ComptebancaireCourant> comptebancaireCourantDAO=new CompteCourantDAO();
        //ComptebancaireCourant comptebancaireCourant =new ComptebancaireCourant(8,2000,"26/04/2022",particulier,0.25,100);

        //Creation et ajout du compte courant dans la base de donnée
        //comptebancaireCourantDAO.creer(comptebancaireCourant);

        //Affichage du compte courant
        //comptebancaireCourant.afficherCOmpte();

        //Affichage propriétaire du compte courant
        //comptebancaireCourant.afficherCOmpte();

        //Recherche d'un compte courant et affichage
      // ComptebancaireCourant comptebancaireCourant2 =  comptebancaireCourantDAO.rechercher(comptebancaireCourant.GetIdCompte());

        //Transaction dans un compte courant (crediter)
       /*
        try
        {
            comptebancaireCourant2.crediter(200);
        }
        catch (MontantNegatifException e)
        {
            e.printStackTrace();
        }
        */

        //Affichage du solde mis à jour
       // System.out.println(comptebancaireCourant2.GetSolde());


        //Transaction dans un compte courant (Debiter)
         /*
         try
        {
            comptebancaireCourant2.debiter(75);
        }
        catch (MontantNegatifException e)
        {
            e.printStackTrace();
        } catch (RetraitImpossibleException e) {
             e.printStackTrace();
         }
         */

        //Affichage du solde mis à jour
         //System.out.println(comptebancaireCourant2.GetSolde());


        //Affichage de l'historique du compte epargne
        //comptebancaireCourant2.AfficheHistorique();

//------------------------------------------------------Fin Test compte epargne-----------------------------------



//-----------------------------------------Test d'une Operation bancaire------------------------------------------------------

             // Creation et Ajout d'une operation bancaire
            /*
            OperationBancaire operationBancaire=new OperationBancaire(particulier,comptebancaireCourant,"26/04/2022",200,"Debit");
            OperationBancaireDAO operationBancaireDAO =new OperationBancaireDAO();
            try {
                operationBancaireDAO.creer(operationBancaire);
            } catch (SQLException e) {
                e.printStackTrace();
            }

             */

        //Affichage d'une operation bancaire
        //operationBancaire.Afficher();

        //Recherche d'une operation bancaire
        /*try {
            System.out.println(particulier.GetIdClient());
            System.out.println(comptebancaireCourant.GetIdCompte());
            OperationBancaire operationBancaire2 = operationBancaireDAO.rechercher(particulier,comptebancaireCourant);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */
//--------------------------------------------Fin test Operation bancaire--------------------------


//---------------------------------------Affichage de tous les comptes bancaires------------------------------
        /*
        //Affichage de la liste de tous les comptes courants

        List<ComptebancaireCourant> comptebancaireCourants= comptebancaireCourantDAO.GetListe();
        for(ComptebancaireCourant comptebancaireCourantI : comptebancaireCourants)
        {
            comptebancaireCourantI.afficherCOmpte();
        }
        */

       //Affichage de la liste de tous les comptes epargnes
        /*
        DAO<CompteBancaireEpargne> compteBancaireEpargneDAO= new CompteBancaireEpargneDAO();
        List<CompteBancaireEpargne> compteBancaireEpargneList= compteBancaireEpargneDAO.GetListe();
        for(CompteBancaireEpargne comptebancaireEpargneI : compteBancaireEpargneList)
        {
            comptebancaireEpargneI.afficherCOmpte();
        }
        */

//-----------------------------------------Fin de l'affichage de la listes des comptes epargnes-------------------------

        //Faire une methode qui enregistre le client dans la base de donnée lors de la creation d'un client.
        //Faire une methode qui enregistre le compte dans la base de donnée lors de la creation d'un compte.


    }
}
