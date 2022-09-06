package org.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The primary purpose of this class is to demonstrate the capabilities of a BankAccount object.
 *
 */
public class TransactionHandler
{
    public static void main(String[] args)
    {
        HashMap<Integer, BankAccount> allAccounts = new HashMap<Integer, BankAccount>();
        int newestAcctID = 0;

        // Example data. Update to test different inputs.
        int account_id = 1;         // This obviously makes this logic very breakable, but my goal here is not a full,
                                    // functional banking app, just a demo of how transactions are handled.
        int initialBalance = 100;
        boolean active = true;

        // Using a collection lets someone test on as many transactions as desired.
        // Adjust the number of transactions and/or the amount to test how deductions are handled.
        // Right now, only the 4th deduction will be rejected, for occurring too soon after the previous 3.
        ArrayList<Double> transactionAmount = new ArrayList<Double>();
        transactionAmount.add(10.00);
        transactionAmount.add(15.00);
        transactionAmount.add(5.00);
        transactionAmount.add(6.00);

        System.out.println( "Welcome to banking services.");
        // Could collect input data from command line here instead of requiring changes to this class
        // to test additional data.

        // Make 2 bank accounts just to rough out the structure an actual banking app would need.
        BankAccount ba = new BankAccount(initialBalance);
        allAccounts.put(++newestAcctID, ba);

        BankAccount other_ba = new BankAccount(1000000);
        allAccounts.put(++newestAcctID, other_ba);

        // Attempt the transaction.
        BankAccount accountToDeductFrom = allAccounts.get(account_id);
        for(Double transAmt:transactionAmount){
            boolean success = accountToDeductFrom.deduct(transAmt);
            if(success){System.out.println("Amount deducted successfully: " + transAmt);}
            else{System.out.println("Deduction failed. Amount requested but rejected: " + transAmt);}
        }
    }
}
