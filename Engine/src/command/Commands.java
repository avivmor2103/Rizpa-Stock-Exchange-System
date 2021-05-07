package command;

import command.Enum.TradingTypes;
import stock.Stock;
import transaction.Transaction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Commands {

    private List<Command> commandsBuyList;
    private List<Command> commandsSellList;
    private int TotalCycleBuyCommand;
    private int TotalCycleSellCommand;

    public Commands() {
        commandsSellList = new LinkedList<>();
        commandsBuyList = new LinkedList<>();
        TotalCycleBuyCommand = 0;
        TotalCycleSellCommand = 0;
    }

    public int getTotalCycleBuyCommand() {
        return TotalCycleBuyCommand;
    }

    public void setTotalCycleBuyCommand(int totalCycleBuyCommand) {
        TotalCycleBuyCommand = totalCycleBuyCommand;
    }

    public int getTotalCycleSellCommand() {
        return TotalCycleSellCommand;
    }

    public void setTotalCycleSellCommand(int totalCycleSellCommand) {
        TotalCycleSellCommand = totalCycleSellCommand;
    }

    public List<Command> getCommandsBuyList() {
        return commandsBuyList;
    }

    public void setCommandsBuyList(List<Command> commandsBuyList) {
        this.commandsBuyList = commandsBuyList;
    }

    public List<Command> getCommandsSellList() {
        return commandsSellList;
    }

    public void setCommandsSellList(List<Command> commandsSellList) {
        this.commandsSellList = commandsSellList;
    }

    public void compareSellOrBuyLmt(int tradingType, Command currCommand, Stock currStock) {
        switch (tradingType) {
            case 1:
                createMatchForLmt(currStock.getCommands().commandsSellList, currCommand, currStock, tradingType);
                break;
            case 2:
                createMatchForLmt(currStock.getCommands().commandsBuyList, currCommand, currStock, tradingType);
                break;
        }
    }

    public void compareSellOrBuyMkt(int tradingType, Command currCommand, Stock currStock) {
        switch (tradingType) {
            case 1:
                createMatchForMkt(currStock.getCommands().commandsSellList, currCommand, currStock, tradingType);
                break;
            case 2:
                createMatchForMkt(currStock.getCommands().commandsBuyList, currCommand, currStock, tradingType);
                break;
        }
    }

    public void createMatchForMkt(List<Command> commandList, Command currCommand, Stock currStock, int tradingType) {
        Iterator<Command> iterator = commandList.iterator();
        if (commandList.isEmpty()) {
            currStock.putNewCommandInCommands(currCommand, tradingType);
        } else {
            while ((iterator.hasNext()) && (currCommand.getStockAmount() > 0)) {
                Command newCommand = iterator.next();

                creatTransaction(newCommand, currCommand, currStock);
                if (newCommand.getStockAmount() == 0) {
                    iterator.remove();
                }
            }
        }
        if (currCommand.getStockAmount() > 0) {
            currStock.putNewCommandInCommands(currCommand, tradingType);
        }
    }


    public void SortBuyList() {
        commandsBuyList.sort(new Command.BuyComparator());
    }

    public void SortSellList() {
        commandsSellList.sort(new Command.SellComparator());
    }

    public void createMatchForLmt(List<Command> commandList, Command currCommand, Stock currStock, int tradingType) {
        Iterator<Command> iterator = commandList.iterator();
        if (commandList.isEmpty()) {
            currStock.putNewCommandInCommands(currCommand, tradingType);
        } else {
            while ((iterator.hasNext()) && (currCommand.getStockAmount() > 0)) {
                Command newCommand = iterator.next();
                if ((newCommand.getPrice() <= currCommand.getPrice()) && tradingType == 1) {
                    creatTransaction(newCommand, currCommand, currStock);
                    if (newCommand.getStockAmount() == 0) {
                        iterator.remove();
                    }
                } else if ((newCommand.getPrice() >= currCommand.getPrice()) && tradingType == 2) {
                    creatTransaction(newCommand, currCommand, currStock);
                    if (newCommand.getStockAmount() == 0) {
                        iterator.remove();
                    }
                }
            }
            if (currCommand.getStockAmount() > 0) {
                currStock.putNewCommandInCommands(currCommand, tradingType);
            }
        }
    }

    public void creatTransaction(Command CounterOrder, Command currCommand, Stock currStock) {
        Transaction newTransaction = null;
        int finalStockAmount = CounterOrder.getStockAmount() - currCommand.getStockAmount();

        if (finalStockAmount >= 0) {
            newTransaction = new Transaction("", currCommand.getStockAmount(), CounterOrder.getPrice(), currCommand.getStockAmount() * CounterOrder.getPrice());
            currStock.putNewTransactionInTransactions(newTransaction);

            currStock.getTransactions().setSumOfCycleTran(currStock.getTransactions().getSumOfCycleTran() + (currCommand.getStockAmount() * CounterOrder.getPrice()));
            CounterOrder.setStockAmount(finalStockAmount);
            currCommand.setStockAmount(0);

        } else {
            newTransaction = new Transaction("", CounterOrder.getStockAmount(), CounterOrder.getPrice(), CounterOrder.getStockAmount() * CounterOrder.getPrice());
            currStock.putNewTransactionInTransactions(newTransaction);

            currStock.getTransactions().setSumOfCycleTran(currStock.getTransactions().getSumOfCycleTran() + (CounterOrder.getStockAmount() * CounterOrder.getPrice()));
            CounterOrder.setStockAmount(0);
            currCommand.setStockAmount(finalStockAmount * (-1));
        }

    }

    @Override
    public String toString() {
        return "The list of commands are: " +
                "commandsBuyList: " + commandsBuyList +
                "\n commandsSellList=" + commandsSellList;
    }
}


