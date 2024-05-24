import common.ai.AiSpi;

module flowfield.ai {
    requires common;
    requires common.ai;
    requires com.almasb.fxgl.all;

    exports flowfield to com.almasb.fxgl.core;
    provides AiSpi with flowfield.FlowFieldFactory;
}