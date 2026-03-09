package com.example.transactiondemo.service;

import com.example.transactiondemo.entity.AuditLog;
import com.example.transactiondemo.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransaction(String action, String status) {
        log.info("Saving audit log in a new transaction for action: {}", action);
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .status(status)
                .build();
        auditLogRepository.save(auditLog);
    }
}
