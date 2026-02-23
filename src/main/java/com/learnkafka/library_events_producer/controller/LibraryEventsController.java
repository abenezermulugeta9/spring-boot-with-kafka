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

@RestController
@Slf4j
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/v1/libraryEvent")
    public ResponseEntity<LibraryEvent> sendLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {

        log.info(">>> Start sending libraryEvent message to kafka - LibraryEvent:  {} ", libraryEvent);

        // Invoke kafka producer service and send the message
        libraryEventsProducer.sendLibraryEvent(libraryEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
