package common.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum ServiceRegistry {
    INSTANCE;

    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void registerService(Class<T> serviceType, T serviceInstance) {
        services.put(serviceType, serviceInstance);
    }

    public static <T> Optional<T> getService(Class<T> serviceType) {
        return Optional.ofNullable(serviceType.cast(services.get(serviceType)));
    }
}
