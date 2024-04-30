package flowfield;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.ai.AI;
import common.ai.IPathFinder;
import common.events.DebugToggleEvent;
import common.events.PlayerMovedEvent;
import javafx.application.Platform;
import javafx.geometry.Point2D;

public class FlowFieldComponent extends Component {
    private AI ai;

    // TODO: I need to make this variable not hardcoded at some point. Add some kind of event for when player is added and listen for that event.
    private Point2D startPosition = new Point2D(100, 100);

    private FlowFieldGrid flowFieldGrid = new FlowFieldGrid(17, 11, 100, startPosition);
    private DebugOverlay debugOverlay;

    //This is for optimizing a bit since my methods lagged the game. Will ensure we don't call the PLAYER_MOVED every frame but instead everytime player has moved enough
    private Point2D lastUpdatePosition = new Point2D(0,0);

    @Override
    public void onAdded() {
        this.debugOverlay = new DebugOverlay(flowFieldGrid);
        entity.getViewComponent().addChild(debugOverlay.getArrowCanvas());
        debugOverlay.getArrowCanvas().setVisible(false);
    }

    @Override
    public void onUpdate(double tpf) {
        //This is the toggle handler
        FXGL.getEventBus().addEventHandler(DebugToggleEvent.ANY, event -> {
            //Without the Platform.runLater this wont load for some reason. Kept calling the event twice, but I am not entirely sure why.
            Platform.runLater(this::toggleDebugOverlay);
        });

        //This is the handler for everytime player moves
        FXGL.getEventBus().addEventHandler(PlayerMovedEvent.PLAYER_MOVED, event -> {
            Point2D playerPosition = event.getNewPosition();

            if (lastUpdatePosition.distance(playerPosition) > flowFieldGrid.getCellSize()) {
                lastUpdatePosition = playerPosition;
                //This updates the vector arrows
                flowFieldGrid.updateField(playerPosition);
                //This updates the debug layout
                flowFieldGrid.centerGridOnPlayer(playerPosition);

                if (debugOverlay.getIsVisible()) {
                    debugOverlay.refreshArrows();
                }

            }

        });
    }

    public void toggleDebugOverlay() {
        debugOverlay.toggleVisibility();
    }
}
