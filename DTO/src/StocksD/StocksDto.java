package StocksD;

import stock.Stock;

import java.util.Map;

public class StocksDto {

    private final Map<String, Stock> allStocks;

    public StocksDto(Map<String, Stock> allStocks) {
        this.allStocks = allStocks;
    }

    public Map<String, Stock> getAllStocks() {
        return allStocks;
    }

    @Override
    public String toString() {
        return "allStocks: \n " + allStocks;
    }


}
