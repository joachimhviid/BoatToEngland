import common.services.IGamePluginService;

module common {
  requires com.almasb.fxgl.all;

  uses IGamePluginService;
  exports common.data;
  exports common.services;
  exports common.events;
}