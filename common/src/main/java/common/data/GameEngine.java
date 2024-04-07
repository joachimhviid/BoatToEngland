package common.data;

import common.services.IGamePluginService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class GameEngine {
    public GameEngine() {
    }

    public Collection<? extends IGamePluginService> getPluginServices() {
        System.out.println("Loading plugins");
        ServiceLoader<IGamePluginService> loader = ServiceLoader.load(IGamePluginService.class);
        loader.forEach(service -> System.out.println("Found service: " + service.getClass().getName()));
        return loader.stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
