package bear.game.Game.Event.Mouse;

import bear.game.Game.Event.Event;
import bear.game.Game.Event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class MousePressed implements EventContainer
{
    private Map<String, EventHandler> events = new HashMap<>();

    @Override
    public void startEvent(Object sender, Object[] args)
    {
        events.forEach((x, y) -> y.handle(args));
    }

    public void addEvent(String eventName, EventHandler eventHandler)
    {
        events.put(eventName, eventHandler);
    }

    public boolean removeEvent(String eventName)
    {
        return events.remove(eventName) != null;
    }
}
