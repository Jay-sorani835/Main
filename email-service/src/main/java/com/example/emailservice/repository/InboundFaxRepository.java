package com.example.emailservice.repository;

import com.example.emailservice.entity.InboundFax;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundFaxRepository extends MongoRepository<InboundFax, String> {

    /** Idempotency check — same email + same file never stored twice */
    boolean existsByMessageIdAndFilename(String messageId, String filename);

    List<InboundFax> findByFaxNumberContainingIgnoreCase(String faxNumber);

    List<InboundFax> findBySenderEmail(String senderEmail);
}
