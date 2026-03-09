package com.example.transactiondemo.controller;

import com.example.transactiondemo.dto.TransferRequest;
import com.example.transactiondemo.entity.Account;
import com.example.transactiondemo.entity.AuditLog;
import com.example.transactiondemo.repository.AccountRepository;
import com.example.transactiondemo.repository.AuditLogRepository;
import com.example.transactiondemo.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bank")
@RequiredArgsConstructor
public class TransferController {

    private final BankService bankService;
    private final AccountRepository accountRepository;
    private final AuditLogRepository auditLogRepository;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(
            @RequestParam String accountNumber,
            @RequestParam BigDecimal initialBalance) {
        return ResponseEntity.ok(bankService.createAccount(accountNumber, initialBalance));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> performTransfer(@RequestBody TransferRequest request) {
        try {
            bankService.transferMoney(
                    request.getFromAccount(),
                    request.getToAccount(),
                    request.getAmount(),
                    request.isSimulateError());
            return ResponseEntity.ok("Transfer successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/audit-logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs() {
        return ResponseEntity.ok(auditLogRepository.findAll());
    }
}
