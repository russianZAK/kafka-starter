package dev.nikita.kafka.reply.starter.template;

import dev.nikita.kafka.reply.starter.properties.KafkaSyncProperties;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSyncTemplate<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;
    private final Map<K, Exchanger<V>> exchangerMap = new ConcurrentHashMap<>();
    private final Long timeout;
    private final String requestTopic;

    public KafkaSyncTemplate(KafkaTemplate<K, V> kafkaTemplate, KafkaSyncProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.timeout = properties.getTimeout();
        this.requestTopic = properties.getRequestTopic();
    }

    public V exchange(K key, V message) throws InterruptedException, TimeoutException {
        Exchanger<V> exchanger = new Exchanger<>();
        exchangerMap.put(key, exchanger);

        kafkaTemplate.send(requestTopic, key, message);

        try {
            return exchanger.exchange(null, timeout, TimeUnit.MILLISECONDS);
        } finally {
            exchangerMap.remove(key);
        }
    }

    @KafkaListener(topics = "${kafka.sync.response-topic}", groupId = "${kafka.sync.response-group-id}")
    public void receive(ConsumerRecord<K, V> record) {
        Exchanger<V> exchanger = exchangerMap.get(record.key());
        if (exchanger != null) {
            try {
                exchanger.exchange(record.value());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
