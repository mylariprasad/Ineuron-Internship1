package ATM;
class Transaction {
    private final double amount;
    private final String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Description: " + description;
    }
}
