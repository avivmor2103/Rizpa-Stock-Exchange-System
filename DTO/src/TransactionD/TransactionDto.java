package TransactionD;

import transaction.Transactions;

public class TransactionDto {

    private final String transactionDate;
    private final int amount;
    private final int price;
    private final int totalValue;


    public TransactionDto(String transactionDate, int amount, int price, int totalValue) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.price = price;
        this.totalValue = totalValue;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalValue() {
        return totalValue;
    }


    public String toString() {
        return "The transactions - " +
                "transactionDate:" + transactionDate + '\'' +
                ", amount:" + amount +
                ", price:" + price +
                ", totalValue:" + totalValue + '\n';
    }
}
