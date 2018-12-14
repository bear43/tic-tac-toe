package bear.game.Game.Exceptions;

public class FigureNotFoundException extends Exception
{
    public FigureNotFoundException()
    {
        super("[Figures] Requested figure not found");
    }
}
