package com.example.transactiondemo.service;

import com.example.transactiondemo.entity.Account;
import com.example.transactiondemo.entity.AuditLog;
import com.example.transactiondemo.repository.AccountRepository;
import com.example.transactiondemo.repository.AuditLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class BankServiceIntegrationTest {

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
        auditLogRepository.deleteAll();

        // Create test accounts
        bankService.createAccount("ACC100", new BigDecimal("1000.00"));
        bankService.createAccount("ACC200", new BigDecimal("500.00"));
    }

    @Test
    void testSuccessfulTransfer() {
        // Act: Transfer 200 from ACC100 to ACC200
        bankService.transferMoney("ACC100", "ACC200", new BigDecimal("200.00"), false);

        // Assert: Balances are updated
        Account fromAccount = accountRepository.findByAccountNumber("ACC100").orElseThrow();
        Account toAccount = accountRepository.findByAccountNumber("ACC200").orElseThrow();

        // Account balances should be updated
        assertThat(fromAccount.getBalance()).isEqualByComparingTo("800.00");
        assertThat(toAccount.getBalance()).isEqualByComparingTo("700.00");

        // Audit log should be created
        List<AuditLog> logs = auditLogRepository.findAll();
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).getAction()).contains("Transfer 200.00 from ACC100 to ACC200");
    }

    @Test
    void testFailedTransferRollsBackBalanceButKeepsAuditLog() {
        // Act: Transfer 200 from ACC100 to ACC200, but SIMULATE ERROR!
        Throwable thrown = catchThrowable(() -> {
            bankService.transferMoney("ACC100", "ACC200", new BigDecimal("200.00"), true);
        });

        // Assert: Exception was thrown
        assertThat(thrown).isInstanceOf(RuntimeException.class)
                .hasMessage("Simulated exception to trigger rollback");

        // Assert: Balances SHOULD NOT CHANGE (Rolled back by @Transactional REQUIRED)
        Account fromAccount = accountRepository.findByAccountNumber("ACC100").orElseThrow();
        Account toAccount = accountRepository.findByAccountNumber("ACC200").orElseThrow();

        assertThat(fromAccount.getBalance()).isEqualByComparingTo("1000.00");
        assertThat(toAccount.getBalance()).isEqualByComparingTo("500.00");

        // Assert: Audit log SHOULD EXIST (Committed independently by @Transactional
        // REQUIRES_NEW)
        List<AuditLog> logs = auditLogRepository.findAll();
        assertThat(logs).hasSize(1);
        assertThat(logs.get(0).getAction()).contains("Transfer 200.00 from ACC100 to ACC200");
        assertThat(logs.get(0).getStatus()).isEqualTo("PENDING");
    }
}
