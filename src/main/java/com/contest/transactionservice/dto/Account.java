package com.contest.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    private final String accountNumber;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;
}
