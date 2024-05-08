module FlowFieldAI {
    requires common;
    requires CommonAI;
    requires com.almasb.fxgl.all;

    exports flowfield to com.almasb.fxgl.core;
    provides common.ai.AI_SPI with flowfield.FlowFieldFactory;
}