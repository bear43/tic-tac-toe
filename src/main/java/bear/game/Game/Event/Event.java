package bear.game.Game.Event;

public interface Event
{
    /**
     * Handle event.
     * @param sender Where from event handled.
     * @param args Information to event. Remain it field null if no parameters to event.
     */
    void startEvent(Object sender, Object[] args);
    default void startEvent(Object sender)
    {
        startEvent(sender, null);
    }
}
