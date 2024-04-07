package flowfield;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.ai.AI;
import common.events.DebugToggleEvent;

public class FlowFieldComponent extends Component {
    private AI ai;
    private FlowFieldGrid flowFieldGrid = new FlowFieldGrid(10, 10);
    private DebugOverlay debugOverlay;

    @Override
    public void onAdded() {
        this.debugOverlay = flowFieldGrid.createDebugOverlay(40);
        debugOverlay.getOverlay().setVisible(false);
        entity.getViewComponent().addChild(debugOverlay.getOverlay());

        FXGL.getEventBus().addEventHandler(DebugToggleEvent.ANY, e -> toggleDebugOverlay());

    }

    @Override
    public void onUpdate(double tpf) {
    }

    public void toggleDebugOverlay() {
        debugOverlay.toggleVisibility();
    }
}
