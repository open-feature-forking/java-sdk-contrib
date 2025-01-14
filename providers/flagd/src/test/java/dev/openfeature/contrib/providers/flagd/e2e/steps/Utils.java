package dev.openfeature.contrib.providers.flagd.e2e.steps;

import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.resolver.grpc.cache.CacheType;
import java.util.Objects;

public final class Utils {

    private Utils() {}

    public static Object convert(String value, String type) throws ClassNotFoundException {
        if (Objects.equals(value, "null")) return null;
        switch (type) {
            case "Boolean":
                return Boolean.parseBoolean(value);
            case "String":
                return value;
            case "Integer":
                return Integer.parseInt(value);
            case "Float":
                return Double.parseDouble(value);
            case "Long":
                return Long.parseLong(value);
            case "ResolverType":
                switch (value.toLowerCase()) {
                    case "in-process":
                        return Config.Resolver.IN_PROCESS;
                    case "rpc":
                        return Config.Resolver.RPC;
                    default:
                        throw new RuntimeException("Unknown resolver type: " + value);
                }
            case "CacheType":
                return CacheType.valueOf(value.toUpperCase()).getValue();
        }
        throw new RuntimeException("Unknown config type: " + type);
    }
}
