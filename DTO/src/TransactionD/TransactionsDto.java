package TransactionD;

import transaction.Transaction;

import java.util.List;

public class TransactionsDto {
    private final List<Transaction> transactionList;
    private final int sumOfCycleTran;

    public int getSumOfCycleTran() {
        return sumOfCycleTran;
    }

    public TransactionsDto(List<Transaction> transactionList, int sumOfCycleTran) {
        this.transactionList = transactionList;
        this.sumOfCycleTran = sumOfCycleTran;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public String toString() {
        return "transactionList: \n" + transactionList;
    }
}
