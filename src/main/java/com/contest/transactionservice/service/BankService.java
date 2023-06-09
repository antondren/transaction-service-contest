package com.contest.transactionservice.service;

import com.contest.transactionservice.dto.Account;
import com.contest.transactionservice.dto.AccountRepository;
import com.contest.transactionservice.dto.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final AccountRepository repository;

    public List<Account> processTransactionsAndGenerateReport(List<Transaction> transactions) {
        processTransactions(transactions);
        return getSortedAccounts();
    }

    private void processTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            Account debitAccount = repository.getAccount(transaction.debitAccount());
            Account creditAccount = repository.getAccount(transaction.creditAccount());

            updateDebitAccount(debitAccount, transaction.amount());
            updateCreditAccount(creditAccount, transaction.amount());

            repository.saveAccountInformation(debitAccount);
            repository.saveAccountInformation(creditAccount);
        }
    }

    private List<Account> getSortedAccounts() {
        List<Account> sortedAccounts = repository.getAllAccounts();
        sortedAccounts.sort(Comparator.comparing(Account::getAccountNumber));
        return sortedAccounts;
    }

    private void updateDebitAccount(Account debitAccount, BigDecimal amount) {
        debitAccount.setDebitCount(debitAccount.getDebitCount() + 1);
        debitAccount.setBalance(debitAccount.getBalance().subtract(amount));
    }

    private void updateCreditAccount(Account creditAccount, BigDecimal amount) {
        creditAccount.setCreditCount(creditAccount.getCreditCount() + 1);
        creditAccount.setBalance(creditAccount.getBalance().add(amount));
    }
}
