package com.contest.transactionservice.service;

import com.contest.transactionservice.dto.Account;
import com.contest.transactionservice.dto.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    private final BankService bankService = new BankService(new AccountRepositoryImpl());

    @Test
    void testProcessingOfTransactionsAndReportGenerating() {
        List<Account> report = bankService.processTransactionsAndGenerateReport(createTransactionsFromJson());
        List<Account> accountsForCheck = createAccountsForCheck();
        for (int i = 0; i < accountsForCheck.size(); i++) {
            Account reportAcc = report.get(i);
            Account checkAcc = accountsForCheck.get(i);
            assertEquals(reportAcc.getAccountNumber(), checkAcc.getAccountNumber());
            assertEquals(reportAcc.getBalance(), checkAcc.getBalance());
            assertEquals(reportAcc.getCreditCount(), checkAcc.getCreditCount());
            assertEquals(reportAcc.getDebitCount(), checkAcc.getDebitCount());
        }
    }

    private List<Account> createAccountsForCheck() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(
                new Account(
                        "06105023389842834748547303",
                        0,
                        1,
                        BigDecimal.valueOf(10.90))
        );
        accounts.add(
                new Account(
                        "31074318698137062235845814",
                        1,
                        0,
                        BigDecimal.valueOf(-200.90))
        );
        accounts.add(
                new Account(
                        "32309111922661937852684864",
                        1,
                        1,
                        BigDecimal.valueOf(39.20))
        );
        accounts.add(
                new Account(
                        "66105036543749403346524547",
                        1,
                        1,
                        BigDecimal.valueOf(150.80))
        );

        return accounts;
    }

    private List<Transaction> createTransactionsFromJson() {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(
                new Transaction(
                        "32309111922661937852684864",
                        "06105023389842834748547303",
                        BigDecimal.valueOf(10.90))
        );
        transactions.add(
                new Transaction(
                        "31074318698137062235845814",
                        "66105036543749403346524547",
                        BigDecimal.valueOf(200.90))
        );
        transactions.add(
                new Transaction(
                        "66105036543749403346524547",
                        "32309111922661937852684864",
                        BigDecimal.valueOf(50.10))
        );

        return transactions;
    }

}