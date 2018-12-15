package bear.game.Game.Exceptions;

public class BlockIsNotEmptyException extends Exception
{
    public BlockIsNotEmptyException()
    {
        super("[Figure] Block is not empty again");
    }
}
