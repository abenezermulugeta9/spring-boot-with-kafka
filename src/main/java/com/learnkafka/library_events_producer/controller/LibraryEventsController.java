package com.learnkafka.library_events_producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.library_events_producer.domain.LibraryEvent;
import com.learnkafka.library_events_producer.producer.LibraryEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/v1/libraryEvent")
    public ResponseEntity<LibraryEvent> sendLibraryEvent(@RequestBody LibraryEvent libraryEvent)
            throws JsonProcessingException, ExecutionException, InterruptedException {

        log.info(">>> Start sending libraryEvent message to kafka - LibraryEvent:  {} ", libraryEvent);

        // Invoke kafka producer service and send the message using the following methods
        libraryEventsProducer.sendLibraryEventAsyncUsingProducerRecord(libraryEvent);
//        libraryEventsProducer.sendLibraryEventAsync(libraryEvent);
//        libraryEventsProducer.sendLibraryEventSync(libraryEvent);

        log.info(">>> End sending libraryEvent message to kafka");

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
