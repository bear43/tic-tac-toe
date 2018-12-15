package bear.game.Game.Event;

public interface EventHandler
{
    default void handle()
    {
        handle(null);
    }
    void handle(Object[] args);
}
