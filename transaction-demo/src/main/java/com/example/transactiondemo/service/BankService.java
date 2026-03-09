package com.example.transactiondemo.service;

import com.example.transactiondemo.entity.Account;
import com.example.transactiondemo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankService {

    private final AccountRepository accountRepository;
    private final AuditService auditService;

    public Account createAccount(String accountNumber, BigDecimal initialBalance) {
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(initialBalance)
                .build();
        return accountRepository.save(account);
    }

    @Transactional
    public void transferMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount,
            boolean simulateError) {
        log.info("Starting transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + toAccountNumber));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds in account: " + fromAccountNumber);
        }

        // Deduct from sender
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);

        // Add to receiver
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);

        // Log the transaction state. This uses REQUIRES_NEW, so it commits
        // independently.
        String actionMessage = String.format("Transfer %s from %s to %s", amount, fromAccountNumber, toAccountNumber);
        auditService.logTransaction(actionMessage, "PENDING");

        if (simulateError) {
            log.error("Simulated error occurred! Rolling back the main transaction.");
            throw new RuntimeException("Simulated exception to trigger rollback");
        }

        // Optionally, we could update the audit log to SUCCESS here, but for
        // demonstration,
        // leaving it PENDING and throwing an error shows the PENDING log is saved even
        // if the transfer fails.
        log.info("Transfer completed successfully.");
    }
}
