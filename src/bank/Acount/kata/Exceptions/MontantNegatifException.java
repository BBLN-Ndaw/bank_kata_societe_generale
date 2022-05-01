package bank.Acount.kata.Exceptions;

/**
 * Exception declancher lorsque le montant à crediter ou à debiter est negatif
 * @author yayandaw95@gmail.com
 */
public class MontantNegatifException extends Exception{
    public MontantNegatifException()
    {
        super();
    }

    /**
     * Constructeur
     * @param message le message d'information
     */
    public MontantNegatifException(String message)
    {
        super(message);
    }

}
