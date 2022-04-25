package bank.kata.DAO;

import bank.kata.connexion.DbBankAccount;

import java.sql.Connection;

public abstract class DAO<T> {
    public Connection d_connexionDAO= DbBankAccount.GetDbBankAccountInstance().GetConnexion();

    /**
     * Permet de recuperer un objet grâce à son identifiant
     * @param id
     * @return
     */
    public abstract T rechercher(int  id);

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     * @return
     */
    public abstract void creer(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base de donnée
     * @param obj
     */
    //public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base de donnée
     * @param obj
     */
    //public abstract void delete(T obj);



}
