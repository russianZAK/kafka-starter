package by.russianzak.kafkatask.kafka;

import by.russianzak.kafkatask.event.ProductCreateEvent;
import dev.nikita.kafka.reply.starter.properties.KafkaSyncProperties;
import dev.nikita.kafka.reply.starter.template.KafkaSyncTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaSyncTemplate<String, ProductCreateEvent> kafkaSyncTemplate(KafkaTemplate<String, ProductCreateEvent> kafkaTemplate, KafkaSyncProperties kafkaSyncProperties) {
        return new KafkaSyncTemplate<>(kafkaTemplate, kafkaSyncProperties);
    }
}
