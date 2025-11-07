package org.example.grpcstreamingclient.config;

import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer protobufJacksonModule() {
        return builder -> builder.modules(new ProtobufModule());
    }
}
