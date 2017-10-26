package org.sagittarius90.api.filters.utils;

//import org.eclipse.persistence.jaxb.JAXBContextProperties;
//import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

import javax.ws.rs.ext.ContextResolver;
import java.util.HashMap;
import java.util.Map;

public class JsonMoxyConfigurationContextResolver { // implements ContextResolver<MoxyJsonConfig> {

    /*private final MoxyJsonConfig config;

    public JsonMoxyConfigurationContextResolver() {
        final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");

        config = new MoxyJsonConfig()
                .setNamespacePrefixMapper(namespacePrefixMapper)
                .setNamespaceSeparator(':')
                .setAttributePrefix("")
                //.setValueWrapper("value")
                //.property(JAXBContextProperties.JSON_WRAPPER_AS_ARRAY_NAME, true)
                .setFormattedOutput(true)
                .setIncludeRoot(true)
                .setMarshalEmptyCollections(true);
    }

    @Override
    public MoxyJsonConfig getContext(Class<?> objectType) {
        return config;
    }*/
}
