package command;

import command.Enum.CommandTypes;
import command.Enum.TradingTypes;

import java.util.Comparator;

public class Command {


    private TradingTypes tradingT;
    private CommandTypes commandT;
    private String symbol;
    private int price;
    private int stockAmount;
    private String commandDate;


    public Command(TradingTypes type, CommandTypes buyOrSellCommand, String symbol, int price, int stockAmount, String commandDate) {
        this.tradingT = type;
        this.commandT = buyOrSellCommand;
        this.symbol = symbol;
        this.price = price;
        this.stockAmount = stockAmount;
        this.commandDate = commandDate;
    }

    public TradingTypes getTradingT() {
        return tradingT;
    }

    public void setTradingT(TradingTypes tradingT) {
        this.tradingT = tradingT;
    }

    public CommandTypes getCommandT() {
        return commandT;
    }

    public void setCommandT(CommandTypes commandT) {
        this.commandT = commandT;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getCommandDate() {
        return commandDate;
    }

    public void setCommandDate(String commandDate) {
        this.commandDate = commandDate;
    }

    public static class BuyComparator implements Comparator<Command> {
        @Override
        public int compare(Command o1, Command o2) {
            if (o1.getPrice() < o2.getPrice()) {
                return 1;
            } else if (o1.getPrice() == o2.getPrice()) {
                return o1.getCommandDate().compareTo(o2.getCommandDate());
            }
            return -1;
        }
    }

    public static class SellComparator implements Comparator<Command> {

        @Override
        public int compare(Command o1, Command o2) {
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            } else if (o1.getPrice() == o2.getPrice()) {
                return o1.getCommandDate().compareTo(o2.getCommandDate());
            }
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Command -" +
                "trading type : " + tradingT +
                ", command type : " + commandT +
                ", symbol : '" + symbol + '\'' +
                ", price : " + price +
                ", stock amount : " + stockAmount +
                ", command date : '" + commandDate + '\n';
    }

}
