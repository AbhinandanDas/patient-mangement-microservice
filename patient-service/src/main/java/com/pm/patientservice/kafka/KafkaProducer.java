package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        // Create a PatientEvent object with the patient's details
        PatientEvent event = PatientEvent.newBuilder()
                        .setPatientId(patient.getId().toString())
                        .setName(patient.getName())
                        .setEmail(patient.getEmail())
                        .setEventType("PATIENT_CREATED")
                        .build();

        // Log the event before sending
        try {
            kafkaTemplate.send("patient",event.toByteArray());
        }catch(Exception e) {
            log.error("Error sending PATIENT_CREATED event to Kafka: {}", e.getMessage());
        }

    }
}
