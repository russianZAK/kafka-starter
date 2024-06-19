package dev.nikita.kafka.reply.starter.listener;

import dev.nikita.kafka.reply.starter.properties.KafkaSyncProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSyncListener<K, V> {
    private final KafkaSyncProperties kafkaSyncProperties;

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaSyncListener(KafkaTemplate<K, V> kafkaTemplate, KafkaSyncProperties kafkaSyncProperties) {
        this.kafkaSyncProperties = kafkaSyncProperties;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka.sync.request-topic}", groupId = "${kafka.sync.request-group-id}")
    public void listen(ConsumerRecord<K, V> record) {
        kafkaTemplate.send(kafkaSyncProperties.getResponseTopic(), record.key(), record.value());
    }
}
