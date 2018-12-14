package bear.game.Game.Exceptions;

public class NotProperpFieldSizeException extends Exception
{
    public NotProperpFieldSizeException()
    {
        super("[Field] Cell count must be divided by two without remainder");
    }
}
