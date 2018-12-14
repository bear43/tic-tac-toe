package bear.game.Game.Exceptions;

public class FigureLimitExceededException extends Exception
{
    public FigureLimitExceededException()
    {
        super("[Figures] Cannot add a figure because figure limit exceeded");
    }
}
