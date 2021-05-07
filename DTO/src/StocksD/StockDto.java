package StocksD;

import command.Commands;
import transaction.Transactions;

public class StockDto {

    private final String Symbol;
    private final String CompanyName;
    private final int stockPrice;
    private final int NumberOfTransactions;
    private final int TransactionsCycle;
    private final Transactions transactions;
    private final Commands commands;

    public StockDto(String symbol, String companyName, int stockPrice, int numberOfTransactions, int transactionsCycle, Transactions transactions, Commands commands) {
        this.Symbol = symbol;
        this.CompanyName = companyName;
        this.stockPrice = stockPrice;
        this.NumberOfTransactions = numberOfTransactions;
        this.TransactionsCycle = transactionsCycle;
        this.transactions = transactions;
        this.commands = commands;
    }


    public Commands getCommands() {
        return commands;
    }

    public String getSymbol() {
        return Symbol;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public int getNumberOfTransactions() {
        return NumberOfTransactions;
    }

    public int getTransactionsCycle() {
        return TransactionsCycle;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "\n" +
                "Symbol='" + Symbol + '\'' +
                " ,CompanyName='" + CompanyName + '\'' +
                " ,stockPrice=" + stockPrice +
                " ,NumberOfTransactions=" + NumberOfTransactions +
                " ,TransactionsCycle=" + TransactionsCycle +
                ",\n" + transactions +
                ",\n" + commands +
                " \n";
    }
}
