package transaction;

import java.util.LinkedList;
import java.util.List;


public class Transactions {
    private List<Transaction> transactionList;
    private int sumOfCycleTran ;

    public Transactions() {
        this.transactionList = new LinkedList<>();
    }

    public int getSumOfCycleTran() {
        return sumOfCycleTran;
    }

    public void setSumOfCycleTran(int sumOfCycleTran) {
        this.sumOfCycleTran = sumOfCycleTran;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "transactionList: \n" + transactionList;
    }
}
