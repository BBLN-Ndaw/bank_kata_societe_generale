package bank.Acount.kata.DAO;

import bank.Acount.kata.connexion.DbBankAccount;

import java.sql.Connection;
import java.util.List;

/**
 * Modele d'acces au données DAO
 * @param <T> type générique (utilisées pour les differents types d'objet pouvez interagir avec la base de données)
 * @author yayandaw95@gmail.com
 */
public abstract class  DAO<T> {
   public Connection d_connexionDAO= DbBankAccount.GetDbBankAccountInstance().GetConnexion();
    /**
     * Permet de recuperer un objet grâce à son identifiant
     * @param idClient
     * @return
     */
    public abstract T rechercher(int  idClient);
    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     * @return
     */
    public  abstract void creer(T obj);

 /**
  * Donne une liste de T
  * @return List<T>
  */
 public abstract List<T>GetListe();

}
