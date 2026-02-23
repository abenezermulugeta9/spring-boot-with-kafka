package com.learnkafka.library_events_producer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.library_events_producer.domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class LibraryEventsProducer {

    @Value("${spring.kafka.topic}")
    private String topicName;

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public LibraryEventsProducer(KafkaTemplate<Integer, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<SendResult<Integer, String>> sendLibraryEventAsync(LibraryEvent libraryEvent)
            throws JsonProcessingException {
        // Use var or kafka will throw exception when using wrapper classes as types
        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        // Async send (happens only once): briefly fetches topic metadata (blocking), then sends the message
        // asynchronously.
        // Returns CompletableFuture completed via whenComplete() callback for error/success handling.
        var completableFuture = this.kafkaTemplate.send(topicName, key, value);

        return completableFuture.whenComplete((sendResult, exception) -> {
            // If exception happened when sending the kafka message log the exception message
            if(exception != null) {
                handleException(exception);
            } else {
                handleSendingMessage(key, value, sendResult);
            }
        });
    }

    public SendResult<Integer, String> sendLibraryEvent(LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException {
        // Use var or kafka will throw exception when using wrapper classes as types
        var key = libraryEvent.libraryEventId();
        var value = objectMapper.writeValueAsString(libraryEvent);

        // Sync send (happens only once): : briefly fetches topic metadata (blocking), then blocks until the message is
        // confirmed sent.
        // Calls .get() to block and wait for the actual SendResult from Kafka.
        var sendResult = this.kafkaTemplate.send(topicName, key, value).get();

        handleSendingMessage(key, value, sendResult);

        return sendResult;
    }

    private static void handleException(Throwable exception) {
        log.error("Error sending kafka message: {} ", exception.getMessage(), exception);
    }

    private static void handleSendingMessage(Integer key, String value, SendResult<Integer, String> sendResult) {
        log.info("Kafka message was sent successfully for key: {} with value: {}, to partition: {} ",
                key, value, sendResult.getRecordMetadata().partition());
    }
}
