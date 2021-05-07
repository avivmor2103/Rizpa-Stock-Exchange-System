package menu;

import Exceptions.*;
import StocksD.StockDto;
import StocksD.StocksDto;
import command.Command;
import command.Commands;
import command.Enum.CommandTypes;
import command.Enum.TradingTypes;
import menu.jaxb.ImportInfo;
import scheme.genreteClasses.RizpaStockExchangeDescriptor;
import stock.Stock;
import stock.Stocks;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MenuEngineImpl implements MenuEngine {

    private StocksDto stocksDto;
    private StockDto stockDto;
    private Stocks stocks;
    private Commands commands;

    public MenuEngineImpl() {

        this.stocks = new Stocks();
        this.commands = new Commands();
    }

    @Override
    public void readSystemInfoFile(String path) throws SymbolIsAlreadyExists, CompanyNameIsAlreadyExists, FileNotFoundException, JAXBException { //method number 1 - יש לבדוק תקינות XML
        {
            ImportInfo importInfo = new ImportInfo();
            RizpaStockExchangeDescriptor resXml = importInfo.unmarshall(path);
            Map<String, Stock> mapOfStocks = stocks.createMapOfStocks(resXml.getRseStocks().getRseStock());
            this.stocksDto = new StocksDto(mapOfStocks);
        }
    }

    @Override
    public StocksDto getAllStocks() {
        return stocksDto;
    }

    @Override
    public StockDto showCurrStock(String symbol) throws SymbolNotExists {
        Stock stock = stocks.checkSymbolInAllStocks(symbol);
        this.stockDto = new StockDto(stock.getSymbol(), stock.getCompanyName(), stock.getStockPrice(), stock.getNumberOfTransactions(), stock.getTransactionsCycle(), stock.getTransactions(), stock.getCommands());
        return this.stockDto;
    }


    @Override
    public void tradingExecution(int tradingType, String symbol, int amount, int price, int commandType) throws SymbolNotExists, AmoutIsLowerThenZero, TradingTypeError {

        Stock currStock = stocks.checkSymbolInAllStocks(symbol);
        switch(commandType)
        {
            case 1:
                Command commandForLmt = new Command(typeCommandVerify(tradingType), commandTypeVerify(commandType), symbol.toUpperCase(), price, amount, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
                commands.compareSellOrBuyLmt(tradingType, commandForLmt, currStock);
                break;
            case 2:
                Command commandForMkt = new Command(typeCommandVerify(tradingType), commandTypeVerify(commandType), symbol.toUpperCase(), currStock.getStockPrice() , amount, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
                commands.compareSellOrBuyMkt(tradingType, commandForMkt, currStock);
                break;
        }

    }

    @Override
    public StockDto showListOfCommands(String symbol) throws SymbolNotExists {
        Stock stock = stocks.checkSymbolInAllStocks(symbol);
        this.stockDto = new StockDto(stock.getSymbol(), stock.getCompanyName(), stock.getStockPrice(), stock.getNumberOfTransactions(), stock.getTransactionsCycle(), stock.getTransactions(), stock.getCommands());
        updateInfoCycle();
        return this.stockDto;
    }

    public void updateInfoCycle()
    {
        stockDto.getCommands().setTotalCycleSellCommand(calculateCycle(stockDto.getCommands().getCommandsSellList()));
        stockDto.getCommands().setTotalCycleBuyCommand( calculateCycle(stockDto.getCommands().getCommandsBuyList()));
    }

    public int calculateCycle(List<Command> commands)
    {
        int sumOfCycle = 0 ;
        for(Command command : commands)
        {
            sumOfCycle = sumOfCycle + (command.getPrice() * command.getStockAmount());
        }
        return sumOfCycle ;
    }

    public CommandTypes commandTypeVerify(int commandType) {

        switch (commandType) {
            case (1):
                return CommandTypes.LMT;
            case (2):
                return CommandTypes.MKT;
            default:
                return null;
        }
    }

    public TradingTypes typeCommandVerify(int tradingOrder) {

        switch (tradingOrder) {
            case (1):
                return TradingTypes.BUY;
            case (2):
                return TradingTypes.SELL;
            default:
                return null ;
        }
    }


    public void CheckIfNumTradingOk(int tradingOrder) throws TradingTypeError {

        if (tradingOrder != 1 && tradingOrder != 2) {
            String message = String.format("the current number of trading order: %d is unavailable", tradingOrder);
            throw new TradingTypeError(message);
        }

    }

    public void checkAmount(int amount) throws AmoutIsLowerThenZero {
        if (amount <= 0) {
            String message = String.format("the current amount: %d is lower then zero", amount);
            throw new AmoutIsLowerThenZero(message);
        }
    }

    public void checkPrice(int price) throws AmoutIsLowerThenZero {
        if (price <= 0) {
            String message = String.format("the current price: %d is lower then zero", price);
            throw new AmoutIsLowerThenZero(message);
        }
    }
}
