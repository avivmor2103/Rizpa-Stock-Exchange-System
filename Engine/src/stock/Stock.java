package stock;

import command.Command;
import command.Commands;
import command.Enum.TradingTypes;
import scheme.genreteClasses.RseStock;
import transaction.Transaction;
import transaction.Transactions;

import java.util.Collections;

public class Stock {

    private String Symbol;
    private String CompanyName;
    private int stockPrice;
    private int NumberOfTransactions;
    private int TransactionsCycle;
    private Transactions transactions;
    private Commands commands;

    public Stock() {
    }

    public Stock(Stock other) {
        this.Symbol = other.Symbol;
        this.stockPrice = other.stockPrice;
        this.NumberOfTransactions = other.NumberOfTransactions;
        this.TransactionsCycle = other.TransactionsCycle;
        this.CompanyName = other.CompanyName;
        this.transactions = other.transactions;
        this.commands = other.commands;
    }

    public Stock(String symbol, String companyName, int stockPrice, int numberOfTransactions, int transactionsCycle, Transactions transactions, Commands commands) {
        this.transactions = transactions;
        Symbol = symbol;
        CompanyName = companyName;
        this.stockPrice = stockPrice;
        NumberOfTransactions = numberOfTransactions;
        TransactionsCycle = transactionsCycle;
        this.commands = commands;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getNumberOfTransactions() {
        return NumberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        NumberOfTransactions = numberOfTransactions;
    }

    public int getTransactionsCycle() {
        return TransactionsCycle;
    }

    public void setTransactionsCycle(int transactionsCycle) {
        TransactionsCycle = transactionsCycle;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public Commands getCommands() {
        return commands;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public Stock createSingleStock(RseStock xmlStock) {
        return new Stock(xmlStock.getRseSymbol(), xmlStock.getRseCompanyName(), xmlStock.getRsePrice(), 0, 0, new Transactions(), new Commands());
    }

    public void putNewCommandInCommands(Command command, int tradingOrder) {
        switch (tradingOrder) {
            case 1:
                commands.getCommandsBuyList().add(command);
                commands.SortBuyList();
                break;
            case 2:
                commands.getCommandsSellList().add(command);
                commands.SortSellList();
                break;
        }
    }

    public void delCommandFromCommands(Command currCommand) {
        if (currCommand.getTradingT() == TradingTypes.BUY) {
            commands.getCommandsBuyList().remove(currCommand);
            commands.SortBuyList();
        } else {
            commands.getCommandsSellList().remove(currCommand);
            commands.SortSellList();
        }
    }


    public void putNewTransactionInTransactions(Transaction newTransaction) {
        transactions.getTransactionList().add(newTransaction);
        setNumberOfTransactions(getNumberOfTransactions() + 1);
        setTransactionsCycle(getTransactionsCycle() + newTransaction.getTotalValue());
        setStockPrice(newTransaction.getPrice());
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
