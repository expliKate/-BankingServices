package org.example;

import org.example.BankAccount;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for bank account transaction app.
 */
public class BankAccountTest {
    /**
     * Test successfully creating an empty account.
     *
     */
    @Test
    public void canCreateEmptyAccount() {
        // Create an account.
        BankAccount ba = new BankAccount();
        assertTrue(ba != null && ba.getBalance() == 0.00);
    }

    /**
     * Test successfully creating an account with a balance.
     *
     */
    @Test
    public void whenCreateAccountWithBalance_thenNewAccountExistsWithCorrectBalance() {
        double newBalance = 234.56;
        BankAccount ba = new BankAccount(newBalance);
        assertTrue(ba != null && ba.getBalance() == newBalance);
    }

    /**
     * Test making the first transaction in 5 minutes
     *
     */
    @Test
    public void givenActiveGoodBalance_whenMakeFirstDeduction_thenDeductionIsAllowed() {
        double newBalance = 234.56;
        double transactionAmt = 12.34;

        BankAccount ba = new BankAccount(newBalance);

        assertTrue(ba.deduct(transactionAmt));
    }

    /**
     * Test making the fourth transaction in under 5 minutes.
     *
     */
    @Test
    public void givenActiveGoodBalance_whenMakeFourthDeduction_thenDeductionIsProhibited() {
        double newBalance = 234.56;
        double transactionAmt = 12.34;

        BankAccount ba = new BankAccount(newBalance);
        ba.deduct(transactionAmt);
        ba.deduct(transactionAmt);
        ba.deduct(transactionAmt);

        assertFalse(ba.deduct(transactionAmt));
    }

    /**
     * Test making a single recent transaction on an inactive account.
     *
     */
    @Test
    public void givenGoodBalanceNoRecentTransacts_whenInactive_thenDeductionIsProhibited() {
        double newBalance = 234.56;
        double transactionAmt = 12.34;

        BankAccount ba = new BankAccount(newBalance);
        ba.setStatus("INACTIVE");

        assertFalse(ba.deduct(transactionAmt));
    }

    /**
     * Test insufficient balance.
     *
     */
    @Test
    public void givenActiveNoRecentTransacts_whenTransactionLargerThanBalance_thenDeductionIsProhibited() {
        double newBalance = 12.34;
        double transactionAmt = 234.56;

        BankAccount ba = new BankAccount(newBalance);

        assertFalse(ba.deduct(transactionAmt));
    }


    // 1. SUCCESS all criteria met: 0 transactions in last 5 minutes, account active, balance > transaction amt
    // 2. SUCCESS all criteria met: 1 transaction in last 5 minutes, account active, balance > transaction amt
    // 3. SUCCESS all criteria met: 2 transactions in last 5 minutes, account active, balance > transaction amt
    // 4. FAIL 1 criterion not met: 3 transactions in last 5 minutes, account active, balance > transaction amt
    // 5. FAIL 1 criterion not met: 4 transactions in last 5 minutes, account active, balance > transaction amt
    // 6. FAIL 1 criterion not met: 1 transaction in last 5 minutes, account inactive, balance >= transaction amt
    // 7. FAIL 1 criterion not met: 1 transaction in last 5 minutes, account active, balance < transaction amt
    // 8. FAIL All 3 criteria not met: 3 transactions in last 5 minutes, account inactive, balance < transaction amt
    // 9. FAIL 2 criteria not met: 3 transactions in last 5 minutes, account active, balance < transaction amt
    // 10. FAIL 2 criteria not met: 3 transactions in last 5 minutes, account inactive, balance >= transaction amt
    // 11. FAIL 2 criteria not met: 1 transaction in last 5 minutes, account inactive, balance < transaction amt
    // 12. Can create account with balance.
    // 13. Can create empty account.
    // 14. Optional: can't create account with negative balance.
    // Thorough testing would also consider tests 6-11 for different numbers of recent transactions.
    // It would also be better to test different times (i.e. transactions at 1, 2, and 3 minutes ago vs
    // transactions at 1, 3, and 4 minutes).
    // I've omitted testing any functionality in TransactionHandler since BankAccount is my real solution;
    // TransactionHandler is just the front end (albeit barely a front end).
}
