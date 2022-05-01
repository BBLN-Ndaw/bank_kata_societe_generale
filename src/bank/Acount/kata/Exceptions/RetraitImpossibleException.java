package bank.Acount.kata.Exceptions;

/**
 * Exception declancher lors d'une tentative de retrait non autorisé
 * @author yayandaw95@gmail.com
 */
public class RetraitImpossibleException extends Exception{
    /**
     * Constructeur
     * @param message le message d'informattion
     */
    public RetraitImpossibleException(String message)
    {
        super(message);
    }
    public RetraitImpossibleException()
    {
        super();
    }

}
