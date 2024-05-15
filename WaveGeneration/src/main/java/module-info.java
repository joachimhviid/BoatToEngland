module WaveGeneration {
    exports waveGenerationSystem;
    requires common;
    requires com.almasb.fxgl.all;

    provides common.services.WaveSPI with waveGenerationSystem.WaveGenerationFactory;
}