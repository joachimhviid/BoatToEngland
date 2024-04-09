package flowfield;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.ai.AI;
import common.events.DebugToggleEvent;
import common.events.PlayerMovedEvent;
import javafx.geometry.Point2D;

public class FlowFieldComponent extends Component {
    private AI ai;
    private FlowFieldGrid flowFieldGrid = new FlowFieldGrid(30, 30, 40);
    private DebugOverlay debugOverlay;

    //This is for optimizing a bit since my methods lagged the game. Will ensure we don't call the PLAYER_MOVED every frame but instead everytime player has moved enough
    private final double UPDATE_THRESHOLD = 40;
    private Point2D lastUpdatePosition = new Point2D(0,0);

    @Override
    public void onAdded() {
        this.debugOverlay = new DebugOverlay(flowFieldGrid);

        entity.getViewComponent().addChild(debugOverlay.getBoxCanvas());
        entity.getViewComponent().addChild(debugOverlay.getArrowCanvas());

        debugOverlay.getBoxCanvas().setVisible(false);
        debugOverlay.getArrowCanvas().setVisible(false);

        //This is the toggle handler
        FXGL.getEventBus().addEventHandler(DebugToggleEvent.ANY, event -> toggleDebugOverlay());

        //This is the handler for everytime player moves
        FXGL.getEventBus().addEventHandler(PlayerMovedEvent.PLAYER_MOVED, event -> {
            Point2D playerPosition = event.getNewPosition();

            if(lastUpdatePosition.distance(playerPosition) > UPDATE_THRESHOLD) {
                lastUpdatePosition = playerPosition;
                flowFieldGrid.updateField(playerPosition);

                if(debugOverlay.getIsVisible()) {
                    debugOverlay.refreshArrows();
                }
            }

        });

    }

    @Override
    public void onUpdate(double tpf) {
    }

    public void toggleDebugOverlay() {
        debugOverlay.toggleVisibility();
    }
}
