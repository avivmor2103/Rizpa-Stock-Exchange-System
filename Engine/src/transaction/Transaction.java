package transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionDate;
    private int amount;
    private int price;
    private int totalValue;

    public Transaction(String transactionDate, int amount, int price, int totalValue) {
        this.transactionDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        this.amount = amount;
        this.price = price;
        this.totalValue = totalValue;
    }

    public Transaction() {
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String toString() {
        return "The transactions - " +
                "transactionDate:" + transactionDate + '\'' +
                ", amount:" + amount +
                ", price:" + price +
                ", totalValue:" + totalValue + '\n';
    }
}
