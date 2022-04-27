package bank.Acount.kata.Exceptions;

public class MontantNegatifException extends Exception{
    public MontantNegatifException()
    {
        super();
    }

    public MontantNegatifException(String message)
    {
        super(message);
    }

}
