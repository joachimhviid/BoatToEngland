package common.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class PlayerMovedEvent extends Event {
    public static final EventType<PlayerMovedEvent> PLAYER_MOVED = new EventType<>(Event.ANY, "PLAYER_MOVED");
    private Point2D newPosition;

    public PlayerMovedEvent(Point2D newPosition) {
        super(PLAYER_MOVED);
        this.newPosition = newPosition;
    }

    public Point2D getNewPosition() {
        return newPosition;
    }
}
