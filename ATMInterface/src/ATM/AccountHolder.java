package ATM;

import java.util.ArrayList;
import java.util.List;

class AccountHolder {
    private final String userId;
    private final String userPin;
    private double balance;
    private final List<Transaction> transactionHistory;

    public AccountHolder(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction(-amount, "Withdrawal"));
            return true;
        }
        return false;
    }
    
    public boolean deduct(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction(amount, "Deposit"));
        }
    }
    
    public void add(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}
