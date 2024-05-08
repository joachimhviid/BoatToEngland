package common.events;

import javafx.event.Event;
import javafx.event.EventType;

public class DebugToggleEvent extends Event {
    public static final EventType<DebugToggleEvent> ANY = new EventType<>(Event.ANY, "DEBUG_TOGGLE_EVENT");

    public DebugToggleEvent() {
        super(ANY);
        System.out.println("DEBUGGING!");
    }
}