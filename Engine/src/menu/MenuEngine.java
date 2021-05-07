package menu;

import Exceptions.*;
import StocksD.StockDto;
import StocksD.StocksDto;
import command.Enum.TradingTypes;
import stock.Stock;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Collection;

public interface MenuEngine {

    public void readSystemInfoFile(String path) throws SymbolIsAlreadyExists, CompanyNameIsAlreadyExists, FileNotFoundException, JAXBException;

    public StocksDto getAllStocks();

    public StockDto showCurrStock(String symbol) throws SymbolNotExists;

    public void tradingExecution(int tradingType, String symbol, int amount, int price, int commandType) throws SymbolNotExists, AmoutIsLowerThenZero, CommandTypeError, TradingTypeError;

    public StockDto showListOfCommands(String symbol) throws SymbolNotExists;
}
