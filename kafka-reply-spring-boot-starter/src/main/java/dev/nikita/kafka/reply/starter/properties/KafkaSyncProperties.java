package dev.nikita.kafka.reply.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.sync")
public class KafkaSyncProperties {

    /**
     * Topic for sending requests.
     */
    private String requestTopic;

    /**
     * Topic for receiving responses.
     */
    private String responseTopic;

    /**
     * Timeout for Kafka operations in milliseconds.
     */
    private Long timeout;

    /**
     * Bootstrap server for Kafka.
     */
    private String bootstrapServers;
    /**
     * Request group id.
     */
    private String requestGroupId;
    /**
     * Response group id.
     */
    private String responseGroupId;

    public String getRequestTopic() {
        return requestTopic;
    }

    public void setRequestTopic(String requestTopic) {
        this.requestTopic = requestTopic;
    }

    public String getResponseTopic() {
        return responseTopic;
    }

    public void setResponseTopic(String responseTopic) {
        this.responseTopic = responseTopic;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getRequestGroupId() {
        return requestGroupId;
    }

    public void setRequestGroupId(String requestGroupId) {
        this.requestGroupId = requestGroupId;
    }

    public String getResponseGroupId() {
        return responseGroupId;
    }

    public void setResponseGroupId(String responseGroupId) {
        this.responseGroupId = responseGroupId;
    }
}
