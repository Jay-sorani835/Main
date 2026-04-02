package com.example.emailservice.repository;

import com.example.emailservice.entity.OutboundMessage;
import com.example.emailservice.entity.OutboundMessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundMessageRepository extends MongoRepository<OutboundMessage, String> {

    boolean existsBySubjectAndFilenameAndStatus(String subject, String filename, OutboundMessageStatus status);

    List<OutboundMessage> findByStatusIn(List<OutboundMessageStatus> statuses);
}
