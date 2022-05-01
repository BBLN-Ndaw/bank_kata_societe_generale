package bank.Acount.kata.Exceptions;
/**
 * Exception declancher lorsque le plafond du compte epargne est depassé
 * @author yayandaw95@gmail.com
 */
public class CrediterCompteImpossibleException extends Exception{
    /**
     * Constructeur
     * @param message le message d'information
     */
    public CrediterCompteImpossibleException(String message)
    {
        super(message);
    }
}
