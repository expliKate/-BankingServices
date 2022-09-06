package org.example;

import java.time.*;
import java.time.temporal.ChronoUnit;

//Business rules:
// 1. Less than 3 transactions were deducted from the same account in the last 5 minutes.
// 2. Transactions can only be deducted if the account of a customer is active.
// 3. The amount of the transaction does not exceed the current customer balance.

public class BankAccount {
    private double balance;
    private String status; // I kind of want an enum for this, but keeping it as simple as possible for this task.
    private LocalDateTime[] recentTransactions = new LocalDateTime[3];
    // In a fully-functioning banking app, all transactions would be stored in a DB. Storing the 3 most recent like
    // this seems like a relatively fast and simple way to have access to the relevant transaction history when
    // determining if a current transaction is permissible.

    public BankAccount(){
        status = "ACTIVE";

        recentTransactions[0] = LocalDateTime.of(2001, Month.JANUARY, 1, 1,00,0, 0);
        recentTransactions[1] = LocalDateTime.of(2001, Month.JANUARY, 1, 1,00,0, 0);
        recentTransactions[2] = LocalDateTime.of(2001, Month.JANUARY, 1, 1,00,0, 0);

        balance = 0.00;
    }

    public BankAccount(double initBalance){
        this();
        balance = initBalance;
    }

    public double getBalance(){
        return balance;
    }

    public boolean deduct(double transactionAmt) {
        // Check if deduction is permitted.
        // (This could be one line of code, but I'm breaking it down for readability.
        Duration oldestTransaction_asDur = Duration.between(recentTransactions[0],LocalDateTime.now());
        long oldestTransaction = oldestTransaction_asDur.getSeconds();
        long fiveMinutes = 300;
        if(oldestTransaction < fiveMinutes || status == "INACTIVE" || balance < transactionAmt){
            return false;
        }

        // Record transaction.
        recentTransactions[0] = recentTransactions[1];
        recentTransactions[1] = recentTransactions[2];
        recentTransactions[2] = LocalDateTime.now();

        // Update balance.
        balance -= transactionAmt;

        // Report whether deduction was successful.
        return true;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }
}
