module wavegeneration {
    exports waveGenerationSystem;
    requires common;
    requires com.almasb.fxgl.all;
    requires common.enemy;

    provides common.services.WaveSPI with waveGenerationSystem.WaveGenerationFactory;
}