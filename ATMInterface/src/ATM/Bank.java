package ATM;
import java.util.*;

class Bank {
    private final List<AccountHolder> accountHolders;

    public Bank() {
        this.accountHolders = new ArrayList<>();
    }

    public void addAccountHolder(AccountHolder accountHolder) {
        accountHolders.add(accountHolder);
    }

    public AccountHolder validateAccountHolder(String userId, String userPin) {
        for (AccountHolder accountHolder : accountHolders) {
            if (accountHolder.getUserId().equals(userId) && accountHolder.getUserPin().equals(userPin)) {
                return accountHolder;
            }
        }
        return null;
    }

    public boolean transferFunds(String senderUserId, String recipientUserId, double amount) {
        AccountHolder sender = findAccountHolder(senderUserId);
        AccountHolder recipient = findAccountHolder(recipientUserId);

        if (sender != null && recipient != null && sender.deduct(amount)) {
            recipient.add(amount);
            sender.addTransaction(new Transaction(-amount, "Transfered to " + recipientUserId));
            recipient.addTransaction(new Transaction(amount, "Transfered by " + senderUserId));
            return true;
        }
        return false;
    }

    private AccountHolder findAccountHolder(String userId) {
        for (AccountHolder accountHolder : accountHolders) {
            if (accountHolder.getUserId().equals(userId)) {
                return accountHolder;
            }
        }
        return null;
    }
}
