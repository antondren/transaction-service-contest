package com.contest.transactionservice.service;

import com.contest.transactionservice.dto.Account;
import com.contest.transactionservice.dto.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankTransactionController {
    private final BankService bankService;

    @PostMapping("/transactions/report")
    public ResponseEntity<List<Account>> processTransactionReport(@RequestBody List<Transaction> transactions) {
        return ResponseEntity.ok(bankService.processTransactionsAndGenerateReport(transactions));
    }
}
