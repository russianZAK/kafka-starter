package dev.nikita.kafka.reply.starter.config;

import dev.nikita.kafka.reply.starter.listener.KafkaSyncListener;
import dev.nikita.kafka.reply.starter.properties.KafkaSyncProperties;
import dev.nikita.kafka.reply.starter.template.KafkaSyncTemplate;
import java.util.Collections;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableConfigurationProperties(KafkaSyncProperties.class)
public class KafkaSyncAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public KafkaSyncProperties kafkaSyncProperties(){
        return new KafkaSyncProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "kafka.sync", name = "bootstrap-servers")
    public KafkaAdmin kafkaAdmin(KafkaSyncProperties kafkaSyncProperties) {
        return new KafkaAdmin(Collections.singletonMap("bootstrap.servers", kafkaSyncProperties.getBootstrapServers()));
    }

    @Bean
    @ConditionalOnBean(KafkaAdmin.class)
    @ConditionalOnMissingBean(AdminClient.class)
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    @ConditionalOnBean(AdminClient.class)
    @ConditionalOnMissingBean(KafkaAdmin.NewTopics.class)
    public KafkaAdmin.NewTopics createTopics(KafkaSyncProperties kafkaSyncProperties) {
        return new KafkaAdmin.NewTopics(
            new NewTopic(kafkaSyncProperties.getRequestTopic(), 1, (short) 1),
            new NewTopic(kafkaSyncProperties.getResponseTopic(), 1, (short) 1)
        );
    }

    @Bean
    @ConditionalOnBean(KafkaSyncTemplate.class)
    @ConditionalOnMissingBean(KafkaSyncListener.class)
    public <K, V> KafkaSyncListener<K, V> kafkaSyncListener(KafkaTemplate<K, V> kafkaTemplate, KafkaSyncProperties kafkaSyncProperties){
        return new KafkaSyncListener<>(kafkaTemplate, kafkaSyncProperties);
    }
}