package bear.game.Game.Event.Mouse;

import bear.game.Game.Event.Event;
import bear.game.Game.Event.EventHandler;

public interface EventContainer extends Event
{
    void addEvent(String eventName, EventHandler eventHandler);
    boolean removeEvent(String eventName);
}
