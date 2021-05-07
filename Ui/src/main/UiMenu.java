package main;

import Exceptions.*;
import StocksD.StockDto;
import StocksD.StocksDto;
import command.Enum.TradingTypes;
import menu.MenuEngine;
import menu.MenuEngineImpl;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UiMenu {

    private boolean flag;
    private final MenuEngine menuEngine;
    private final MenuEngineImpl menuEngineImpl;

    public UiMenu() {
        menuEngine = new MenuEngineImpl();
        menuEngineImpl = new MenuEngineImpl();
        flag = false;
    }

    public void getMenu() {
        System.out.println("Hello, please choose one of the following options:\n 1.Extracting information from XML \n" +
                " 2.View all stocks \n 3.Show info about specific stock \n 4.Preform a trading order " +
                "\n 5.View the lists of commands to execute \n 6.Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                LoadFile();
                break;
            case 2:
                getAllStocksInSystem(flag);
                break;
            case 3:
                showSpecificStock(flag);
                break;
            case 4:
                tradingExecution(flag);
                break;
            case 5:
                showListsInSystem(flag);
                break;
            case 6:
                System.out.println("Bye Bye");
                System.exit(0);
                break;
            default:
                System.out.format("you entered invalid integer: %d \n please try again!\n \n", choice);
                getMenu();
        }
        scanner.close();
    }


    public void LoadFile() {
        System.out.println("you chose option number 1 - Extracting information from XML"); //need to add - XML is OK
        Scanner s = new Scanner(System.in);
        System.out.println("please insert full XML path");
        String path = s.nextLine();
        if (path.endsWith("xml")) {
            if (new File(path).isFile()) {
                try {
                    menuEngine.readSystemInfoFile(path); //needUseToString
                    flag = true;
                    System.out.println("the XML file is loaded properly");
                } catch (SymbolIsAlreadyExists symbolIsAlreadyExists) {
                    System.out.println(symbolIsAlreadyExists.getMessage());
                    ;
                } catch (CompanyNameIsAlreadyExists companyNameIsAlreadyExists) {
                    System.out.println(companyNameIsAlreadyExists.getMessage());
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (JAXBException e) {
                    System.out.println(e.getMessage());
                } finally {
                    getMenu();
                }
            } else {
                System.out.println("the given file is not exist - please load different file");
            }
        } else {
            System.out.println("the given path is not an XML file - please load different path");
        }
        getMenu();
    }

    public void getAllStocksInSystem(boolean flag) {
        CheckFlag(flag);
        System.out.println("you chose option number 2 - show all stocks in the system");
        StocksDto allStocks = menuEngine.getAllStocks();
        System.out.println(allStocks.toString());
        getMenu();
    }

    public void showSpecificStock(boolean flag) {
        CheckFlag(flag);
        System.out.println("you chose option number 3 - show specific stock");
        Scanner s = new Scanner(System.in);
        System.out.println("please insert a stock symbol you wish to watch");
        String SymbolStock = s.nextLine();
        try {
            StockDto stockDto = menuEngine.showCurrStock(SymbolStock);
            System.out.println(stockDto.toString());
        } catch (SymbolNotExists e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getMenu();
        }
    }

    /*public void tradingExecution(boolean flag) {
        CheckFlag(flag);
        System.out.println("you chose option number 4  - creating trading execution");
        System.out.println("please choose one of the following trading order: \n 1.buy command \n 2.sell command ");
        Scanner s = new Scanner(System.in);
        int tradingType = s.nextInt();
        System.out.println("please enter the symbol name of stock:");
        Scanner scanner1 = new Scanner(System.in);
        String symbol = scanner1.nextLine();
        System.out.println("please enter the amount of stock you want:");
        Scanner scanner2 = new Scanner(System.in);
        int amount = scanner2.nextInt();
        System.out.println("please enter the number 1 or 2 for the desired command type: \n 1.LMT command \n 2.MKT command :");
        Scanner scanner4 = new Scanner(System.in);
        int commandType = scanner4.nextInt();
        try {
            switch (commandType) {
                case 1:
                    System.out.println("please enter the price you wish to invest:");
                    Scanner scanner3 = new Scanner(System.in);
                    int price = scanner3.nextInt();
                    menuEngine.tradingExecution(tradingType, symbol, amount, price, commandType);
                    System.out.println("the trading order preformed properly ! \n");
                    break;
                case 2:
                    menuEngine.tradingExecution(tradingType, symbol, amount, 1, commandType);
                    System.out.println("the trading order preformed properly ! \n");
                    break;
                default:
                    String message = String.format("the number: %d is unavailable in the current given commands", commandType);
                    throw new CommandTypeError(message);
            }
        } catch (SymbolNotExists e) {
            System.out.println(e.getMessage());
        } catch (AmoutIsLowerThenZero e) {
            System.out.println(e.getMessage());
        } catch (CommandTypeError e) {
            System.out.println(e.getMessage());
        } catch (TradingTypeError e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getMenu();
        }
    }*/

    public void tradingExecution(boolean flag) {
        CheckFlag(flag);
        System.out.println("you chose option number 4  - creating trading execution");
        System.out.println("please choose one of the following trading order: \n 1.buy command \n 2.sell command ");
        Scanner s = new Scanner(System.in);
        int tradingType = s.nextInt();
        try {
            menuEngineImpl.CheckIfNumTradingOk(tradingType);
            System.out.println("please enter the amount of stock you want:");
            Scanner scanner2 = new Scanner(System.in);
            int amount = scanner2.nextInt();
            menuEngineImpl.checkAmount(amount);
            System.out.println("please enter the number 1 or 2 for the desired command type: \n 1.LMT command \n 2.MKT command :");
            Scanner scanner4 = new Scanner(System.in);
            int commandType = scanner4.nextInt();
            switch (commandType) {
                case 1:
                    System.out.println("please enter the price you wish to invest:");
                    Scanner scanner3 = new Scanner(System.in);
                    int price = scanner3.nextInt();
                    menuEngineImpl.checkPrice(price);
                    System.out.println("please enter the symbol name of stock:");
                    Scanner scanner = new Scanner(System.in);
                    String symbolLmt = scanner.nextLine();
                    menuEngine.tradingExecution(tradingType, symbolLmt, amount, price, commandType);
                    System.out.println("the trading order preformed properly ! \n");
                    break;
                case 2:
                    System.out.println("please enter the symbol name of stock:");
                    Scanner scanner1 = new Scanner(System.in);
                    String symbolMkt = scanner1.nextLine();
                    menuEngine.tradingExecution(tradingType, symbolMkt, amount, 1, commandType);
                    System.out.println("the trading order preformed properly ! \n");
                    break;
                default:
                    String message = String.format("the number: %d is unavailable in the current given commands", commandType);
                    throw new CommandTypeError(message);
            }

        } catch (TradingTypeError e) {
            System.out.println(e.getMessage());
            getMenu();
        } catch (AmoutIsLowerThenZero e) {
            System.out.println(e.getMessage());
            getMenu();
        } catch (CommandTypeError e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getMenu();
        }
    }

    public void showListsInSystem(boolean flag) {
        CheckFlag(flag);
        System.out.println("you chose option number 5 - showing lists of : BuyCommand, SellCommand & Transactions");
        System.out.println("please enter the symbol of stock you wish to watch:");
        Scanner scanner1 = new Scanner(System.in);
        String symbol = scanner1.nextLine();
        try {
            StockDto stockDto = menuEngine.showListOfCommands(symbol);
            if(stockDto.getNumberOfTransactions()==0)
            {
                System.out.println("No transactions were made in this current stock ");
            }
            if(stockDto.getCommands().getCommandsSellList().isEmpty())
            {
                System.out.println("There are no commands in buy list");
            }
            if(stockDto.getCommands().getCommandsBuyList().isEmpty())
            {
                System.out.println("There are no commands in sell list");
            }
            System.out.println(stockDto.getTransactions().toString() + "\n" + stockDto.getCommands().getCommandsBuyList().toString() + "\n"
                    + stockDto.getCommands().getCommandsSellList().toString() + "\n" + "The total transaction cycles is: " + stockDto.getTransactions().getSumOfCycleTran() + "\n"
                    + "the total commands buy cycle is:" + stockDto.getCommands().getTotalCycleBuyCommand() + "\n"
                    + "the total commands sell cycle is:" + stockDto.getCommands().getTotalCycleSellCommand() + "\n");
        } catch (SymbolNotExists e) {
            System.out.println(e.getMessage());
        } finally {
            getMenu();
        }
    }

    public void CheckFlag(boolean flag) {
        try {
            if (!flag) {
                throw new NoXmlLoaded("File has not been loaded properly - please try again! \n");
            }

        } catch (NoXmlLoaded e) {
            System.out.println(e.getMessage());
            getMenu();
        }
    }

}