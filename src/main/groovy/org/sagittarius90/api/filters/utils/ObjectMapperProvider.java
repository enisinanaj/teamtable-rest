package org.sagittarius90.api.filters.utils;

import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperProvider { //implements ContextResolver<ObjectMapper>{

    /*private final ObjectMapper defaultObjectMapper;

    public ObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
        return objectMapper;
    }*/
}
